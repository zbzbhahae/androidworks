package com.zb.review.adapter.holder;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.review.R;

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

    public Button btn;

    public MainRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        btn = itemView.findViewById(R.id.item_main_recycler_view_button);
    }
}
