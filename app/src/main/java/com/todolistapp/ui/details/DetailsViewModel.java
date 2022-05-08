package com.todolistapp.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.todolistapp.repo.ToDoListDaoRepository;

public class DetailsViewModel extends AndroidViewModel {
    private final ToDoListDaoRepository toDoListDaoRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        toDoListDaoRepository = new ToDoListDaoRepository(application);
    }

    public void update(int to_do_list_id, String to_do_list_context) {
        toDoListDaoRepository.updateToDoList(to_do_list_id, to_do_list_context);
    }

    public void del(int to_do_list_id) {
        toDoListDaoRepository.delToDoListItem(to_do_list_id);
    }
}
