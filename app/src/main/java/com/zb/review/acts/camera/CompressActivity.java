package com.zb.review.acts.camera;

import android.os.Bundle;
import android.view.View;

import com.hw.videoprocessor.VideoProcessor;
import com.hw.videoprocessor.util.VideoProgressListener;
import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;

import org.jetbrains.annotations.Nullable;

import butterknife.OnClick;

public class CompressActivity extends BaseActivity {

    private static final String path = "" +
            "/storage/emulated/0/Android/data/com.zb.review/files/Movies/mp4_20210804_235794_6197349717226449598.mp4";
    private static final String outPath = "" +
            "/storage/emulated/0/Android/data/com.zb.review/files/Movies/mp4_20210804_235794_6197349717226449598_pressed.mp4";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compress);

    }

    @OnClick(R.id.compress_button)
    public void onClick(View v) {
        P.p("开始压缩");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    VideoProcessor.processor(CompressActivity.this)
                            .input(path)
                            .output(outPath)
                            .outHeight(360)
                            .outWidth(640)
                            .progressListener(new VideoProgressListener() {
                                @Override
                                public void onProgress(float progress) {
                                    P.p("压缩进度->" + progress);
                                }
                            }).process();
                } catch (Exception e) {
                    P.p("压缩发生错误 " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
