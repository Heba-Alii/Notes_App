package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentSplashScreenBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SplashScreenFragment extends Fragment {
    FragmentSplashScreenBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View splashFragment = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        binding = FragmentSplashScreenBinding.bind(splashFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Thread splash = new Thread() {
            public void run() {
                try {
                    sleep(3 * 1000);
                    if (!AppSharedPreference.isUserLogin(getActivity())) {

                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashScreenFragment_to_registerScreenFragment);
                    } else {
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashScreenFragment_to_homeFragment);
                    }
                } catch (Exception e) {
                }
            }
        };
        splash.start();
       BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
       bottomNavigationView.setVisibility(View.GONE);
    }

}
