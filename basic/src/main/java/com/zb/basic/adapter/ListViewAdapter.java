package com.zb.basic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.zb.basic.R;
import com.zb.common.utils.P;

public class ListViewAdapter extends BaseAdapter {


    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        View itemView;
        if(null == view) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_list, viewGroup, false);
        } else {
            itemView = view;
        }
        vh = (ViewHolder) itemView.getTag();
        if(null == vh) {
            vh = new ViewHolder();
            itemView.setTag(vh);
        }
        vh.button = itemView.findViewById(R.id.item_main_button);
        vh.button.setText("ListView" + i);
        vh.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                P.p("ListView::onItemClick(" + i + ")");
            }
        });
//        vh.button.setOnClickListener();
        return itemView;
    }

    private final class ViewHolder {
        Button button;
    }
}
