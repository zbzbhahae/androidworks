package com.zb.review.acts.data;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.data.room.AppDatabase;
import com.zb.review.data.room.bean.Student;
import com.zb.review.utils.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomDatabaseActivity extends BaseActivity implements View.OnClickListener {

    private Button createBtn, addBtn, readBtn, deleteBtn;
    private TextView showTv;
    private AppDatabase db;
    private HandlerThread handlerThread;
    private Handler mainHandler, sqlHandler;

    public static final int MESSAGE_WHAT_ADD = 1;
    public static final int MESSAGE_WHAT_READ = 2;
    public static final int MESSAGE_WHAT_DELETE = 3;
    public static final int MESSAGE_WHAT_UPDATE_VIEW = 4;



    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        createBtn = findViewById(R.id.sqlite_create_button);
        addBtn = findViewById(R.id.sqlite_add_button);
        readBtn = findViewById(R.id.sqlite_read_button);
        deleteBtn = findViewById(R.id.sqlite_delete_button);
        showTv = findViewById(R.id.sqlite_show_text);

        showTv.setMovementMethod(ScrollingMovementMethod.getInstance());

        createBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        handlerThread = new HandlerThread("sql_thread", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
        /** 此hanlder运行在looper所在的线程 */
        sqlHandler = new Handler(handlerThread.getLooper(), new sqlThreadCallback());
        mainHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MESSAGE_WHAT_ADD:
                        break;
                    case MESSAGE_WHAT_READ:
                        break;
                    case MESSAGE_WHAT_UPDATE_VIEW://更新tv
                        Bundle b = msg.getData();
                        if(null != b) {
                            String result = b.getString("result");
                            if(null != result)
                                showTv.setText(result);
                        }
                        break;
                }
            }
        };
    }



    /** 运行在handlerThread线程的handler中的回调 用来实现具体数据库操作*/
    class sqlThreadCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MESSAGE_WHAT_ADD:
                    doAdd();
                    doRead();
                    break;
                case MESSAGE_WHAT_READ:
                    doRead();
                    break;
                case MESSAGE_WHAT_DELETE:
                    doDelete();
                    doRead();
                    break;
            }
            return false;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sqlite_create_button:
                doCreate();
                break;
            case R.id.sqlite_add_button:
                sendAddMessage();
                break;
            case R.id.sqlite_read_button:
                sendReadMessage();
                break;
            case R.id.sqlite_delete_button:
                sendDeleteMessage();
//                doRead();
                break;
        }
    }

    void doCreate() {
        if(null == db) {
            db = AppDatabase.getInstance(this);
            if(null == db) {
                P.p("数据库打开 失败");
            } else {
                P.p("数据库打开 成功");
                sendReadMessage();
            }
        }
    }
    /** handlerThread中运行*/
    void doAdd() {
        if(null == db)
            return;
        long startTime = System.nanoTime();
        db.studentDao().insertAll(createData());
        long stopTime = System.nanoTime();
        P.p("SqliteActivity  add database data used time : " + (stopTime - startTime)/1000000 + " ms");
    }
    /** handlerThread中运行*/
    void doRead() {
        List<Student> students = readDataFromDatabase();
        String content = createDataString(students);
        updateTextviewContent(content);
    }

    /**通过sendMessage来让sqlHandlerThread来与数据库进行读写操作*/
    void sendAddMessage() {
            sqlHandler.sendEmptyMessage(MESSAGE_WHAT_ADD);
    }
    void sendReadMessage() {
        sqlHandler.sendEmptyMessage(MESSAGE_WHAT_READ);
    }

    void sendDeleteMessage() {
        sqlHandler.sendEmptyMessage(MESSAGE_WHAT_DELETE);
    }

    /** 更新textview的内容*/
    void updateTextviewContent(String content) {
        Message updateMessage = new Message();
        updateMessage.what = MESSAGE_WHAT_UPDATE_VIEW;
        Bundle b = new Bundle();
        b.putString("result", content);
        updateMessage.setData(b);
        mainHandler.sendMessage(updateMessage);
    }

    /** 生成假数据*/
    List<Student> createData() {
        List<Student> data = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<10; i++) {
            String name = SqliteActivity.getRandomName();
            int age = random.nextInt(13) + 18;
            char gender = random.nextBoolean()? '男' : '女';
            float height = random.nextInt(600) / 10f + 140f;
            float weight = random.nextInt(500) / 10f  + 40f;
            Student stu = new Student();
            stu.name = name;
            stu.age = age;
            stu.gender = gender;
            stu.height = height;
            stu.weight = weight;
            data.add(stu);
        }
        return data;
    }



    List<Student> readDataFromDatabase() {
        List<Student> students = null;
        if(null != db)
            students = db.studentDao().getALL();
        return students;
    }

    String createDataString(List<Student> students) {
        if(null != students && students.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i<students.size(); i++) {
                Student sb = students.get(i);
                stringBuilder.append(sb.name);
                if(sb.name.length() <= 2)
                    stringBuilder.append("    ");
                stringBuilder.append("\t");
                stringBuilder.append(sb.age);
                stringBuilder.append("\t");
                stringBuilder.append(sb.gender);
                stringBuilder.append("\t");
                stringBuilder.append(sb.height);
                stringBuilder.append("\t");
                stringBuilder.append(sb.weight);
                stringBuilder.append("  \n");
            }
            String result = stringBuilder.toString();
            return result;
        } else {
            return "";
        }
    }



    void doDelete() {
        if(null == db)
            return;
        db.studentDao().deleteAll();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quitSafely();
        if(null != db)
            db.close();
    }
}
