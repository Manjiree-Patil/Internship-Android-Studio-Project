package com.example.autosprings


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayActivity : AppCompatActivity() {


    var myDBHelper = MyDBHelper(this)
    var column_id = ArrayList<String>()
    var column_first = ArrayList<String>()
    var column_last = ArrayList<String>()
    var column_email = ArrayList<String>()
    var column_mob = ArrayList<String>()

    // car
    var column_id_car = ArrayList<String>()
    var column_house_name = ArrayList<String>()
    var column_city_name = ArrayList<String>()
    var column_state_name = ArrayList<String>()
    var column_code_name = ArrayList<String>()
    var column_mfg_name = ArrayList<String>()
    var column_model_name = ArrayList<String>()
    var column_year = ArrayList<String>()


    private lateinit var emptyImage: ImageView
    private lateinit var emptyText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

//        context = parent.applicationContext


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        emptyText = findViewById(R.id.emptyText)
        emptyImage = findViewById(R.id.emptyImage)

        storeDataInArray()
        storeCarDataInArray()

        val customAdapter = CustomAdapter(
            this, this, column_id, column_first, column_last, column_email, column_mob,
            column_id_car,column_house_name, column_city_name, column_state_name, column_code_name,
            column_mfg_name, column_model_name, column_year
        )

        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }

    private fun storeDataInArray() {
        var cursor = myDBHelper.readAllData()
        if (cursor?.count == 0) {
            emptyImage.visibility = View.VISIBLE
            emptyText.visibility = View.VISIBLE

        } else {
            while (cursor?.moveToNext() == true) {
                column_id.add(cursor.getString(0))
                column_first.add(cursor.getString(1))
                column_last.add(cursor.getString(2))
                column_email.add(cursor.getString(3))
                column_mob.add(cursor.getString(4))

            }
            emptyImage.visibility = View.GONE
            emptyText.visibility = View.GONE
        }
    }

    private fun storeCarDataInArray() {
        var cursorCar = myDBHelper.readAllCarData()

        if (cursorCar?.count == 0) {
            emptyImage.visibility = View.VISIBLE
            emptyText.visibility = View.VISIBLE
        } else {
            while (cursorCar?.moveToNext() == true) {

                // car
                column_id_car.add(cursorCar.getString(0))
                column_house_name.add(cursorCar.getString(1))
                column_city_name.add(cursorCar.getString(2))
                column_state_name.add(cursorCar.getString(3))
                column_code_name.add(cursorCar.getString(4))
                column_mfg_name.add(cursorCar.getString(5))
                column_model_name.add(cursorCar.getString(6))
                column_year.add(cursorCar.getString(7))
            }

        }
        emptyImage.visibility = View.GONE
        emptyText.visibility = View.GONE
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll) {
            confirmDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All ?")
        builder.setMessage("Are you sure you want to delete all data?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->

            val myDBHelper = MyDBHelper(this)
            myDBHelper.deleteAllData()
            Toast.makeText(applicationContext, "All Data Deleted", Toast.LENGTH_SHORT).show()
            // refresh activity
            startActivity(Intent(applicationContext, DisplayActivity::class.java))
            finish()

        }

        builder.setNegativeButton("No") { dialogInteface: DialogInterface, i: Int ->

        }
        builder.create().show()

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            recreate()
        }
    }

}