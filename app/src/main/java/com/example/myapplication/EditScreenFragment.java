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

import com.example.myapplication.databinding.FragmentEditScreenBinding;


public class EditScreenFragment extends Fragment {
    FragmentEditScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View editScreenFragment = inflater.inflate(R.layout.fragment_edit_screen, container, false);
        binding = FragmentEditScreenBinding.bind(editScreenFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String name = AppSharedPreference.getName(getActivity());
        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "empty name", Toast.LENGTH_SHORT).show();
        } else {
            binding.nameEt.setText(name);
        }
        String mail = AppSharedPreference.getMail(getActivity());
        if (mail.isEmpty()) {
            Toast.makeText(getActivity(), "empty mail", Toast.LENGTH_SHORT).show();
        } else {
            binding.mailEt.setText(mail);
        }
        String phone = AppSharedPreference.getPhone(getActivity());
        if (phone.isEmpty()) {
            Toast.makeText(getActivity(), "empty phone", Toast.LENGTH_SHORT).show();
        } else {
            binding.phoneEt.setText(phone);
        }
    }
}