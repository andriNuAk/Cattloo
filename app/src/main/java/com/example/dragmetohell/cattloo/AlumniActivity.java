package com.example.dragmetohell.cattloo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.history.DetailHistoryActivity;
import com.example.dragmetohell.cattloo.info.AsistenActivity;
import com.example.dragmetohell.cattloo.info.KandangActivity;
import com.example.dragmetohell.cattloo.info.PeternakActivity;
import com.example.dragmetohell.cattloo.laporan.LaporanActivity;
import com.example.dragmetohell.cattloo.pesan.PesanActivity;
import com.example.dragmetohell.cattloo.profil.ProfilActivity;
import com.example.dragmetohell.cattloo.ternakku.TernakkuActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import helper.AkunHelper;
import helper.SessionHelper;
import model.ListAlumniAdapter;
import model.ListHistoryAdapter;
import model.ListLapJualAdapter;
import model.ListLapPerawatanAdapter;
import model.ModelHistory;
import model.Penjualan;
import model.PenjualanResponse;
import model.Perawatan;
import model.PerawatanResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlumniActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<ModelHistory> mHistory;
    private String kategori;
    ListView lvAlumni;
    ListAlumniAdapter alumniAdapter;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser, inIdLokasi, id_belanja, pemilik;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, inKodeSampel, currDate;
    ArrayList<Penjualan> mPenjualan;
    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        txtStatus = (TextView) findViewById(R.id.txtStatusConn);
        initAkun();
        //array list
        initModelHistory();
        // tampil recycleview
        initView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profil) {
            Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.logout){
            confirmDialog();
            return true;
        } else if (id == R.id.refresh){
            initView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.beranda){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == R.id.lihat_ternak) {
            // Handle the camera action
            Intent intent = new Intent(getApplicationContext(), TernakkuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.history) {
            Intent intent = new Intent(getApplicationContext(), AlumniActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.lap_belanja) {
            Intent intent = new Intent(getApplicationContext(), LaporanActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.pesan){
            Intent intent = new Intent(getApplicationContext(), PesanActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.info_peternak){
            Intent intent = new Intent(getApplicationContext(), PeternakActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.info_kandang){
            Intent intent = new Intent(getApplicationContext(), KandangActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.info_asisten){
            Intent intent = new Intent(getApplicationContext(), AsistenActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initView(){
        //APIInterface apiInterface2 = APIClient.getURL().create(APIInterface.class);
        //lvAlumni = (ListView) findViewById(R.id.list_view_alumni);
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<PenjualanResponse> call2 = apiInterface.getPenjualan(inIdUser);
        call2.enqueue(new Callback<PenjualanResponse>() {
            @Override
            public void onResponse(Call<PenjualanResponse> call, Response<PenjualanResponse> response) {
                ArrayList<Penjualan> laporanPenjualan = (ArrayList<Penjualan>) response.body().getPenjualan();
                mPenjualan = laporanPenjualan;

                lvAlumni = (ListView) findViewById(R.id.list_view_alumni);
                if (mPenjualan == null){
                    txtStatus.setText("Tidak ada data alumni");
                    lvAlumni.setVisibility(View.GONE);
                } else {
                    alumniAdapter = new ListAlumniAdapter(getApplicationContext(), mPenjualan);
                    lvAlumni.setAdapter(alumniAdapter);
                }

            }

            @Override
            public void onFailure(Call<PenjualanResponse> call, Throwable t) {
                txtStatus.setText("Tidak dapat terhubung ke server");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlumniActivity.this);

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

//        lvAlumni = (ListView) findViewById(R.id.list_view_alumni);
//        historyAdapter = new ListHistoryAdapter(this, mHistory);
//        lvAlumni.setAdapter(historyAdapter);
//        lvAlumni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new  Intent(getApplicationContext(), DetailHistoryActivity.class);
//                String kodeVIsual = mHistory.get(i).getKodeVisual();
//                String kodeTernak = mHistory.get(i).getKodeTernak();
//                String tanggalVisual = mHistory.get(i).getTglVisual();
//                String caraTernak = mHistory.get(i).getCaraTernak();
//                String namaPeternak = mHistory.get(i).getNamaPeternak();
//                String namaAsisten = mHistory.get(i).getNamaAsisten();
//                String imgUtama = mHistory.get(i).getImgUtama();
//                String img1 = mHistory.get(i).getImg1();
//                String img2 = mHistory.get(i).getImg2();
//                String img3 = mHistory.get(i).getImg3();
//                String videoURL = mHistory.get(i).getVideoURL();
//
//
//                intent.putExtra("KodeVisual", kodeVIsual);
//                intent.putExtra("KodeTernak", kodeTernak);
//                intent.putExtra("TanggalVisual", tanggalVisual);
//                intent.putExtra("CaraTernak", caraTernak);
//                intent.putExtra("NamaPeternak", namaPeternak);
//                intent.putExtra("NamaAsisten", namaAsisten);
//                intent.putExtra("LinkVideo", videoURL);
//                intent.putExtra("ImgUtama", imgUtama);
//                intent.putExtra("Img1", img1);
//                intent.putExtra("Img2", img2);
//                intent.putExtra("Img3", img3);
//
//
//                startActivity(intent);
//            }
//        });
    }

    private void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlumniActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Logout");

        // Setting Dialog Message
        alertDialog.setMessage("Apakah anda yakin untuk logout?");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                // Write your code here to invoke YES event
                //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                initAkun();
                SessionHelper dbHelper =  new SessionHelper(AlumniActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                dbHelper.deleteDataAkun(db, inIdUser);
                initAkun();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    private ArrayList<ModelHistory> initModelHistory(){
        mHistory = new ArrayList<>();
        ModelHistory history = new ModelHistory("V001","2017-04-10","T0001","Domba","Domba Garut","Growing", "http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg",
                "https://youtu.be/RoNsKd3J6Ww", "Aziz Firmansyah","Opik Sutisna");
        mHistory.add(history);


        return mHistory;
    }

    public void initAkun(){
//        for (int i=0 ; i < akunHelpers.size(); i++){
//            kodeKeluarga = akunHelpers.get(i).getKodeKeluarga();
//            noIdentitas = akunHelpers.get(i).getNoIdentitas();
//            username = akunHelpers.get(i).getUsername();
//            password = akunHelpers.get(i).getPassword();
//        }

        dbHelper = new SessionHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        try {
//            loadDataMahasiswa();
            Cursor cursor = dbHelper.getAllAkun(db);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
//            String data = cursor.getString(0)+" - "+cursor.getString(1);
//            akun.add(data);
                akunHelper = new ArrayList();

                inIdUser = Integer.valueOf(cursor.getString(0));
                inUsername = cursor.getString(1);
                inPassword = cursor.getString(2);
                inEmail = cursor.getString(3);
                inNoTelepon = cursor.getString(4);
                inNamaDepan = cursor.getString(5);
                inNamaBelakang = cursor.getString(6);
                AkunHelper ah = new AkunHelper(inIdUser, inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang);
                akunHelper.add(ah);
                cursor.moveToNext();
            }
// close cursor
            cursor.close();
//            Collections.sort(akun);
        } catch (Exception e){
            Log.e("masuk","-> "+e.getMessage()) ;
        }

        System.out.println("Udah ada di arraylist, datanya : kode user = "+inIdUser);
    }


}
