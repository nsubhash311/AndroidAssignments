package com.example.androidassignments;

import static com.example.androidassignments.ChatDatabaseHelper.KEY_MESSAGE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.TextView;
import java.util.ArrayList;

import android.view.ViewGroup;

public class ChatWindow extends AppCompatActivity {


    private ChatDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    private EditText EditText;

    private ArrayList<String> chat_messages;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        dbHelper = new ChatDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        android.widget.ListView listView = findViewById(R.id.chatListView);
        EditText = findViewById(R.id.chatEditText);
        Button sendButton = findViewById(R.id.sendButton);
        chat_messages= new ArrayList<>();

        ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        String[] projection = {KEY_MESSAGE};
        String selection = null;
        String[] selection_Args = null;
        Cursor cursor = database.query(ChatDatabaseHelper.TABLE_NAME, projection, selection, selection_Args, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast() ){
            int ind =  cursor.getColumnIndex(KEY_MESSAGE);
            String msg = cursor.getString(ind);
            chat_messages.add(msg);
            Log.i("ChatWindow", "SQL MESSAGE"+ msg);
            cursor.moveToNext();
        }

        Log.i("ChatWindow" , "Cursor's column count = " + cursor.getColumnCount());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            Log.i("ChatWindow", "Column " + i + " name: " + columnName);
        }
        cursor.close();

        sendButton.setOnClickListener(v->{
            String message=EditText.getText().toString().trim();

            if (!message.isEmpty()) {
                chat_messages.add(message);
                EditText.setText("");
                messageAdapter.notifyDataSetChanged();
                ContentValues values = new ContentValues();
                values.put(ChatDatabaseHelper.KEY_MESSAGE, message);

                // You don't need to specify the ID since it's auto-incremented
                long newRowId = database.insert(ChatDatabaseHelper.TABLE_NAME, null, values);

            }
        });
    }


    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(@NonNull Context ctx) {
            super(ctx,0);
        }

        public int getCount() {
            return chat_messages.size();
        }

        public String getItem(int position){
            return chat_messages.get(position);
        }


        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;

            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy(); // Call the superclass onDestroy method first

        // Close the database if it was opened in onCreate
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

}