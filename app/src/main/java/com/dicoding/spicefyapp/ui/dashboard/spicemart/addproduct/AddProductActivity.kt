package com.dicoding.spicefyapp.ui.dashboard.spicemart.addproduct

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.spicefyapp.databinding.ActivityAddProductBinding

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}