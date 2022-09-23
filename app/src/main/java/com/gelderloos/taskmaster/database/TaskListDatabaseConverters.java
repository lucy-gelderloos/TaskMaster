package com.gelderloos.taskmaster.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class TaskListDatabaseConverters {
    @TypeConverter
    public static Date dateFromTimeStamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long timeStampFromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
