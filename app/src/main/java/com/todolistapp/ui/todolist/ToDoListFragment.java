package com.todolistapp.ui.todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.todolistapp.R;
import com.todolistapp.databinding.FragmentToDoListBinding;


public class ToDoListFragment extends Fragment implements SearchView.OnQueryTextListener {
    private FragmentToDoListBinding binding;
    private ToDoListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_to_do_list, container, false);
        binding.setToDoListFragment(this);
        binding.setToolbarTitle("To Do List");
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);


        viewModel.toDoList.observe(getViewLifecycleOwner(), toDoLists -> {
            ToDoListAdapter adapter = new ToDoListAdapter(requireContext(), toDoLists, viewModel);
            binding.setToDoListAdapter(adapter);
        });

        return binding.getRoot();
    }

    public void fabClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_toDoListFragment_to_addItemFragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(this,
                new ToDoListVMF(requireActivity().getApplication()))
                .get(ToDoListViewModel.class);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.search(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewModel.search(newText);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadToDoList();
    }
}