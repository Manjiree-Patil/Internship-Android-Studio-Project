package com.example.autosprings


import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.IOException

class UpdateCarActivity : AppCompatActivity() {

    var id: String = ""
    var houseText: String = ""
    var cityText: String = ""
    var stateText: String = ""
    var codeText: String = ""
    var mfgNameText: String = ""
    var modelNameText: String = ""
    var mfgYearText: String = ""

    lateinit var houseName2: EditText
    lateinit var cityName2: EditText
    lateinit var stateName2: EditText
    lateinit var codeName2: EditText
    lateinit var mfgName2: EditText
    lateinit var modelName2: EditText
    lateinit var mfgYear2: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_car)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        val updateCarBtn = findViewById<Button>(R.id.updateCarBtn)
        val deleteCarBtn = findViewById<Button>(R.id.deleteCarBtn)

        houseName2 = findViewById<EditText>(R.id.house2)
        cityName2 = findViewById<EditText>(R.id.city2)
        stateName2 = findViewById<EditText>(R.id.state2)
        codeName2 = findViewById<EditText>(R.id.pincode2)
        mfgName2 = findViewById<EditText>(R.id.mfgName2)
        modelName2 = findViewById<EditText>(R.id.modelName2)
        mfgYear2 = findViewById<EditText>(R.id.mfgYear2)


        getAndSetIntentDataCar()

        updateCarBtn.setOnClickListener{

            val myDBHelper = MyDBHelper(this)
            houseText = houseName2.getText().toString().trim();
            cityText = cityName2.getText().toString().trim();
            stateText = stateName2.getText().toString().trim();
            codeText = codeName2.getText().toString().trim();
            mfgNameText = mfgName2.getText().toString().trim();
            modelNameText = modelName2.text.toString().trim();
            mfgYearText = mfgYear2.getText().toString().trim();

            myDBHelper.updateCarData(id,houseText, cityText, stateText, codeText ,mfgNameText, modelNameText, mfgYearText)

        }

        deleteCarBtn.setOnClickListener {
            confirmDialog()
        }
    }

    private fun getAndSetIntentDataCar()
    {

        try {
            if(intent.hasExtra("id") &&
                intent.hasExtra("mfgNameText") &&
                intent.hasExtra("modelNameText") &&
                intent.hasExtra("mfgYearText"))
            {
                var houseText: String = ""
                var cityText: String = ""
                var stateText: String = ""
                var codeText: String = ""

                // getting data from intent
                id = intent.getStringExtra("id").toString()
                houseText = intent.getStringExtra("houseText").toString()
                cityText = intent.getStringExtra("cityText").toString()
                stateText = intent.getStringExtra("stateText").toString()
                codeText = intent.getStringExtra("codeText").toString()
                mfgNameText = intent.getStringExtra("mfgNameText").toString()
                modelNameText = intent.getStringExtra("modelNameText").toString()
                mfgYearText = intent.getStringExtra("mfgYearText").toString()

                // setting intent data
                houseName2.setText(houseText)
                cityName2.setText(cityText)
                stateName2.setText(stateText)
                codeName2.setText(codeText)
                mfgName2.setText(mfgNameText)
                modelName2.setText(modelNameText)
                mfgYear2.setText(mfgYearText)
//                Toast.makeText(applicationContext, mfgNameText, Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(applicationContext, "nope", Toast.LENGTH_SHORT).show()
            }

        }catch(e: IOException){
            //code that handles exception
            Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    private fun confirmDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete $mfgNameText ?")
        builder.setMessage("Are you sure you want to delete $mfgNameText ?")
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