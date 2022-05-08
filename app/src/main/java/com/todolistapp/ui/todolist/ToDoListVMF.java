package com.todolistapp.ui.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ToDoListVMF extends ViewModelProvider.NewInstanceFactory {
    private final Application application;

    public ToDoListVMF(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ToDoListViewModel(application);
    }
}
