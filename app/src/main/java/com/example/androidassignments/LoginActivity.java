package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;

public class LoginActivity extends AppCompatActivity {
    Button b3;
    String Tag ="LoginActivityTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b3 = findViewById(R.id.button);
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
        String defaultEmail = sharedPreferences.getString("DefaultEmail","email@domain.com");
        EditText text = findViewById(R.id.editTextText);
        text.setText(defaultEmail);
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String eMail = ((EditText) findViewById(R.id.editTextText)).getText().toString();
                        String password = ((EditText) findViewById(R.id.editTextText3)).getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("DefaultEmail", eMail);
                        editor.apply();
                        if ((!eMail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(eMail).matches()) && (!password.isEmpty())) {

//                            Log.d(Tag,"shhjh");
//                            if(!password.isEmpty()){
//                                Log.d(Tag,password);
                            String text = getResources().getString(R.string.loginsuccessful);
                            Toast.makeText(LoginActivity.this, text, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            String text2 = getResources().getString(R.string.notvalid);
                            Toast.makeText(LoginActivity.this, text2, Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }
    protected  void onResume() {
        super.onResume();
        Log.i("Resume", "Calling OnResume function");
    }

    protected  void onStart() {
        super.onStart();
        Log.i("Start", "Calling OnStart function");

    }
    protected  void onPause() {
        super.onPause();
        Log.i("Pause", "Calling OnPause function");

    }
    protected  void onStop() {
        super.onStop();
        Log.i("Stop", "Calling OnStop function");

    }protected  void onDestroy() {
        super.onDestroy();
        Log.i("Destroy", "Calling OnDestroy function");
    }
    protected  void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("SaveInstanceState()", "Calling OnSaveInstanceState function");
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("RestoreInstanceState", "Calling RestoreInstanceState function");
    }
}