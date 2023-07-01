package com.example.autosprings

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlin.coroutines.coroutineContext


private const val DATABASE_NAME : String = "UserRegistration.db"
private const val DATABASE_VERSION : Int = 1



class MyDBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    // user table
    val TABLE_NAME = "my_registration"
    val COLUMN_ID = "_id"
    val COLUMN_FIRST = "user_first"
    val COLUMN_LAST = "user_last"
    val COLUMN_EMAIL = "user_email"
    val COLUMN_MOB = "user_mob"

    // car details table
    val TABLE_NAME_CAR = "my_cars"
    val COLUMN_ID_CAR = "_id"
    val COLUMN_MFG_NAME = "car_mfg_name"
    val COLUMN_MODEL_NAME = "car_model_name"
    val COLUMN_YEAR = "car_year"
    val COLUMN_HOUSE = "car_house"
    val COLUMN_CITY = "car_city"
    val COLUMN_STATE = "car_state"
    val COLUMN_PINCODE = "car_pincode"


    override fun onCreate(db: SQLiteDatabase?) {

        val query : String = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST + " TEXT, " +
                COLUMN_LAST + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_MOB + " TEXT);"

        val query2 : String = "CREATE TABLE " + TABLE_NAME_CAR +
                " (" + COLUMN_ID_CAR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_HOUSE + " TEXT, " +
                COLUMN_CITY + " TEXT, " +
                COLUMN_STATE + " TEXT, " +
                COLUMN_PINCODE + " TEXT, " +
                COLUMN_MFG_NAME + " TEXT, " +
                COLUMN_MODEL_NAME + " TEXT, " +
                COLUMN_YEAR + " TEXT);"

        db?.execSQL(query)
        db?.execSQL(query2)


    }


    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_CAR")
        onCreate(db)
    }

    fun addBook(first: String, last: String, email: String, mob: String) {
        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COLUMN_FIRST, first)
        contentValues.put(COLUMN_LAST, last)
        contentValues.put(COLUMN_EMAIL, email)
        contentValues.put(COLUMN_MOB, mob)

        var result = database.insert(TABLE_NAME, null, contentValues)

        if(result == -1L)
        {
            Log.d("REG_DATA", "addBook: Failed")
        }else
        {
            Log.d("REG_DATA", "addBook: Added Successfully")

        }

    }

    // car
    fun addCarDetails(houseName: String, cityName: String, stateName: String, pinCode: String, mfgName: String, modelName: String, mfgYear: String)
    {
        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COLUMN_HOUSE, houseName)
        contentValues.put(COLUMN_CITY, cityName)
        contentValues.put(COLUMN_STATE, stateName)
        contentValues.put(COLUMN_PINCODE, pinCode)
        contentValues.put(COLUMN_MFG_NAME, mfgName)
        contentValues.put(COLUMN_MODEL_NAME, modelName)
        contentValues.put(COLUMN_YEAR, mfgYear)

        var result = database.insert(TABLE_NAME_CAR, null, contentValues)
        if(result == -1L)
        {
            Log.d("REG_DATA", "addCar: Failed")
        }else
        {
            Log.d("REG_DATA", "addCar: Added Successfully")

        }
    }

    fun readAllData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME"
        val database = this.readableDatabase

        var cursor : Cursor? = null
        if(database != null)
        {
            cursor = database.rawQuery(query, null)

        }
        return cursor
    }

    // car
    fun readAllCarData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME_CAR"
        val database = this.readableDatabase

        var cursor : Cursor? = null
        if(database != null)
        {
            cursor = database.rawQuery(query, null)

        }
        return cursor
    }




    fun updateData(row_id: String, first: String, last: String, email: String, mob: String)
    {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FIRST, first)
        contentValues.put(COLUMN_LAST, last)
        contentValues.put(COLUMN_EMAIL, email)
        contentValues.put(COLUMN_MOB, mob)

        val result = database.update(TABLE_NAME, contentValues, "_id=?", arrayOf(row_id))
        if(result == -1)
        {
            Log.d("REG_DATA", "addBook: Failed to update")
        }else {
            Log.d("REG_DATA", "addBook: Successfully Updated")
        }

    }



    fun updateCarData(row_id: String, houseName: String, cityName: String, stateName: String, pinCode: String, mfgName: String, modelName: String, mfgYear: String)
    {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_HOUSE, houseName)
        contentValues.put(COLUMN_CITY, cityName)
        contentValues.put(COLUMN_STATE, stateName)
        contentValues.put(COLUMN_PINCODE, pinCode)
        contentValues.put(COLUMN_MFG_NAME, mfgName)
        contentValues.put(COLUMN_MODEL_NAME, modelName)
        contentValues.put(COLUMN_YEAR, mfgYear)

        val result = database.update(TABLE_NAME_CAR, contentValues, "_id=?", arrayOf(row_id))
        if(result == 0)
        {
            Log.d("REG_DATA", "addCar: Failed to update")
        }else {
            Log.d("REG_DATA", "addCar: Successfully Updated")
        }

    }

    fun deleteOneRow(row_id: String)
    {
        val database = this.writableDatabase
        val result = database.delete(TABLE_NAME, "_id=?", arrayOf(row_id))
        if(result == -1)
        {
            Log.d("REG_DATA", "addBook: Failed to delete")
        }else {
            Log.d("REG_DATA", "addBook: Successfully Deleted")
        }
    }

    fun deleteAllData() {
        val database = this.writableDatabase
        database.execSQL("DELETE FROM $TABLE_NAME")
    }

}

