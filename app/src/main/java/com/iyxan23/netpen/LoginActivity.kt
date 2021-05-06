package com.iyxan23.netpen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    fun login(view: View) {
        val email: String = findViewById<TextInputEditText>(R.id.email_login).text.toString()
        val password: String = findViewById<TextInputEditText>(R.id.password_login).text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
            }
    }

    fun register(view: View) {
        val email: String = findViewById<TextInputEditText>(R.id.email_login).text.toString()
        val password: String = findViewById<TextInputEditText>(R.id.password_login).text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
            }
    }
}