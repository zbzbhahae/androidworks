package com.zb.share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        shareOnOtherSocialMedia();
//        shareMsg("activityTItle", "msgTitle", "msgText", "/sdcard/Review/share2.png");
//
        File f = new File("/sdcard/Review/share.png");
        boolean exist = f.exists();
        Uri uri = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                uri = Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),

                        f.getAbsolutePath(), "genexQRCode", null));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }else{
            uri = Uri.fromFile(new File(f.getPath()));

        }
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        i.putExtra(Intent.EXTRA_STREAM, uri);
        i.setAction(Intent.ACTION_SEND);
        i.setType("image/png");
        i.putExtra(Intent.EXTRA_TEXT, "内容");
        i.putExtra(Intent.EXTRA_TITLE, "标题");
        i.putExtra(Intent.EXTRA_CHOOSER_TARGETS, new String[]{"com.tencent.mm", "com.huawei.works"});
        i.putExtra(Intent.EXTRA_INITIAL_INTENTS, createIntentArray());
        startActivity(i);


    }

    private Intent[] createIntentArray() {
        Intent[] choosed = new Intent[2];
        choosed[0] = new Intent();
        choosed[0].setPackage("com.tencent.mm");
        choosed[1] = new Intent();
        choosed[1].setPackage("com.huawei.works");
        return choosed;
    }

    private void shareOnOtherSocialMedia() {

        List<Intent> shareIntentsLists = new ArrayList<Intent>();
        Intent shareIntent = new Intent();
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("/sdcard/Review/share.png"));
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("image/png");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "内容");
        shareIntent.putExtra(Intent.EXTRA_TITLE, "标题");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("/sdcard/Review/share.png"));
        List<ResolveInfo> resInfos = getPackageManager().queryIntentActivities(shareIntent, 0);
        if (!resInfos.isEmpty()) {
            for (ResolveInfo resInfo : resInfos) {
                String packageName = resInfo.activityInfo.packageName;
//                if (packageName.toLowerCase().contains("com.tencent.mm")) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("/sdcard/Review/share2.png"));
                    intent.setPackage(packageName);
                    intent.putExtra(Intent.EXTRA_TEXT, "内容");
                    intent.putExtra(Intent.EXTRA_TITLE, "分享到welink或微信");
                    shareIntentsLists.add(intent);
//                } else if(packageName.toLowerCase().contains("com.huawei.works")) {
//                    Intent intent = new Intent();
//                    intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
//                    intent.setAction(Intent.ACTION_SEND);
//                    intent.setType("image/*");
//                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("/sdcard/Review/share.png"));
//                    intent.setPackage(packageName);
//                    intent.putExtra(Intent.EXTRA_TEXT, "内容");
//                    intent.putExtra(Intent.EXTRA_TITLE, "分享到welink或微信");
//                    shareIntentsLists.add(intent);
//                }
            }
            if (!shareIntentsLists.isEmpty()) {
                Intent chooserIntent = Intent.createChooser(shareIntentsLists.remove(0), "Choose app to share");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentsLists.toArray(new Parcelable[]{}));
                startActivity(chooserIntent);
            } else {
                Log.e("Error", "No Apps can perform your task");
            }
        }
    }

    public void shareMsg(String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }
}