package com.example.fpo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fpo.R
import com.example.fpo.model.KharfiData

class KharfiAdapter(val kharfiList: ArrayList<KharfiData>, val listener: OnFarmItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return KharfiRabiHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.kharfi_innerlayout2, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return kharfiList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val kharfimodel = kharfiList[position]
        when (holder) {
            is KharfiRabiHolder -> {
                holder.kharfiCropImage.setImageResource(kharfimodel.imgKharfi)
                if (!kharfimodel.isSelected) {
                    holder.kharfiCropImage.setBackgroundColor(Color.WHITE)
                } else {
                    holder.run { kharfiCropImage.setBackgroundColor(Color.parseColor("#EEFFF7")) }
                }
            }
        }
    }

    inner class KharfiRabiHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var kharfiCropImage: ImageView

        init {
            run {
                kharfiCropImage = itemView.findViewById(R.id.img_kharfi_crops) as ImageView
                itemView.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onFarmItemClick(position)
        }
    }
}
