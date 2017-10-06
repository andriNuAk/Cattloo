package com.example.dragmetohell.cattloo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.UserResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistActivity extends AppCompatActivity {
    Button btnResgist;
    int kodeUser, hakAKses;
    String username,password,email,noTelp,namaDepan,namaBelakang,foto;
    EditText txtUsername,txtPassword,txtEmail,txtNoTelp,txtNamaDepan,txtNamaBelakang,txtRePassword;
    private static final String TAG = RegistActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnResgist = (Button) findViewById(R.id.btnRegister);
        txtNamaDepan = (EditText) findViewById(R.id.txtNamaDepan);
        txtNamaBelakang = (EditText) findViewById(R.id.txtNamaBelakang);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtNoTelp = (EditText) findViewById(R.id.txtNoTelp);
        txtRePassword = (EditText) findViewById(R.id.txtRePassword);
        final APIInterface apiService = APIClient.getURL().create(APIInterface.class);

        setErrorNull();

        btnResgist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                email = txtEmail.getText().toString();
                noTelp = txtNoTelp.getText().toString();
                namaDepan = txtNamaDepan.getText().toString();
                namaBelakang = txtNamaBelakang.getText().toString();
                System.out.println("Yang ini  : "+username +" : "+password+" : "+email+" : "+noTelp+" : "+namaDepan+" : "+namaBelakang);
                if (txtNamaDepan.getText().toString().equals("")){
                    txtNamaDepan.setError("Nama depan harus diisi");
                    txtNamaDepan.requestFocus();
                } else if (txtUsername.getText().toString().equals("")){
                    txtUsername.setError("Username harus diisi");
                    txtUsername.requestFocus();
                } else if (txtPassword.getText().toString().equals("")){
                    txtPassword.setError("Password harus diisi");
                    txtPassword.requestFocus();
                } else if (txtEmail.getText().toString().equals("")){
                    txtEmail.setError("Email harus diisi");
                    txtEmail.requestFocus();
                } else if (txtNoTelp.getText().toString().equals("")){
                    txtNoTelp.setError("No telepon harus diisi");
                    txtNoTelp.requestFocus();
                } else if (txtRePassword.getText().toString().equals("")){
                    txtRePassword.setError("Harap ulangi password anda");
                    txtRePassword.requestFocus();
                } else if (!txtRePassword.getText().toString().equals(txtPassword.getText().toString())){
                    txtRePassword.setError("Password tidak sama");
                    txtRePassword.requestFocus();
                } else {
                    sendPost(kodeUser, username, password, email, noTelp, namaDepan, namaBelakang, foto, hakAKses);
                }

            }

            private void sendPost(int kodeUser, String username, String password, String email, String noTelp, String namaDepan, String namaBelakang, String foto, int hakAKses) {
                System.out.println("Yang itu  : "+username +" : "+password+" : "+email+" : "+noTelp+" : "+namaDepan+" : "+namaBelakang+" : "+foto+" : "+hakAKses);

                apiService.addUser(kodeUser, username, password, email, noTelp, namaDepan, namaBelakang, foto, 0).enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        Log.i(TAG, "post submitted to API." + response.body().toString());
                        Toast.makeText(getApplicationContext(), "Data berhasil terdaftar", Toast.LENGTH_LONG);
                        System.out.println("Data berhasil dikirim");
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Data gagal dikirim", Toast.LENGTH_LONG);
                        System.out.println("Failed response but inserted to database");
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setErrorNull(){
        txtNamaDepan.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                txtNamaDepan.setError(null);
            }
        });

        txtUsername.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                txtUsername.setError(null);
            }
        });

        txtEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                txtEmail.setError(null);
            }
        });
    }

}
