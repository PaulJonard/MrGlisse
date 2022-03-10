package com.example.mrglisse.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mrglisse.R
import com.example.mrglisse.model.Ski

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var skiList = emptyList<Ski>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val customBrand: TextView = itemView.findViewById(R.id.customRowBrand)
        val customModel: TextView = itemView.findViewById(R.id.customRowModel)
        val customSize: TextView = itemView.findViewById(R.id.customRowSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = skiList[position]
        holder.customBrand.text = currentItem.brand
        holder.customModel.text = currentItem.model
        holder.customSize.text = currentItem.size.toString()
    }

    override fun getItemCount(): Int {
        return skiList.size
    }

    fun setData(skis:List<Ski>){
        this.skiList = skis
        notifyDataSetChanged()
    }
}