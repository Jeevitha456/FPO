package com.example.fpo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fpo.R
import com.example.fpo.model.LivestockData

class LiveStockAdapter(val livestockList: ArrayList<LivestockData>, val listener: OnLivestockClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewLiveStockHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.farm_livestock_innerlayout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return livestockList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val livestockmodel = livestockList[position]
        when (holder) {
            is ViewLiveStockHolder -> {
                holder.livestockCropImage.setImageResource(livestockmodel.img_livestock)
                if (!livestockmodel.isSelected) {
                    holder.livestockCropImage.setBackgroundColor(Color.WHITE)
                } else {
                    holder.livestockCropImage.setBackgroundColor(Color.parseColor("#EEFFF7"))
                }
            }
        }
    }

    inner class ViewLiveStockHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var livestockCropImage: ImageView

        init {
            run {
                livestockCropImage = itemView.findViewById(R.id.img_livestock_crops) as ImageView
                itemView.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onLivestockItemClick(position)
        }
    }
}

