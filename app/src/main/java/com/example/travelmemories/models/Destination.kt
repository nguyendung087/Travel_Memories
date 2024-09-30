package com.example.travelmemories.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Destination(
    val day : Int,
    @StringRes val name : Int,
    @DrawableRes val image : Int
)
