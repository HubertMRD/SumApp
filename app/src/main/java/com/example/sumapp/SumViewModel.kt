package com.example.sumapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SumViewModel: ViewModel() {

    var item1 by  mutableStateOf("")
    var item2 by  mutableStateOf("")
    var item3 by mutableStateOf("")

    var subtotal by  mutableStateOf(0.0)
    var tax by  mutableStateOf(0.0)
    var total by  mutableStateOf(0.0)

}