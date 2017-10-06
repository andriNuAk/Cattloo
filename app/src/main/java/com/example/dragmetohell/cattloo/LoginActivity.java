package com.example.dragmetohell.cattloo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import helper.SessionHelper;
import model.User;
import model.UserResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    TextView textRegister;
    Button btnLogin;
    EditText txtUsername, txtPassword;
    String username, password, inUsername, inPassword,  inNamaLengkap, inEmail, inNoTelp, inNamaDepan, inNamaBelakang;
    int inIdUser;
    ArrayList<User> mAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (EditText) findViewById(R.id.usernameText);
        txtPassword = (EditText) findViewById(R.id.passwordText);

        toRegister();
        login();
    }

    //Link ke register
    public void toRegister(){
        textRegister = (TextView) findViewById(R.id.textRegister);

        textRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
                startActivity(intent);
            }
        });
    }


    //Login
    public void login(){
        btnLogin = (Button) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                retrofit2.Call<UserResponse> call = apiService.login(username, password);
                System.out.println("Username = "+username+" , password = "+password);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        ArrayList<User> akun = (ArrayList<User>) response.body().getUser();
                        if (akun == null){
                            //statusText.setText("Username dan password salah !!!");
                            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                            alertDialog.setTitle("Terdapat Kesalahan!!!");
                            alertDialog.setMessage("Username atau password salah");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } else {
                            mAkun = akun;
                            for (int i = 0; i < mAkun.size(); i++) {
                                inIdUser = mAkun.get(i).getIdUser();
                                inUsername = mAkun.get(i).getUsername();
                                inPassword = mAkun.get(i).getPassword();
                                inNamaLengkap = mAkun.get(i).getNamaDepan()+" "+mAkun.get(i).getNamaBelakang();
                                inNamaDepan = mAkun.get(i).getNamaDepan();
                                inNamaBelakang = mAkun.get(i).getNamaBelakang();
                                inEmail = mAkun.get(i).getEmail();
                                inNoTelp = mAkun.get(i).getNoTelepon();
                            }

                            System.out.println("Id user = "+inIdUser+" , Nama = "+inNamaLengkap+" , Username = "+inUsername+" , Password = "+inPassword+" , Email = "+inEmail+" , No Telepon = "+inNoTelp);
                            SessionHelper dbHelper =  new SessionHelper(LoginActivity.this);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            dbHelper.createTabelAkun(db);
                            dbHelper.insertAkun(db, inIdUser, inUsername, inPassword, inEmail, inNoTelp, inNamaDepan, inNamaBelakang);
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            //intent.putExtra("NIK", NIK);
                            startActivity(intent);

                            //statusText.setText("Username dan password benar, username = "+username+" password = "+password+" No keluarga = "+kodeKeluarga+" NIK = "+NIK);

                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
