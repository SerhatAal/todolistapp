package com.todolistapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.todolistapp.entity.ToDoList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ToDoListDao {
    @Query("SELECT * FROM to_do_list_table")
    Single<List<ToDoList>> toDoItems();

    @Insert
    Completable addToDoItem(ToDoList toDoList);

    @Update
    Completable updateToDoItem(ToDoList toDoList);

    @Delete
    Completable delToDoItem(ToDoList toDoList);

    @Query("SELECT * FROM to_do_list_table WHERE to_do_list_context like '%' || :searchWord || '%'")
    Single<List<ToDoList>> searchToDoItem(String searchWord);
}
