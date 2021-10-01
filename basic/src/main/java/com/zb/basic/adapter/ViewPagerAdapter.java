package com.zb.basic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.zb.basic.R;
import com.zb.common.utils.P;

import java.util.Random;

public class ViewPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        View pager = LayoutInflater.from(container.getContext())
                .inflate(R.layout.pager_view, container, false);
        P.p("PagerAdapter::instantiateItem()");
        pager.setBackgroundColor((new Random().nextInt()) | 0xff0000 );
        TextView txt = pager.findViewById(R.id.pager_text);
        txt.setText("这是第" + position + "页!");
        container.addView(pager);
        return pager;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
