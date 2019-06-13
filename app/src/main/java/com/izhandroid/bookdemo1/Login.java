package com.izhandroid.bookdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Login extends AppCompatActivity {
    private static final String LOGIN_KEY = "LOGIN_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        if (pref.getBoolean(LOGIN_KEY, false)) {
            //has login
            startActivity(new Intent(this, MainActivity.class));
            //must finish this activity (the login activity will not be shown when click back in main activity)
            finish();
        } else {
            //mark login
            pref.edit().putBoolean(LOGIN_KEY, true).apply();

           final EditText emil = findViewById(R.id.usrname);
          final  EditText pwdf = findViewById(R.id.pwd);
            Button btn = findViewById(R.id.btnlogin);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(emil.getText().toString().contains("user@test.com")&&pwdf.getText().toString().contains("12345")){
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }else {
                        Toast.makeText(Login.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                        emil.setError("Use Email - test@user.com");
                        pwdf.setError("Use password - 12345");

                    }
                }
            });



            //do something
        }
    }


}
