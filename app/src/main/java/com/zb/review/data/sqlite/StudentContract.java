package com.zb.review.data.sqlite;

import android.provider.BaseColumns;

public class StudentContract {
    private StudentContract() {}

    /**
     * 通过实现 BaseColumns 接口，您的内部类
     * 可以继承名为 _ID 的主键字段，CursorAdapter
     * 等某些 Android 类需要内部类拥有该字段。这
     * 并非强制要求，但这样做有助于您的数据库与
     * Android 框架和谐地工作。
     */
    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_WEIGHT = "weight";
    }
}
