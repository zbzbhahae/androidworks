package com.zb.review.data.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.zb.review.data.room.bean.Student;
import com.zb.review.data.room.convert.DateLongConverter;
import com.zb.review.data.room.dao.StudentDao;

import org.jetbrains.annotations.NotNull;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
//@TypeConverters({DateLongConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            /** 迁移不仅要在这里修改， 相应的数据模型也要修改
             * !!!!添加新列为integer时 需要设置默认值*/
            database.execSQL("ALTER TABLE table_student add COLUMN info text");
        }
    };

    private static final Object o = new Object();
    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {
        if(null != instance)
            return instance;
        synchronized (o) {
            if(null == instance)
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "database")
                        .fallbackToDestructiveMigrationOnDowngrade()
                        .addMigrations(MIGRATION_1_2)
//                        .addTypeConverter(new DateLongConverter()) /** 声明数据格式转换器 也可在上方用注释声明*/
                        .build();
        }
        return instance;
    }
}
