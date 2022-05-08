package com.todolistapp.ui.addtodo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.todolistapp.ui.addtodo.AddItemViewModel;

public class AddItemVMF extends ViewModelProvider.NewInstanceFactory {
    private final Application application;

    public AddItemVMF(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddItemViewModel(application);
    }
}
