package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.CurrentWeather
import com.example.weatherapp.ui.screens.DailyForecast
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.accompanist.permissions.ExperimentalPermissionsApi


class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainViewModel = MainViewModel()
        setContent {
            var isDark by remember { mutableStateOf(false) }

            WeatherAppTheme(darkTheme = isDark) {
                GetLocation(mainViewModel)
                DisplayUI(mainViewModel = mainViewModel, isDark = isDark, onToggleTheme = { isDark = !isDark })
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetLocation(mainViewModel: MainViewModel) {
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    if (permissionState.status.isGranted) {
        val currentContext = LocalContext.current
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentContext)

        if (ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        {
            val cancellationTokenSource = CancellationTokenSource()
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val lat = location.latitude.toString()
                        val lng = location.longitude.toString()
                        val coordinates = "$lat,$lng"
                        Log.i("LOCATION", "coords: $coordinates")
                        // pass coords into ViewModel to trigger API call
                        mainViewModel.fetchWeather(coordinates, 3)
                    } else {
                        Log.i("LOCATION", "Location returned null")
                    }
                }
        }
    } else {
        LaunchedEffect(permissionState){
            permissionState.launchPermissionRequest()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(mainViewModel: MainViewModel, isDark: Boolean, onToggleTheme: () -> Unit) {

    val navController = rememberNavController()
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text("Weather or Not")
                }, 
                actions = {
                    IconButton(onClick = onToggleTheme) {

                        val iconRes = if (isDark) R.drawable.outline_brightness_5_24 else R.drawable.outline_brightness_4_24
                        Icon(
                            painter = painterResource(iconRes),
                            contentDescription = "Toggle Theme"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                //Nav items here
                NavigationBarItem(
                    label = {
                        Text("Current Weather")
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_home_24),
                            contentDescription = "Current Weather"
                        )
                    },
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        navController.navigate("current_weather")
                    }
                )

                NavigationBarItem(
                    label = {
                        Text("Daily Forecast")
                    },
                    icon = {
                        Icon(
                                painter = painterResource(R.drawable.outline_air_24),
                                contentDescription = "Daily Forecast"
                                )
                    },
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        navController.navigate("daily_forecast")
                    }
                )
            }
        }
    ) { innerPadding ->
        //Nav host to render screens

        NavHost(
            navController = navController,
            startDestination = "current_weather",
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = "current_weather")
            {
                CurrentWeather(mainViewModel)
            }
            composable(route = "daily_forecast")
            {
                DailyForecast(mainViewModel)
            }

        }
    }



}
