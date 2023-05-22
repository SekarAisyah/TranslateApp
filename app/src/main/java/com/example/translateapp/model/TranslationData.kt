package com.example.translateapp.model

import com.google.gson.annotations.SerializedName

data class TranslationData(
    @SerializedName("translations") val translations: List<Translation>
)