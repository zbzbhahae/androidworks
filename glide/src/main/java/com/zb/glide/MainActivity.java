package com.zb.glide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.zb.glide.option.GlideApp;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class MainActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);

        RequestOptions requestOptions = new RequestOptions().centerCrop();

//        GlideApp.with(this)
//                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201104%2F16%2F2136492e16kpc6oqcz1rie.jpg&refer=http%3A%2F%2Fattach.bbs.miui.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630950781&t=11c6139e65f5851f10f2b438dc8d2da7")
//                .apply(requestOptions).into(imageView);
//        GlideApp.with(this)
////                .asFile()
////                .listener(new RequestListener<File>() {
////                    @Override
////                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
////                        return false;
////                    }
////
////                    @Override
////                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
////                        return false;
////                    }
////                })
//                .load("https://home.baidu.com/Public/img/bd_map.jpg")
//                .applyAvatar(300 * 2)
//
////                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(imageView);

//        FutureTarget<Bitmap> futureTarget = GlideApp.with(this)
//                .asBitmap()
//                .load("https://home.baidu.com/Public/img/bd_map.jpg")
//                .submit();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Bitmap b = futureTarget.get();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            imageView.setImageBitmap(b);
//                        }
//                    });
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();



        GlideApp.with(this).load("https://home.baidu.com/Public/img/bd_map.jpg")
        .applyBlur().into(imageView);
    }
}