package com.todolistapp.ui.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.todolistapp.R;
import com.todolistapp.databinding.CardDesignBinding;
import com.todolistapp.entity.ToDoList;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.CardViewHolder> {
    private final Context mContext;
    private final List<ToDoList> toDoListList;
    private final ToDoListViewModel toDoListViewModel;

    public ToDoListAdapter(Context mContext, List<ToDoList> toDoListList, ToDoListViewModel toDoListViewModel) {
        this.mContext = mContext;
        this.toDoListList = toDoListList;
        this.toDoListViewModel = toDoListViewModel;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private final CardDesignBinding binding;

        public CardViewHolder(CardDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        CardDesignBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_design, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        ToDoList item = toDoListList.get(position);
        CardDesignBinding b = holder.binding;

        b.setToDoListItem(item);

        b.toDoCard.setOnClickListener(view -> {
            ToDoListFragmentDirections.ActionToDoListFragmentToDetailsFragment toDetailsFragment =
                    ToDoListFragmentDirections.actionToDoListFragmentToDetailsFragment(item);
            Navigation.findNavController(view).navigate(toDetailsFragment);
        });

        b.imageViewDeleteIcon.setOnClickListener(view ->
                Snackbar.make(
                        view,
                        item.getContent() + " Are you sure you want to delete?",
                        Snackbar.LENGTH_LONG
                ).setAction("Yes", view1 -> toDoListViewModel.del(item.getId())).show());
    }

    @Override
    public int getItemCount() {
        return toDoListList.size();
    }
}
