package com.dicoding.spicefyapp.ui.dashboard.spicemart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.spicefyapp.databinding.ActivitySpiceMartBinding
import com.dicoding.spicefyapp.ui.dashboard.spicemart.addproduct.AddProductActivity

class SpiceMartActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpiceMartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpiceMartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddProduct.setOnClickListener {
            startActivity(Intent(this,AddProductActivity::class.java))
        }


    }
}