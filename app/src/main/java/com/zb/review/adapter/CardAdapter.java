package com.zb.review.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.zb.review.R;
import com.zb.review.utils.P;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<String> data;

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new CardViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.card.getLayoutParams();
        LinearLayoutCompat.LayoutParams params1 = (LinearLayoutCompat.LayoutParams) holder.image.getLayoutParams();
        int width = ((WindowManager)holder.image.getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth()/2;
        P.p("card width: " + width);
        int height = 0;
        switch (position % 5) {
            case 0://500 x 528
                height = (int) (width / 500f * 528);
                break;
            case 1://500 x 500
                height = (int) (width / 500f * 500);
                break;
            case 2://500 x 500
                height = (int) (width / 500f * 500);
                break;
            case 3://1280 * 800
                height = (int) (width / 1280f * 800);
                break;
            case 4://683 * 1024
                height = (int) (width / 683f * 1024);
                break;
        }
        params1.height = height;
        params1.width = width;
        holder.image.setLayoutParams(params1);
        P.p("width-height : " + width + "  x  " + height);
        Glide.with(holder.image).load(data.get(position)).into(holder.image);
        holder.text.setText(" " + position + " ");
    }



    @Override
    public int getItemCount() {
        if(null == data)
            return 0;
        return data.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView text;
        private CardView card;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_card_image);
            text = itemView.findViewById(R.id.item_card_text);
            card = itemView.findViewById(R.id.item_card_card);
        }
    }
}
