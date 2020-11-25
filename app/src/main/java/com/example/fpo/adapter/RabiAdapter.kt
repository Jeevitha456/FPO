package com.example.fpo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fpo.R
import com.example.fpo.model.RabiData

class RabiAdapter(val rabiList: ArrayList<RabiData>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewRabiHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.rabi_innerlayout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return rabiList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rabimodel = rabiList[position]
        when (holder) {
            is ViewRabiHolder -> {
                holder.rabiCropImage.setImageResource(rabimodel.rabiImage)
                if (!rabimodel.isSelected) {
                    holder.rabiCropImage.setBackgroundColor(Color.WHITE)
                } else {
                    holder.rabiCropImage.setBackgroundColor(Color.parseColor("#EEFFF7"))
                }

            }

        }

    }

    inner class ViewRabiHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var rabiCropImage: ImageView

        init {
            run {
                rabiCropImage = itemView.findViewById(R.id.img_rabi_crops) as ImageView
                itemView.setOnClickListener(this)

            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onItemClick(position)
        }
    }

}
