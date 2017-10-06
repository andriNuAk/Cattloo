package com.example.dragmetohell.cattloo.history;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dragmetohell.cattloo.HomeActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.ternakku.DetailTernakkuActivity;

import java.util.ArrayList;

import model.ListHistoryAdapter;
import model.ListTernakkuAdapter;
import model.ModelHistory;
import model.ModelTernak;
import model.Visual;
import model.VisualResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<ModelHistory> mHistory;
    private String kategori;
    ListView lvHistory;
    ListHistoryAdapter historyAdapter;
    ArrayList<Visual> mVisual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initModelHistory();
        initView();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initView(){
//        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        RecyclerView.Adapter adapter = new HomeAdapter(getApplicationContext(), mHewan);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setOnClickListener(new AdapterView.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String idTernak = bundle.getString("IdTernak");
            APIInterface apiService = APIClient.getURL().create(APIInterface.class);
            retrofit2.Call<VisualResponse> call = apiService.getVisual(Integer.valueOf(idTernak));
            call.enqueue(new Callback<VisualResponse>() {
                @Override
                public void onResponse(Call<VisualResponse> call, Response<VisualResponse> response) {
                    ArrayList<Visual> visuals = (ArrayList<Visual>) response.body().getVisual();
                    mVisual = visuals;
                    lvHistory = (ListView) findViewById(R.id.list_view_history);
                    historyAdapter = new ListHistoryAdapter(getApplicationContext(), mVisual);
                    lvHistory.setAdapter(historyAdapter);
                    lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new  Intent(getApplicationContext(), DetailHistoryActivity.class);
                            String kodeTernak = mVisual.get(i).getIdTernak().toString();
                            String tanggalVisual = mVisual.get(i).getTglPeriksa();
                            String caraTernak = mVisual.get(i).getCaraTernak();
                            String img1 = mVisual.get(i).getFoto1();
                            String img2 = mVisual.get(i).getFoto2();
                            String img3 = mVisual.get(i).getFoto3();
                            String videoURL = mVisual.get(i).getUrlVideo();
                            String jenisTernak = mVisual.get(i).getJenisHewan();
                            String spesies = mVisual.get(i).getSpesies();
                            String berat = mVisual.get(i).getBobot().toString();
                            String panjang = mVisual.get(i).getPanjang().toString();
                            String tinggi = mVisual.get(i).getTinggi().toString();



                            intent.putExtra("KodeTernak", kodeTernak);
                            intent.putExtra("TanggalVisual", tanggalVisual);
                            intent.putExtra("CaraTernak", caraTernak);
                            intent.putExtra("LinkVideo", videoURL);
                            intent.putExtra("Img1", img1);
                            intent.putExtra("Img2", img2);
                            intent.putExtra("Img3", img3);
                            intent.putExtra("JenisTernak", jenisTernak);
                            intent.putExtra("Spesies", spesies);
                            intent.putExtra("Berat", berat);
                            intent.putExtra("Panjang", panjang);
                            intent.putExtra("Tinggi", tinggi);


                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onFailure(Call<VisualResponse> call, Throwable t) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(HistoryActivity.this);

                    // Setting Dialog Title
                    alertDialog.setTitle("Error");

                    // Setting Dialog Message
                    alertDialog.setMessage("Not connected to server, please check internet connections");

                    // Setting Icon to Dialog
                    //alertDialog.setIcon(R.drawable.delete);

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                            // Write your code here to invoke YES event
                            //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();

                        }
                    });

                    // Setting Negative "NO" Button


                    // Showing Alert Message
                    alertDialog.show();
                }
            });
        }


    }

    private ArrayList<ModelHistory> initModelHistory(){
        mHistory = new ArrayList<>();
        ModelHistory history = new ModelHistory("V001","2017-04-10","T0001","Domba","Domba Garut","Growing", "http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg",
                "https://youtu.be/RoNsKd3J6Ww", "Aziz Firmansyah","Opik Sutisna");
        mHistory.add(history);


        return mHistory;
    }

}
