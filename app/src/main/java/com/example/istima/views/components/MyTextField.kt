//package com.example.istima.views.components
//
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import com.example.istima.views.cornerShape
//import com.example.istima.views.elementHeight
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyTextField (theValue: String) {
//    OutlinedTextField(
//        value = theValue,
//        singleLine = true,
//        modifier = Modifier
//            .height(elementHeight)
//            .fillMaxWidth(),
//        onValueChange = { newText: String -> theValue = newText },
//        textStyle = TextStyle(color = Color.DarkGray),
//        placeholder = { Text("Email") },
//        shape = RoundedCornerShape(cornerShape)
//    )
//}