package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity {
    private EditText Email, Pass;
    private Button AuthB;
    private TextView RegT;
    private API api;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Email = findViewById(R.id.edEmail);
        Pass = findViewById(R.id.edPass);
        AuthB = findViewById(R.id.AuthB);
        RegT = findViewById(R.id.RegT);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su").build();
        api = retrofit.create(API.class);
       /* AuthB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth();
            }
        });*/

    }
    public void Auth(){
        AuthParam authParam = new AuthParam();
        authParam.setEmail(Email.getText().toString());
        authParam.setPass(Pass.getText().toString());
        Call<AuthParam> call = api.doAuth(authParam);
        call.enqueue(new Callback<AuthParam>() {
            @Override
            public void onResponse(Call<AuthParam> call, Response<AuthParam> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthParam> call, Throwable t) {

            }
        });
    }

    public void onClickAuth(View v){
        Auth();
    }


}
