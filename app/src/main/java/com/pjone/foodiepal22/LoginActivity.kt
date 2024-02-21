package com.pjone.foodiepal22

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        sharedPreferences = getSharedPreferences("foodiePalPref", Context.MODE_PRIVATE)
        if (checkLogin()) {
            navigateMain()
        }
        setClickListeners()
    }

    private fun navigateMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setClickListeners() {
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username == "admin" && password == "admin") {
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("username", username)
                editor.apply()

                navigateMain()

            } else {
                Toast.makeText(this, "Invalid credentials, try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkLogin(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}
