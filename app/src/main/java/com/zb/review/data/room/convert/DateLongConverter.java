package com.zb.review.data.room.convert;

import androidx.room.TypeConverter;

import java.util.Date;


/**
 * 提供数据模型中database可存储值与不可存储值之间的转换。这样就可以在数据模型中设置Date属性
 * 需要在database初始化时声明转化器，以便room使用转换器存储不支持的数据类型
 */
public class DateLongConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
