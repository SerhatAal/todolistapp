package com.todolistapp.ui.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.todolistapp.entity.ToDoList;
import com.todolistapp.repo.ToDoListDaoRepository;

import java.util.List;

public class ToDoListViewModel extends AndroidViewModel {

    private final ToDoListDaoRepository toDoListDaoRepository;
    public MutableLiveData<List<ToDoList>> toDoList = new MutableLiveData<>();

    public ToDoListViewModel(@NonNull Application application) {
        super(application);
        toDoListDaoRepository = new ToDoListDaoRepository(application);
        loadToDoList();
        toDoList = toDoListDaoRepository.getToDoList();
    }

    public void search(String searchWord) {
        toDoListDaoRepository.searchToDoList(searchWord);
    }

    public void del(int to_do_list_id) {
        toDoListDaoRepository.delToDoListItem(to_do_list_id);
    }

    public void loadToDoList() {
        toDoListDaoRepository.getItemsToDoList();
    }

}
