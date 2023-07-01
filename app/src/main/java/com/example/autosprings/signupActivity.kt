package com.example.autosprings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class signupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );


        val firstName = findViewById<EditText>(R.id.firstName)
        val lastName = findViewById<EditText>(R.id.lastName)
        val emailId = findViewById<EditText>(R.id.emailID)
        val mobileNumber = findViewById<EditText>(R.id.mobileNumber)

        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {

            val myDBHelper = MyDBHelper(this)
            myDBHelper.addBook(firstName.text.toString(), lastName.text.toString(), emailId.text.toString(), mobileNumber.text.toString())

            startActivity(Intent(applicationContext, MainActivity::class.java))

        }
    }
}