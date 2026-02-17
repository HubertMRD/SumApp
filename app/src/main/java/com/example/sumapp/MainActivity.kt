package com.example.sumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sumapp.ui.theme.SumAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SumAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Fooditems(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Fooditems(modifier: Modifier = Modifier){
    var item1 by remember{ mutableStateOf("") }
    var item2 by remember{ mutableStateOf("") }
    var item3 by remember { mutableStateOf("") }

    var subtotal by remember { mutableStateOf(0.0) }
    var tax by remember { mutableStateOf(0.0) }
    var total by remember { mutableStateOf(0.0) }

    Column(
        modifier= modifier
            .fillMaxSize()
            .padding(24.dp)


    ) {
        Text( " Item #1: ")
        TextField(
            value = item1,
            onValueChange = {item1 = it},
            modifier = modifier
                .fillMaxWidth()
                .padding( bottom = 12.dp)


        )
        Text( " Item #2: ")
        TextField(
            value = item2,
            onValueChange = {item2 = it},
            modifier = modifier
                .fillMaxWidth()
                .padding( bottom = 12.dp)

        )
        Text( " Item #3: ")
        TextField(
            value = item3,
            onValueChange = {item3 = it},
            modifier = modifier
                .fillMaxWidth()
                .padding( bottom = 12.dp)
        )
    }

        Button(
            onClick ={
                val p1 = item1.toDoubleOrNull() ?: 0.0
                val p2 = item2.toDoubleOrNull() ?: 0.0
                val p3 = item3.toDoubleOrNull() ?: 0.0

                subtotal= p1+p2+p3
                tax = subtotal* 0.06
                total = subtotal+ tax
            }
        ) {

            Text("Compute")
        }
        Column(horizontalAlignment = Alignment.Start) {
            Row(){
                Text("Subtotal: ")
                Text("$${"%2f.format(subtotal)"}")
            }
            Row(){
                Text("Tax: ")
                Text("$${"%2f.format(tax)"}")
            }
            Row(){
                Text("Total: ")
                Text("$${"%2f.format(total)"}")
            }

        }
}