package com.example.fpo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fpo.R
import com.example.fpo.model.UserData

class CustomAdapter(val userList: ArrayList<UserData>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.innerlayout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val usermodel = userList[position]
        when (holder) {
            is ViewHolder -> {
                holder.textCompanyName.text = usermodel.name
                holder.textCompanyCurrency.text = usermodel.address
                holder.textImage.text = usermodel.image
            }
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var textImage: TextView
        lateinit var textCompanyName: TextView
        lateinit var textCompanyCurrency: TextView

        init {
            run {
                textImage = itemView.findViewById(R.id.imageView1) as TextView
                textCompanyName = itemView.findViewById(R.id.company_name) as TextView
                textCompanyCurrency = itemView.findViewById(R.id.company_currency) as TextView
            }
        }

    }
}