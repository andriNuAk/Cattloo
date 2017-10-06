package com.example.dragmetohell.cattloo.ternakku;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import com.example.dragmetohell.cattloo.AlumniActivity;
import com.example.dragmetohell.cattloo.HomeActivity;
import com.example.dragmetohell.cattloo.LoginActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.info.AsistenActivity;
import com.example.dragmetohell.cattloo.info.KandangActivity;
import com.example.dragmetohell.cattloo.info.PeternakActivity;
import com.example.dragmetohell.cattloo.laporan.LaporanActivity;
import com.example.dragmetohell.cattloo.pesan.PesanActivity;
import com.example.dragmetohell.cattloo.profil.ProfilActivity;

import java.util.ArrayList;

import helper.AkunHelper;
import helper.SessionHelper;
import model.ListTernakkuAdapter;
import model.ModelTernak;
import model.Ternakku;
import model.TernakkuResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TernakkuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<ModelTernak> mTernak;
    private String kategori;
    ListView lvTernak;
    ListTernakkuAdapter ternakAdapter;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang;
    ArrayList<Ternakku> mTernakku;
    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ternakku);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initAkun();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        txtStatus = (TextView) findViewById(R.id.txtStatusConn);

        initModelTernak();
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

        } else if (id == R.id.pesan) {
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
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<TernakkuResponse> call = apiService.getTernakUser(inIdUser);
        call.enqueue(new Callback<TernakkuResponse>() {
            @Override
            public void onResponse(Call<TernakkuResponse> call, Response<TernakkuResponse> response) {
                ArrayList<Ternakku> ternakku = (ArrayList<Ternakku>) response.body().getTernakku();
                mTernakku = ternakku;
                lvTernak = (ListView) findViewById(R.id.list_view_ternakku);
                System.out.println("Ini : "+mTernakku);
                if (ternakku == null){
                    txtStatus.setVisibility(View.VISIBLE);
                    txtStatus.setText("Tidak ada data ternak");
                } else {
                    ternakAdapter = new ListTernakkuAdapter(getApplicationContext(), mTernakku);
                    lvTernak.setAdapter(ternakAdapter);
                    lvTernak.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new  Intent(getApplicationContext(), DetailTernakkuActivity.class);
                            String kodeTernak = mTernakku.get(i).getIdTernak().toString();
                            String jenisHewan = mTernakku.get(i).getJenisHewan();
                            String spesies = mTernakku.get(i).getSpesies();
                            String caraTernak = mTernakku.get(i).getCaraTernak();
                            String namaPeternak = mTernakku.get(i).getNamaDepanPeternak()+" "+mTernakku.get(i).getNamaBelakangPeternak();
                            String namaAsisten = mTernakku.get(i).getNamaDepanAsisten()+" "+mTernakku.get(i).getNamaBelakangAsisten();
                            String imgUtama = mTernakku.get(i).getFoto1();
                            String img1 = mTernakku.get(i).getFoto2();
                            String img2 = mTernakku.get(i).getFoto3();
                            String video = mTernakku.get(i).getUrlVideo();
                            String statusPasar = mTernakku.get(i).getStatusPasar().toString();

                            intent.putExtra("KodeTernak", kodeTernak);
                            intent.putExtra("JenisHewan", jenisHewan);
                            intent.putExtra("Spesies", spesies);
                            intent.putExtra("CaraTernak", caraTernak);
                            intent.putExtra("NamaPeternak", namaPeternak);
                            intent.putExtra("NamaAsisten", namaAsisten);
                            intent.putExtra("ImgUtama", imgUtama);
                            intent.putExtra("Img1", img1);
                            intent.putExtra("Img2", img2);
                            intent.putExtra("Video", video);
                            intent.putExtra("Status", statusPasar);


                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<TernakkuResponse> call, Throwable t) {
                txtStatus.setText("Tidak dapat terhubung ke server");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TernakkuActivity.this);

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

    private void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TernakkuActivity.this);

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
                SessionHelper dbHelper =  new SessionHelper(TernakkuActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                dbHelper.deleteDataAkun(db, inIdUser);
                initAkun();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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

    private ArrayList<ModelTernak> initModelTernak(){
        mTernak = new ArrayList<>();
        ModelTernak ternak = new ModelTernak("T001","Domba","Domba Garut", "Garut","7000000","Growing","Aziz Firmansyah","Opik Sutisna","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg",
                "https://youtu.be/RoNsKd3J6Ww","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt diam sed lectus tincidunt, quis congue purus aliquet. Morbi urna risus, consequat quis tincidunt non, semper eu neque. In dapibus, nulla ac euismod cursus, enim enim hendrerit nisi, ac consectetur ante tortor ac risus. Integer pellentesque luctus est, eu faucibus enim tempor vulputate. Nunc vel tincidunt erat. Ut sit amet nulla lacinia, fermentum quam sit amet, condimentum metus. Donec eu massa dolor.\n" +
                "\n" +
                "Suspendisse molestie tortor eget commodo mollis. Nam quis libero facilisis, pharetra sem sed, ultrices ante. Nulla in dui ac lorem consequat iaculis lobortis in risus. Quisque turpis lorem, varius a consequat blandit, sollicitudin elementum odio. Vestibulum in elementum magna. Curabitur lacus tortor, aliquet vitae urna non, auctor laoreet quam. Ut mauris lectus, finibus eget mi sit amet, suscipit tincidunt est. Aliquam quis consectetur est, consequat volutpat ligula. Ut lectus nulla, vehicula nec ultricies in, blandit eu ipsum.\n" +
                "\n" +
                "Donec semper nunc vestibulum massa tempor pellentesque at in metus. Praesent ut nulla metus. Quisque ac dignissim orci. Mauris pellentesque tortor vitae sagittis auctor. Pellentesque accumsan nulla quis erat aliquet, placerat scelerisque ipsum lacinia. Duis et convallis augue, vel placerat est. Integer cursus odio felis, sed mattis lacus tempor dictum.");
        mTernak.add(ternak);


        return mTernak;
    }
}
