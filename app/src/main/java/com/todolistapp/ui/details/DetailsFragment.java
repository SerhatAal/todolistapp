package com.todolistapp.ui.details;

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

import com.google.android.material.snackbar.Snackbar;
import com.todolistapp.R;
import com.todolistapp.databinding.FragmentDetailsBinding;
import com.todolistapp.entity.ToDoList;

public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;
    private DetailsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        binding.setDetailsFragment(this);
        binding.setDetailsToolbarTitle("Update");

        DetailsFragmentArgs bundle = DetailsFragmentArgs.fromBundle(getArguments());
        ToDoList toDoListItem = bundle.getToDoItem();
        binding.setToDoListItem(toDoListItem);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,
                new DetailsVMF(requireActivity().getApplication()))
                .get(DetailsViewModel.class);
    }

    public void updateButton(int to_do_list_id, String to_do_list_context) {
        if (binding.editToDoList.getText().toString().equals("")) {
            Snackbar.make(binding.buttonUpdate, "Item deleted", Snackbar.LENGTH_SHORT).show();
            viewModel.del(to_do_list_id);
        } else {
            viewModel.update(to_do_list_id, to_do_list_context);
        }
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}
