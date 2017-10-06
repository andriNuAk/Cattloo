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
import model.Asisten;
import model.AsistenResponse;
import model.ListAsistenAdapter;
import model.Peternak;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsistenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang;
    ArrayList<Asisten> mAsisten;
    ListView lvAsisten;
    ListAsistenAdapter asistenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asisten);
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
        initAsisten(inIdUser);
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
            initAkun();
            initAsisten(inIdUser);
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
    public void initAsisten(int idUser) {
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<AsistenResponse> call = apiService.getAsisten(idUser);
        call.enqueue(new Callback<AsistenResponse>() {
            @Override
            public void onResponse(Call<AsistenResponse> call, Response<AsistenResponse> response) {
                ArrayList<Asisten> asisten = (ArrayList<Asisten>) response.body().getAsisten();
                mAsisten = asisten;
                if (mAsisten == null) {
                    Toast.makeText(getApplicationContext(), "Data tidak ada", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < asisten.size(); i++) {
                        System.out.println("Nama Peternak = '"+mAsisten.get(i).getNamaDepan()+ "' " + mAsisten.get(i).getNamaBelakang() + " Kapasitas = " + mAsisten.get(i).getJumlahMerawat());
                    }

                    lvAsisten = (ListView) findViewById(R.id.list_asisten);
                    asistenAdapter = new ListAsistenAdapter(getApplicationContext(), mAsisten);
                    lvAsisten.setAdapter(asistenAdapter);
                    lvAsisten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), InfoAsistenActivity.class);
                            String idAsisten = mAsisten.get(position).getAsisten().toString();
                            String namaDepan = mAsisten.get(position).getNamaDepan();
                            String namaBelakang = mAsisten.get(position).getNamaBelakang();
                            String foto = mAsisten.get(position).getFoto();
                            String alamat = mAsisten.get(position).getAlamat()+" , "+mAsisten.get(position).getName();

                            intent.putExtra("Id", idAsisten);
                            intent.putExtra("NamaDepan", namaDepan);
                            intent.putExtra("NamaBelakang", namaBelakang);
                            intent.putExtra("Foto", foto);
                            intent.putExtra("Alamat", alamat);
                            startActivity(intent);
                        }
                    });


                }

            }

            @Override
            public void onFailure(Call<AsistenResponse> call, Throwable t) {

            }
        });

    }

    private void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AsistenActivity.this);

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
                SessionHelper dbHelper =  new SessionHelper(AsistenActivity.this);
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
