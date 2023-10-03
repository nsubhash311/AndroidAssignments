package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;


public class ListItemsActivity extends AppCompatActivity {
    Button b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        ImageButton upload = (ImageButton) findViewById(R.id.imageButton);
        Switch Toggle = (Switch) findViewById((R.id.switch1));
        CheckBox ch = (CheckBox) findViewById(R.id.checkBox);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 00);
            }
        });
//        b5 = findViewById(R.id.button5);
//        b5.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent i = new Intent(ListItemsActivity.this,MainActivity.class);
//                        startActivity(i);
//                    }
//                }
//        );

//        Toggle.setOnCheckedChangeListener(
    Toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean c) {
            if (c) {
                String text3 = getResources().getString(R.string.itson);
                Toast.makeText(ListItemsActivity.this, text3, Toast.LENGTH_SHORT).show();
            } else {
                String text4 = getResources().getString(R.string.itsoff);
                Toast.makeText(ListItemsActivity.this, text4, Toast.LENGTH_SHORT).show();
            }
        }
    });


        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean c) {
                if (c) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                    String text5 = getResources().getString(R.string.areusure);
                    builder.setMessage(text5)
                            .setPositiveButton((getResources().getString(R.string.yes)), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
//                                    Toast.makeText(ListItemsActivity.this, "It is ON", Toast.LENGTH_SHORT).show();
                                    Intent resultIntent = new Intent();
                                    String text6 = getResources().getString(R.string.response);

                                    resultIntent.putExtra("Response", text6);
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }
                            }).setNegativeButton((getResources().getString(R.string.no)), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    compoundButton.setChecked(false);
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
    }
    });
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 00) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageButton upload = (ImageButton) findViewById(R.id.imageButton);
            upload.setImageBitmap(image);
        }
    }
    void print (String message){
        Toast t = Toast.makeText(ListItemsActivity.this, message, Toast.LENGTH_SHORT);
        t.show();
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