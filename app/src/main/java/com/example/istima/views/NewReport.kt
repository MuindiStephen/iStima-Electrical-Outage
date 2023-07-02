package com.example.istima.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.istima.services.FirebaseFirestoreService
import com.example.istima.ui.theme.KplcDarkGreen
import com.example.istima.ui.theme.KplcLightGreen
import com.example.istima.ui.theme.LightRed
import com.example.istima.utils.Global
import com.example.istima.views.auth.cornerShape
import com.example.istima.views.auth.elementHeight
import com.example.istima.views.auth.pagePadding
import com.google.android.gms.location.LocationServices
import java.util.Calendar
import java.util.Date
import java.util.logging.Handler

@Composable
fun NewReport(navController: NavHostController) {

    val sendIcon: Painter = painterResource(id = R.mipmap.send_icon)
    val mContext = LocalContext.current

    val useCurrentLocation = remember { mutableStateOf(true) }

    val fusedLocationProviderClient =
        remember { LocationServices.getFusedLocationProviderClient(mContext) }

    var firebaseFirestoreService = FirebaseFirestoreService(mContext)

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

    val mDate = remember { mutableStateOf("$mDay/${mMonth+1}/$mYear") }
    val mTime = remember { mutableStateOf("$mHour:$mMinute") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    val sharedPreferences = mContext.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)

    val userId = sharedPreferences.getString(Global.sharedPreferencesUserId, "NULL")
    val userName = sharedPreferences.getString(Global.sharedPreferencesUserName, "NULL")
    val latitude = sharedPreferences.getString(Global.sharedPreferencesLatitude, "0.0")
    val longitude = sharedPreferences.getString(Global.sharedPreferencesLongitude, "0.0")

    Column(
        modifier = Modifier
            .background(LightRed)
            .padding(pagePadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = elementHeight),
//        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(elementHeight),)
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
        Spacer(modifier = Modifier.height(elementHeight),)
        Box {
            Text(
                "Describe the situation (optional)",
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(pagePadding / 4),)
        OutlinedTextField(
            value = description,
            singleLine = true,
            modifier = Modifier
                .height(elementHeight)
                .fillMaxWidth(),
            onValueChange = {newText: String ->
                description = newText
            },
            textStyle = TextStyle(color = Color.DarkGray),
            shape = RoundedCornerShape(cornerShape),
            placeholder = { Text(text = "Describe the situation (optional)") }
        )
        Spacer(modifier = Modifier.height(pagePadding),)
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
        Spacer(modifier = Modifier.height(pagePadding),)
        Row(
//            modifier = Modifier
//                .align(HorizontalAlignment.Start)
        ) {
            Checkbox(
                checked = useCurrentLocation.value,
                onCheckedChange = { useCurrentLocation.value = it }
            )
            Text (
                "Use current location",
                modifier = Modifier
                    .clickable {
                        useCurrentLocation.value = !useCurrentLocation.value
                    }
            )
        }
        if (!useCurrentLocation.value) {
            Spacer(modifier = Modifier.height(pagePadding),)
            Box {
                Text(
                    "Enter the location",
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(pagePadding / 4),)
            OutlinedTextField(
                value = description,
                singleLine = true,
                modifier = Modifier
                    .height(elementHeight)
                    .fillMaxWidth(),
                onValueChange = {  },
                textStyle = TextStyle(color = Color.DarkGray),
                shape = RoundedCornerShape(cornerShape),
                placeholder = { Text(text = "City, Street/Village") }
            )
        }
        Spacer(modifier = Modifier.height(elementHeight),)
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
                    Log.d("ABC", "user1: $longitude")
                    if (userId != null) {
                        if (userName != null) {
                            firebaseFirestoreService.postReport(
                                time = mTime.value, date = mDate.value,
                                latitude = latitude!!.toDouble(),
                                longitude = longitude!!.toDouble(),
                                userId = userId, userName = userName,
                                description = description
                            )

                            android.os.Handler().postDelayed({
                                firebaseFirestoreService.getAllReports()
                            }, 4000)
                            navController.navigate("feed")
                        }
                    }
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

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview2() {
    var ctx = LocalContext.current
    var navController: NavHostController = NavHostController(ctx)

    NewReport(navController)
}
