package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    private EditText chatEditText;
    private Button sendButton;
    private ListView chatListView;
    private ArrayList<String> chatMessages;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        // Initialize UI elements
        chatEditText = findViewById(R.id.chatEditText);
        sendButton = findViewById(R.id.sendButton);
        chatListView = findViewById(R.id.chatListView);

        // Initialize chatMessages ArrayList and ChatAdapter
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(this);
        chatListView.setAdapter(chatAdapter);

//        ChatAdapter messageAdapter =new ChatAdapter( this );
//        chatListView.setAdapter (messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from EditText
                String message = chatEditText.getText().toString().trim();

                // Add message to the chatMessages ArrayList
                if (!message.isEmpty()) {
                    chatMessages.add(message);
                    chatAdapter.notifyDataSetChanged(); // Update the ListView
                    chatEditText.setText(""); // Clear EditText
                }
            }
        });
    }

    // Inner class for the ChatAdapter
    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return chatMessages.size();
        }

        @Override
        public String getItem(int position) {
            return chatMessages.get(position);
        }

        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result;

            if (position % 2 == 0) {
                // For even positions (incoming messages)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                // For odd positions (outgoing messages)
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            // Get the TextView which holds the message
            TextView messageTextView = result.findViewById(R.id.message_text);
            messageTextView.setText(getItem(position)); // Set the chat message text

            return result;
        }
    }
}
