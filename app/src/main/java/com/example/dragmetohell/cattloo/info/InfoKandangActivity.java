package com.example.dragmetohell.cattloo.info;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import model.ListTernakKandangAdapter;
import model.ListTernakPeternakAdapter;
import model.TernakKandang;
import model.TernakKandangResponse;
import model.TernakPeternak;
import model.TernakPeternakResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoKandangActivity extends AppCompatActivity {
    TextView txtNamaKandang, txtNamaPemilik, txtLokasi;
    CircleImageView imgFoto;
    String idKandang;
    ArrayList<TernakKandang> mTernakKandang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_kandang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNamaKandang = (TextView) findViewById(R.id.txtNama);
        txtNamaPemilik = (TextView) findViewById(R.id.txtPemilikKandang);
        txtLokasi = (TextView) findViewById(R.id.txtLokasiKandang);
        imgFoto = (CircleImageView) findViewById(R.id.imageViewKandang);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerTernakKandang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            txtNamaKandang.setText(bundle.getString("NamaKandang"));
//            Picasso.with(getApplicationContext()).load(bundle.getString("Foto")).into(imgFoto);
            imgFoto.setImageResource(R.drawable.home);
            txtNamaPemilik.setText(bundle.getString("NamaPemilik"));
            txtLokasi.setText(bundle.getString("Alamat"));
            idKandang = bundle.getString("Id");
        }

        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<TernakKandangResponse> callTernakKandang = apiService.getTernakKandang(Integer.valueOf(idKandang));
        callTernakKandang.enqueue(new Callback<TernakKandangResponse>() {
            @Override
            public void onResponse(Call<TernakKandangResponse> call, Response<TernakKandangResponse> response) {
                ArrayList<TernakKandang> dataTernak = (ArrayList<TernakKandang>) response.body().getTernakKandang();
                mTernakKandang = dataTernak;
                if (mTernakKandang == null){
                    Toast.makeText(getApplicationContext(), "Data tidak ada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data ada", Toast.LENGTH_SHORT).show();

                }


                ListTernakKandangAdapter listTernakKandangAdapter = new ListTernakKandangAdapter(getApplicationContext(), mTernakKandang);
                recyclerView.setAdapter(listTernakKandangAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onFailure(Call<TernakKandangResponse> call, Throwable t) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
