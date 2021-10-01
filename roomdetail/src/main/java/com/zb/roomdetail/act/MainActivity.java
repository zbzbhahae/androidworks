package com.zb.roomdetail.act;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.zb.roomdetail.R;
import com.zb.roomdetail.act.BaseActivity;
import com.zb.roomdetail.bean.Student;
import com.zb.roomdetail.db.AppDatabase;
import com.zb.roomdetail.db.dao.StudentDao;
import com.zb.roomdetail.utils.P;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private AppDatabase database;
    private StudentDao studentDao;


    private ScrollView scrollView;
    private Button insertBtn, updateBtn, clearBtn, deleteBtn;
    private TextView textView;
    private LiveData<List<Student>> allStudents;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertBtn = findViewById(R.id.button);
        updateBtn = findViewById(R.id.button2);
        clearBtn = findViewById(R.id.button3);
        deleteBtn = findViewById(R.id.button4);

        textView = findViewById(R.id.text);
        scrollView = findViewById(R.id.scroll_view);

        insertBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        database = AppDatabase.getInstance(this);
        studentDao = database.getStudentDao();



        allStudents = studentDao.getAllStudents();
        allStudents.observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                long startT = System.currentTimeMillis();
                if(null == students || students.size() == 0) {
                    textView.setText("无数据");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<students.size(); i++) {
                    sb.append(students.get(i).toString() + "\n");
                }
                textView.setText(sb.toString());

                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
//                int offset = textView.getMeasuredHeight() - scrollView.getHeight();
//                scrollView.scrollTo(0, offset);
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
                long stopT = System.currentTimeMillis();
                P.p("LiveData用时:" + (stopT - startT));
            }
        });
    }

    void updateContent() {
        long startT = System.currentTimeMillis();
        List<Student> students = studentDao.getStudentsDESC();
        if(null == students || students.size() == 0) {
            textView.setText("无数据");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<students.size(); i++) {
            sb.append(students.get(i).toString() + "\n");
        }
        textView.setText(sb.toString());

        scrollView.post(new Runnable() {
            @Override
            public void run() {
//                int offset = textView.getMeasuredHeight() - scrollView.getHeight();
//                scrollView.scrollTo(0, offset);
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        long stopT = System.currentTimeMillis();
        P.p("查询用时:" + (stopT - startT));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                doInsert();
                break;
            case R.id.button2:
                doUpdate();
                break;
            case R.id.button3:
                doClear();
                break;
            case R.id.button4:
                doDelete();
                break;
        }
//        updateContent();
    }

    private void doDelete() {
        Student student = new Student();
        student.setId(34334);
        student.setUid(10);
        studentDao.deleteStudent(student);
    }

    private void doClear() {
        studentDao.deleteAllStudents();
    }

    private void doUpdate() {
        Student student = new Student();
        student.setId(34334);
        student.setName("我更新");
        student.setUid(12);
        student.setAge(22);
        studentDao.updateStudent(student);
    }

    private void doInsert() {

        Student student1 = new Student("王大发", 19, 33315);
        Student student2 = new Student("王小发", 15, 34334);
        studentDao.insertStudents(new Student[]{student1, student2});
    }
}
