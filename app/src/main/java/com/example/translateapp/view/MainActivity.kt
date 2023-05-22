package com.example.translateapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.translateapp.viewmodel.TranslationViewModel
import com.example.translateapp.databinding.ActivityMainBinding

@Suppress("ReplaceGetOrSet")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TranslationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TranslationViewModel::class.java)

        val languages = getLanguageList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sourceLanguageSpinner.adapter = adapter
        binding.targetLanguageSpinner.adapter = adapter

        binding.translateButton.setOnClickListener {
            val textToTranslate = binding.myEditText.text.toString()
            val selectedSourceLanguage = getLanguageCode(binding.sourceLanguageSpinner.selectedItem.toString())
            val selectedTargetLanguage = getLanguageCode(binding.targetLanguageSpinner.selectedItem.toString())
            viewModel.translateText(textToTranslate, selectedSourceLanguage, selectedTargetLanguage)
        }

        binding.switchButton.setOnClickListener {
            val sourcePosition = binding.sourceLanguageSpinner.selectedItemPosition
            val targetPosition = binding.targetLanguageSpinner.selectedItemPosition

            binding.sourceLanguageSpinner.setSelection(targetPosition)
            binding.targetLanguageSpinner.setSelection(sourcePosition)
        }

        viewModel.translationResult.observe(this) { translationResult ->
            binding.myTextView.text = translationResult
        }
    }

    private fun getLanguageList(): List<String> {
        return listOf("English","Indonesia","Spanish", "French", "German")
    }

    private fun getLanguageCode(language: String): String {
        return when (language) {
            "English" -> "en"
            "Indonesia" -> "id"
            "Spanish" -> "es"
            "French" -> "fr"
            "German" -> "de"
            else -> ""
        }
    }
}

