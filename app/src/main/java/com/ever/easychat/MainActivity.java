package com.ever.easychat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.*;
import android.text.format.DateFormat;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth, auth;
    private static final String TAG = "MainActivity";
    private static int SIGN_IN_CODE = 1;
    private RelativeLayout activity_main;
    private FloatingActionButton sendBtn,menuBtn;

    //-------------------------------
    //GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
    //if (acct != null) {
    //  String personName = acct.getDisplayName();
    //  String personGivenName = acct.getGivenName();
    //  String personFamilyName = acct.getFamilyName();
    //  String personEmail = acct.getEmail();
    //  String personId = acct.getId();
    //  Uri personPhoto = acct.getPhotoUrl();
    //}
    //-------------------------------
    //private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        //if (user != null) {
           // EditText textField = findViewById(R.id.log_name);
           // textField.setText(getString(R.string.name_hint, user.getDisplayName()));
            //mDetailTextView.setText(getString(R.string.U, user.getUid()));

            //findViewById(R.id.go).setVisibility(View.GONE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        //} //else {
            //mStatusTextView.setText(R.string.signed_out);
            //mDetailTextView.setText(null);

            //findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
           // findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        //}
    //}
    //-----------------------------------------------------------------------------------------------------
    //1. Вид сообщения
    public FirebaseListAdapter <Message> adapter = new FirebaseListAdapter<Message>(this, Message.class,R.layout.item,
            FirebaseDatabase.getInstance().getReference("/message")) {
        @Override
        protected void populateView(View v, Message model, int position) {
            TextView mess_user, mess_time;

            BubbleTextView mess_text;
            mess_user=v.findViewById(R.id.message_user);
            mess_time=v.findViewById(R.id.message_time);
            mess_text=v.findViewById(R.id.message_text);
            mess_user.setText(model.getUserName());
            mess_text.setText(model.getTextMessage());
            mess_time.setText(DateFormat.format("HH:mm:ss / dd-MM-yyyy", model.getMessageTime()));
        }
    };
    //-----------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2. Функция открытия главного окна activity_main
        setContentView(R.layout.activity_main);
        //--------------------------------------------------------------------------
        activity_main = findViewById(R.id.activity_main);
        //----------------------------------------------------------------
        //3. Автоматически настроенная регистрация/вход пользователя
        // 1)startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_CODE);
        //Intent i=new Intent(MainActivity.this,start.class);
        if(FirebaseAuth.getInstance().getCurrentUser()==null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_CODE);
            //startActivity(i);
        } else {
            Snackbar.make(activity_main, "Авторизация прошла успешно!", Snackbar.LENGTH_SHORT).show();
            displayAllMessages();
        }
        //----------------------------------------------------------------
        sendBtn = findViewById(R.id.btnSend);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textField = findViewById(R.id.messageField);
                if(textField.getText().toString().equals("")){
                    Snackbar.make(activity_main, "Пустое сообщение!", Snackbar.LENGTH_SHORT).show();
                }
                else {
                FirebaseDatabase.getInstance().getReference("/message").push().setValue(
                        new Message(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),//.getEmail()
                                textField.getText().toString()
                        )
                );
                textField.setText("");
                }
            }
        });
        //пользовать не автооризован
        menuBtn = findViewById(R.id.menu);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
//метод проверки имени при регистрации на уникальность (мб поменять мэйнактивити)
     /*   Button btm=findViewById(R.id.btnSend);
        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText txt = findViewById(R.id.log_name);
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                            if(txt.getText().toString().equals(childDataSnapshot))
                                Toast.makeText(MainActivity.this,"Такой пользователь есть",Toast.LENGTH_SHORT).show();
                            else Toast.makeText(MainActivity.this,"Вход/Регистрация успешна",Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });*/
    }
    //-----------------------------------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN_CODE){
            if(resultCode==RESULT_OK){
                Snackbar.make(activity_main,"Авторизация прошла успешно!",Snackbar.LENGTH_SHORT).show();
            }
            else {
                Snackbar.make(activity_main,"Авторизация не удалась :(",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------
    public void displayAllMessages(){
        ListView listOfMess = findViewById(R.id.list_of_messages);
        listOfMess.setAdapter(adapter);
    }
    //-----------------------------------------------------------------------------------------------------
    public void check() {
        EditText textField = findViewById(R.id.messageField);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/");

        myRef.orderByChild("message/userName").equalTo(textField.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    Snackbar.make(activity_main, "Этот Пользователь найден! User:", Snackbar.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
               // Snackbar.make(activity_main, databaseError.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

//Toast.makeText(MainActivity.this,"Success: "+ finalTextView.getText().toString(),Toast.LENGTH_SHORT);
//        if(value[0].equals(textField.getText().toString()))
//            Snackbar.make(activity_main, "Этот Пользователь найден!", Snackbar.LENGTH_SHORT).show();
//        else
//            Snackbar.make(activity_main, "Этот PPPользователь ne найден!", Snackbar.LENGTH_SHORT).show();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser users = mAuth.getCurrentUser();
        if (!textField.getText().toString().equals(users.getDisplayName()))
            Snackbar.make(activity_main, "Пользователь не найден!", Snackbar.LENGTH_SHORT).show();
        else {
            Snackbar.make(activity_main, "Это я", Snackbar.LENGTH_SHORT).show();
        }
        }

     }