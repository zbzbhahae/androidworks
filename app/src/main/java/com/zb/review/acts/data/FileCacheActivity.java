package com.zb.review.acts.data;

import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class FileCacheActivity extends BaseActivity implements View.OnClickListener {

    private TextView showTv;
    private Button saveBtn, readBtn, deleteBtn, uselessBtn;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        saveBtn = findViewById(R.id.sqlite_create_button);
        uselessBtn = findViewById(R.id.sqlite_add_button);
        readBtn = findViewById(R.id.sqlite_read_button);
        deleteBtn = findViewById(R.id.sqlite_delete_button);
        showTv = findViewById(R.id.sqlite_show_text);

        showTv.setMovementMethod(ScrollingMovementMethod.getInstance());

        saveBtn.setText("存储信息到cache目录文件");
        readBtn.setText("读取文件信息");
        deleteBtn.setText("删除文件");

        uselessBtn.setVisibility(View.GONE);

        saveBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);


//        P.p("FileCacheActivity--->getCacheDir--->" + getCacheDir().getAbsolutePath());
//        P.p("FileCacheActivity--->getDataDir- API 24-->" + getDataDir().getAbsolutePath());
//        P.p("FileCacheActivity--->getExternalCacheDir--->" + getExternalCacheDir().getAbsolutePath());
//        P.p("FileCacheActivity--->getExternalFilesDir---(DIRECTORY_PICTURES)--->" + getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
//        P.p("FileCacheActivity--->getFilesDir--->" + getFilesDir().getAbsolutePath());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sqlite_create_button:
                doSave();
                doRead();
                break;
            case R.id.sqlite_read_button:
                doRead();
                break;
            case R.id.sqlite_delete_button:
                doDelete();
                doRead();
                break;
        }
    }

    private String MESSAGE = "作为一位口腔医学专业的学生，我想分享以下见解：\n" +
            "1.冲牙器（俗称水牙线）和电动牙刷的推广确实有利于大家的口腔卫生保健，大家可以多多尝试。\n" +
            "2.但是要注意：冲牙器是通过水流机械冲刷掉口腔死角的食物残渣，牙刷和牙膏则是物理摩擦掉牙齿表面的牙菌斑（危害口腔健康的领头羊）。只靠冲牙器冲刷不掉牙菌斑，所以冲牙器是无法替代牙刷的作用的。大家切忌用了冲牙器就不用牙刷了噢。\n" +
            "3.说到电动牙刷，与手动牙刷相比，电动牙刷清洁程度较高。虽然手动牙刷如果用对的刷牙方法也是可以刷干净的（标准的方法是巴斯刷牙法），但很多人比如孩子老人甚至工作忙的成年人都很难规范刷牙，所以比较便捷的电动牙刷比较符合这类人的需求。\n" +
            "4建议是：早晚刷牙一次，饭后漱口（此时可以用冲牙器）。刷牙时感觉牙缝刷不干净可以用冲牙器。\n" +
            "希望大家重视口腔健康，一口好牙伴一生~";
    private static final String FILE_NAME = "messageCache.txt";

    /** 应用的普通持久性文件位于您可以使用上下文对象的 filesDir 属性访问的目录中。
     * 此框架提供了多种方法帮助您在此目录中访问和存储文件。*/
    void doSave() {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            byte[] b = MESSAGE.getBytes("UTF-8");
            P.p("message byte size " + b.length);
            fos.write(b);
            fos.close();
        } catch (FileNotFoundException e) {
            P.p("file not found");
            e.printStackTrace();
        } catch (IOException e) {
            P.p("IOException");
            e.printStackTrace();
        }

    }
    void doRead() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while(null != line) {
                stringBuilder.append(line);
                line = reader.readLine();
            }
            showTv.setText(stringBuilder.toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            showTv.setText("文件未找到");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void doDelete() {
        File f = new File(getFilesDir() + "/" + FILE_NAME);
        if(f.exists())
            f.delete();
    }
}
