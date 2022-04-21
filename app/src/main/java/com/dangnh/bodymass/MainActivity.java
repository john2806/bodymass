package com.dangnh.bodymass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import java.text.DecimalFormat;
import java.text.ParseException;


public class MainActivity extends AppCompatActivity {
    private com.dangnh.bodymass.databinding.ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.dangnh.bodymass.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupViews();
        setupEvents();
    }

    private void setupViews() {
        binding.etBMI.setInputType(InputType.TYPE_NULL);
        binding.etDiagnostic.setInputType(InputType.TYPE_NULL);
    }

    @SuppressLint("SetTextI18n")
    private void setupEvents() {
        binding.btnCalculate.setOnClickListener(v -> {
            long weight = 0;
            long height = 0;
            long bmi;
            if (!binding.etWeight.getText().toString().isEmpty()) {
                weight = Long.parseLong(binding.etWeight.getText().toString());
            }
            if (!binding.etHeight.getText().toString().isEmpty()) {
                height = Long.parseLong(binding.etHeight.getText().toString());
            }
            bmi = weight / (height * height);
            DecimalFormat df = new DecimalFormat("0.000");
            String formate = df.format(bmi);
            binding.etBMI.setText(formate);
            binding.etDiagnostic.setText(diagnostic(bmi));
        });
    }

    private String diagnostic(double bmi) {
        if (bmi < 18) {
            return "Người gầy";
        } else if (18 <= bmi && bmi <= 24.9) {
            return "Người bình thường";
        } else if (25 <= bmi && bmi <= 29.9) {
            return "Người béo phì độ I";
        } else if (30 <= bmi && bmi <= 34.9) {
            return "Người béo phì độ II";
        } else if (bmi > 35) {
            return "Người béo phì độ III";
        }
        return "Không chuẩn đoán được";
    }
}