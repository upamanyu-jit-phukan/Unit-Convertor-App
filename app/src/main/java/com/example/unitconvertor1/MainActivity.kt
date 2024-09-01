package com.example.unitconvertor1

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconvertor1.ui.theme.UnitConvertor1Theme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle
import kotlin.io.encoding.Base64
import kotlin.math.roundToInt

//import androidx.compose.material.OutlinedTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertor1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    unitConvertor(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun unitConvertor(modifier: Modifier = Modifier) {

    var inputValue = remember { mutableStateOf("") }
    var outputValue = remember { mutableStateOf("")}
    var inputUnit = remember { mutableStateOf("Meters") }
    var outputUnit = remember { mutableStateOf("Meters")}
    var iExpanded = remember { mutableStateOf(false) }
    var oExpanded = remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00)}

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        color = Color.Red

    )

    fun convertUnits() {
        val inputValueDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        // ?: - Elvis operator (if NULL, then 0.0)
        val result = (inputValueDouble * conversionFactor.value * 100 / oConversionFactor.value).roundToInt()
        outputValue.value = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Convertor"/*, modifier = Modifier.padding(100.dp)*/,
            style = /*customTextStyle */ MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue.value, onValueChange = { it->
            inputValue.value = it
            convertUnits()
        },
            label = {Text("Enter value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
//           val context = LocalContext.current
//           Button(onClick = { Toast.makeText(context,
            //           "You have clicked the button",
            //           Toast.LENGTH_LONG).show() }) {
//               Text("My button")
//           }
            Box {
                Button(onClick = { iExpanded.value = true }) {
                    Text("inputUnit")
                    androidx.compose.material3.Icon( imageVector = Icons.Default.ArrowDropDown,
                        "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded.value, onDismissRequest = { iExpanded.value = false }) {
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {
                            iExpanded.value = false
                            inputUnit.value = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Meters")},
                        onClick = {
                            iExpanded.value = false
                            inputUnit.value = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {
                            iExpanded.value = false
                            inputUnit.value = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {
                            iExpanded.value = false
                            inputUnit.value = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded.value = true }) {
                    Text("outputUnit")
                    androidx.compose.material3.Icon( imageVector = Icons.Default.ArrowDropDown,
                        "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded.value, onDismissRequest = { oExpanded.value = false }) {
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {  oExpanded.value = false
                            outputUnit.value = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Meters")},
                        onClick = {  oExpanded.value = false
                            outputUnit.value = "Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {  oExpanded.value = false
                            outputUnit.value = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {  oExpanded.value = false
                            outputUnit.value = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        })
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${outputValue.value} ${outputUnit.value}",
            style = MaterialTheme.typography.headlineMedium
        )
    }


}

@Preview(showBackground = true)
@Composable
fun unitConvertorPreview() {
    UnitConvertor1Theme {
        unitConvertor()
    }
}
