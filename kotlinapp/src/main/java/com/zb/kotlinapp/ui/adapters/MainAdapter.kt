package com.zb.kotlinapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.zb.kotlinapp.R

class MainAdapter : Adapter<MainAdapter.MainViewHolder>() {

    var data : List<String>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var listener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    class MainViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        private var btn:Button = item.findViewById(R.id.item_main_button)
        fun bind(position: Int, str:String, listener:OnItemClickListener?) {
            btn.text = str
            btn.setOnClickListener(View.OnClickListener {
                listener?.onClick(adapterPosition)
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(position, data?.get(position)?:"", listener)
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

}