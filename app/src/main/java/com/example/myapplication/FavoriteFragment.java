package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

    FragmentFavoriteBinding binding;
    List<NotesEntity> notesEntityList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View favoriteFragment = inflater.inflate(R.layout.fragment_favorite, container, false);
        binding = FragmentFavoriteBinding.bind(favoriteFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotesBuilder notesBuilder = NotesBuilder.getInstance(getContext());
                notesEntityList = notesBuilder.notesDao().getAllFavoriteItems();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(notesEntityList);
                        binding.favRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                        binding.favRecycler.setAdapter(favoritesAdapter);
                    }
                });
            }
        }).start();
    }

}