package com.example.translateapp.model

import com.google.gson.annotations.SerializedName

data class TranslationResponse(
    @SerializedName("data") val data: TranslationData
)




