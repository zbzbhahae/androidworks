package com.zb.review.acts.network;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.zb.review.R;
import com.zb.review.acts.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class GlideActivity extends BaseActivity {

    @BindView(R.id.glide_image)
    ImageView image;
    @BindView(R.id.glide_button)
    Button button;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        button.setBackground(new ColorDrawable(Color.BLACK));
    }

    private static final String IMAGE_URL = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx1.sinaimg.cn%2Flarge%2F006FB4sAgy1fdnkqil672g30p00dwe1t.gif&refer=http%3A%2F%2Fwx1.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628259673&t=752b63883e50edd49ee400cc1b922719";

    @OnClick(R.id.glide_button)
    public void onClick(Button b) {
        Glide.with(this)
                .load(IMAGE_URL)
                .error(new ColorDrawable(Color.BLACK))
                .placeholder(new ColorDrawable(Color.GRAY))
                .fallback(new ColorDrawable(Color.BLUE))
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image);
    }
}
