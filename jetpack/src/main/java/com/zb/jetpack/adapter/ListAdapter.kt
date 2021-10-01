package com.zb.jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.zb.jetpack.R

class ListAdapter : Adapter<ListAdapter.ListViewHolder>() {

    var data : List<String>? = null
    var listener : OnItemClicked? = null


    class ListViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var btn:Button = item.findViewById(R.id.item_list_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(item)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.btn.text = data?.get(position)?:""
        holder.btn.setOnClickListener {
            listener?.onClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    interface OnItemClicked {
        fun onClick(position:Int)
    }
}