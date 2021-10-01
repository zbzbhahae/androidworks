package com.zb.review.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.review.R;
import com.zb.review.adapter.holder.MainRecyclerViewHolder;
import com.zb.review.mvp.bean.MainRecycleModel;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<MainRecycleModel> data;
    private  OnMainItemClickedListener listener;
    private int cachedDataSize = 0;

    public void setOnImteClickedListener(OnMainItemClickedListener listener) {
        this.listener = listener;
    }

    public MainRecyclerViewAdapter(List<MainRecycleModel> data) {
        this.data = data;
        if(null != data)
            cachedDataSize = data.size();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_recycler_view, parent, false);
        return new MainRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MainRecyclerViewHolder) {
            Button btn = ((MainRecyclerViewHolder) holder).btn;
            btn.setText(data.get(position).getText());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null != listener)
                        listener.onItemClicked(holder.getAdapterPosition());
                }
            });
            btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(null != listener) {
                        listener.onTiemLongClick(holder.getAdapterPosition());
                        if(data != null && data.size() != cachedDataSize) {
                            cachedDataSize = data.size();
                            notifyItemRemoved(holder.getAdapterPosition());
                        }
                    }
                    return false;
                }
            });
        }
    }

    public interface OnMainItemClickedListener {
        void onItemClicked(int position);
        void onTiemLongClick(int position);
    }

    @Override
    public int getItemCount() {
        if(null == data)
            return 0;
        return data.size();
    }
}
