package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.etUsername.addTextChangedListener(new validateTextWatcher(binding.etUsername));
        binding.etEmail.addTextChangedListener(new validateTextWatcher(binding.etEmail));
        binding.etPassword.addTextChangedListener(new validateTextWatcher(binding.etPassword));
        binding.etPhone.addTextChangedListener(new validateTextWatcher(binding.etPhone));
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateUsername() && validateEmail() && validatePassword() && validatePhoneNumber()){
                    Toast.makeText(MainActivity.this, "Validation successful! Proceeding with login.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Validation failed! Please check your inputs.", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    private boolean validateUsername() {
        String username = binding.etUsername.getText().toString().trim();

        // Check if the username field is empty
        if (username.isEmpty()) {
            binding.inputLayoutUsername.setError("Username is required");
            binding.etUsername.requestFocus();
            return false;
        }

        // Check if the username contains only letters (no numbers or special characters)
        if (!username.matches("[a-zA-Z]+")) {
            binding.inputLayoutUsername.setError("Username can only contain letters");
            binding.etUsername.requestFocus();
            return false;
        }

        // If everything is okay, disable the error and return true
        binding.inputLayoutUsername.setErrorEnabled(false);
        return true;
    }


    private boolean validateEmail(){
        String email = binding.etEmail.getText().toString().trim();
        if(email.isEmpty()){
            binding.inputLayoutEmail.setError("Email is required");
            binding.etEmail.requestFocus();
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.inputLayoutEmail.setError("please enter valid email address");
            binding.etEmail.requestFocus();
            return false;
        }
        binding.inputLayoutEmail.setErrorEnabled(false);
        return true;
    }

    private boolean validatePassword() {
        String password = binding.etPassword.getText().toString().trim();

        if (password.isEmpty()) {
            binding.inputLayoutPassword.setError("Password is required");
            binding.etPassword.requestFocus();
            return false;
        } else if (password.length() < 6) {
            binding.inputLayoutPassword.setError("Password must be at least 6 characters long");
            binding.etPassword.requestFocus();
            return false;
        } else if (!password.matches(".*[A-Z].*")) {
            binding.inputLayoutPassword.setError("Password must contain at least one uppercase letter");
            binding.etPassword.requestFocus();
            return false;
        } else if (!password.matches(".*[a-z].*")) {
            binding.inputLayoutPassword.setError("Password must contain at least one lowercase letter");
            binding.etPassword.requestFocus();
            return false;
        } else if (!password.matches(".*[0-9].*")) {
            binding.inputLayoutPassword.setError("Password must contain at least one digit");
            binding.etPassword.requestFocus();
            return false;
        } else if (!password.matches(".*[@#$%^&+=!].*")) {
            binding.inputLayoutPassword.setError("Password must contain at least one special character (@#$%^&+=!)");
            binding.etPassword.requestFocus();
            return false;
        } else {
            binding.inputLayoutPassword.setError(null); // Clear any existing error
            return true;
        }
    }
    private boolean validatePhoneNumber() {
        String phoneNumber = binding.etPhone.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            binding.inputLayoutPhone.setError("Phone number is required");
            binding.etPhone.requestFocus();
            return false;
        } else if (!phoneNumber.matches("\\d{10}")) {
            binding.inputLayoutPhone.setError("Phone number must be 10 digits");
            binding.etPhone.requestFocus();
            return false;
        } else {
            binding.inputLayoutPhone.setError(null); // Clear any existing error
            return true;
        }
    }
 private class validateTextWatcher implements TextWatcher {
     private View view;
     private validateTextWatcher(View view) {
         this.view = view;
     }
     public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
     }
     public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
     }
     public void afterTextChanged(Editable editable) {
       if(view.getId() == R.id.etUsername){
            validateUsername();
       } else if (view.getId() == R.id.etEmail) {
           validateEmail();
       } else if (view.getId() == R.id.etPassword) {
           validatePassword();
       } else if (view.getId() == R.id.etPhone) {
           validatePhoneNumber();
       }
     }
 }
}