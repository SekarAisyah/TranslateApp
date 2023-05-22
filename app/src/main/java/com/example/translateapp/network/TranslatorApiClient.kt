package com.example.translateapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TranslatorApiClient {
    private const val BASE_URL = "https://google-translate1.p.rapidapi.com/"

    private val retrofit: Retrofit by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "e170452ebcmsh5e04bb89adf8217p10b789jsncb5122d12601")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build()
                chain.proceed(request)
            }
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val translatorService: TranslatorService by lazy {
        retrofit.create(TranslatorService::class.java)
    }
}
