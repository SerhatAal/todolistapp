package com.todolistapp.ui.addtodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.todolistapp.R;
import com.todolistapp.databinding.FragmentAddItemBinding;

public class AddItemFragment extends Fragment {
    private FragmentAddItemBinding binding;
    private AddItemViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_item, container, false);
        binding.setAddItemFragment(this);
        binding.setAddItemToolbarTitle("Add To Do List Item");
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,
                new AddItemVMF(requireActivity().getApplication()))
                .get(AddItemViewModel.class);
    }

    public void buttonSave(@NonNull String to_do_list_context) {
        if (binding.editToDoList.getText().toString().equals("")) {
            Snackbar.make(binding.buttonSave, "You should add to do list item", Snackbar.LENGTH_SHORT).show();
        } else {
            viewModel.add(to_do_list_context);
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        }

    }
}
