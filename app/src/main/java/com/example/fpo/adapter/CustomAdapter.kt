package com.example.fpo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.fpo.R
import com.example.fpo.model.Globals
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
                holder.textImage1.text = usermodel.image
                holder.textName.text = usermodel.name
                holder.textAdress.text = usermodel.address
                holder.textGender.text = usermodel.gender
                holder.textVillage.text = usermodel.villagename
                holder.textAcreage.text = usermodel.acreagename
                holder.textCrop.text = usermodel.cropname
            }
        }
        holder.itemView.setOnClickListener() {


           var expanded=Globals.getList()
            // Change the state
            if(expanded)
            {
                Globals.list(false)
                notifyItemChanged(position);

            }
            else
            {
                Globals.list(true)
                notifyItemChanged(position);

            }



        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       // lateinit var textImage: TextView
        lateinit var textCompanyName: TextView
        lateinit var textCompanyCurrency: TextView
        lateinit var textImage1: TextView
        lateinit var textName: TextView
        lateinit var textAdress: TextView
        lateinit var textGender: TextView
        lateinit var textVillage: TextView
        lateinit var textAcreage: TextView
        lateinit var textCrop: TextView
        lateinit var layout1: ConstraintLayout
        lateinit var layout2: ConstraintLayout

        init {
            run {
                //textImage = itemView.findViewById(R.id.imageView1) as TextView
                textCompanyName = itemView.findViewById(R.id.company_name) as TextView
                textCompanyCurrency = itemView.findViewById(R.id.company_currency) as TextView
                textImage1 = itemView.findViewById(R.id.imageView2) as TextView
                textAdress = itemView.findViewById(R.id.sl_no) as TextView
                textName = itemView.findViewById(R.id.name) as TextView
                textGender = itemView.findViewById(R.id.gender) as TextView
                textVillage = itemView.findViewById(R.id.village_name) as TextView
                textAcreage = itemView.findViewById(R.id.acreage_name) as TextView
                textCrop = itemView.findViewById(R.id.maincrops_name) as TextView

                layout1 = itemView.findViewById(R.id.profile) as ConstraintLayout
                layout2 = itemView.findViewById(R.id.data) as ConstraintLayout
                var expanded = Globals.getList()
                if (expanded) {
                    layout1.isVisible = false
                    layout2.isVisible = true
                } else {
                    layout1.isVisible = true
                    layout2.isVisible = false
                }

            }

        }
    }
}

