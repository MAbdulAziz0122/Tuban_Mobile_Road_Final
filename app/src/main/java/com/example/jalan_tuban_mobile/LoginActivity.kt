package com.example.jalan_tuban_mobile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jalan_tuban_mobile.Application.RoadDatabase
import com.example.jalan_tuban_mobile.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)
        }
        setContentView(R.layout.login_layout)

        usernameEditText = findViewById(R.id.usernameloginEditText)
        passwordEditText = findViewById(R.id.passwordloginEditText)
        userDao = RoadDatabase.getDatabase(this).userDao()

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Periksa autentikasi login dengan menggunakan UserDao
            CoroutineScope(Dispatchers.IO).launch {
                val user = userDao.getUserByUsername(username)
                if (user != null && user.password == password) {
                    // Jika autentikasi berhasil, pindah ke halaman utama
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Optional: Mengakhiri activity login setelah berhasil login
                } else {
                    // Jika autentikasi gagal, tampilkan pesan kesalahan
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Login gagal. Periksa username dan password.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        val signUpTextView = findViewById<TextView>(R.id.signUpTextView)
        signUpTextView.setOnClickListener {
            // Pindah ke halaman signup
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}


