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
import com.example.jalan_tuban_mobile.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {

    private lateinit var usernamesignupEditText: EditText
    private lateinit var passwordsignupEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)
        }
        setContentView(R.layout.signup_layout)

        usernamesignupEditText = findViewById(R.id.usernamesignupEditText)
        passwordsignupEditText = findViewById(R.id.passwordsignupEditText)
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        userDao = RoadDatabase.getDatabase(this).userDao()

        val signupButton = findViewById<Button>(R.id.signUpButton)
        signupButton.setOnClickListener {
            val username = usernamesignupEditText.text.toString()
            val password = passwordsignupEditText.text.toString()
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            // Simpan data pengguna ke database
            val user = User(username = username, password = password, name = name, email = email)
            CoroutineScope(Dispatchers.IO).launch {
                userDao.insertUser(user)
            }


            // Pindah ke halaman login setelah pendaftaran berhasil
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Optional: Mengakhiri activity signup setelah pendaftaran berhasil
        }

        val loginTextView = findViewById<TextView>(R.id.loginTextView)
        loginTextView.setOnClickListener {
            // Pindah ke halaman signup
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}


