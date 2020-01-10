package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.ApiFacebook;
import com.example.myapplication.Url.Url;
import com.example.myapplication.api.Facebook;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    private LinearLayout Linear1,linearName,linearDob, linearGender;
    private LinearLayout linearPhone,linearEmail,linearPassword;
    private TextView toolbarhead;
    private RelativeLayout Finish;
    EditText firstname,lastname,phone,email1,password;
    DatePicker date;
    String gender;
    RadioGroup radioGroup;
    private Button  btnStart,btnName,btnDob,btnGender,btnPhone,btnEmail,btnPassword,btnBack,register;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnStart = findViewById(R.id.start);
         btnName = findViewById(R.id.btnTakeName);
         btnDob = findViewById(R.id.btnTakeDob);
         btnGender = findViewById(R.id.btnTakeGender);
         btnPhone = findViewById(R.id.btnTakePhone);
         btnEmail = findViewById(R.id.btnTakeEmail);
         btnPassword = findViewById(R.id.btnTakePassword);
         btnBack = findViewById(R.id.back);
         register=findViewById(R.id.btnSignup);

         firstname=findViewById(R.id.fname);
         lastname=findViewById(R.id.lname);
         date=findViewById(R.id.datePicker);
         radioGroup=findViewById(R.id.rgGender);
         phone=findViewById(R.id.etPhone);
         email1=findViewById(R.id.etEmail);
         password=findViewById(R.id.password);

       linearName = findViewById(R.id.layoutForFullName);
        linearDob = findViewById(R.id.layoutForDob);
        linearGender = findViewById(R.id.layoutForGender);
        linearPhone = findViewById(R.id.layoutForPhone);
        Linear1 = findViewById(R.id.linear1);
        toolbarhead = findViewById(R.id.toolbarhead);
        linearEmail = findViewById(R.id.layoutForEmail);
        linearPassword = findViewById(R.id.layoutForPassword);
        Finish = findViewById(R.id.layoutForSignUp);


        btnStart.setOnClickListener(this);
        btnName.setOnClickListener(this);
        btnDob.setOnClickListener(this);
        btnGender.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnPassword.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signUp();
            }
        });

    }
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.rbMale){
            gender = "Male";
        }

        if(checkedId == R.id.rbFemale){
            gender = "Female";
        }

        if(checkedId == R.id.rbOther){
            gender = "Other";
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start:
                linearName.setVisibility(View.VISIBLE);
                Linear1.setVisibility(View.GONE);
                toolbarhead.setText("Name");
                break;

            case R.id.btnTakeName:
                linearDob.setVisibility(View.VISIBLE);
                linearName.setVisibility(View.GONE);
                toolbarhead.setText("Birthday");

                break;

            case R.id.btnTakeDob:
                linearDob.setVisibility(View.GONE);
                linearGender.setVisibility(View.VISIBLE);
                toolbarhead.setText("Gender");

                break;
            case R.id.btnTakeGender:
                linearGender.setVisibility(View.GONE);
                linearPhone.setVisibility(View.VISIBLE);
                toolbarhead.setText("Mobile Number");
                break;
            case R.id.btnTakePhone:
                linearPhone.setVisibility(View.GONE);
                linearEmail.setVisibility(View.VISIBLE);
                toolbarhead.setText("Email");
                break;
            case R.id.btnTakeEmail:
                linearEmail.setVisibility(View.GONE);
                linearPassword.setVisibility(View.VISIBLE);
                toolbarhead.setText("Password");
                break;
            case R.id.btnTakePassword:
                linearPassword.setVisibility(View.GONE);
                Finish.setVisibility(View.VISIBLE);
                toolbarhead.setText("Terms & Privacy");
                break;

            case R.id.back:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void signUp() {



        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String email = email1.getText().toString();
        String password1 = password.getText().toString();
        String mobile = phone.getText().toString();
      //  String datepicker = date.getMaxDate();


        ApiFacebook user = new ApiFacebook(fname, lname, email,gender, password1, mobile);

        Facebook userapi = Url.getInstance().create(Facebook.class);
        Call<Void> signUpCall = userapi.registerUser(user);

        signUpCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }


        });

    }

}
