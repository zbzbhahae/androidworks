package com.zb.review.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zb.review.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewpagerAdapter extends RecyclerView.Adapter {

    private List<String> data;

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager, parent, false);
        return new ViewPagerHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewPagerHolder) {
            ViewPagerHolder h = (ViewPagerHolder) holder;
            String url = data.get(position);
            Glide.with(h.imageView.getContext()).load(url).into(h.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if(null != data && data.size()>0)
            return data.size();
        return 0;
    }


    class ViewPagerHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ViewPagerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_pager_image);
        }
    }
}
