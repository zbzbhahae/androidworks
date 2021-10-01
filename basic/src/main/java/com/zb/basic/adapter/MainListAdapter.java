package com.zb.basic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.basic.R;
import com.zb.common.utils.P;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {

    private List<String> data = null;
    private OnItemClickListener listener;

    public void setOnItemClickedListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.button.setText(data.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != listener)
                    listener.onItemClicked(view, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data==null? 0:data.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(View v, int position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.item_main_button);
        }
    }
}
