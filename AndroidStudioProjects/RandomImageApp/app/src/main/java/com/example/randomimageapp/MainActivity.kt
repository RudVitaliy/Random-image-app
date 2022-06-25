package com.example.randomimageapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.randomimageapp.databinding.ActivityMainBinding
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var useKeyword: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getRandomImageButton.setOnClickListener {
            onGetRandomImagePressed()
        }

        binding.keywordEditText.setOnEditorActionListener { _, actionId, _  ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH){
            return@setOnEditorActionListener onGetRandomImagePressed()
        }
            return@setOnEditorActionListener false
        }
        binding.useKeywordCheckBox.setOnClickListener{
            this.useKeyword = binding.useKeywordCheckBox.isChecked
            updateUi()
        }
        binding.useKeywordCheckBox.setOnCheckedChangeListener{ _, isChecked, ->
            // todo
        }
        updateUi()
    }

    private fun onGetRandomImagePressed (): Boolean{
        val keyword = binding.keywordEditText.text.toString()
        if (useKeyword && keyword.isBlank()){
            binding.keywordEditText.error = "Keyword is empty"
            return true
        }
        val encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.name())
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600?$encodedKeyword")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.some_image)
            .into(binding.testImageView)
        return false
    }
    private fun updateUi() = with(binding){
        useKeywordCheckBox.isChecked = useKeyword
        if(useKeyword) {
            keywordEditText.visibility = View.VISIBLE
        } else {
            keywordEditText.visibility = View.GONE
        }
    }
}




