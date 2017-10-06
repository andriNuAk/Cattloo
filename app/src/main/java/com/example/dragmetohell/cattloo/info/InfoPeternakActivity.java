package com.example.dragmetohell.cattloo.info;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.DetailHewanActivity;
import com.example.dragmetohell.cattloo.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;
import model.ListTernakPeternakAdapter;
import model.TernakPeternak;
import model.TernakPeternakResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoPeternakActivity extends AppCompatActivity {
    TextView txtNama, txtDeskripsi, txtLokasi;
    CircleImageView imgFoto;
    String idPeternak;
    ArrayList<TernakPeternak> mTernakPeternak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_peternak);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNama = (TextView) findViewById(R.id.txtNama);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskripsiPeternak);
        txtLokasi = (TextView) findViewById(R.id.txtLokasiPeternak);
        imgFoto = (CircleImageView) findViewById(R.id.imageViewPeternak);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerTernakPeternak);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            txtNama.setText(bundle.getString("Nama"));
            Picasso.with(getApplicationContext()).load(bundle.getString("Foto")).into(imgFoto);
            txtDeskripsi.setText(bundle.getString("Deskripsi"));
            txtLokasi.setText(bundle.getString("Lokasi"));
            idPeternak = bundle.getString("Id");
        }

        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<TernakPeternakResponse> callTernakPeternak = apiService.getPeternakUser(Integer.valueOf(idPeternak));
        callTernakPeternak.enqueue(new Callback<TernakPeternakResponse>() {
            @Override
            public void onResponse(Call<TernakPeternakResponse> call, Response<TernakPeternakResponse> response) {
                ArrayList<TernakPeternak> dataTernak = (ArrayList<TernakPeternak>) response.body().getTernakPeternak();
                mTernakPeternak = dataTernak;
                if (mTernakPeternak == null){
                    Toast.makeText(getApplicationContext(), "Data tidak ada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data ada", Toast.LENGTH_SHORT).show();

                }


                ListTernakPeternakAdapter listTernakPeternakAdapter = new ListTernakPeternakAdapter(getApplicationContext(), mTernakPeternak);
                recyclerView.setAdapter(listTernakPeternakAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onFailure(Call<TernakPeternakResponse> call, Throwable t) {

            }
        });
    }

}
