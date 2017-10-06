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
import model.ListTernakAsistenAdapter;
import model.TernakAsisten;
import model.TernakAsistenResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAsistenActivity extends AppCompatActivity {
    TextView txtNama, txtNamaLengkap, txtLokasi;
    CircleImageView imgFoto;
    String idAsisten;
    ArrayList<TernakAsisten> mTernakAsisten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_asisten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNama = (TextView) findViewById(R.id.txtNama);
        txtNamaLengkap = (TextView) findViewById(R.id.txtNamaLengkap);
        txtLokasi = (TextView) findViewById(R.id.txtLokasiAsisten);
        imgFoto = (CircleImageView) findViewById(R.id.imageViewAsisten);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerTernakAsisten);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            txtNama.setText(bundle.getString("NamaDepan"));
            Picasso.with(getApplicationContext()).load(bundle.getString("Foto")).into(imgFoto);
//            imgFoto.setImageResource(R.drawable.home);
            txtNamaLengkap.setText(bundle.getString("NamaDepan")+" "+bundle.getString("NamaBelakang"));
            txtLokasi.setText(bundle.getString("Alamat"));
            idAsisten = bundle.getString("Id");
        }

        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<TernakAsistenResponse> callTernakAsisten = apiService.getTernakAsisten(Integer.valueOf(idAsisten));
        callTernakAsisten.enqueue(new Callback<TernakAsistenResponse>() {
            @Override
            public void onResponse(Call<TernakAsistenResponse> call, Response<TernakAsistenResponse> response) {
                ArrayList<TernakAsisten> dataTernak = (ArrayList<TernakAsisten>) response.body().getTernakAsisten();
                mTernakAsisten = dataTernak;
                if (mTernakAsisten == null){
                    Toast.makeText(getApplicationContext(), "Data tidak ada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data ada", Toast.LENGTH_SHORT).show();

                }


                ListTernakAsistenAdapter listTernakAsistenAdapter = new ListTernakAsistenAdapter(getApplicationContext(), mTernakAsisten);
                recyclerView.setAdapter(listTernakAsistenAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onFailure(Call<TernakAsistenResponse> call, Throwable t) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
