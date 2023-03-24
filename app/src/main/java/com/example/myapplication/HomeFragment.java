package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment implements FavInterface {
    FragmentHomeBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View homeFragment = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.bind(homeFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        getNotes();
        binding.floatActionNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), AddNewNotes.class));
            }
        });
    }

    private void getNotes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotesBuilder notesBuilder = NotesBuilder.getInstance(getContext());
                List<NotesEntity> notesList = notesBuilder.notesDao().getAllNotes();
                NotesAdapter notesAdapter = new NotesAdapter(notesList, HomeFragment.this);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.recycler.setLayoutManager(new
                                LinearLayoutManager(getActivity()));
                        binding.recycler.setAdapter(notesAdapter);
                    }
                });
            }
        }).start();
    }

    @Override
    public void isFavorite(NotesEntity notesEntities) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotesBuilder notesBuilder = NotesBuilder.getInstance(getContext());
                notesEntities.setFavorite(true);
                notesBuilder.notesDao().addNotes(notesEntities);
                Log.d("TAG", "run: " + notesEntities);

            }
        }).start();


    }

    @Override
    public void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                NotesBuilder notesBuilder = NotesBuilder.getInstance(getContext());

                List<NotesEntity> notesList = notesBuilder.notesDao().getAllNotes();

                NotesAdapter notesAdapter = new NotesAdapter(notesList, HomeFragment.this);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                        binding.recycler.setAdapter(notesAdapter);
                    }
                });
            }
        }).start();

    }
}