package com.example.dragmetohell.cattloo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.info.AsistenActivity;
import com.example.dragmetohell.cattloo.info.KandangActivity;
import com.example.dragmetohell.cattloo.info.PeternakActivity;
import com.example.dragmetohell.cattloo.laporan.LaporanActivity;
import com.example.dragmetohell.cattloo.pesan.PesanActivity;
import com.example.dragmetohell.cattloo.profil.ProfilActivity;
import com.example.dragmetohell.cattloo.ternakku.TernakkuActivity;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

import helper.AkunHelper;
import helper.Lokasi;
import helper.LokasiResponse;
import helper.SessionHelper;
import model.Hewan;
import model.HewanResponse;
import model.ListHewanAdapter;
import model.ModelHewan;
import model.Pasar;
import model.PasarResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<ModelHewan> mHewan;
    private String kategori;
    ListView lvHewan;
    ListHewanAdapter hewanAdapter;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser, kiri, kanan;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, kategoriFilter, jenisFilter;
    ArrayList<Pasar> mPasar;
    ArrayList<Hewan> jenisHewan;
    ArrayList<Lokasi> namaDaerah;
    Button btnFilter;
    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnFilter = (Button) findViewById(R.id.btnFilter);
        txtStatus = (TextView) findViewById(R.id.txtStatusConn);
        //



        initSpinner();
        //cek akun dari session
        initAkun();
        //cek pasar
        initPasar();
        //array list
        initModelHewan();
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
            initPasar();
            initSpinner();
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
//        for (int i=0; i<mPasar.size();i++){
//            System.out.println("Ada data pemilik "+mPasar.get(i).getNamaDepan());
//        }

//
    }



    private ArrayList<ModelHewan> initModelHewan(){
        mHewan = new ArrayList<>();
        ModelHewan hewan = new ModelHewan("Andri Nurul Akbar","Domba","Domba Garut", "Garut","7000000","085860307677","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","https://youtu.be/RoNsKd3J6Ww","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt diam sed lectus tincidunt, quis congue purus aliquet. Morbi urna risus, consequat quis tincidunt non, semper eu neque. In dapibus, nulla ac euismod cursus, enim enim hendrerit nisi, ac consectetur ante tortor ac risus. Integer pellentesque luctus est, eu faucibus enim tempor vulputate. Nunc vel tincidunt erat. Ut sit amet nulla lacinia, fermentum quam sit amet, condimentum metus. Donec eu massa dolor.\n" +
                "\n" +
                "Suspendisse molestie tortor eget commodo mollis. Nam quis libero facilisis, pharetra sem sed, ultrices ante. Nulla in dui ac lorem consequat iaculis lobortis in risus. Quisque turpis lorem, varius a consequat blandit, sollicitudin elementum odio. Vestibulum in elementum magna. Curabitur lacus tortor, aliquet vitae urna non, auctor laoreet quam. Ut mauris lectus, finibus eget mi sit amet, suscipit tincidunt est. Aliquam quis consectetur est, consequat volutpat ligula. Ut lectus nulla, vehicula nec ultricies in, blandit eu ipsum.\n" +
                "\n" +
                "Donec semper nunc vestibulum massa tempor pellentesque at in metus. Praesent ut nulla metus. Quisque ac dignissim orci. Mauris pellentesque tortor vitae sagittis auctor. Pellentesque accumsan nulla quis erat aliquet, placerat scelerisque ipsum lacinia. Duis et convallis augue, vel placerat est. Integer cursus odio felis, sed mattis lacus tempor dictum.");
        mHewan.add(hewan);

        hewan = new ModelHewan("Andri Nurul Akbar","Sapi","Sapi Australia", "Garut","7000000","085860307677","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","https://youtu.be/RoNsKd3J6Ww","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt diam sed lectus tincidunt, quis congue purus aliquet. Morbi urna risus, consequat quis tincidunt non, semper eu neque. In dapibus, nulla ac euismod cursus, enim enim hendrerit nisi, ac consectetur ante tortor ac risus. Integer pellentesque luctus est, eu faucibus enim tempor vulputate. Nunc vel tincidunt erat. Ut sit amet nulla lacinia, fermentum quam sit amet, condimentum metus. Donec eu massa dolor.\n" +
                "\n" +
                "Suspendisse molestie tortor eget commodo mollis. Nam quis libero facilisis, pharetra sem sed, ultrices ante. Nulla in dui ac lorem consequat iaculis lobortis in risus. Quisque turpis lorem, varius a consequat blandit, sollicitudin elementum odio. Vestibulum in elementum magna. Curabitur lacus tortor, aliquet vitae urna non, auctor laoreet quam. Ut mauris lectus, finibus eget mi sit amet, suscipit tincidunt est. Aliquam quis consectetur est, consequat volutpat ligula. Ut lectus nulla, vehicula nec ultricies in, blandit eu ipsum.\n" +
                "\n" +
                "Donec semper nunc vestibulum massa tempor pellentesque at in metus. Praesent ut nulla metus. Quisque ac dignissim orci. Mauris pellentesque tortor vitae sagittis auctor. Pellentesque accumsan nulla quis erat aliquet, placerat scelerisque ipsum lacinia. Duis et convallis augue, vel placerat est. Integer cursus odio felis, sed mattis lacus tempor dictum.");
        mHewan.add(hewan);

        hewan = new ModelHewan("Opik Sutsina","Sapi","Sapi Jawa", "Bandung","7000000","085860307677","http://1.bp.blogspot.com/-XnRLePkz7e8/UzodLhnq2VI/AAAAAAAAAQw/JGNX3HeiSJ4/s1600/sapi+po+peranakn+ongole+-+foto+gambar+sapi+lengkap+-+usahaternak.jpg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","https://youtu.be/RoNsKd3J6Ww","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt diam sed lectus tincidunt, quis congue purus aliquet. Morbi urna risus, consequat quis tincidunt non, semper eu neque. In dapibus, nulla ac euismod cursus, enim enim hendrerit nisi, ac consectetur ante tortor ac risus. Integer pellentesque luctus est, eu faucibus enim tempor vulputate. Nunc vel tincidunt erat. Ut sit amet nulla lacinia, fermentum quam sit amet, condimentum metus. Donec eu massa dolor.\n" +
                "\n" +
                "Suspendisse molestie tortor eget commodo mollis. Nam quis libero facilisis, pharetra sem sed, ultrices ante. Nulla in dui ac lorem consequat iaculis lobortis in risus. Quisque turpis lorem, varius a consequat blandit, sollicitudin elementum odio. Vestibulum in elementum magna. Curabitur lacus tortor, aliquet vitae urna non, auctor laoreet quam. Ut mauris lectus, finibus eget mi sit amet, suscipit tincidunt est. Aliquam quis consectetur est, consequat volutpat ligula. Ut lectus nulla, vehicula nec ultricies in, blandit eu ipsum.\n" +
                "\n" +
                "Donec semper nunc vestibulum massa tempor pellentesque at in metus. Praesent ut nulla metus. Quisque ac dignissim orci. Mauris pellentesque tortor vitae sagittis auctor. Pellentesque accumsan nulla quis erat aliquet, placerat scelerisque ipsum lacinia. Duis et convallis augue, vel placerat est. Integer cursus odio felis, sed mattis lacus tempor dictum.");
        mHewan.add(hewan);

        hewan = new ModelHewan("Anggiyasti Yaktining","Kambing","Kambing Jawa", "Lembang","7000000","085860307677","http://1.bp.blogspot.com/-RujP1DH90sE/VHsj246SXFI/AAAAAAAAALs/yYKTm57RHHU/s1600/pe.jpg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","https://youtu.be/RoNsKd3J6Ww","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt diam sed lectus tincidunt, quis congue purus aliquet. Morbi urna risus, consequat quis tincidunt non, semper eu neque. In dapibus, nulla ac euismod cursus, enim enim hendrerit nisi, ac consectetur ante tortor ac risus. Integer pellentesque luctus est, eu faucibus enim tempor vulputate. Nunc vel tincidunt erat. Ut sit amet nulla lacinia, fermentum quam sit amet, condimentum metus. Donec eu massa dolor.\n" +
                "\n" +
                "Suspendisse molestie tortor eget commodo mollis. Nam quis libero facilisis, pharetra sem sed, ultrices ante. Nulla in dui ac lorem consequat iaculis lobortis in risus. Quisque turpis lorem, varius a consequat blandit, sollicitudin elementum odio. Vestibulum in elementum magna. Curabitur lacus tortor, aliquet vitae urna non, auctor laoreet quam. Ut mauris lectus, finibus eget mi sit amet, suscipit tincidunt est. Aliquam quis consectetur est, consequat volutpat ligula. Ut lectus nulla, vehicula nec ultricies in, blandit eu ipsum.\n" +
                "\n" +
                "Donec semper nunc vestibulum massa tempor pellentesque at in metus. Praesent ut nulla metus. Quisque ac dignissim orci. Mauris pellentesque tortor vitae sagittis auctor. Pellentesque accumsan nulla quis erat aliquet, placerat scelerisque ipsum lacinia. Duis et convallis augue, vel placerat est. Integer cursus odio felis, sed mattis lacus tempor dictum.");
        mHewan.add(hewan);

        hewan = new ModelHewan("Nur Sofia","Sapi","Sapi Bali", "Yogyakarta","7000000","085860307677","https://satwapedia.com/wp-content/uploads/2016/01/Gambar-sapi-bali.jpg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","http://www.riaubook.com/photos/berita/60-juta-dolar-amerika-untuk-pengembangbiakan-sapi.jpeg","https://youtu.be/RoNsKd3J6Ww","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt diam sed lectus tincidunt, quis congue purus aliquet. Morbi urna risus, consequat quis tincidunt non, semper eu neque. In dapibus, nulla ac euismod cursus, enim enim hendrerit nisi, ac consectetur ante tortor ac risus. Integer pellentesque luctus est, eu faucibus enim tempor vulputate. Nunc vel tincidunt erat. Ut sit amet nulla lacinia, fermentum quam sit amet, condimentum metus. Donec eu massa dolor.\n" +
                "\n" +
                "Suspendisse molestie tortor eget commodo mollis. Nam quis libero facilisis, pharetra sem sed, ultrices ante. Nulla in dui ac lorem consequat iaculis lobortis in risus. Quisque turpis lorem, varius a consequat blandit, sollicitudin elementum odio. Vestibulum in elementum magna. Curabitur lacus tortor, aliquet vitae urna non, auctor laoreet quam. Ut mauris lectus, finibus eget mi sit amet, suscipit tincidunt est. Aliquam quis consectetur est, consequat volutpat ligula. Ut lectus nulla, vehicula nec ultricies in, blandit eu ipsum.\n" +
                "\n" +
                "Donec semper nunc vestibulum massa tempor pellentesque at in metus. Praesent ut nulla metus. Quisque ac dignissim orci. Mauris pellentesque tortor vitae sagittis auctor. Pellentesque accumsan nulla quis erat aliquet, placerat scelerisque ipsum lacinia. Duis et convallis augue, vel placerat est. Integer cursus odio felis, sed mattis lacus tempor dictum.");
        mHewan.add(hewan);
        return mHewan;
    }

    private void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);

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
                SessionHelper dbHelper =  new SessionHelper(HomeActivity.this);
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

    //init spinner
    public void initSpinner(){
        final ArrayList<String> kategoriCari = new ArrayList<String>();
        kategoriCari.add("Pilih Kategori FIlter");
        kategoriCari.add("Jenis Hewan");
        kategoriCari.add("Lokasi");
        kategoriCari.add("Harga");

        //initSpinnerKategori
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerKategori);
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinnerSearch);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kategori_cari, R.layout.spinner_search_list);
        //CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(HomeActivity.this, kategoriCari);
        adapter.setDropDownViewResource(R.layout.custom_dropdown);
        kategori = String.valueOf(spinner.getSelectedItem());
        System.out.println(kategori);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Pilih Filter");

        //initSpinnerCari
//
//


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    System.out.println("masuk hewan");
                    String item = adapterView.getItemAtPosition(i).toString();
                    kategoriFilter = item;
                    //
                    APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                    retrofit2.Call<HewanResponse> call = apiService.getHewan();
                    call.enqueue(new Callback<HewanResponse>() {
                        @Override
                        public void onResponse(Call<HewanResponse> call, Response<HewanResponse> response) {
                            jenisHewan = new ArrayList<>();

                            ArrayList<Hewan> hewan = (ArrayList<Hewan>) response.body().getHewan();
                            jenisHewan = hewan;
                            for (int i=0; i<jenisHewan.size(); i++){
                                System.out.println("Jenis hewan : "+jenisHewan.get(i));
                            }
                            ArrayAdapter<Hewan> adapterHewan = new ArrayAdapter<Hewan>(getApplicationContext(), R.layout.spinner_search_list, jenisHewan);
                            adapterHewan.setDropDownViewResource(R.layout.custom_dropdown);
                            adapterHewan.notifyDataSetChanged();
                            spinner1.setAdapter(adapterHewan);


                        }

                        @Override
                        public void onFailure(Call<HewanResponse> call, Throwable t) {

                        }
                    });
//                    ArrayList<String> subKategoriCari = new ArrayList<String>();
//                    subKategoriCari.add("Domba");
//                    subKategoriCari.add("Kambing");
//                    subKategoriCari.add("Sapi");
//
//                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.jenis_hewan, R.layout.spinner_search_list);
//                    adapter1.setDropDownViewResource(R.layout.custom_dropdown);
//                    adapter1.notifyDataSetChanged();
//                    spinner1.setAdapter(adapter1);
                } else if (i == 2){
                    System.out.println("masuk harga");
                    String item = adapterView.getItemAtPosition(i).toString();
                    kategoriFilter = item;
                    //
                    ArrayList<String> subKategoriCari = new ArrayList<String>();
                    subKategoriCari.add("0 = 1.000.000");
                    subKategoriCari.add("1.000.000 - 5.000.000");
                    subKategoriCari.add("5.000.000 - 10.000.000");


                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.harga, R.layout.spinner_search_list);
                    adapter1.setDropDownViewResource(R.layout.custom_dropdown);
                    adapter1.notifyDataSetChanged();;
                    spinner1.setAdapter(adapter1);
                } else if (i == 1){
                    String item = adapterView.getItemAtPosition(i).toString();
                    kategoriFilter = item;
                    APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                    retrofit2.Call<LokasiResponse> call = apiService.getLokasi();
                    call.enqueue(new Callback<LokasiResponse>() {
                        @Override
                        public void onResponse(Call<LokasiResponse> call, Response<LokasiResponse> response) {
                            namaDaerah = new ArrayList<>();

                            ArrayList<Lokasi> lokasi = (ArrayList<Lokasi>) response.body().getLokasi();
                            namaDaerah = lokasi;

                            ArrayAdapter<Lokasi> adapterLokasi = new ArrayAdapter<Lokasi>(getApplicationContext(), R.layout.spinner_search_list, namaDaerah);
                            adapterLokasi.setDropDownViewResource(R.layout.custom_dropdown);
                            adapterLokasi.notifyDataSetChanged();
                            spinner1.setAdapter(adapterLokasi);
                        }

                        @Override
                        public void onFailure(Call<LokasiResponse> call, Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (kategoriFilter.equalsIgnoreCase("Harga")){
                    String item = parent.getItemAtPosition(position).toString();
                    System.out.println("EAAAA "+item);
                    String jf = item;
                    jenisFilter = item;
                    if (jenisFilter.equalsIgnoreCase("0 - 10.000.000")){
                        kiri = 0;
                        kanan = 10000000;
                    }
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    System.out.println("EAAAA "+item);
                    String jf = item;
                    jenisFilter = item;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Jenis Hewan Clicked "+kategoriFilter);
                System.out.println("Spinner 1 selected "+jenisFilter);

                if (kategoriFilter.equalsIgnoreCase("Jenis Hewan")){
                    System.out.println("Masukan fungsi filter by jenis hewan "+jenisFilter);
                    APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                    retrofit2.Call<PasarResponse> call = apiService.getPasarByHewan(jenisFilter);
                    call.enqueue(new Callback<PasarResponse>() {
                        @Override
                        public void onResponse(Call<PasarResponse> call, Response<PasarResponse> response) {

                            ArrayList<Pasar> pasar = (ArrayList<Pasar>) response.body().getPasar();
                            mPasar = pasar;
//                            for (int i = 0; i < pasar.size() ; i++){
//                                System.out.println("Nama Pemilik = "+mPasar.get(i).getNamaDepan()+""+mPasar.get(i).getNamaBelakang()+" Kode Ternak = "+pasar.get(i).getIdTernak());
//                            }

                            if (mPasar == null){
                                Toast.makeText(getApplication(), "Data tidak ditemukan!!", Toast.LENGTH_LONG).show();
                                txtStatus.setVisibility(View.VISIBLE);
                                lvHewan = (ListView) findViewById(R.id.list_view_home);
                                lvHewan.setVisibility(View.GONE);
                            } else {
                                lvHewan = (ListView) findViewById(R.id.list_view_home);
                                lvHewan.setVisibility(View.VISIBLE);
                                txtStatus.setVisibility(View.GONE);
                                hewanAdapter = new ListHewanAdapter(getApplicationContext(), mPasar);
                                lvHewan.setAdapter(hewanAdapter);
                                lvHewan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new  Intent(getApplicationContext(), DetailHewanActivity.class);
                                        String pemilik = mPasar.get(i).getNamaDepan()+" "+mPasar.get(i).getNamaBelakang();
                                        //String gambarUtama = mHewan.get(i).getImgUtama();
                                        String gambar1 = mPasar.get(i).getFoto1();
                                        String gambar2 = mPasar.get(i).getFoto2();
                                        String gambar3 = mPasar.get(i).getFoto3();
                                        String jenis = mPasar.get(i).getJenisHewan();
                                        String spesies = mPasar.get(i).getSpesies();
                                        String lokasi = mPasar.get(i).getNamaDaerah();
                                        String harga = mPasar.get(i).getHarga().toString();
                                        String nomorKontak = mPasar.get(i).getNoTelepon();
                                        String linkVideo = mPasar.get(i).getUrlVideo();
                                        String deskripsi = mPasar.get(i).getDeskripsi();


                                        intent.putExtra("NamaPemilik", pemilik);
                                        intent.putExtra("Gambar1", gambar1);
                                        intent.putExtra("Gambar2", gambar2);
                                        intent.putExtra("Gambar3", gambar3);
                                        intent.putExtra("Jenis", jenis);
                                        intent.putExtra("Spesies", spesies);
                                        intent.putExtra("Lokasi", lokasi);
                                        intent.putExtra("Harga", harga);
                                        intent.putExtra("NomorKontak", nomorKontak);
                                        intent.putExtra("LinkVideo", linkVideo);
                                        intent.putExtra("Deskripsi", deskripsi);
                                        intent.putExtra("IdTernak", mPasar.get(i).getIdTernak().toString());
                                        intent.putExtra("Pemilik", mPasar.get(i).getIdUser().toString());
                                        startActivity(intent);
                                    }
                                });
                            }


                        }

                        @Override
                        public void onFailure(Call<PasarResponse> call, Throwable t) {
                            Toast.makeText(getApplication(),"No connection, Please check internet connection!!"+t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                } else if (kategoriFilter.equalsIgnoreCase("Lokasi")){
                    System.out.println("Masukin fungsi lokasi jenis hewan"+jenisFilter);
                    APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                    retrofit2.Call<PasarResponse> call = apiService.getPasarByLokasi(jenisFilter);
                    call.enqueue(new Callback<PasarResponse>() {
                        @Override
                        public void onResponse(Call<PasarResponse> call, Response<PasarResponse> response) {

                            ArrayList<Pasar> pasar = (ArrayList<Pasar>) response.body().getPasar();
                            mPasar = pasar;

                            if (mPasar == null){
                                Toast.makeText(getApplication(), "Data tidak ditemukan!!", Toast.LENGTH_LONG).show();
                                txtStatus.setVisibility(View.VISIBLE);
                                lvHewan = (ListView) findViewById(R.id.list_view_home);
                                lvHewan.setVisibility(View.GONE);
                            } else {
                                for (int i = 0; i < pasar.size() ; i++){
                                    System.out.println("Nama Pemilik = "+mPasar.get(i).getNamaDepan()+""+mPasar.get(i).getNamaBelakang()+" Kode Ternak = "+pasar.get(i).getIdTernak());
                                }

                                lvHewan = (ListView) findViewById(R.id.list_view_home);
                                txtStatus.setVisibility(View.GONE);
                                lvHewan.setVisibility(View.VISIBLE);
                                hewanAdapter = new ListHewanAdapter(getApplicationContext(), mPasar);
                                lvHewan.setAdapter(hewanAdapter);
                                lvHewan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new  Intent(getApplicationContext(), DetailHewanActivity.class);
                                        String pemilik = mPasar.get(i).getNamaDepan()+" "+mPasar.get(i).getNamaBelakang();
                                        //String gambarUtama = mHewan.get(i).getImgUtama();
                                        String gambar1 = mPasar.get(i).getFoto1();
                                        String gambar2 = mPasar.get(i).getFoto2();
                                        String gambar3 = mPasar.get(i).getFoto3();
                                        String jenis = mPasar.get(i).getJenisHewan();
                                        String spesies = mPasar.get(i).getSpesies();
                                        String lokasi = mPasar.get(i).getNamaDaerah();
                                        String harga = mPasar.get(i).getHarga().toString();
                                        String nomorKontak = mPasar.get(i).getNoTelepon();
                                        String linkVideo = mPasar.get(i).getUrlVideo();
                                        String deskripsi = mPasar.get(i).getDeskripsi();


                                        intent.putExtra("NamaPemilik", pemilik);
                                        intent.putExtra("Gambar1", gambar1);
                                        intent.putExtra("Gambar2", gambar2);
                                        intent.putExtra("Gambar3", gambar3);
                                        intent.putExtra("Jenis", jenis);
                                        intent.putExtra("Spesies", spesies);
                                        intent.putExtra("Lokasi", lokasi);
                                        intent.putExtra("Harga", harga);
                                        intent.putExtra("NomorKontak", nomorKontak);
                                        intent.putExtra("LinkVideo", linkVideo);
                                        intent.putExtra("Deskripsi", deskripsi);
                                        intent.putExtra("IdTernak", mPasar.get(i).getIdTernak().toString());
                                        intent.putExtra("Pemilik", mPasar.get(i).getIdUser().toString());
                                        startActivity(intent);
                                    }
                                });
                            }

                        }

                        @Override
                        public void onFailure(Call<PasarResponse> call, Throwable t) {
                            Toast.makeText(getApplication(),"No connection, Please check internet connection!!"+t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                } else if (kategoriFilter.equalsIgnoreCase("Harga")){
                    System.out.println("Masukin fungsi harga jenis hewan "+jenisFilter);
                    if (jenisFilter.equalsIgnoreCase("0 - 10.000.000")){
                        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                        retrofit2.Call<PasarResponse> call = apiService.getPasarByBetween1();
                        call.enqueue(new Callback<PasarResponse>() {
                            @Override
                            public void onResponse(Call<PasarResponse> call, Response<PasarResponse> response) {

                                ArrayList<Pasar> pasar = (ArrayList<Pasar>) response.body().getPasar();
                                mPasar = pasar;

                                if (mPasar == null){
                                    Toast.makeText(getApplication(), "Data tidak ditemukan!!", Toast.LENGTH_LONG).show();
                                    txtStatus.setVisibility(View.VISIBLE);
                                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                                    lvHewan.setVisibility(View.GONE);
                                } else {
                                    for (int i = 0; i < pasar.size() ; i++){
                                        System.out.println("Nama Pemilik = "+mPasar.get(i).getNamaDepan()+""+mPasar.get(i).getNamaBelakang()+" Kode Ternak = "+pasar.get(i).getIdTernak());
                                    }

                                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                                    txtStatus.setVisibility(View.GONE);
                                    lvHewan.setVisibility(View.VISIBLE);
                                    hewanAdapter = new ListHewanAdapter(getApplicationContext(), mPasar);
                                    lvHewan.setAdapter(hewanAdapter);
                                    lvHewan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Intent intent = new  Intent(getApplicationContext(), DetailHewanActivity.class);
                                            String pemilik = mPasar.get(i).getNamaDepan()+" "+mPasar.get(i).getNamaBelakang();
                                            //String gambarUtama = mHewan.get(i).getImgUtama();
                                            String gambar1 = mPasar.get(i).getFoto1();
                                            String gambar2 = mPasar.get(i).getFoto2();
                                            String gambar3 = mPasar.get(i).getFoto3();
                                            String jenis = mPasar.get(i).getJenisHewan();
                                            String spesies = mPasar.get(i).getSpesies();
                                            String lokasi = mPasar.get(i).getNamaDaerah();
                                            String harga = mPasar.get(i).getHarga().toString();
                                            String nomorKontak = mPasar.get(i).getNoTelepon();
                                            String linkVideo = mPasar.get(i).getUrlVideo();
                                            String deskripsi = mPasar.get(i).getDeskripsi();


                                            intent.putExtra("NamaPemilik", pemilik);
                                            intent.putExtra("Gambar1", gambar1);
                                            intent.putExtra("Gambar2", gambar2);
                                            intent.putExtra("Gambar3", gambar3);
                                            intent.putExtra("Jenis", jenis);
                                            intent.putExtra("Spesies", spesies);
                                            intent.putExtra("Lokasi", lokasi);
                                            intent.putExtra("Harga", harga);
                                            intent.putExtra("NomorKontak", nomorKontak);
                                            intent.putExtra("LinkVideo", linkVideo);
                                            intent.putExtra("Deskripsi", deskripsi);
                                            intent.putExtra("IdTernak", mPasar.get(i).getIdTernak().toString());
                                            intent.putExtra("Pemilik", mPasar.get(i).getIdUser().toString());
                                            startActivity(intent);
                                        }
                                    });
                                }

                            }

                            @Override
                            public void onFailure(Call<PasarResponse> call, Throwable t) {
                                Toast.makeText(getApplication(),"No connection, Please check internet connection!!"+t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    } else if (jenisFilter.equalsIgnoreCase("10.000.000 - 20.000.000")){
                        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                        retrofit2.Call<PasarResponse> call = apiService.getPasarByBetween2();
                        call.enqueue(new Callback<PasarResponse>() {
                            @Override
                            public void onResponse(Call<PasarResponse> call, Response<PasarResponse> response) {

                                ArrayList<Pasar> pasar = (ArrayList<Pasar>) response.body().getPasar();
                                mPasar = pasar;

                                if (mPasar == null){
                                    Toast.makeText(getApplication(), "Data tidak ditemukan!!", Toast.LENGTH_LONG).show();
                                    txtStatus.setVisibility(View.VISIBLE);
                                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                                    lvHewan.setVisibility(View.GONE);
                                }  else {
                                    for (int i = 0; i < pasar.size() ; i++){
                                        System.out.println("Nama Pemilik = "+mPasar.get(i).getNamaDepan()+""+mPasar.get(i).getNamaBelakang()+" Kode Ternak = "+pasar.get(i).getIdTernak());
                                    }

                                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                                    txtStatus.setVisibility(View.GONE);
                                    lvHewan.setVisibility(View.VISIBLE);
                                    hewanAdapter = new ListHewanAdapter(getApplicationContext(), mPasar);
                                    lvHewan.setAdapter(hewanAdapter);
                                    lvHewan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Intent intent = new  Intent(getApplicationContext(), DetailHewanActivity.class);
                                            String pemilik = mPasar.get(i).getNamaDepan()+" "+mPasar.get(i).getNamaBelakang();
                                            //String gambarUtama = mHewan.get(i).getImgUtama();
                                            String gambar1 = mPasar.get(i).getFoto1();
                                            String gambar2 = mPasar.get(i).getFoto2();
                                            String gambar3 = mPasar.get(i).getFoto3();
                                            String jenis = mPasar.get(i).getJenisHewan();
                                            String spesies = mPasar.get(i).getSpesies();
                                            String lokasi = mPasar.get(i).getNamaDaerah();
                                            String harga = mPasar.get(i).getHarga().toString();
                                            String nomorKontak = mPasar.get(i).getNoTelepon();
                                            String linkVideo = mPasar.get(i).getUrlVideo();
                                            String deskripsi = mPasar.get(i).getDeskripsi();


                                            intent.putExtra("NamaPemilik", pemilik);
                                            intent.putExtra("Gambar1", gambar1);
                                            intent.putExtra("Gambar2", gambar2);
                                            intent.putExtra("Gambar3", gambar3);
                                            intent.putExtra("Jenis", jenis);
                                            intent.putExtra("Spesies", spesies);
                                            intent.putExtra("Lokasi", lokasi);
                                            intent.putExtra("Harga", harga);
                                            intent.putExtra("NomorKontak", nomorKontak);
                                            intent.putExtra("LinkVideo", linkVideo);
                                            intent.putExtra("Deskripsi", deskripsi);
                                            intent.putExtra("IdTernak", mPasar.get(i).getIdTernak().toString());
                                            intent.putExtra("Pemilik", mPasar.get(i).getIdUser().toString());
                                            startActivity(intent);
                                        }
                                    });
                                }

                            }

                            @Override
                            public void onFailure(Call<PasarResponse> call, Throwable t) {
                                Toast.makeText(getApplication(),"No connection, Please check internet connection!!"+t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    } else if (jenisFilter.equalsIgnoreCase("20.000.000 - 30.000.000")){
                        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                        retrofit2.Call<PasarResponse> call = apiService.getPasarByBetween3();
                        call.enqueue(new Callback<PasarResponse>() {
                            @Override
                            public void onResponse(Call<PasarResponse> call, Response<PasarResponse> response) {

                                ArrayList<Pasar> pasar = (ArrayList<Pasar>) response.body().getPasar();
                                mPasar = pasar;

                                if (mPasar == null){
                                    Toast.makeText(getApplication(), "Data tidak ditemukan!!", Toast.LENGTH_LONG).show();
                                    txtStatus.setVisibility(View.VISIBLE);
                                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                                    lvHewan.setVisibility(View.GONE);
                                } else {
                                    for (int i = 0; i < pasar.size() ; i++){
                                        System.out.println("Nama Pemilik = "+mPasar.get(i).getNamaDepan()+""+mPasar.get(i).getNamaBelakang()+" Kode Ternak = "+pasar.get(i).getIdTernak());
                                    }

                                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                                    txtStatus.setVisibility(View.GONE);
                                    lvHewan.setVisibility(View.VISIBLE);
                                    hewanAdapter = new ListHewanAdapter(getApplicationContext(), mPasar);
                                    lvHewan.setAdapter(hewanAdapter);
                                    lvHewan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Intent intent = new  Intent(getApplicationContext(), DetailHewanActivity.class);
                                            String pemilik = mPasar.get(i).getNamaDepan()+" "+mPasar.get(i).getNamaBelakang();
                                            //String gambarUtama = mHewan.get(i).getImgUtama();
                                            String gambar1 = mPasar.get(i).getFoto1();
                                            String gambar2 = mPasar.get(i).getFoto2();
                                            String gambar3 = mPasar.get(i).getFoto3();
                                            String jenis = mPasar.get(i).getJenisHewan();
                                            String spesies = mPasar.get(i).getSpesies();
                                            String lokasi = mPasar.get(i).getNamaDaerah();
                                            String harga = mPasar.get(i).getHarga().toString();
                                            String nomorKontak = mPasar.get(i).getNoTelepon();
                                            String linkVideo = mPasar.get(i).getUrlVideo();
                                            String deskripsi = mPasar.get(i).getDeskripsi();


                                            intent.putExtra("NamaPemilik", pemilik);
                                            intent.putExtra("Gambar1", gambar1);
                                            intent.putExtra("Gambar2", gambar2);
                                            intent.putExtra("Gambar3", gambar3);
                                            intent.putExtra("Jenis", jenis);
                                            intent.putExtra("Spesies", spesies);
                                            intent.putExtra("Lokasi", lokasi);
                                            intent.putExtra("Harga", harga);
                                            intent.putExtra("NomorKontak", nomorKontak);
                                            intent.putExtra("LinkVideo", linkVideo);
                                            intent.putExtra("Deskripsi", deskripsi);
                                            intent.putExtra("IdTernak", mPasar.get(i).getIdTernak().toString());
                                            intent.putExtra("Pemilik", mPasar.get(i).getIdUser().toString());
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<PasarResponse> call, Throwable t) {
                                Toast.makeText(getApplication(),"No connection, Please check internet connection!!"+t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    } else if (jenisFilter.equalsIgnoreCase("> 30.000.000")){
                        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                        retrofit2.Call<PasarResponse> call = apiService.getPasarByBetween4();
                        call.enqueue(new Callback<PasarResponse>() {
                            @Override
                            public void onResponse(Call<PasarResponse> call, Response<PasarResponse> response) {

                                ArrayList<Pasar> pasar = (ArrayList<Pasar>) response.body().getPasar();
                                mPasar = pasar;
                                if (mPasar == null){
                                    Toast.makeText(getApplicationContext(),"Data tidak ada",Toast.LENGTH_LONG).show();
                                    txtStatus.setVisibility(View.VISIBLE);
                                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                                    lvHewan.setVisibility(View.GONE);
                                } else {
                                    for (int i = 0; i < pasar.size() ; i++){
                                        System.out.println("Nama Pemilik = "+mPasar.get(i).getNamaDepan()+""+mPasar.get(i).getNamaBelakang()+" Kode Ternak = "+pasar.get(i).getIdTernak());
                                    }

                                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                                    txtStatus.setVisibility(View.GONE);
                                    lvHewan.setVisibility(View.VISIBLE);
                                    hewanAdapter = new ListHewanAdapter(getApplicationContext(), mPasar);
                                    lvHewan.setAdapter(hewanAdapter);
                                    lvHewan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Intent intent = new  Intent(getApplicationContext(), DetailHewanActivity.class);
                                            String pemilik = mPasar.get(i).getNamaDepan()+" "+mPasar.get(i).getNamaBelakang();
                                            //String gambarUtama = mHewan.get(i).getImgUtama();
                                            String gambar1 = mPasar.get(i).getFoto1();
                                            String gambar2 = mPasar.get(i).getFoto2();
                                            String gambar3 = mPasar.get(i).getFoto3();
                                            String jenis = mPasar.get(i).getJenisHewan();
                                            String spesies = mPasar.get(i).getSpesies();
                                            String lokasi = mPasar.get(i).getNamaDaerah();
                                            String harga = mPasar.get(i).getHarga().toString();
                                            String nomorKontak = mPasar.get(i).getNoTelepon();
                                            String linkVideo = mPasar.get(i).getUrlVideo();
                                            String deskripsi = mPasar.get(i).getDeskripsi();


                                            intent.putExtra("NamaPemilik", pemilik);
                                            intent.putExtra("Gambar1", gambar1);
                                            intent.putExtra("Gambar2", gambar2);
                                            intent.putExtra("Gambar3", gambar3);
                                            intent.putExtra("Jenis", jenis);
                                            intent.putExtra("Spesies", spesies);
                                            intent.putExtra("Lokasi", lokasi);
                                            intent.putExtra("Harga", harga);
                                            intent.putExtra("NomorKontak", nomorKontak);
                                            intent.putExtra("LinkVideo", linkVideo);
                                            intent.putExtra("Deskripsi", deskripsi);
                                            intent.putExtra("IdTernak", mPasar.get(i).getIdTernak().toString());
                                            intent.putExtra("Pemilik", mPasar.get(i).getIdUser().toString());
                                            startActivity(intent);
                                        }
                                    });
                                }

                            }

                            @Override
                            public void onFailure(Call<PasarResponse> call, Throwable t) {
                                Toast.makeText(getApplication(),"No connection, Please check internet connection!!"+t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else {
                    System.out.println("Salah jenis hewan");
                }
            }
        });
    }


    //API pasar
    public void initPasar(){
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<PasarResponse> call = apiService.getPasar();
        call.enqueue(new Callback<PasarResponse>() {
            @Override
            public void onResponse(Call<PasarResponse> call, Response<PasarResponse> response) {
                ArrayList<Pasar> pasar = (ArrayList<Pasar>) response.body().getPasar();
                mPasar = pasar;
                if (mPasar == null){
                    Toast.makeText(getApplicationContext(),"Data tidak ada",Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < pasar.size() ; i++){
                        System.out.println("Nama Pemilik = "+mPasar.get(i).getNamaDepan()+""+mPasar.get(i).getNamaBelakang()+" Kode Ternak = "+pasar.get(i).getIdTernak());
                    }

                    lvHewan = (ListView) findViewById(R.id.list_view_home);
                    hewanAdapter = new ListHewanAdapter(getApplicationContext(), mPasar);
                    lvHewan.setAdapter(hewanAdapter);
                    lvHewan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new  Intent(getApplicationContext(), DetailHewanActivity.class);
                            String pemilik = mPasar.get(i).getNamaDepan()+" "+mPasar.get(i).getNamaBelakang();
                            //String gambarUtama = mHewan.get(i).getImgUtama();
                            String gambar1 = mPasar.get(i).getFoto1();
                            String gambar2 = mPasar.get(i).getFoto2();
                            String gambar3 = mPasar.get(i).getFoto3();
                            String jenis = mPasar.get(i).getJenisHewan();
                            String spesies = mPasar.get(i).getSpesies();
                            String lokasi = mPasar.get(i).getNamaDaerah();
                            String harga = mPasar.get(i).getHarga().toString();
                            String nomorKontak = mPasar.get(i).getNoTelepon();
                            String linkVideo = mPasar.get(i).getUrlVideo();
                            String deskripsi = mPasar.get(i).getDeskripsi();


                            intent.putExtra("NamaPemilik", pemilik);
                            intent.putExtra("Gambar1", gambar1);
                            intent.putExtra("Gambar2", gambar2);
                            intent.putExtra("Gambar3", gambar3);
                            intent.putExtra("Jenis", jenis);
                            intent.putExtra("Spesies", spesies);
                            intent.putExtra("Lokasi", lokasi);
                            intent.putExtra("Harga", harga);
                            intent.putExtra("NomorKontak", nomorKontak);
                            intent.putExtra("LinkVideo", linkVideo);
                            intent.putExtra("Deskripsi", deskripsi);
                            intent.putExtra("IdTernak", mPasar.get(i).getIdTernak().toString());
                            intent.putExtra("Pemilik", mPasar.get(i).getIdUser().toString());
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<PasarResponse> call, Throwable t) {
                txtStatus.setText("Tidak dapat terhubung ke server");
                //Toast.makeText(getApplicationContext(), "No connected to server, please check internet connections", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);

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


    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
        private Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context activity, ArrayList<String> asr) {
            this.activity = activity;
            this.asr = asr;
        }

        @Override
        public int getCount() {
            return asr.size();
        }

        @Override
        public Object getItem(int i) {
            return asr.get(i);
        }

        @Override
        public long getItemId(int i) {
            return (long)i;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(HomeActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(HomeActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(16);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop_down, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }
    }

}
