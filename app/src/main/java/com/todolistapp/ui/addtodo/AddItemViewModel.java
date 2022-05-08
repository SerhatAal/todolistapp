package com.todolistapp.ui.addtodo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.todolistapp.repo.ToDoListDaoRepository;

public class AddItemViewModel extends AndroidViewModel {
    private final ToDoListDaoRepository toDoListDaoRepository;

    public AddItemViewModel(@NonNull Application application) {
        super(application);
        toDoListDaoRepository = new ToDoListDaoRepository(application);
    }

    public void add(String to_do_list_context) {
        toDoListDaoRepository.addToDoList(to_do_list_context);
    }
}
