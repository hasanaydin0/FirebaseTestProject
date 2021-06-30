package com.hasanaydin.firebasetestproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.hasanaydin.firebasetestproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

    }

    fun signInClicked(view : View){

    }

    fun signUpClicked(view : View){

        val email = binding.userEmailText.text.toString()
        val password = binding.passwordText.text.toString()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if (task.isSuccessful){
                val intent = Intent(this,FeedActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.addOnFailureListener { exception ->

            if (exception != null){
                Toast.makeText(applicationContext, exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()
            }

        }

    }

}