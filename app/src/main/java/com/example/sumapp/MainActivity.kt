package com.example.sumapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun Fooditems(modifier: Modifier = Modifier, sumViewModel: SumViewModel = viewModel()) {
    if (LocalConfiguration.current.orientation== Configuration.ORIENTATION_PORTRAIT){
        SumAppProtrait(modifier, sumViewModel)
    }else{
        SumApplandscape(modifier,sumViewModel)
    }
}


@Composable
fun SumAppProtrait(modifier: Modifier = Modifier,sumViewModel: SumViewModel){

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Item #1:")
        TextField(
            value = sumViewModel.item1,
            onValueChange = { sumViewModel.item1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text("Item #2:")
        TextField(
            value = sumViewModel.item2,
            onValueChange = { sumViewModel.item2 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text("Item #3:")
        TextField(
            value = sumViewModel.item3,
            onValueChange = { sumViewModel.item3 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val p1 = sumViewModel.item1.toDoubleOrNull() ?: 0.0
                val p2 = sumViewModel.item2.toDoubleOrNull() ?: 0.0
                val p3 = sumViewModel.item3.toDoubleOrNull() ?: 0.0

                sumViewModel.subtotal = p1 + p2 + p3
                sumViewModel.tax = sumViewModel.subtotal * 0.06
                sumViewModel.total = sumViewModel.subtotal + sumViewModel.tax
            },
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text("Compute")
        }

        Column(horizontalAlignment = Alignment.Start) {
            Row {
                Text("Subtotal: ")
                Text(String.format("$%.2f", sumViewModel.subtotal))
            }
            Row {
                Text("Tax: ")
                Text(String.format("$%.2f", sumViewModel.tax))
            }
            Row {
                Text("Total: ")
                Text(String.format("$%.2f", sumViewModel.total))
            }
        }
    }
}


@Composable
fun SumAppLandscape(modifier: Modifier = Modifier, sumViewModel: SumViewModel) {

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

     
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {

            Text("Item #1:")
            TextField(
                value = sumViewModel.item1,
                onValueChange = { sumViewModel.item1 = it }
            )

            Text("Item #2:")
            TextField(
                value = sumViewModel.item2,
                onValueChange = { sumViewModel.item2 = it }
            )

            Text("Item #3:")
            TextField(
                value = sumViewModel.item3,
                onValueChange = { sumViewModel.item3 = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                val p1 = sumViewModel.item1.toDoubleOrNull() ?: 0.0
                val p2 = sumViewModel.item2.toDoubleOrNull() ?: 0.0
                val p3 = sumViewModel.item3.toDoubleOrNull() ?: 0.0

                sumViewModel.subtotal = p1 + p2 + p3
                sumViewModel.tax = sumViewModel.subtotal * 0.06
                sumViewModel.total = sumViewModel.subtotal + sumViewModel.tax
            }) {
                Text("Compute")
            }
        }

      
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text("Subtotal: ${String.format("$%.2f", sumViewModel.subtotal)}")
            Text("Tax: ${String.format("$%.2f", sumViewModel.tax)}")
            Text("Total: ${String.format("$%.2f", sumViewModel.total)}")
        }
    }
}
