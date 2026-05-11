package com.example.gramaangana.ui.screens

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
fun LoginScreen(onLogin: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            text = "Manage your community space easily.",
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
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E8B2E)
            )
        ) {

            Text(
                "Sign In",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

// ---------------- HOME DASHBOARD ----------------

@Composable
fun HomeDashboard(navController: NavController) {

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
                modifier = Modifier.size(52.dp)
            ) {

                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = Color(0xFF2E8B2E),
                    modifier = Modifier.padding(14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2E8B2E)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    "Hall Availability",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "The main hall is currently free for the next 4 hours.",
                    color = Color.White.copy(alpha = 0.9f),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {
                        navController.navigate("booking")
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF2E8B2E)
                    )
                ) {

                    Text(
                        "Book Now",
                        fontWeight = FontWeight.Bold
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
                    navController.navigate("booking")
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
                    title = "Board",
                    icon = Icons.Default.List,
                    bgColor = Color(0xFFF1F1F1)
                ) {
                    navController.navigate("board")
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
fun EventCalendarScreen() {

    val bookedDates = listOf(1, 6, 11, 16, 21, 26, 31)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        // HEADER

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

        // CALENDAR CARD

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

                // DAYS HEADER

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

                // CALENDAR GRID

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier.height(280.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = false
                ) {

                    items(31) { index ->

                        val day = index + 1
                        val isBooked = bookedDates.contains(day)

                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color =
                                if (isBooked)
                                    Color(0xFFFFEAEA)
                                else
                                    Color(0xFFDDF5DD),

                            border =
                                if (day == 10)
                                    BorderStroke(
                                        2.dp,
                                        Color.Red
                                    )
                                else
                                    null,

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

        Spacer(modifier = Modifier.height(28.dp))

        // UPCOMING STATUS

        Text(
            "Upcoming Status",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        val events = listOf(
            Triple("May 15, 2026", "Monthly Village Meeting", "OPEN"),
            Triple("May 20, 2026", "Wedding: Kamal & Sunila", "RESERVED"),
            Triple("May 22, 2026", "Yoga Workshop", "OPEN"),
            Triple("May 28, 2026", "Agricultural Seminar", "OPEN")
        )

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
                            event.first.uppercase(),
                            color = Color.Gray,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            event.second,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(50.dp),
                        color =
                            if (event.third == "OPEN")
                                Color(0xFFDDF5DD)
                            else
                                Color(0xFFFFE0E0)
                    ) {

                        Text(
                            event.third,
                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 8.dp
                            ),
                            color =
                                if (event.third == "OPEN")
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
fun BookingRequestScreen(onSuccess: () -> Unit) {

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

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Purpose") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E8B2E)
            )
        ) {

            Text(
                "Submit Request",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ---------------- MAINTENANCE SCREEN ----------------

@Composable
fun MaintenanceJarScreen() {

    val repairs = listOf(
        Triple("Bulb Replacement", 0.75f, "URGENT"),
        Triple("Chair Repairs", 0.20f, "PENDING"),
        Triple("Ceiling Fan Service", 0.80f, "PENDING"),
        Triple("Roof Tiling", 0.50f, "PENDING")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        // HEADER

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
                        "Rs 4.5k",
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        repairs.forEachIndexed { index, repair ->

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
                                repair.first,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Surface(
                                shape = RoundedCornerShape(50.dp),
                                color =
                                    if (repair.third == "URGENT")
                                        Color(0xFFFFE0E0)
                                    else
                                        Color(0xFFFFF1DD)
                            ) {

                                Text(
                                    repair.third,
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 6.dp
                                    ),
                                    color =
                                        if (repair.third == "URGENT")
                                            Color(0xFFD32F2F)
                                        else
                                            Color(0xFFFF8F00),

                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Button(
                            onClick = {},
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
                            when(index) {
                                0 -> "75 / 100 Rs"
                                1 -> "40 / 200 Rs"
                                2 -> "120 / 150 Rs"
                                else -> "500 / 1000 Rs"
                            },
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { repair.second },
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
}
// ---------------- EVENT BOARD ----------------

@Composable
fun EventBoardScreen() {

    val events = listOf(
        Triple(
            "Annual Sports Meet",
            "Community Event",
            "May 18 • Main Ground"
        ),

        Triple(
            "Village Music Night",
            "Cultural Event",
            "May 21 • Community Hall"
        ),

        Triple(
            "Agriculture Workshop",
            "Educational",
            "May 24 • Hall Room B"
        ),

        Triple(
            "Wedding Ceremony",
            "Private Event",
            "May 27 • Main Hall"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        // HEADER

        Text(
            "Event Board",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Discover upcoming village activities.",
            color = Color.Gray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        // FEATURED EVENT

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
                    "Village Cultural Festival",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    lineHeight = 34.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "Join the entire community for music, food, games and cultural performances.",
                    color = Color.White.copy(alpha = 0.9f),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF2E7D32)
                    )
                ) {

                    Text(
                        "View Details",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // SECTION TITLE

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
                "4 Events",
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // EVENT LIST

        events.forEachIndexed { index, event ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // DATE BOX

                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color =
                            when(index) {
                                0 -> Color(0xFFE7F6EA)
                                1 -> Color(0xFFE8E8FF)
                                2 -> Color(0xFFFFF1DD)
                                else -> Color(0xFFFFE5FB)
                            },

                        modifier = Modifier.size(64.dp)
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                "MAY",
                                fontSize = 10.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                "${18 + (index * 3)}",
                                fontWeight = FontWeight.Black,
                                fontSize = 20.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // EVENT INFO

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            event.second.uppercase(),
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            event.first,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                event.third,
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                        }
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