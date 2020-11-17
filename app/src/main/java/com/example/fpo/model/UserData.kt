package com.example.fpo.model

data class UserData(
    val name: String,
    val address: String,
    val image:String,
    val gender: String,
    val villagename:String,
    val acreagename: String,
    val cropname:String,
   var expanded:Boolean)