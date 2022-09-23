package com.gelderloos.taskmaster.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gelderloos.taskmaster.models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertATask(Task task);

    @Query("SELECT * FROM Task")
    List<Task> findAll();

    @Query("SELECT * FROM Task WHERE id = :id")
    List<Task> findByTaskId(long id);

    @Query("SELECT * FROM Task WHERE state = :state")
    List<Task> findAllByType(Task.StateEnum state);

    @Query("SELECT * FROM Task ORDER BY state ASC")
    List<Task> findAllSortByState();

}
