package com.hasanaydin.firebasetestproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hasanaydin.firebasetestproject.databinding.ActivityFeedBinding

class FeedActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFeedBinding

    private lateinit var auth : FirebaseAuth

    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        db = FirebaseFirestore.getInstance()

        getDataFromFirestore()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.options_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.add_post){

            // Upload Activity

            val intent = Intent(applicationContext,UploadActivity::class.java)
            startActivity(intent)
            // finish()

        }else if (item.itemId == R.id.logout){

            // Logout

            auth.signOut()
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        return super.onOptionsItemSelected(item)
    }


    fun getDataFromFirestore(){

        db.collection("Posts").addSnapshotListener { snapshot, exception ->
            if (exception != null){
                Toast.makeText(applicationContext, exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()
            }else{

                if (snapshot != null){
                    if (!snapshot.isEmpty){

                        val documents = snapshot.documents

                        for (document in documents){
                            val comment = document.get("comment") as String
                            val useremail = document.get("userEmail") as String
                            val downloadUrl = document.get("downloadUrl") as String
                            val timestamp = document.get("date") as Timestamp
                            val date = timestamp.toDate()

                            println(comment)
                            println(useremail)
                            println(downloadUrl)
                            println(date)

                        }

                    }
                }

            }
        }

    }

}