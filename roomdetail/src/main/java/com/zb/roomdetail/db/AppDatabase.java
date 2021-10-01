package com.zb.roomdetail.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zb.roomdetail.bean.Student;
import com.zb.roomdetail.db.dao.StudentDao;

@androidx.room.Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;
    private static final Object o = new Object();
    public static synchronized AppDatabase getInstance(Context context) {
        if(null == instance) {
            synchronized (o) {
                if(null == instance)
                    instance = Room.databaseBuilder(context, AppDatabase.class, "database")
                            .allowMainThreadQueries()
                            .build();
            }
        }
        return instance;
    }



    public abstract StudentDao getStudentDao();
}
