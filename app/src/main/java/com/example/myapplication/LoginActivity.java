package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.ApiFacebook;
import com.example.myapplication.Model.Strick;
import com.example.myapplication.Login.login;
import com.example.myapplication.Url.Url;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email, password;
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password= findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        signup = findViewById(R.id.btn_signup);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiFacebook user
                        = new ApiFacebook(email.getText().toString(),
                        password.getText().toString());
                // userLogin(user);

                login login =new login();

                if (login.userLogin(user))
                {
                    Toast.makeText(LoginActivity.this, Url.token, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this  , DashboardActivity.class );
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "user id and password wrong", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.btn_signup:
                intent = new Intent(this, SignupActivity.class );
                startActivity(intent);
                break;

        }
    }
}
