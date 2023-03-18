package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.nameEt.getText().toString();
                String mail = binding.emailEt.getText().toString();
                String phone = binding.phoneEt.getText().toString();
                if (isDataValid(name, mail, phone)) {
                    writetoSharedPreference(name, mail, phone);

                } else {
                    Toast.makeText(getActivity(), "All Data is required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void writetoSharedPreference(String name, String mail, String phone) {
        AppSharedPreference.writeToSharedPref(getActivity(), name, mail, phone);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_registerScreenFragment_to_homeFragment);

    }

    private boolean isDataValid(String name, String mail, String phone) {

        return !name.isEmpty() && !mail.isEmpty() && !phone.isEmpty();
    }

}