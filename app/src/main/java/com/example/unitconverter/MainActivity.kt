package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlinx.coroutines.runInterruptible
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter() {


    var inputValue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    var conversionFactor = remember { mutableStateOf(1.00)}
    var oconversionFactor = remember { mutableStateOf(1.00)}

    fun convertUnites(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0
        outputvalue = result.toString()
    }

    Column(

        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        // here all the ui element will be stacked each other
        Text(text = "UnitConverter" ,
            style = MaterialTheme.typography.headlineLarge
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it
                convertUnites()
            // here goes what should happen  , when the value of our outlionedTexedField changes
                            },
     label = { Text(text = "Enter Values")}
            )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {

                Button(onClick = { iExpanded = true }) {
                    Text(text = "$inputUnit")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = { iExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.value = 0.01
                    convertUnites()})
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = { iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0
                        convertUnites() })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = { iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3848
                        convertUnites() })
                    DropdownMenuItem(text = { Text(text = "Milimeter") }, onClick = {iExpanded = false
                        inputUnit = "Milimeter"
                        conversionFactor.value = 0.001
                        convertUnites() })
                }


            }
            Spacer(modifier = Modifier.width(20.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = { oExpanded = false
                        outputUnit = "Centimeter"
                        oconversionFactor.value = 0.01
                         convertUnites()})
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = { oExpanded = false
                        outputUnit = "Meters"
                        oconversionFactor.value = 1.0
                        convertUnites() })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = { oExpanded = false
                        outputUnit = "Feet"
                        oconversionFactor.value = 0.3048
                        convertUnites() })
                    DropdownMenuItem(text = { Text(text = "Milimeter") }, onClick = {oExpanded = false
                        outputUnit = "Milimeter"
                        oconversionFactor.value = 0.001
                        convertUnites() })
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result : $outputvalue $outputUnit",
            style = MaterialTheme.typography.headlineSmall
            )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){

    UnitConverter()

}
