package com.xhetriva.basicchattingapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xhetriva.basicchattingapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}