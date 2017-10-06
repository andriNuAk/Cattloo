package com.example.dragmetohell.cattloo.profil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.UbahSandiActivity;

import java.util.ArrayList;

import helper.AkunHelper;
import helper.SessionHelper;
import model.ListProfilAdapter;
import model.ModelProfil;

public class ProfilActivity extends AppCompatActivity {
    ArrayList<ModelProfil> listProfil;
    ListView lvProfil;
    ListProfilAdapter listProfilAdapter;
    Button ubahSandi;

    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser, kiri, kanan;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, kategoriFilter, jenisFilter;
    TextView txtHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ubahSandi = (Button) findViewById(R.id.btnUbahSandi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvProfil = (ListView) findViewById(R.id.listProfil);
        txtHello = (TextView) findViewById(R.id.textView5);
        initAkun();
        setProfil();
        txtHello.setText("Hello "+inNamaDepan+" "+inNamaBelakang);
        listProfilAdapter = new ListProfilAdapter(this, listProfil);
        lvProfil.setAdapter(listProfilAdapter);
        ubahSandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UbahSandiActivity.class);
                startActivity(intent);
            }
        });
    }


    private ArrayList<ModelProfil> setProfil(){
        listProfil = new ArrayList<>();
        ModelProfil modelProfil = new ModelProfil("profil_icon_name", "Nama Pengguna", inNamaDepan+" "+inNamaBelakang);
        listProfil.add(modelProfil);

        modelProfil = new ModelProfil("profil_icon_phone", "No Telepon", inNoTelepon);
        listProfil.add(modelProfil);

        modelProfil = new ModelProfil("profil_icon_email", "Email", inEmail);
        listProfil.add(modelProfil);

        return listProfil;
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
