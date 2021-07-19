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

        //if (FirebaseAuth.getInstance().getCurrentUser() != null) {
        //   Intent s = new Intent(getApplicationContext(), MainActivity.class);
        //   startActivity(s);
        //} else
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
                //signIn(name.getText().toString(), pass.getText().toString());
                Intent h = new Intent(getApplicationContext(), first.class);
                startActivity(h);

            }
        });
        //---
        //код регистрации и проверок
        //---
        /*go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });*/
        //---
        //Конец проверок и регистрации
        //---


    /*private void signIn(final String username, final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){
                        Intent s=new Intent(getApplicationContext(),first.class);
                        startActivity(s);
                       // User login = dataSnapshot.child(username).getValue(User.class);
                        //if(login.getPassword.equals(password)){
                          //  Toast.makeText(start.this,"Success",Toast.LENGTH_SHORT).show();
                        //}
                        //else Toast.makeText(start.this,"Ne success  pass",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(start.this,"Ne success  name",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
    }
}
