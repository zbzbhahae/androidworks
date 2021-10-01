package com.zb.review.acts.data;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.data.greendao.DatabaseManager;
import com.zb.review.data.greendao.bean.Person;
import com.zb.review.data.greendao.dao.PersonDao;
import com.zb.review.utils.P;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GreendaoActivity extends BaseActivity implements View.OnClickListener {

    private Button createBtn, addBtn, readBtn, deleteBtn;
    private TextView showTv;
    private DatabaseManager databaseManager;
    private PersonDao personDao;

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

        databaseManager = DatabaseManager.getManager(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseManager.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sqlite_create_button:
                doCreate();
                break;
            case R.id.sqlite_add_button:
                doAdd();
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

    void doCreate() {
        if(null == personDao)
            personDao = databaseManager.getDaoSession().getPersonDao();
    }

    /** 启用事务插入10000条数据大约用时2s  不启用大约用时40秒*/
    void doAdd() {
        if(null == personDao)
            return;
        int count = 10;
        List<Person> persons = createPersons(count);
        long startTime = System.currentTimeMillis();
        /** 插入多条数据使用事务*/
        personDao.insertInTx(persons);
        long stopTime = System.currentTimeMillis();
        P.p("GreendaoActivity ---增加 " + count + " 条数据用时： " + (stopTime - startTime) + "  ms");
    }

    void doRead() {
        if(null == personDao) {
            showTv.setText("");
            return;
        }
        List<Person> persons = personDao.loadAll();
        showTv.setText(createDataString(persons));

        /** 查询id>50的person  gt=greater than    forCurrentThread如果在子线程查询需要调用此方法*/
//        personDao.queryBuilder().where(PersonDao.Properties.Id.gt(50)).build().forCurrentThread().list();
    }

    void doDelete() {
        if(null == personDao)
            return;
        personDao.deleteAll();
    }

    /** 生成假数据*/
    List<Person> createPersons(int count) {
        List<Person> data = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<count; i++) {
            String name = SqliteActivity.getRandomName();
            int age = random.nextInt(13) + 18;
            String gender = random.nextBoolean()? "男" : "女";
            float height = random.nextInt(600) / 10f + 140f;
            float weight = random.nextInt(500) / 10f  + 40f;
            Person person = new Person();
            person.setAge(age);
            person.setGender(gender);
            person.setHeight(height);
            person.setWeight(weight);
            person.setName(name);
            data.add(person);
        }
        return data;
    }

    String createDataString(List<Person> Persons) {
        if(null != Persons && Persons.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i<Persons.size(); i++) {
                Person person = Persons.get(i);
                stringBuilder.append(person.getName());
                if(person.getName().length() <= 2)
                    stringBuilder.append("    ");
                stringBuilder.append("\t");
                stringBuilder.append(person.getAge());
                stringBuilder.append("\t");
                stringBuilder.append(person.getGender());
                stringBuilder.append("\t");
                stringBuilder.append(person.getHeight());
                stringBuilder.append("\t");
                stringBuilder.append(person.getWeight());
                stringBuilder.append("  \n");
            }
            String result = stringBuilder.toString();
            return result;
        } else {
            return "";
        }
    }
}
