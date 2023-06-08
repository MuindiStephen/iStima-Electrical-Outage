package com.example.istima.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.istima.R
import com.example.istima.ui.theme.IStimaTheme

val cornerShape = 1.dp
val pagePadding = 20.dp
val elementHeight = 60.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val googleIcon: Painter = painterResource(id = R.mipmap.google_icon)
    val appleIcon: Painter = painterResource(id = R.mipmap.apple_icon)

    Column(
        modifier = Modifier
            .padding(pagePadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login to iStima", fontWeight = FontWeight.W400, fontSize = 23.sp)
        Spacer(modifier = Modifier
            .padding(pagePadding)
            .height(pagePadding))
        OutlinedTextField(
            value = email,
            singleLine = true,
            modifier = Modifier
                .height(elementHeight)
                .fillMaxWidth(),
            onValueChange = { newText: String -> email = newText },
            textStyle = TextStyle(color = Color.DarkGray),
            placeholder = { Text("Email") },
            shape = RoundedCornerShape(cornerShape)
        )
        Spacer(Modifier.height(pagePadding/2))
        OutlinedTextField(
            value = password,
            singleLine = true,
            modifier = Modifier
                .height(elementHeight)
                .fillMaxWidth(),
            onValueChange = { newText: String -> password = newText },
            textStyle = TextStyle(color = Color.DarkGray),
            placeholder = { Text("Password") },
            shape = RoundedCornerShape(cornerShape)
        )
        Spacer(modifier = Modifier.height(pagePadding))
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(cornerShape),
            modifier = Modifier
                .fillMaxWidth()
                .height(elementHeight),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = KplcGreen,
//                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
//            )
        ) {
            Text("LOGIN")
        }
        Spacer(Modifier.height(pagePadding/2))
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
                    containerColor = Color.White,
                    contentColor = Color.DarkGray,
                ),
                border= BorderStroke(1.dp, Color.LightGray),
            ) {
                Row {
                    Icon(
                        painter = googleIcon,
                        contentDescription = null, // Set a proper content description if necessary
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(pagePadding / 4))
                    Text("GOOGLE")
                }
            }
            Spacer(modifier = Modifier.width(pagePadding / 4))
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(cornerShape),
                modifier = Modifier
                    .weight(1f)
                    .height(elementHeight),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray,
                ),
                border= BorderStroke(1.dp, Color.LightGray),
            ) {
                Row {
                    Icon(
                        painter = appleIcon,
                        contentDescription = null, // Set a proper content description if necessary
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(pagePadding / 4))
                    Text("APPLE")
                }
            }
        }
        Spacer(modifier = Modifier.height(elementHeight))
        Text(
            text = "Create Account",
            Modifier
                .clickable {
                },
            color = Color.Blue
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    IStimaTheme {
        LoginPage()
    }
}
