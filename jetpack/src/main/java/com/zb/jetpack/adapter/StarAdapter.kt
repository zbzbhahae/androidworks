package com.zb.jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zb.jetpack.R
import com.zb.jetpack.databinding.ItemRecyclerDataBindingBinding
import com.zb.jetpack.mvvm.bean.Star
import com.zb.jetpack.utils.P

class StarAdapter : RecyclerView.Adapter<StarAdapter.ViewHolder>() {

    private var data : List<Star>? = null

    fun setData(data: List<Star>) {
        this.data = data
        notifyDataSetChanged()
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemRecyclerDataBindingBinding? = null

        constructor(binding : ItemRecyclerDataBindingBinding) : this(binding.root) {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRecyclerDataBindingBinding>(LayoutInflater.from(parent.context),
            R.layout.item_recycler_data_binding, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val star = data?.get(position)
        P.p(star.toString())
        holder.binding?.star = star
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }
}