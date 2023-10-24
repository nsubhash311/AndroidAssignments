package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;

    Button b6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button1);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                    }
                }
        );
        b2 = findViewById(R.id.button2);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,ListItemsActivity.class);
                        startActivityForResult(i, 10);
                    }
                }
        );
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("MainActivity", "User clicked Start Chat");

                        Intent intent = new Intent(MainActivity.this, ChatWindow.class);
                        startActivity(intent);
                    }
                }
        );

        b6 = findViewById(R.id.button6);
        b6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, TestToolbar.class);
                        startActivity(i);
                    }
                }
        );

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10){
            // Toast.makeText(MainActivity.this, "I've come here!!", Toast.LENGTH_SHORT).show();
            Log.i("ListItemsActivity","Returned to MainActivity.onActivityResult");

        }

        if(resultCode == Activity.RESULT_OK){
            Toast.makeText(MainActivity.this, (String)data.getExtras().get("Response"), Toast.LENGTH_SHORT).show();

        }
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
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        Log.i("SaveInstanceState", "Calling onSaveInstanceState function");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.i("RestoreInstanceState", "Calling onRestoreInstanceState function");
        super.onRestoreInstanceState(savedInstanceState);
    }
}