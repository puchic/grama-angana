package com.example.gramaangana.ui.screens
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.foundation.shape.CircleShape
import com.google.firebase.firestore.FirebaseFirestore
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import java.util.Calendar
import com.google.firebase.firestore.Query
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


// ---------------- COMPONENTS ----------------

@Composable
fun GramaCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            content()
        }
    }
}

// ---------------- SPLASH SCREEN ----------------

@Composable
fun SplashScreen(onFinish: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(2000)
        onFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E8B2E)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                shape = RoundedCornerShape(32.dp),
                color = Color.White,
                modifier = Modifier.size(110.dp)
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        "GA",
                        color = Color(0xFF2E8B2E),
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Grama-Angana",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Community Space Manager",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 16.sp
            )
        }
    }
}

// ---------------- LOGIN SCREEN ----------------

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onSignupClick: () -> Unit
) {

    var email by remember { mutableStateOf("") }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),

        verticalArrangement = Arrangement.Center
    ) {

        Surface(
            shape = RoundedCornerShape(24.dp),

            color = Color(0xFFE7F6EA),

            modifier = Modifier.size(72.dp)
        ) {

            Icon(
                Icons.Default.Person,

                contentDescription = null,

                tint = Color(0xFF2E8B2E),

                modifier = Modifier.padding(18.dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Welcome Back",

            fontSize = 34.sp,

            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Login to continue",

            color = Color.Gray,

            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = email,

            onValueChange = { email = it },

            label = { Text("Email") },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(

            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            trailingIcon = {

                IconButton(
                    onClick = {

                        passwordVisible =
                            !passwordVisible
                    }
                ) {

                    Icon(

                        imageVector =
                            if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,

                        contentDescription = null
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {

                isLoading = true

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        isLoading = false

                        if (task.isSuccessful) {

                            Toast.makeText(
                                context,
                                "Login Successful",
                                Toast.LENGTH_SHORT
                            ).show()

                            onLogin()

                        } else {

                            Toast.makeText(
                                context,
                                task.exception?.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(50.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E8B2E)
            )
        ) {

            if (isLoading) {

                CircularProgressIndicator(
                    color = Color.White,

                    modifier = Modifier.size(22.dp),

                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    "Login",

                    fontWeight = FontWeight.Bold,

                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        TextButton(
            onClick = onSignupClick,

            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        ) {

            Text(
                "Don't have an account? Sign Up",

                color = Color(0xFF2E8B2E)
            )
        }
    }
}
// ---------------- HOME DASHBOARD ----------------

@Composable
fun HomeDashboard(navController: NavController) {

    val db = FirebaseFirestore.getInstance()

    var nextBooking by remember {
        mutableStateOf<Map<String, Any>?>(null)
    }

    LaunchedEffect(Unit) {

        db.collection("bookings")
            .whereEqualTo("status", "BOOKED")
            .orderBy("timestamp")
            .limit(1)
            .get()

            .addOnSuccessListener { result ->

                nextBooking =
                    result.documents.firstOrNull()?.data
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Community Hall Management",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Text(
                    text = "Grama Angana",
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    color = Color(0xFF1F1F1F)
                )
            }

            Surface(
                shape = RoundedCornerShape(18.dp),
                color = Color(0xFFE8DDFB),

                modifier = Modifier
                    .size(52.dp)
                    .clickable {

                        FirebaseAuth.getInstance().signOut()

                        navController.navigate("login") {

                            popUpTo("home") {
                                inclusive = true
                            }
                        }
                    }
            ) {

                Icon(
                    Icons.Default.Logout,
                    contentDescription = null,

                    tint = Color(0xFF2E8B2E),

                    modifier = Modifier.padding(14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),

            shape = RoundedCornerShape(30.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2E7D32)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        "Hall Availability",

                        color = Color.White,

                        fontSize = 30.sp,

                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        "Track upcoming bookings and community events in real time.",

                        color = Color.White.copy(alpha = 0.85f),

                        fontSize = 15.sp,

                        lineHeight = 22.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                Color(0xFFB9F6CA),
                                CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(

                        text =
                            if (nextBooking != null)

                                "Upcoming booking: " +
                                        "${nextBooking?.get("date") ?: "--"} • " +
                                        "${nextBooking?.get("time") ?: "--"}"

                            else

                                "Hall currently available",

                        color = Color.White,

                        fontWeight = FontWeight.Bold,

                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),

            modifier = Modifier.height(300.dp),

            horizontalArrangement = Arrangement.spacedBy(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                ModernGridItem(
                    title = "Calendar",
                    icon = Icons.Default.DateRange,
                    bgColor = Color(0xFFE7F6EA)
                ) {
                    navController.navigate("calendar")
                }
            }

            item {

                ModernGridItem(
                    title = "Booking",
                    icon = Icons.Default.Add,
                    bgColor = Color(0xFFE8E8FF)
                ) {
                    navController.navigate("booking/manual")
                }
            }

            item {

                ModernGridItem(
                    title = "Funds",
                    icon = Icons.Default.Build,
                    bgColor = Color(0xFFFFE5FB)
                ) {
                    navController.navigate("maintenance")
                }
            }

            item {

                ModernGridItem(
                    title = "Bookings",
                    icon = Icons.Default.List,
                    bgColor = Color(0xFFF1F1F1)
                ) {
                    navController.navigate("adminBookings")
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                "Upcoming Village Events",

                fontWeight = FontWeight.Bold,

                fontSize = 20.sp
            )

            Text(
                "See All",

                color = Color(0xFF2E8B2E),

                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        repeat(2) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),

                shape = RoundedCornerShape(24.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {

                Row(
                    modifier = Modifier.padding(18.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Surface(
                        shape = RoundedCornerShape(16.dp),

                        color = Color(0xFFE7F6EA),

                        modifier = Modifier.size(58.dp)
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,

                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                "MAY",

                                fontSize = 10.sp,

                                color = Color.Gray
                            )

                            Text(
                                if (it == 0) "15" else "20",

                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            if (it == 0)
                                "Monthly Village Meeting"
                            else
                                "Wedding: Kamal & Sunila",

                            fontWeight = FontWeight.Bold,

                            fontSize = 17.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            if (it == 0)
                                "Community Gathering"
                            else
                                "Private Event",

                            color = Color.Gray,

                            fontSize = 13.sp
                        )
                    }

                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,

                        tint = Color.LightGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}
@Composable
fun SignupScreen(
    onSignupSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {

    var email by remember { mutableStateOf("") }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),

        verticalArrangement = Arrangement.Center
    ) {

        Surface(
            shape = RoundedCornerShape(24.dp),

            color = Color(0xFFE7F6EA),

            modifier = Modifier.size(72.dp)
        ) {

            Icon(
                Icons.Default.PersonAdd,

                contentDescription = null,

                tint = Color(0xFF2E8B2E),

                modifier = Modifier.padding(18.dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Create Account",

            fontSize = 34.sp,

            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sign up to continue",

            color = Color.Gray,

            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = email,

            onValueChange = { email = it },

            label = { Text("Email") },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(

            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            trailingIcon = {

                IconButton(
                    onClick = {

                        passwordVisible =
                            !passwordVisible
                    }
                ) {

                    Icon(

                        imageVector =
                            if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,

                        contentDescription = null
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(

            value = confirmPassword,

            onValueChange = {
                confirmPassword = it
            },

            label = {
                Text("Confirm Password")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            visualTransformation =
                if (confirmPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            trailingIcon = {

                IconButton(
                    onClick = {

                        confirmPasswordVisible =
                            !confirmPasswordVisible
                    }
                ) {

                    Icon(

                        imageVector =
                            if (confirmPasswordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,

                        contentDescription = null
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {

                if (password != confirmPassword) {

                    Toast.makeText(
                        context,
                        "Passwords do not match",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                isLoading = true

                auth.createUserWithEmailAndPassword(
                    email,
                    password
                )
                    .addOnCompleteListener { task ->

                        isLoading = false

                        if (task.isSuccessful) {

                            Toast.makeText(
                                context,
                                "Account Created",
                                Toast.LENGTH_SHORT
                            ).show()

                            onSignupSuccess()

                        } else {

                            Toast.makeText(
                                context,
                                task.exception?.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(50.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E8B2E)
            )
        ) {

            if (isLoading) {

                CircularProgressIndicator(
                    color = Color.White,

                    modifier = Modifier.size(22.dp),

                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    "Sign Up",

                    fontWeight = FontWeight.Bold,

                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        TextButton(
            onClick = onBackToLogin,

            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        ) {

            Text(
                "Already have an account? Login",

                color = Color(0xFF2E8B2E)
            )
        }
    }
}
// ---------------- MODERN GRID ITEM ----------------

@Composable
fun ModernGridItem(
    title: String,
    icon: ImageVector,
    bgColor: Color,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
        ) {

            Surface(
                shape = RoundedCornerShape(14.dp),
                color = bgColor,
                modifier = Modifier.size(46.dp)
            ) {

                Icon(
                    icon,
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

// ---------------- BOTTOM NAV ----------------

@Composable
fun GramaBottomNav(
    navController: NavController,
    currentRoute: String?
) {

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 12.dp
    ) {

        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home")
            },
            icon = {
                Icon(Icons.Default.Home, null)
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "calendar",
            onClick = {
                navController.navigate("calendar")
            },
            icon = {
                Icon(Icons.Default.DateRange, null)
            },
            label = {
                Text("Calendar")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "maintenance",
            onClick = {
                navController.navigate("maintenance")
            },
            icon = {
                Icon(Icons.Default.Build, null)
            },
            label = {
                Text("Funds")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "board",
            onClick = {
                navController.navigate("board")
            },
            icon = {
                Icon(Icons.Default.List, null)
            },
            label = {
                Text("Board")
            }
        )
    }
}

// ---------------- CALENDAR SCREEN ----------------

@Composable
fun EventCalendarScreen(
    navController: NavController
) {

    val db = FirebaseFirestore.getInstance()

    var events by remember {
        mutableStateOf(listOf<Map<String, Any>>())
    }

    var bookedDates by remember {
        mutableStateOf(listOf<Int>())
    }

    LaunchedEffect(Unit) {

        db.collection("calendar")
            .get()
            .addOnSuccessListener { result ->

                events = result.documents.mapNotNull {
                    it.data
                }
            }

        db.collection("calendar")
            .get()
            .addOnSuccessListener { result ->

                bookedDates = result.documents.mapNotNull {

                    val dateString =
                        it.getString("date")

                    dateString
                        ?.split(" ")
                        ?.getOrNull(1)
                        ?.toIntOrNull()
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        Text(
            "Hall Calendar",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Track bookings and availability.",
            color = Color.Gray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        "MAY 2026",
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp
                    )

                    Row {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(
                                        Color(0xFFDDF5DD),
                                        CircleShape
                                    )
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                "Free",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(
                                        Color(0xFFFFE0E0),
                                        CircleShape
                                    )
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                "Booked",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    listOf("S","M","T","W","T","F","S").forEach {

                        Text(
                            it,
                            modifier = Modifier.width(36.dp),
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),

                    modifier = Modifier.height(280.dp),

                    verticalArrangement = Arrangement.spacedBy(10.dp),

                    horizontalArrangement = Arrangement.spacedBy(8.dp),

                    userScrollEnabled = false
                ) {

                    items(31) { index ->

                        val day = index + 1

                        val isBooked =
                            bookedDates.contains(day)

                        Box(
                            modifier = Modifier.clickable {

                                if (!isBooked) {

                                    navController.navigate(
                                        "booking/$day"
                                    )
                                }
                            }
                        ) {

                            Surface(
                                shape = RoundedCornerShape(14.dp),

                                color =
                                    if (isBooked)
                                        Color(0xFFFFEAEA)
                                    else
                                        Color(0xFFDDF5DD),

                                modifier = Modifier.size(42.dp)
                            ) {

                                Box(
                                    contentAlignment = Alignment.Center
                                ) {

                                    Text(
                                        "$day",
                                        fontWeight = FontWeight.Bold,

                                        color =
                                            if (isBooked)
                                                Color(0xFFD32F2F)
                                            else
                                                Color(0xFF2E7D32)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            "Upcoming Status",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        events.forEach { event ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),

                shape = RoundedCornerShape(24.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {

                Row(
                    modifier = Modifier.padding(18.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            event["date"].toString().uppercase(),

                            color = Color.Gray,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            event["title"].toString(),

                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(50.dp),

                        color =
                            if (event["type"] == "OPEN")
                                Color(0xFFDDF5DD)
                            else
                                Color(0xFFFFE0E0)
                    ) {

                        Text(
                            event["type"].toString(),

                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 8.dp
                            ),

                            color =
                                if (event["type"] == "OPEN")
                                    Color(0xFF2E7D32)
                                else
                                    Color(0xFFD32F2F),

                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}
// ---------------- BOOKING SCREEN ----------------

@Composable
fun BookingRequestScreen(
    navController: NavController,
    selectedDate: String = "",
    onSuccess: () -> Unit
) {

    var name by remember { mutableStateOf("") }

    var phone by remember {
        mutableStateOf("")
    }

    var bookingDate by remember {
        mutableStateOf(selectedDate)
    }

    var fromTime by remember {
        mutableStateOf("")
    }

    var toTime by remember {
        mutableStateOf("")
    }

    var purpose by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            "Request Booking",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                    navController.navigate("calendar")
                },

            shape = RoundedCornerShape(18.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE7F6EA)
            )
        ) {

            Text(

                text =
                    if (bookingDate == "manual")
                        "Select a date from Calendar"
                    else
                        "Selected Date: May $bookingDate",

                modifier = Modifier.padding(18.dp),

                color = Color(0xFF2E7D32),

                fontWeight = FontWeight.Bold,

                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box {

            OutlinedTextField(
                value = fromTime,
                onValueChange = {},
                label = { Text("From Time") },

                modifier = Modifier.fillMaxWidth(),

                readOnly = true,

                enabled = false,

                shape = RoundedCornerShape(18.dp)
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {

                        val calendar = Calendar.getInstance()

                        TimePickerDialog(
                            context,

                            { _, hour, minute ->

                                val amPm =
                                    if (hour >= 12) "PM"
                                    else "AM"

                                val formattedHour =
                                    if (hour > 12) hour - 12
                                    else if (hour == 0) 12
                                    else hour

                                fromTime =
                                    "$formattedHour:$minute $amPm"
                            },

                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),

                            false

                        ).show()
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box {

            OutlinedTextField(
                value = toTime,
                onValueChange = {},
                label = { Text("To Time") },

                modifier = Modifier.fillMaxWidth(),

                readOnly = true,

                enabled = false,

                shape = RoundedCornerShape(18.dp)
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {

                        val calendar = Calendar.getInstance()

                        TimePickerDialog(
                            context,

                            { _, hour, minute ->

                                val amPm =
                                    if (hour >= 12) "PM"
                                    else "AM"

                                val formattedHour =
                                    if (hour > 12) hour - 12
                                    else if (hour == 0) 12
                                    else hour

                                toTime =
                                    "$formattedHour:$minute $amPm"
                            },

                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),

                            false

                        ).show()
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = purpose,
            onValueChange = { purpose = it },
            label = { Text("Purpose") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (
                    name.isEmpty() ||
                    phone.isEmpty() ||
                    purpose.isEmpty() ||
                    bookingDate.isEmpty() ||
                    fromTime.isEmpty() ||
                    toTime.isEmpty()
                ) {

                    Toast.makeText(
                        context,
                        "Fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                isLoading = true

                val bookingData = hashMapOf(

                    "name" to name,
                    "phone" to phone,
                    "date" to bookingDate,
                    "fromTime" to fromTime,
                    "toTime" to toTime,
                    "purpose" to purpose,
                    "status" to "BOOKED",
                    "timestamp" to System.currentTimeMillis()
                )

                db.collection("bookings")
                    .add(bookingData)

                    .addOnSuccessListener {

                        isLoading = false

                        Toast.makeText(
                            context,
                            "Booking Confirmed",
                            Toast.LENGTH_SHORT
                        ).show()

                        db.collection("calendar")
                            .add(
                                hashMapOf(
                                    "title" to purpose,
                                    "date" to "May $bookingDate",
                                    "type" to "BOOKED"
                                )
                            )

                        onSuccess()
                    }

                    .addOnFailureListener {

                        isLoading = false

                        Toast.makeText(
                            context,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(50.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E8B2E)
            )
        ) {

            if (isLoading) {

                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(22.dp),
                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    "Confirm Booking",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
// ---------------- MAINTENANCE SCREEN ----------------

@Composable
fun MaintenanceJarScreen() {

    val db = FirebaseFirestore.getInstance()

    var repairs by remember {
        mutableStateOf(
            listOf<Pair<String, Map<String, Any>>>()
        )
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var selectedRepairId by remember {
        mutableStateOf("")
    }

    var pledgeAmount by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        db.collection("maintenance")
            .addSnapshotListener { result, _ ->

                if (result != null) {

                    repairs = result.documents.mapNotNull {

                        val data = it.data

                        if (data != null)
                            Pair(it.id, data)
                        else
                            null
                    }
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Column {

                Text(
                    "Maintenance Jar",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    "Help keep our community hall perfect.",
                    color = Color.Gray,
                    fontSize = 15.sp
                )
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF2E7D32)
            ) {

                Column(
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "TOTAL",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Rs ${
                            repairs.sumOf {
                                (
                                        it.second["collected"]
                                                as Number
                                        ).toInt()
                            }
                        }",

                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        repairs.forEachIndexed { index, repairPair ->

            val repairId = repairPair.first
            val repair = repairPair.second

            val collected =
                (repair["collected"] as Number).toFloat()

            val target =
                (repair["target"] as Number).toFloat()

            val progress =
                collected / target

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),

                shape = RoundedCornerShape(28.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {

                        Column {

                            Text(
                                repair["title"].toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Surface(
                                shape = RoundedCornerShape(50.dp),

                                color =
                                    if (repair["status"] == "URGENT")
                                        Color(0xFFFFE0E0)
                                    else
                                        Color(0xFFFFF1DD)
                            ) {

                                Text(
                                    repair["status"].toString(),

                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 6.dp
                                    ),

                                    color =
                                        if (repair["status"] == "URGENT")
                                            Color(0xFFD32F2F)
                                        else
                                            Color(0xFFFF8F00),

                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Button(
                            onClick = {

                                selectedRepairId = repairId
                                showDialog = true
                            },

                            shape = RoundedCornerShape(50.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE7F6EA),
                                contentColor = Color(0xFF2E7D32)
                            )
                        ) {

                            Text(
                                "Pledge",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            "Progress",
                            color = Color.Gray,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            "${collected.toInt()} / ${target.toInt()} Rs",

                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { progress },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp),

                        strokeCap = StrokeCap.Round,

                        color = Color(0xFF2E7D32),

                        trackColor = Color(0xFFEAEAEA)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }

    if (showDialog) {

        AlertDialog(

            onDismissRequest = {
                showDialog = false
            },

            title = {
                Text("Enter Pledge Amount")
            },

            text = {

                OutlinedTextField(
                    value = pledgeAmount,

                    onValueChange = {
                        pledgeAmount = it
                    },

                    label = {
                        Text("Amount")
                    }
                )
            },

            confirmButton = {

                Button(
                    onClick = {

                        val amount =
                            pledgeAmount.toIntOrNull() ?: 0

                        val currentRepair =
                            repairs.find {
                                it.first == selectedRepairId
                            }

                        if (currentRepair != null) {

                            val currentCollected =
                                (
                                        currentRepair.second["collected"]
                                                as Number
                                        ).toInt()

                            db.collection("maintenance")
                                .document(selectedRepairId)
                                .update(
                                    "collected",
                                    currentCollected + amount
                                )
                        }

                        pledgeAmount = ""
                        showDialog = false
                    }
                ) {

                    Text("Submit")
                }
            }
        )
    }
}
// ---------------- EVENT BOARD ----------------

@Composable
fun EventBoardScreen() {

    val db = FirebaseFirestore.getInstance()

    var events by remember {
        mutableStateOf(listOf<Map<String, Any>>())
    }

    LaunchedEffect(Unit) {

        db.collection("events")
            .get()
            .addOnSuccessListener { result ->

                events = result.documents.mapNotNull {
                    it.data
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        Text(
            "Community Events",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Stay connected with upcoming village activities and announcements.",

            color = Color.Gray,

            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        if (events.isNotEmpty()) {

            val featuredEvent = events.first()

            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(30.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2E7D32)
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        "FEATURED EVENT",

                        color = Color.White.copy(alpha = 0.8f),

                        fontWeight = FontWeight.Bold,

                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        featuredEvent["title"].toString(),

                        color = Color.White,

                        fontWeight = FontWeight.Bold,

                        fontSize = 26.sp,

                        lineHeight = 34.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        featuredEvent["description"].toString(),

                        color = Color.White.copy(alpha = 0.9f),

                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        "${featuredEvent["date"]} • " +
                                "${featuredEvent["organizer"]}",

                        color = Color.White,

                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                "Upcoming Events",

                fontWeight = FontWeight.Bold,

                fontSize = 22.sp
            )

            Text(
                "${events.size} Events",

                color = Color.Gray,

                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        events.forEach { event ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),

                shape = RoundedCornerShape(28.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Surface(
                        shape = RoundedCornerShape(50.dp),

                        color = Color(0xFFE7F6EA)
                    ) {

                        Text(
                            event["category"].toString(),

                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 8.dp
                            ),

                            color = Color(0xFF2E7D32),

                            fontWeight = FontWeight.Bold,

                            fontSize = 11.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        event["title"].toString(),

                        fontWeight = FontWeight.Bold,

                        fontSize = 22.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        event["description"]?.toString()
                            ?: "Community gathering event",

                        color = Color.Gray,

                        fontSize = 14.sp,

                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = null,

                            tint = Color.Gray,

                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            event["date"].toString(),

                            color = Color.Gray,

                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.width(18.dp))

                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,

                            tint = Color.Gray,

                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            event["organizer"]?.toString()
                                ?: "Village Committee",

                            color = Color.Gray,

                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}
@Composable
fun AdminBookingsScreen() {

    val db = FirebaseFirestore.getInstance()

    var bookings by remember {
        mutableStateOf(
            listOf<Pair<String, Map<String, Any>>>()
        )
    }

    LaunchedEffect(Unit) {

        db.collection("bookings")

            .orderBy(
                "timestamp",
                Query.Direction.DESCENDING
            )

            .addSnapshotListener { result, _ ->

                if (result != null) {

                    bookings = result.documents.mapNotNull {

                        val data = it.data

                        if (data != null)
                            Pair(it.id, data)
                        else
                            null
                    }
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            "Hall Bookings",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        bookings.forEach { bookingPair ->

            val bookingId = bookingPair.first

            val booking = bookingPair.second

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),

                shape = RoundedCornerShape(24.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        booking["name"].toString(),

                        fontWeight = FontWeight.Bold,

                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        "Phone: ${booking["phone"]}",
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        "Purpose: ${booking["purpose"]}",
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        "Date: ${booking["date"] ?: "--"}",
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        "Time: ${booking["time"] ?: "--"}",
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Surface(
                        shape = RoundedCornerShape(50.dp),

                        color = Color(0xFFDDF5DD)
                    ) {

                        Text(
                            "Confirmed Booking",

                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 8.dp
                            ),

                            color = Color(0xFF2E7D32),

                            fontWeight = FontWeight.Bold,

                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(

                        onClick = {

                            db.collection("bookings")
                                .document(bookingId)
                                .delete()

                            db.collection("calendar")
                                .whereEqualTo(
                                    "title",
                                    booking["purpose"]
                                )
                                .whereEqualTo(
                                    "date",
                                    "May ${booking["date"]}"
                                )
                                .get()

                                .addOnSuccessListener { result ->

                                    result.documents.forEach {

                                        it.reference.delete()
                                    }
                                }
                        },

                        border = BorderStroke(
                            1.dp,
                            Color.Red
                        ),

                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Red
                        ),

                        shape = RoundedCornerShape(50.dp)
                    ) {

                        Icon(
                            Icons.Default.Delete,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            "Cancel Booking",

                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}