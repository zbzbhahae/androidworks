package com.zb.customview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.customview.R;

public class GridScenesAdapter extends RecyclerView.Adapter<GridScenesAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_scene_grid, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 300;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contentTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contentTxt = (TextView) itemView;
        }
    }
}
