package com.example.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    SharedPreferences sharedPreferences;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sharedPreferences = getSharedPreferences("com.example.storage.credentials", MODE_PRIVATE);
        login = findViewById(R.id.button);
        login.setOnClickListener(v->{
            if(email.getText().toString().equals("sp@bt") && password.getText().toString().equals("1234")){
                Intent noteApp = new Intent(this, Courses.class);
                startActivity(noteApp);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //save the email to the shared preferences
        sharedPreferences.edit().putString("email", email.getText().toString()).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //get the email from the shared preferences
        email.setText(sharedPreferences.getString("email", ""));
    }

    @Override
    protected void onStop() {
        super.onStop();

        //save the email to the shared preferences
        sharedPreferences.edit().putString("email", email.getText().toString()).apply();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get the email from the shared preferences
        email.setText(sharedPreferences.getString("email", ""));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //remove the email from the shared preferences
        sharedPreferences.edit().remove("email").apply();
    }
}