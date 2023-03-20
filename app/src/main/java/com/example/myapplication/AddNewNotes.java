package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityAddNewNotesBinding;

public class AddNewNotes extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityAddNewNotesBinding binding;
    String[] courses = {"Android", "C++", "iOS", "Java", "Kotlin", "Java Script"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewNotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Spinner spinner = binding.programmingLangSpinner;
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, courses);
        arrayAdapter.setDropDownViewResource(com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = binding.programmingLangSpinner.getSelectedItem().toString();
                String userDesc = binding.descEt.getText().toString();
                NotesEntity notesEntity = new NotesEntity(courseName, userDesc, false);
                if (desc(userDesc)) {
                    saveData(notesEntity);
                    Toast.makeText(AddNewNotes.this, "Your Data is added success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddNewNotes.this, "Please Enter Yoyr Description", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void saveData(NotesEntity notes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotesBuilder notesBuilder = NotesBuilder.getInstance(AddNewNotes.this);
                notesBuilder.notesDao().addNotes(notes);
            }
        }).start();
    }

    private boolean desc(String description) {
        return !description.isEmpty();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(AddNewNotes.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        Toast.makeText(this, courses[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}