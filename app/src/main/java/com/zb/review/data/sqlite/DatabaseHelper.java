package com.zb.review.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zb.review.data.sqlite.StudentContract.StudentEntry;
import com.zb.review.mvp.bean.sqlite.StudentBean;
import com.zb.review.utils.P;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    /**改变数据库结构后需要自增的数据库版本标志*/
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Student";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        P.p("DatabaseHelper------> onCreate");
        db.execSQL(SQL_CREATE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        P.p("DatabaseHelper------> onCreate");
        /** 数据库版本升级时的sql操作*/
        db.execSQL(SQL_DELETE_STUDENT);
        onCreate(db);
    }


    /** if not exists do nothing
     * float double存储会损失精度*/
    private static final String SQL_CREATE_STUDENT =
            "create table if not exists " + StudentEntry.TABLE_NAME + " (" +
            StudentEntry._ID + " integer primary key," +
            StudentEntry.COLUMN_NAME_NAME + " text," +
            StudentEntry.COLUMN_NAME_AGE + " integer," +
            StudentEntry.COLUMN_NAME_GENDER + " text," +
            StudentEntry.COLUMN_NAME_HEIGHT + " real," +
            StudentEntry.COLUMN_NAME_WEIGHT + " real)";

    private static final String SQL_DELETE_STUDENT = "drop table if exists " + StudentEntry.TABLE_NAME;

    public boolean saveStudents(List<StudentBean> stds, SQLiteDatabase db) {
        if(null == stds || null == db || stds.size() == 0)
            return false;
        ContentValues values = new ContentValues();
        Iterator<StudentBean> iterator = stds.listIterator();
        db.beginTransaction();
        while (iterator.hasNext()) {
            StudentBean sb = iterator.next();
            values.put(StudentEntry.COLUMN_NAME_NAME, sb.getName());
            values.put(StudentEntry.COLUMN_NAME_AGE, sb.getAge());
            values.put(StudentEntry.COLUMN_NAME_GENDER, sb.getGender());
            values.put(StudentEntry.COLUMN_NAME_HEIGHT, sb.getHeight());
            values.put(StudentEntry.COLUMN_NAME_WEIGHT, sb.getWeight());
            long rowId = db.insert(StudentEntry.TABLE_NAME, null, values);
            P.p("DatabaseHelper----saveStudents----rowId is : " + rowId);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return true;
    }

    public List<StudentBean> getAllStudentsFromDatabase(SQLiteDatabase db) {
        if(null == db)
            return null;
        Cursor c = db.query(StudentEntry.TABLE_NAME, null, null, null,null,null,null);
        if(null != c && c.getCount() > 0) {
            List<StudentBean> data = new ArrayList<>();
            for(int i=0; i<c.getCount(); i++) {
                /** 如需查看光标中的某一行，请使用 Cursor move 方法之一，
                 * 您必须始终在开始读取值之前调用该方法。由于光标从位置 -1 开始，
                 * 因此调用 moveToNext() 会将“读取位置”置于结果的第一个条目上，
                 * 并返回光标是否已经过结果集中的最后一个条目 */
                while (c.moveToNext()) {
                    StudentBean sb = new StudentBean(
                            c.getString(c.getColumnIndex(StudentEntry.COLUMN_NAME_NAME)),
                            c.getInt(c.getColumnIndex(StudentEntry.COLUMN_NAME_AGE)),
                            c.getString(c.getColumnIndex(StudentEntry.COLUMN_NAME_GENDER)),
                            c.getFloat(c.getColumnIndex(StudentEntry.COLUMN_NAME_HEIGHT)),
                            c.getFloat(c.getColumnIndex(StudentEntry.COLUMN_NAME_WEIGHT)));
                    data.add(sb);
                }
                c.close();
            }
            return data;
        }
        return null;
    }

    public void deleteStudents(SQLiteDatabase db) {
        db.delete(StudentEntry.TABLE_NAME, null, null);
    }
}
