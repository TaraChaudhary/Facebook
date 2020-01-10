package com.example.myapplication.Login;


import android.util.Log;

import com.example.myapplication.Model.ApiFacebook;
import com.example.myapplication.Model.Strick;
import com.example.myapplication.Url.Url;
import com.example.myapplication.api.Facebook;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class login {

boolean isloggedIn=false;

    public boolean userLogin(ApiFacebook apiUser)
    {
        Facebook fb= Url.getInstance().create(Facebook.class);
        Call<ApiFacebook> userCall=fb.userLogin(apiUser);
        Strick.StrictMode();

        try {
            Response<ApiFacebook>loginResponse=userCall.execute();

            if (loginResponse.isSuccessful())
            {
                isloggedIn=true;

                Url.token +=loginResponse.body().getToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Myex:", e.getMessage());
        }
        return isloggedIn;
    }

    public void userReg()
    {

    }


}
