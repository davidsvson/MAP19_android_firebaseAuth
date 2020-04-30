package com.example.firestoreauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    lateinit var textEmail : EditText
    lateinit var textPassword : EditText
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textEmail = findViewById(R.id.editTextEmail)
        textPassword = findViewById(R.id.editTextPassword)
        auth = FirebaseAuth.getInstance()

        val createButton = findViewById<Button>(R.id.buttonCreate)
        createButton.setOnClickListener {
           createUser()
        }

        val loginButton = findViewById<Button>(R.id.buttonLogin)
        loginButton.setOnClickListener {
            loginUser()
        }


    }

    fun goToAddActivity() {
        val intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }

    fun loginUser() {
        if (textEmail.text.toString().isEmpty() || textPassword.text.toString().isEmpty())
            return

        auth.signInWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   goToAddActivity()
                } else {
                   // Snackbar.make(textEmail, "User not logedin", Snackbar.LENGTH_SHORT)
                    println("!!! user not LogedIn ${textEmail.text} ${textPassword.text} ${task.exception}")
                }
            }
    }

    fun createUser() {
        if (textEmail.text.toString().isEmpty() || textPassword.text.toString().isEmpty())
            return

        auth.createUserWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   goToAddActivity()
                } else {
                   // Snackbar.make(textEmail, "User not created", Snackbar.LENGTH_SHORT)
                    println("!!! user not created ${textEmail.text} ${textPassword.text} ${task.exception}")
                }
            }
    }


}
