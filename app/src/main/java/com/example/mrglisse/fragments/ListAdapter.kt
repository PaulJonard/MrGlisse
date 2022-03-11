package com.example.mrglisse.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mrglisse.R
import com.example.mrglisse.model.Alpin
import com.example.mrglisse.model.Fond
import com.example.mrglisse.model.Ski

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var skiList = emptyList<Ski>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val customBrand: TextView = itemView.findViewById(R.id.customRowBrand)
        val customModel: TextView = itemView.findViewById(R.id.customRowModel)
        val customSize: TextView = itemView.findViewById(R.id.customRowSize)

        val customItem:ConstraintLayout = itemView.findViewById(R.id.customItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = skiList[position]
        holder.customBrand.text = currentItem.brand
        holder.customModel.text = currentItem.model
        holder.customSize.text = currentItem.size.toString()

        holder.customItem.setOnClickListener {
            val action: NavDirections = if (currentItem is Alpin)
                AlpinMasterFragmentDirections.actionAlpinStockFragmentToSkiDetailFragment(null,currentItem)
            else
                FondMasterFragmentDirections.actionFondStockFragmentToSkiDetailFragment(currentItem as Fond, null)

            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return skiList.size
    }

    fun setData(skis:List<Ski>){
        this.skiList = skis
        notifyDataSetChanged()
    }
}