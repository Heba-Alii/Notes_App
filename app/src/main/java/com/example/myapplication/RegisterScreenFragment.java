package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentRegisterScreenBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RegisterScreenFragment extends Fragment {
    FragmentRegisterScreenBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View registerScreenFragment = inflater.inflate(R.layout.fragment_register_screen, container, false);
        binding = FragmentRegisterScreenBinding.bind(registerScreenFragment);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
    }
}