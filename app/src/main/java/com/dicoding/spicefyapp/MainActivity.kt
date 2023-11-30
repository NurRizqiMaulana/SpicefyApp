package com.dicoding.spicefyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.spicefyapp.databinding.ActivityMainBinding
import com.dicoding.spicefyapp.ui.chatbot.ChatbotActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_scan, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Cek apakah halaman saat ini adalah halaman utama
            if (destination.id == R.id.navigation_home) {
                // Tampilkan FAB jika halaman utama
                binding.btnFabChat.show()
            } else {
                // Sembunyikan FAB jika bukan halaman utama
                binding.btnFabChat.hide()
            }
        }

        binding.btnFabChat.setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }
    }
}