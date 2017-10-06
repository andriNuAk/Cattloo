package com.example.dragmetohell.cattloo.info;

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
import android.widget.Toast;

import com.example.dragmetohell.cattloo.AlumniActivity;
import com.example.dragmetohell.cattloo.HomeActivity;
import com.example.dragmetohell.cattloo.LoginActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.laporan.LaporanActivity;
import com.example.dragmetohell.cattloo.pesan.PesanActivity;
import com.example.dragmetohell.cattloo.profil.ProfilActivity;
import com.example.dragmetohell.cattloo.ternakku.TernakkuActivity;

import java.util.ArrayList;

import helper.AkunHelper;
import helper.SessionHelper;
import model.Kandang;
import model.KandangResponse;
import model.ListKandangAdapter;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KandangActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int inIdUser;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    ArrayList<Kandang> mKandang;
    ListView lvKandang;
    ListKandangAdapter kandangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kandang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initAkun();
        initKandang(inIdUser);
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
//            initView();
            initAkun();
            initKandang(inIdUser);
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


    //API pasar
    public void initKandang(int idUser) {
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<KandangResponse> call = apiService.getKandang(idUser);
        call.enqueue(new Callback<KandangResponse>() {
            @Override
            public void onResponse(Call<KandangResponse> call, Response<KandangResponse> response) {
                ArrayList<Kandang> kandang = (ArrayList<Kandang>) response.body().getKandang();
                mKandang = kandang;
                if (mKandang == null) {
                    Toast.makeText(getApplicationContext(), "Data tidak ada", Toast.LENGTH_LONG).show();
                } else {
//                    for (int i = 0; i < kandang.size(); i++) {
//                        System.out.println("Nama Peternak = '"+mPeternak.get(i).getPeternak()+ "' " + mPeternak.get(i).getNamaBelakang() + " Kapasitas = " + mPeternak.get(i).getKapasitasTernak());
//                    }
                    Toast.makeText(getApplicationContext(), "Data ada", Toast.LENGTH_LONG).show();
                    lvKandang = (ListView) findViewById(R.id.list_kandang);
                    kandangAdapter = new ListKandangAdapter(getApplicationContext(), mKandang);
                    lvKandang.setAdapter(kandangAdapter);
                    lvKandang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new  Intent(getApplicationContext(), InfoKandangActivity.class);
                            String idKandang = mKandang.get(position).getKandang().toString();
                            String namaKandang = mKandang.get(position).getNamaKandang();
                            String namaPemilik = mKandang.get(position).getPemilikKandang();
                            String alamat = mKandang.get(position).getAlamat()+" ,"+mKandang.get(position).getName();

                            intent.putExtra("Id", idKandang);
                            intent.putExtra("NamaKandang", namaKandang);
                            intent.putExtra("NamaPemilik", namaPemilik);
                            intent.putExtra("Alamat", alamat);

                            startActivity(intent);
                        }
                    });
//                    lvHewan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                            Intent intent = new  Intent(getApplicationContext(), DetailHewanActivity.class);
//                            String pemilik = mPasar.get(i).getNamaDepan()+" "+mPasar.get(i).getNamaBelakang();
//                            //String gambarUtama = mHewan.get(i).getImgUtama();
//                            String gambar1 = mPasar.get(i).getFoto1();
//                            String gambar2 = mPasar.get(i).getFoto2();
//                            String gambar3 = mPasar.get(i).getFoto3();
//                            String jenis = mPasar.get(i).getJenisHewan();
//                            String spesies = mPasar.get(i).getSpesies();
//                            String lokasi = mPasar.get(i).getNamaDaerah();
//                            String harga = mPasar.get(i).getHarga().toString();
//                            String nomorKontak = mPasar.get(i).getNoTelepon();
//                            String linkVideo = mPasar.get(i).getUrlVideo();
//                            String deskripsi = mPasar.get(i).getDeskripsi();
//
//
//                            intent.putExtra("NamaPemilik", pemilik);
//                            intent.putExtra("Gambar1", gambar1);
//                            intent.putExtra("Gambar2", gambar2);
//                            intent.putExtra("Gambar3", gambar3);
//                            intent.putExtra("Jenis", jenis);
//                            intent.putExtra("Spesies", spesies);
//                            intent.putExtra("Lokasi", lokasi);
//                            intent.putExtra("Harga", harga);
//                            intent.putExtra("NomorKontak", nomorKontak);
//                            intent.putExtra("LinkVideo", linkVideo);
//                            intent.putExtra("Deskripsi", deskripsi);
//                            intent.putExtra("IdTernak", mPasar.get(i).getIdTernak().toString());
//                            intent.putExtra("Pemilik", mPasar.get(i).getIdUser().toString());
//                            startActivity(intent);
//                        }
//                    });


                }

            }

            @Override
            public void onFailure(Call<KandangResponse> call, Throwable t) {

            }
        });

    }

    private void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(KandangActivity.this);

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
                SessionHelper dbHelper =  new SessionHelper(KandangActivity.this);
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
}
