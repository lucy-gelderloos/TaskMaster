package com.gelderloos.taskmaster.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.gelderloos.taskmaster.dao.TaskDao;
import com.gelderloos.taskmaster.models.Task;

//updating version deletes database
@TypeConverters({TaskListDatabaseConverters.class})
@Database(entities = {Task.class}, version = 1)
public abstract class TaskListDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
