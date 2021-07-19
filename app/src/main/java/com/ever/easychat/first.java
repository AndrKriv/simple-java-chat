package com.ever.easychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class first extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference users;
    FloatingActionButton message,music,text;
    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");//-----

        text = findViewById(R.id.forum);
        text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txt = findViewById(R.id.manField);
                    if(txt.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Пустая строка!!!", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        Intent s = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(s);
                    }
                }
            });

        message = findViewById(R.id.chat);
        //---
        //код регистрации и проверок
        //---
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Нажата кнопка message", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        music = findViewById(R.id.music);
        //---
        //код регистрации и проверок
        //---
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Нажата кнопка music", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}