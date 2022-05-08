package com.todolistapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.todolistapp.entity.ToDoList;

@Database(entities = {ToDoList.class}, version = 1, exportSchema = false)
public abstract class ToDoListRoomDatabase extends RoomDatabase {

    public abstract ToDoListDao toDoListDao();

    public static volatile ToDoListRoomDatabase INSTANCE;

    public static ToDoListRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ToDoListRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDoListRoomDatabase.class, "to_do_list_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
