package com.zb.review.acts;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.chrisbanes.photoview.PhotoView;
import com.zb.review.R;
import com.zb.review.utils.P;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;

public class PhotoViewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.photoview_image)
    PhotoView pv;
    @BindView(R.id.photoview_button)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);

        pv.setMaximumScale(100f);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RectF rect = pv.getDisplayRect();


        P.p("缩放-》" + pv.getScale() + "  位置-> " + rect.toString());
    }
}
