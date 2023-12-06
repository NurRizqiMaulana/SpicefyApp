package com.dicoding.spicefyapp.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.spicefyapp.MainActivity
import com.dicoding.spicefyapp.databinding.ActivityLoginBinding
import com.dicoding.spicefyapp.ui.auth.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.btnTvSignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.btnSignIn.setOnClickListener {
            var email = binding.edLoginEmail.text.toString()
            var password = binding.edLoginPassword.text.toString()

            if (email.isEmpty()) {
                // Menampilkan pesan kesalahan jika email kosong
                Toast.makeText(this, "Email harus diisi", Toast.LENGTH_SHORT).show()
                binding.edLoginEmail.requestFocus()
            } else if (password.isEmpty()) {
                // Melakukan validasi jika password kosong
                // Menampilkan pesan kesalahan jika password kosong
                Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show()
                binding.edLoginPassword.requestFocus()
            } else {
                // Jika tidak ada input yang kosong, panggil fungsi signIn()
                signIn(email, password)
            }
        }
    }
    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Intent(this@LoginActivity, MainActivity::class.java).also {intent ->
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }

                    Toast.makeText(
                        this,
                        "Berhasil Masuk.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        // [END sign_in_with_email]
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@LoginActivity, MainActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

}