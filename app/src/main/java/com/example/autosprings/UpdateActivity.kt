package com.example.autosprings


import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class UpdateActivity : AppCompatActivity() {

    var id: String = ""
    var firstText: String = ""
    var lastText: String = ""
    var emailText: String = ""
    var mob: String = ""
    lateinit var firstName2: EditText
    lateinit var lastName2: EditText
    lateinit var emailID2: EditText
    lateinit var mobileNumber2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        var updateBtn: Button = findViewById<Button>(R.id.updateBtn)
        var deleteBtn: Button = findViewById<Button>(R.id.deleteBtn)

        firstName2 = findViewById<EditText>(R.id.firstName2)
        lastName2 = findViewById<EditText>(R.id.lastName2)
        emailID2 = findViewById<EditText>(R.id.emailID2)
        mobileNumber2 = findViewById<EditText>(R.id.mobileNumber2)

        getAndSetIntentData()

        updateBtn.setOnClickListener{

            val myDBHelper = MyDBHelper(this)
            firstText = firstName2.getText().toString().trim();
            lastText = lastName2.getText().toString().trim();
            emailText = emailID2.getText().toString().trim();
            mob = mobileNumber2.getText().toString().trim();
            myDBHelper.updateData(id, firstText, lastText, emailText, mob)

        }

        deleteBtn.setOnClickListener {
            confirmDialog()
        }



    }

    private fun getAndSetIntentData()
    {

        if(intent.hasExtra("id") &&
            intent.hasExtra("firstText") &&
            intent.hasExtra("lastText") &&
            intent.hasExtra("emailText") &&
            intent.hasExtra("mobText"))
        {
            // getting data from intent
            id = intent.getStringExtra("id").toString()
            firstText = intent.getStringExtra("firstText").toString()
            lastText = intent.getStringExtra("lastText").toString()
            emailText = intent.getStringExtra("emailText").toString()
            mob = intent.getStringExtra("mobText").toString()

            // setting intent data
            firstName2.setText(firstText)
            lastName2.setText(lastText)
            emailID2.setText(emailText)
            mobileNumber2.setText(mob)



        }else {
//            Toast.makeText(applicationContext, "No data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete $firstText ?")
        builder.setMessage("Are you sure you want to delete $firstText ?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            val myDBHelper = MyDBHelper(this)
            myDBHelper.deleteOneRow(id)
            finish()
        }

        builder.setNegativeButton("No") { dialogInteface: DialogInterface, i: Int ->

        }
        builder.create().show()

    }
}