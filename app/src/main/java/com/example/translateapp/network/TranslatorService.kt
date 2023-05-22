package com.example.translateapp.network

import com.example.translateapp.model.TranslationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TranslatorService {
    @FormUrlEncoded
    @POST("language/translate/v2")
    fun translateText(
        @Field("q") text: String,
        @Field("source") sourceLang: String,
        @Field("target") targetLang: String
    ): Call<TranslationResponse>
}
