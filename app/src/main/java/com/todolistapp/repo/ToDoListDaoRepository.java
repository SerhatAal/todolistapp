package com.todolistapp.repo;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.todolistapp.entity.ToDoList;
import com.todolistapp.room.ToDoListRoomDatabase;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ToDoListDaoRepository {
    private final MutableLiveData<List<ToDoList>> toDoList;
    private final ToDoListRoomDatabase database;

    public ToDoListDaoRepository(Application application) {
        toDoList = new MutableLiveData<>();
        database = ToDoListRoomDatabase.getDatabase(application);
    }

    public MutableLiveData<List<ToDoList>> getToDoList() {
        return toDoList;
    }

    public void addToDoList(String to_do_list_context) {
        ToDoList newItem = new ToDoList(0, to_do_list_context);
        database.toDoListDao().addToDoItem(newItem).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void updateToDoList(int to_do_list_id, String to_do_list_context) {
        ToDoList updatedItem = new ToDoList(to_do_list_id, to_do_list_context);
        database.toDoListDao().updateToDoItem(updatedItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void searchToDoList(String searchWord) {
        Disposable disposable = database.toDoListDao().searchToDoItem(searchWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(toDoList::setValue);
    }

    public void delToDoListItem(int to_do_list_id) {
        ToDoList deletedItem = new ToDoList(to_do_list_id, "");
        database.toDoListDao().delToDoItem(deletedItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getItemsToDoList();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getItemsToDoList() {
        Disposable disposable = database.toDoListDao().toDoItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(toDoList::setValue);
    }
}
