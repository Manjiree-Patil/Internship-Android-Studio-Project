package com.example.autosprings


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private var activity: DisplayActivity,
                    private var context: Context,
                    private val column_id: ArrayList<String>,
                    private val column_first : ArrayList<String>,
                    private val column_last : ArrayList<String>,
                    private val column_email : ArrayList<String>,
                    private val column_mob : ArrayList<String>,
                    private val column_id_car : ArrayList<String>,
                    private val column_house_name : ArrayList<String>,
                    private val column_city_name : ArrayList<String>,
                    private val column_state_name : ArrayList<String>,
                    private val column_code_name : ArrayList<String>,
                    private val column_mfg_name : ArrayList<String>,
                    private val column_model_name : ArrayList<String>,
                    private val column_year : ArrayList<String>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.my_row, parent, false)

        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return column_id.size


    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context1: Context = holder.itemView.context

        holder.idText.text = column_id[position]
        holder.firstText.text = column_first[position]
        holder.lastText.text = column_last[position]
        holder.emailText.text = column_email[position]
        holder.mobText.text = column_mob[position]

        // car
        holder.idCarText.text = column_id_car[position]
        holder.houseText.text = column_house_name[position]
        holder.cityText.text = column_city_name[position]
        holder.stateText.text = column_state_name[position]
        holder.codeText.text = column_code_name[position]
        holder.mfgNameText.text = column_mfg_name[position]
        holder.modelNameText.text = column_model_name[position]
        holder.mfgYearText.text = column_year[position]



        holder.mainLayout.setOnClickListener {

            val intent = Intent(context1, UpdateActivity::class.java).apply {

                putExtra("id", column_id[position])
                putExtra("firstText", column_first[position])
                putExtra("lastText", column_last[position])
                putExtra("emailText", column_email[position])
                putExtra("mobText", column_mob[position])

            }
            activity.startActivityForResult(intent, 1)

        }

        holder.carLayout.setOnClickListener {

            val intent = Intent(context1, UpdateCarActivity::class.java).apply {

                putExtra("id", column_id_car[position])
                putExtra("houseText", column_house_name[position])
                putExtra("cityText", column_city_name[position])
                putExtra("stateText", column_state_name[position])
                putExtra("codeText", column_code_name[position])
                putExtra("mfgNameText", column_mfg_name[position])
                putExtra("modelNameText", column_model_name[position])
                putExtra("mfgYearText", column_year[position])

            }
            activity.startActivityForResult(intent, 1)
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var idText: TextView = itemView.findViewById(R.id.idText)
        var firstText: TextView = itemView.findViewById(R.id.firstText)
        var lastText: TextView = itemView.findViewById(R.id.lastText)
        var emailText: TextView = itemView.findViewById(R.id.emailText)
        var mobText: TextView = itemView.findViewById(R.id.mobText)
        var mainLayout: LinearLayout = itemView.findViewById(R.id.mainLayout)
        var carLayout: LinearLayout = itemView.findViewById(R.id.carLayout)


        // car
        var idCarText: TextView = itemView.findViewById(R.id.idCarText)
        var houseText: TextView = itemView.findViewById(R.id.houseText)
        var cityText: TextView = itemView.findViewById(R.id.cityText)
        var stateText: TextView = itemView.findViewById(R.id.stateText)
        var codeText: TextView = itemView.findViewById(R.id.codeText)
        var mfgNameText: TextView = itemView.findViewById(R.id.mfgNameText)
        var modelNameText: TextView = itemView.findViewById(R.id.modelNameText)
        var mfgYearText: TextView = itemView.findViewById(R.id.mfgYearText)


    }
}
