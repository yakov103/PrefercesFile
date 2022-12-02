package com.example.prefercesfile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import java.util.prefs.Preferences


@Composable
fun PreferenceScreen() {
    var gender = listOf<String>("Male" , "Female" , "Prefers not to say", "Other")
    var selectedGender by remember { mutableStateOf("") }
    var age = listOf<String>("18-25", "26-32", "33-40", "41-50", "51-60", "61-70", "71-80", "More then 81")
    var selectedAge by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        dropDownMenu(list = gender, labeli = "Select gender")
        dropDownMenu(list = age, labeli = "Select age")
    }

    Text(text = selectedGender)
    Text(text = selectedAge)


}

@Composable
fun dropDownMenu(list : List<String>  , labeli : String) {

    var expended by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("")
    }
    var textFiledSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expended) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selected,
            onValueChange = {
                selected = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFiledSize = coordinates.size.toSize()
                },
            label = { Text(text = labeli) },
            trailingIcon = {
                Icon(icon, "", Modifier.clickable { expended = !expended })
            }
        )

        DropdownMenu(
            expanded = expended, onDismissRequest = { expended = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFiledSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(onClick = {
                    selected = label
                    expended = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}