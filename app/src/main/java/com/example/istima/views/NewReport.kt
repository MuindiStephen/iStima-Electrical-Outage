package com.example.istima.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.istima.R
import com.example.istima.model.Report
import com.example.istima.ui.theme.KplcDarkGreen
import com.example.istima.ui.theme.KplcLightGreen
import com.example.istima.ui.theme.LightRed
import com.example.istima.utils.Global
import com.example.istima.views.auth.cornerShape
import com.example.istima.views.auth.elementHeight
import com.example.istima.views.auth.pagePadding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@Composable
fun NewReport(navController: NavHostController) {

    val sendIcon: Painter = painterResource(id = R.mipmap.send_icon)

    /**
     * Stephen Muindi Implementation
     * @2023
     */
    val mContext = LocalContext.current

    val useCurrentLocation = remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    val fusedLocationProviderClient: FusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(mContext)
    }

    var latitude by remember {
        mutableStateOf(0.0)
    }
    var longitude by remember {
        mutableStateOf(0.0)
    }

    LaunchedEffect(Unit) {
        fetchLocation(
            coroutineScope,
            fusedLocationProviderClient
        ) { fetchedLatitude, fetchedLongitude ->

            latitude = fetchedLatitude
            longitude = fetchedLongitude
        }
    }

    var description by remember {
        mutableStateOf("")
    }

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("$mDay/${mMonth + 1}/$mYear") }
    val mTime = remember { mutableStateOf("$mHour:$mMinute") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    val sharedPreferences =
        mContext.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)

    // var userId = sharedPreferences.getString(Global.sharedPreferencesUserId, "NULL")
   // val userName = sharedPreferences.getString(Global.sharedPreferencesUserName, "NULL")
//    val latitude = sharedPreferences.getString(Global.sharedPreferencesLatitude, "34.433")
//    val longitude = sharedPreferences.getString(Global.sharedPreferencesLongitude, "0.671")




    /**
     * Stephen Muindi implementation
     * @2023
     */

    val databaseReference = FirebaseDatabase.getInstance().reference

    // Location
    // Location fetching variables
    var locationPermissionGranted by remember { mutableStateOf(false) }




    // Check for location permission
    val requestLocationPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            locationPermissionGranted = isGranted
            if (isGranted) {
                fetchLocation(coroutineScope,fusedLocationProviderClient) { fetchedLatitude, fetchedLongitude ->
                    latitude = fetchedLatitude
                    longitude = fetchedLongitude
                }
            }
        }



    Column(
        modifier = Modifier
            .background(LightRed)
            .padding(pagePadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = elementHeight),
//        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(elementHeight))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                "Post a Report",
                fontWeight = FontWeight.W700,
                fontSize = 20.sp,
            )
        }
        Spacer(modifier = Modifier.height(elementHeight))
        Box {
            Text(
                "Describe the situation (optional)",
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(pagePadding / 4))
        OutlinedTextField(
            value = description,
            singleLine = true,
            modifier = Modifier
                .height(elementHeight)
                .fillMaxWidth(),
            onValueChange = { newText: String ->
                description = newText
            },
            textStyle = TextStyle(color = Color.DarkGray),
            shape = RoundedCornerShape(cornerShape),
            placeholder = { Text(text = "Describe the situation (optional)") }
        )
        Spacer(modifier = Modifier.height(pagePadding))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Column() {
                    Box {
                        Text(
                            "Date",
                            textAlign = TextAlign.Start
                        )
                    }
                    OutlinedTextField(
                        value = mDate.value,
                        singleLine = true,
                        modifier = Modifier
                            .height(elementHeight),
                        onValueChange = { newText: String ->
                            mDate.value = newText
                        },
                        textStyle = TextStyle(color = Color.DarkGray),
                        shape = RoundedCornerShape(cornerShape),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    mDatePickerDialog.show()
                                },
                            ) {
                                Icon(
                                    Icons.Default.DateRange,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(pagePadding / 4))
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Column() {
                    Box {
                        Text(
                            "Time",
                            textAlign = TextAlign.Start
                        )
                    }
                    OutlinedTextField(
                        value = mTime.value,
                        singleLine = true,
                        modifier = Modifier
                            .height(elementHeight)
                            .clickable(onClick = {
                                mTimePickerDialog.show()
                            }),
                        onValueChange = { newText: String ->
                            mTime.value = newText
                        },
                        textStyle = TextStyle(color = Color.DarkGray),
                        shape = RoundedCornerShape(cornerShape),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    mTimePickerDialog.show()
                                },
                            ) {
                                Icon(
                                    Icons.Default.DateRange,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(pagePadding))
        Row(
//            modifier = Modifier
//                .align(HorizontalAlignment.Start)
        ) {
            Checkbox(
                checked = useCurrentLocation.value,
                onCheckedChange = { isChecked ->
                    useCurrentLocation.value = isChecked
                    if (isChecked && !locationPermissionGranted) {
                        requestLocationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                }
            )
            Text(
                "Use current location",
                modifier = Modifier
                    .clickable {
                        useCurrentLocation.value = !useCurrentLocation.value
                        if (useCurrentLocation.value && !locationPermissionGranted) {
                            requestLocationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                        }
                    }
            )
        }
        if (!useCurrentLocation.value) {
            Spacer(modifier = Modifier.height(pagePadding))
            Box {
                Text(
                    "Enter the location",
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(pagePadding / 4))
            OutlinedTextField(
                value = description,
                singleLine = true,
                modifier = Modifier
                    .height(elementHeight)
                    .fillMaxWidth(),
                onValueChange = { },
                textStyle = TextStyle(color = Color.DarkGray),
                shape = RoundedCornerShape(cornerShape),
                placeholder = { Text(text = "City, Street/Village") }
            )
        }
        Spacer(modifier = Modifier.height(elementHeight))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(cornerShape),
                modifier = Modifier
                    .weight(1f)
                    .height(elementHeight),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Red,
                ),
//                border = BorderStroke(1.dp, Color.LightGray),
            ) {
                Text("CANCEL")
            }
            Spacer(modifier = Modifier.width(pagePadding / 4))
            Button(
                onClick = {
                    /**
                     * Stephen Muindi Implementation
                     * @2023
                     */
                    Log.d("$mContext", "Report: Report added.")

                    val latitudeValue = if (useCurrentLocation.value) {
                        latitude
                    } else {
                        34.41
                    }

                    val longitudeValue = if (useCurrentLocation.value) {
                        longitude
                    } else {
                        0.0343
                    }

                    val report = Report(
                        description,
                        mDate.value,
                        mTime.value,
                        latitudeValue,
                        longitudeValue
                    )

                    databaseReference.child("reports").push().setValue(report)

                    Toast.makeText(mContext, "KPLC Reporting successful", Toast.LENGTH_SHORT)
                        .show()

                    navController.navigate("feed")

                },
                shape = RoundedCornerShape(cornerShape),
                modifier = Modifier
                    .weight(2f)
                    .height(elementHeight),
                colors = ButtonDefaults.buttonColors(
                    containerColor = KplcLightGreen,
                    contentColor = Color.White,
                ),
                border = BorderStroke(1.dp, KplcDarkGreen),
            ) {
                Row {
                    Text("SEND", fontWeight = FontWeight.Bold, color = KplcDarkGreen)
                    Spacer(modifier = Modifier.width(pagePadding / 4))
                    Icon(
                        painter = sendIcon,
                        contentDescription = null, // Set a proper content description if necessary
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }

}


/**
 * Stephen Muindi Implementation
 * @2023
 */

fun fetchLocation(
    coroutineScope: CoroutineScope,
    fusedLocationProviderClient: FusedLocationProviderClient,
    onLocationFetched: (Double, Double) -> Unit
) {
    coroutineScope.launch {
        try {
            val locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000) // 5 seconds interval for location updates

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        p0.lastLocation?.let { location ->
                            val latitude = location.latitude
                            val longitude = location.longitude
                            onLocationFetched(latitude, longitude)

                            Log.d("NewReport","Fetched: $latitude $longitude")
                        }
                    }
                },
                null
            )
        } catch (e: SecurityException) {
            Log.d("NewReport","Location: Not available")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview2() {
    val ctx = LocalContext.current
    val navController: NavHostController = NavHostController(ctx)

    NewReport(navController)
}