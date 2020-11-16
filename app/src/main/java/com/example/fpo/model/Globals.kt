package com.example.fpo.model

import androidx.lifecycle.MutableLiveData

object Globals {
    private var isUserDetails:Boolean=false

    fun list(data: Boolean) {
        isUserDetails = data
    }

    fun getList(): Boolean {
        return isUserDetails
    }
}