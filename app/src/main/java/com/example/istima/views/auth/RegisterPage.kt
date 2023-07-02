package com.example.istima.views.auth

import android.app.Activity
import com.example.istima.data.FirebaseAuthHelper
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.istima.R
import com.example.istima.services.AuthService
import com.example.istima.services.FirebaseFirestoreService
import com.example.istima.ui.theme.KplcDarkGreen
import com.example.istima.utils.Global
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavHostController) {

    val context: Context = LocalContext.current

    val googleIcon: Painter = painterResource(id = R.mipmap.google_icon)
    val appleIcon: Painter = painterResource(id = R.mipmap.apple_icon)

    val cornerShape = 1.dp
    val pagePadding = 20.dp
    val elementHeight = 60.dp

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var error by remember { mutableStateOf("") }

    val auth = Firebase.auth
    val firebaseAuthHelper = FirebaseAuthHelper(context, navController)
    val authService = AuthService(context)

    val sharedPreferences = context.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    var firebaseFirestoreService = FirebaseFirestoreService(context)

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(pagePadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Join iStima", fontWeight = FontWeight.W400, fontSize = 23.sp)
        Spacer(modifier = Modifier
            .padding(pagePadding)
            .height(pagePadding))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                error,
                color = Color.Red
            )
        }
        Row {
            OutlinedTextField(
                value = firstName,
                singleLine = true,
                modifier = Modifier
                    .height(elementHeight)
                    .fillMaxWidth()
                    .weight(1f),
                onValueChange = { newText: String -> firstName = newText },
                textStyle = TextStyle(color = Color.DarkGray),
                placeholder = { Text("First Name") },
                shape = RoundedCornerShape(cornerShape)
            )
            Spacer(modifier = Modifier.width(pagePadding / 4))
            OutlinedTextField(
                value = lastName,
                singleLine = true,
                modifier = Modifier
                    .height(elementHeight)
                    .fillMaxWidth()
                    .weight(1f),
                onValueChange = { newText: String -> lastName = newText },
                textStyle = TextStyle(color = Color.DarkGray),
                placeholder = { Text("Last Name") },
                shape = RoundedCornerShape(cornerShape)
            )
        }
        Spacer(Modifier.height(pagePadding/2))
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
        Spacer(Modifier.height(pagePadding/2))
        OutlinedTextField(
            value = confirmPassword,
            singleLine = true,
            modifier = Modifier
                .height(elementHeight)
                .fillMaxWidth(),
            onValueChange = { newText: String -> confirmPassword = newText },
            textStyle = TextStyle(color = Color.DarkGray),
            placeholder = { Text("Confirm Password") },
            shape = RoundedCornerShape(cornerShape)
        )
        Spacer(Modifier.height(pagePadding))
        Button(
            onClick = {
                error = ""
                val status = authService.validateCredentials(
                    newUser = true, email = email,
                    password = password,
                    confirmPassword = confirmPassword
                )

                if(status == Global.SuccessStatus) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(context as Activity) {
                        if (it.isSuccessful) {
                            var user = auth.currentUser
                            Toast.makeText(context, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                            editor.putString("userEmail", email)
                            editor.putString("userName", "$firstName $lastName")
                            editor.apply()
                            firebaseFirestoreService.addUser(userName = "$firstName $lastName", email = email, userId = user!!.uid)
                            navController.navigate("main")
                        } else {
                            Toast.makeText(context, "Sing Up Failed!", Toast.LENGTH_SHORT).show()
                            error = "Sing Up Failed!. Please try again"
                        }
                    }
                } else {
                    error = status
                }
            },
            shape = RoundedCornerShape(cornerShape),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = pagePadding / 2)
                .height(elementHeight),
            colors = ButtonDefaults.buttonColors(
                containerColor = KplcDarkGreen,
                contentColor = Color.White
            )
        ) {
            Text("SIGN UP")
        }
        Spacer(Modifier.height(pagePadding/2))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                          firebaseAuthHelper.googleSignIn()
                          },
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
            text = "Log in",
            Modifier
                .clickable {
                    navController.navigate("login")
                },
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun RegisterPreview() {

    var ctx = LocalContext.current
    var navController: NavHostController = NavHostController(ctx)
    RegisterPage(navController)
}