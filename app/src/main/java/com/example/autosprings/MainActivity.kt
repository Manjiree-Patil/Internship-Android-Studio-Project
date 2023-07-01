package com.example.autosprings

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.dzmitry_lakisau.month_year_picker_dialog.MonthYearPickerDialog
import java.time.Month
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        val house = findViewById<EditText>(R.id.house)
        val city = findViewById<EditText>(R.id.city)
        val state = findViewById<EditText>(R.id.state)
        val pincode = findViewById<EditText>(R.id.pincode)
        val mfgName = findViewById<EditText>(R.id.mfgName)
        val modelName = findViewById<EditText>(R.id.modelName)
        val mfgYear = findViewById<EditText>(R.id.mfgYear)

        val submit = findViewById<Button>(R.id.submit)

        submit.setOnClickListener {

            val myDBHelper = MyDBHelper(this)

            myDBHelper.addCarDetails(house.text.toString(), city.text.toString(), state.text.toString(), pincode.text.toString(), mfgName.text.toString(), modelName.text.toString(), mfgYear.text.toString())

            startActivity(Intent(applicationContext, DisplayActivity::class.java))

        }


    }

}

