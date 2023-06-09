package com.example.istima.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
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
import com.example.istima.ui.theme.KplcDarkGreen
import com.example.istima.ui.theme.KplcLightGreen
import com.example.istima.ui.theme.LightRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReport(navController: NavHostController) {
    val cornerShape = 1.dp
    val pagePadding = 20.dp
    val elementHeight = 60.dp

    val sendIcon: Painter = painterResource(id = R.mipmap.send_icon)

    var description by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .background(LightRed)
            .padding(pagePadding)
            .fillMaxWidth(),
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
            onValueChange = {  },
            textStyle = TextStyle(color = Color.DarkGray),
            shape = RoundedCornerShape(cornerShape),
            placeholder = { Text(text = "Describe the situation (optional)") }
        )
        Spacer(modifier = Modifier.height(pagePadding),)
        Box {
            Text(
                "Describe the situation",
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
            shape = RoundedCornerShape(cornerShape)
        )
        Spacer(modifier = Modifier.height(elementHeight))

        Spacer(modifier = Modifier.height(pagePadding / 4),)
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
                onClick = { /*TODO*/ },
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
