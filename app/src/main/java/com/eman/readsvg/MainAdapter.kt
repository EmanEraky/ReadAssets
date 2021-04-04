package com.eman.readsvg

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eman.readsvg.databinding.ItemDoorBinding

class MainAdapter(private var doors: ArrayList<ModelDoor>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        context = parent.context
        val binding = DataBindingUtil.inflate<ItemDoorBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_door,
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = doors.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.binding.txtClass.text = doors.get(position).id +"..."+doors.get(position)._class+"..."+doors.get(position).x1+"..." +
                ""+doors.get(position).y1+"..."+doors.get(position).x2+"..."+doors.get(position).y2
    }

    class DataViewHolder(val binding: ItemDoorBinding) : RecyclerView.ViewHolder(binding.root)

    fun addData(list: List<ModelDoor>) {
        doors.addAll(list)
        notifyDataSetChanged()
    }


}