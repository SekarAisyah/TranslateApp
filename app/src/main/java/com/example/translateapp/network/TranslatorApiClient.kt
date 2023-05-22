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
                    .header("X-RapidAPI-Key", "45d322ce00mshf7f13126db1e389p1c5bfajsn75ea01c857d5")
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
