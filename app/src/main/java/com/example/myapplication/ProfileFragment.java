package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View profileFragment = inflater.inflate(R.layout.fragment_profile, container, false);
        binding = FragmentProfileBinding.bind(profileFragment);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String name = AppSharedPreference.getName(getActivity());
        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "empty name", Toast.LENGTH_SHORT).show();
        } else {
            binding.nameTv.setText(name);
        }
        String mail = AppSharedPreference.getMail(getActivity());
        if (mail.isEmpty()) {
            Toast.makeText(getActivity(), "empty mail", Toast.LENGTH_SHORT).show();
        } else {
            binding.emailTv.setText(mail);
        }
        String phone = AppSharedPreference.getPhone(getActivity());
        if (phone.isEmpty()) {
            Toast.makeText(getActivity(), "empty phone", Toast.LENGTH_SHORT).show();
        } else {
            binding.phoneTv.setText(phone);
        }
        binding.removeAcccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Account")
                        .setMessage("Are You sure Delete Your Account ?!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AppSharedPreference.deleteDataFromSharedPref(getActivity());
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileFragment_to_registerScreenFragment);
                            }
                        })
                        .setNegativeButton("No", null)
                        .setIcon(R.drawable.baseline_add_alert_24).show();

            }
        });
        binding.editUserDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileFragment_to_editScreenFragment);
            }
        });
    }
}