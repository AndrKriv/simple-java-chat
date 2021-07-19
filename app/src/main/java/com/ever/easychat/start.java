package com.ever.easychat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class start extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference users;
    Button reg, go_to;
    EditText name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        database = FirebaseDatabase.getInstance();
        users = database.getReference("/users");
        name = findViewById(R.id.log_name);
        pass = findViewById(R.id.log_pass);

        reg = findViewById(R.id.create);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), Registr.class);
                startActivity(s);
            }
        });

        go_to = findViewById(R.id.go);
        go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          
                Intent h = new Intent(getApplicationContext(), first.class);
                startActivity(h);

            }
        });
        //---
       
    }
}
