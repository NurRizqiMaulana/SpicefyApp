package com.dicoding.spicefyapp.ui.chatbot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.spicefyapp.databinding.ActivityChatbotBinding

class ChatbotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatbotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


    }
}