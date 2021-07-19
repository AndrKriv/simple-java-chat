package com.ever.easychat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Registr extends AppCompatActivity {
    LinearLayout reg;
    FirebaseDatabase database;
    DatabaseReference users;
    Button registr, back;
    EditText email, name, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("/users");//-----
        email = findViewById(R.id.reg_mail);
        name = findViewById(R.id.reg_name);
        pass = findViewById(R.id.reg_pass);
//Кнопка возврата к окну входа
        back = findViewById(R.id.back_to_go);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s=new Intent(getApplicationContext(),start.class);
                startActivity(s);
            }
        });
//Конец кнопки
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/users");
        boolean x=false;
        if(x==false)
        myRef.orderByChild("mail").equalTo(email.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    //x=true;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Snackbar.make(activity_main, databaseError.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
        //---
        //Кнопка регистрации
        //---
        registr = findViewById(R.id.registr);
        registr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mail = findViewById(R.id.reg_mail);
                EditText name = findViewById(R.id.reg_name);
                EditText pass = findViewById(R.id.reg_pass);

                if(mail.getText().toString().equals("MAIL iz bazi dannix")){
                    Snackbar.make(reg, "Такая почта уже есть!", Snackbar.LENGTH_SHORT).show();

                }
                else if(name.getText().toString().equals("NAME iz bazi dannix")){
                    Snackbar.make(reg, "Такая почта уже есть!", Snackbar.LENGTH_SHORT).show();

                } else {
                    Snackbar.make(reg, "Регистрация прошла успешно!", Snackbar.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference("/users").push().setValue(
                            new User(mail.getText().toString(),//.getEmail()
                                    name.getText().toString(),
                                    pass.getText().toString()
                            )
                    );
                }
            }

        });
        //---
        //Конец кнопки
        //---

    }
   /* public void check() {
        EditText textField = findViewById(R.id.messageField);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/");
        myRef.orderByChild("userName").equalTo(textField.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             int i=(int)dataSnapshot.getChildrenCount();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    Snackbar.make(activity_main, "Этот Пользователь найден!", Snackbar.LENGTH_SHORT).show();
                    //updateUI(user);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Snackbar.make(activity_main, databaseError.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }*/
}