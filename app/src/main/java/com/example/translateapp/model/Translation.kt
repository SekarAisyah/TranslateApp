package com.example.translateapp.model

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("translatedText") val translatedText: String
)