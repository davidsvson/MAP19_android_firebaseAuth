package com.example.firestoreauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddItemActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var textField : EditText
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        textField = findViewById(R.id.editText)
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            saveItem()
        }
    }

    fun saveItem() {

        val item = Item(textField.text.toString(), false, "mat")
       // println("!!! ${item.name}")
        val user = auth.currentUser
        if ( user == null)
            return

        db.collection("users").document(user!!.uid).collection("items").add(item)
            .addOnCompleteListener {
                println("complete")

            }.addOnCanceledListener {
                println("cancel")

            }
            .addOnSuccessListener {
            println("write")
        }
            .addOnFailureListener {
                println("did not write")
            }

    }


}
