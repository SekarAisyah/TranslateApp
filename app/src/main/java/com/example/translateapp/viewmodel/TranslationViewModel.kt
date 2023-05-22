package com.example.translateapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.translateapp.network.TranslatorApiClient
import com.example.translateapp.model.TranslationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TranslationViewModel : ViewModel() {
    private val _translationResult = MutableLiveData<String>()
    val translationResult: LiveData<String> = _translationResult

    fun translateText(text: String, sourceLang: String, targetLang: String) {
        TranslatorApiClient.translatorService.translateText(text, sourceLang, targetLang)
            .enqueue(object : Callback<TranslationResponse> {
                override fun onResponse(
                    call: Call<TranslationResponse>,
                    response: Response<TranslationResponse>
                ) {
                    if (response.isSuccessful) {
                        val translation = response.body()?.data?.translations?.get(0)
                        val translatedText = translation?.translatedText ?: "Translation Failed"
                        _translationResult.value = translatedText
                    } else {
                        _translationResult.value = "Translation Failed"
                    }
                }

                override fun onFailure(call: Call<TranslationResponse>, t: Throwable) {
                    _translationResult.value = "Translation Failed"
                }
            })
    }
}
