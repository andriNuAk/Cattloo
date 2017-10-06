package com.example.dragmetohell.cattloo.laporan;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.dragmetohell.cattloo.pesan.PesanActivity;
import com.example.dragmetohell.cattloo.ternakku.DetailTernakkuActivity;
import com.example.dragmetohell.cattloo.ternakku.TernakkuActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import helper.AkunHelper;
import helper.ModelBelanja;
import helper.SessionHelper;
import model.Belanja;
import model.BelanjaResponse;
import model.LaporanBelanja;
import model.LaporanBelanjaResponse;
import model.ListLapBeliAdapter;
import model.ListLapJualAdapter;
import model.ListLapPerawatanAdapter;
import model.ListLapStokAdapter;
import model.ListTernakkuAdapter;
import model.ModelTernak;
import model.Pasar;
import model.PasarResponse;
import model.Penjualan;
import model.PenjualanResponse;
import model.Perawatan;
import model.PerawatanResponse;
import model.Ternakku;
import model.TernakkuResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ArrayList<ModelTernak> mTernak;
    private String kategori;
    ListView lvLapBeli, lvLapJual, lvLapPerawatan, lvLapStok;
    ListLapBeliAdapter lapBeliAdapter;
    ListLapJualAdapter lapJualAdapter;
    ListLapPerawatanAdapter lapPerawatanAdapter;
    ListLapStokAdapter lapStokAdapter;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, inKodeSampel, currDate;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser, inIdLokasi, id_belanja, pemilik;
    ArrayList<Belanja> mBelanja;
    ArrayList<Pasar> mPasar;
    ArrayList<LaporanBelanja> mLaporanBelanja;
    ArrayList<Penjualan> mPenjualan;
    ArrayList<Perawatan> mPerawatan;
    TextView txtTotal, txtTotalJual, txtTotalPerawatan, txtTotalStok;
    ArrayList<Ternakku> mTernakku;
    int totalBeli = 0;
    int totalJual, totalPerawatan, totalStok;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new LaporanActivity.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initAkun();


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
        getMenuInflater().inflate(R.menu.menu_laporan, menu);
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
            return true;
        } else if (id == R.id.logout){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LaporanActivity.this);

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
                        SessionHelper dbHelper =  new SessionHelper(LaporanActivity.this);
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


//        lvTernak.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new  Intent(getApplicationContext(), DetailTernakkuActivity.class);
//                String kodeTernak = mTernak.get(i).getKodeTernak();
//                String jenisHewan = mTernak.get(i).getJenisHewan();
//                String spesies = mTernak.get(i).getSpesies();
//                String caraTernak = mTernak.get(i).getCaraTernak();
//                String namaPeternak = mTernak.get(i).getNamaPeternak();
//                String namaAsisten = mTernak.get(i).getNamaAsisten();
//                String imgUtama = mTernak.get(i).getImgUtama();
//                String img1 = mTernak.get(i).getImgDetail1();
//                String img2 = mTernak.get(i).getImgDetail2();
//                String img3 = mTernak.get(i).getImgDetail3();
//                String video = mTernak.get(i).getUrlVIdeo();
//
//                intent.putExtra("KodeTernak", kodeTernak);
//                intent.putExtra("JenisHewan", jenisHewan);
//                intent.putExtra("Spesies", spesies);
//                intent.putExtra("CaraTernak", caraTernak);
//                intent.putExtra("NamaPeternak", namaPeternak);
//                intent.putExtra("NamaAsisten", namaAsisten);
//                intent.putExtra("ImgUtama", imgUtama);
//                intent.putExtra("Img1", img1);
//                intent.putExtra("Img2", img2);
//                intent.putExtra("Img3", img3);
//                intent.putExtra("Video", video);
//
//
//                startActivity(intent);
//            }
//        });
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




    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private SectionsPagerAdapter mSectionsPagerAdapter;
        private ArrayList<ModelTernak> mTernak;
        private String kategori;
        ListView lvLapBeli, lvLapJual, lvLapPerawatan, lvLapStok;
        ListLapBeliAdapter lapBeliAdapter;
        ListLapJualAdapter lapJualAdapter;
        ListLapPerawatanAdapter lapPerawatanAdapter;
        ListLapStokAdapter lapStokAdapter;
        String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, inKodeSampel, currDate;
        protected SessionHelper dbHelper;
        protected SQLiteDatabase db;
        ArrayList<AkunHelper> akunHelper;
        int inIdUser, inIdLokasi, id_belanja, pemilik;
        ArrayList<Belanja> mBelanja;
        ArrayList<Pasar> mPasar;
        ArrayList<LaporanBelanja> mLaporanBelanja;
        ArrayList<Penjualan> mPenjualan;
        ArrayList<Perawatan> mPerawatan;
        TextView txtTotal, txtTotalJual, txtTotalPerawatan, txtTotalStok, txtStatusList;
        ArrayList<Ternakku> mTernakku;
        int totalBeli = 0;
        int totalJual, totalPerawatan, totalStok;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int section = getArguments().getInt(ARG_SECTION_NUMBER);
            initModelTernak();
            initAkun();
            View rootView = null;
            APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
            if (section == 1){
                rootView = inflater.inflate(R.layout.fragment_lap_beli, container, false);
                txtTotal = (TextView) rootView.findViewById(R.id.txtTotalBeli);
                txtStatusList = (TextView) rootView.findViewById(R.id.txtStatusList);
                lvLapBeli = (ListView) rootView.findViewById(R.id.list_beli);
                totalBeli = 0;
                final View finalRootView = rootView;

                retrofit2.Call<LaporanBelanjaResponse> call = apiInterface.getLaporanBelanja(inIdUser);
                call.enqueue(new Callback<LaporanBelanjaResponse>() {
                    @Override
                    public void onResponse(Call<LaporanBelanjaResponse> call, Response<LaporanBelanjaResponse> response) {
                        ArrayList<LaporanBelanja> laporanBelanjas = (ArrayList<LaporanBelanja>) response.body().getLaporanBelanja();
                        mLaporanBelanja = laporanBelanjas;
                        // bikin exception
                        if (mLaporanBelanja == null){
//                            txtTotalPerawatan.setText("Tidak ada data");
                            txtStatusList.setVisibility(View.VISIBLE);
                            txtStatusList.setText("Tidak Ada Data");
                            txtTotal.setVisibility(View.GONE);
//                            lvLapBeli.setVisibility(View.GONE);
                        } else {
                            for (int i=0; i<mLaporanBelanja.size();i++){
                                totalBeli = totalBeli + mLaporanBelanja.get(i).getHarga();
                            }
                            txtStatusList.setVisibility(View.GONE);
                            txtTotal.setVisibility(View.VISIBLE);
                            lvLapBeli.setVisibility(View.VISIBLE);
                            Double hargaRupiah = Double.valueOf(totalBeli);
                            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                            formatRp.setCurrencySymbol("Rp. ");
                            formatRp.setMonetaryDecimalSeparator(',');
                            formatRp.setGroupingSeparator('.');

                            kursIndonesia.setDecimalFormatSymbols(formatRp);

                            txtTotal.setText("Total belanja : "+kursIndonesia.format(hargaRupiah));
                            lvLapBeli = (ListView) finalRootView.findViewById(R.id.list_beli);
                            lapBeliAdapter = new ListLapBeliAdapter(getActivity(), mLaporanBelanja);
                            lvLapBeli.setAdapter(lapBeliAdapter);
                        }


                    }

                    @Override
                    public void onFailure(Call<LaporanBelanjaResponse> call, Throwable t) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

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


            } else if (section == 2){
                rootView = inflater.inflate(R.layout.fragment_lap_jual, container, false);
                totalBeli = 0;
                totalJual = 0;
                txtTotalJual = (TextView) rootView.findViewById(R.id.txtTotalJual);
                txtStatusList = (TextView) rootView.findViewById(R.id.txtStatusList);
                final View finalRootView = rootView;
                //APIInterface apiInterface2 = APIClient.getURL().create(APIInterface.class);
                retrofit2.Call<PenjualanResponse> call2 = apiInterface.getPenjualan(inIdUser);
                call2.enqueue(new Callback<PenjualanResponse>() {
                    @Override
                    public void onResponse(Call<PenjualanResponse> call, Response<PenjualanResponse> response) {
                        ArrayList<Penjualan> laporanPenjualan = (ArrayList<Penjualan>) response.body().getPenjualan();
                        mPenjualan = laporanPenjualan;

                        if (mPerawatan == null){
//                            txtTotalPerawatan.setText("Tidak ada data");
                            txtStatusList.setVisibility(View.VISIBLE);
                            txtStatusList.setText("Tidak Ada Data");
//                            lvLapPerawatan.setVisibility(View.GONE);
                        } else {
                            for (int i=0; i<mPenjualan.size();i++){
                                totalJual = totalJual + mPenjualan.get(i).getHargaJual();
                            }

                            Double hargaRupiah = Double.valueOf(totalJual);
                            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                            formatRp.setCurrencySymbol("Rp. ");
                            formatRp.setMonetaryDecimalSeparator(',');
                            formatRp.setGroupingSeparator('.');

                            kursIndonesia.setDecimalFormatSymbols(formatRp);

                            txtTotalJual.setText("Total penjualan : "+kursIndonesia.format(hargaRupiah));

                            lvLapJual = (ListView) finalRootView.findViewById(R.id.list_jual);
                            lapJualAdapter = new ListLapJualAdapter(getActivity(), mPenjualan);
                            lvLapJual.setAdapter(lapJualAdapter);
                        }


                    }

                    @Override
                    public void onFailure(Call<PenjualanResponse> call, Throwable t) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

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


            } else if (section == 3){
                rootView = inflater.inflate(R.layout.fragment_lap_perawatan, container, false);
                final View finalRootView = rootView;
                txtTotalPerawatan = (TextView) rootView.findViewById(R.id.txtTotalPerawatan);
                txtStatusList = (TextView) rootView.findViewById(R.id.txtStatusList);
                totalBeli = 0;
                totalPerawatan = 0;
                //APIInterface apiInterface2 = APIClient.getURL().create(APIInterface.class);
                retrofit2.Call<PerawatanResponse> call3 = apiInterface.getPerawatan(inIdUser);
                call3.enqueue(new Callback<PerawatanResponse>() {
                    @Override
                    public void onResponse(Call<PerawatanResponse> call, Response<PerawatanResponse> response) {
                        ArrayList<Perawatan> laporanPeawatan = (ArrayList<Perawatan>) response.body().getPerawatan();
                        mPerawatan = laporanPeawatan;
                        if (mPerawatan == null){
//                            txtTotalPerawatan.setText("Tidak ada data");
                            txtStatusList.setVisibility(View.VISIBLE);
                            txtStatusList.setText("Tidak Ada Data");
//                            lvLapPerawatan.setVisibility(View.GONE);
                        } else {
                            for (int i=0; i<mPerawatan.size();i++){
                                totalPerawatan = totalPerawatan + mPerawatan.get(i).getTotalHarga();
                            }

                            Double hargaRupiah = Double.valueOf(totalPerawatan);
                            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                            formatRp.setCurrencySymbol("Rp. ");
                            formatRp.setMonetaryDecimalSeparator(',');
                            formatRp.setGroupingSeparator('.');

                            kursIndonesia.setDecimalFormatSymbols(formatRp);

                            txtTotalPerawatan.setText("Total perawatan : "+kursIndonesia.format(hargaRupiah));

                            lvLapPerawatan = (ListView) finalRootView.findViewById(R.id.list_perawatan);
                            lapPerawatanAdapter = new ListLapPerawatanAdapter(getActivity(), mPerawatan);
                            lvLapPerawatan.setAdapter(lapPerawatanAdapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<PerawatanResponse> call, Throwable t) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

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


            } else if (section == 4){
                rootView = inflater.inflate(R.layout.fragment_stok, container, false);
                totalBeli = 0;
                totalStok = 0;
                txtTotalStok = (TextView) rootView.findViewById(R.id.txtTotalStok);
                txtStatusList = (TextView) rootView.findViewById(R.id.txtStatusList);
                retrofit2.Call<TernakkuResponse> call = apiInterface.getTernakUser(inIdUser);
                final View finalRootView1 = rootView;
                call.enqueue(new Callback<TernakkuResponse>() {
                    @Override
                    public void onResponse(Call<TernakkuResponse> call, Response<TernakkuResponse> response) {
                        ArrayList<Ternakku> ternakku = (ArrayList<Ternakku>) response.body().getTernakku();
                        mTernakku = ternakku;
                        if (mTernakku == null){
//                            txtTotalPerawatan.setText("Tidak ada data");
                            txtStatusList.setVisibility(View.VISIBLE);
                            txtStatusList.setText("Tidak Ada Data");
//                            lvLapPerawatan.setVisibility(View.GONE);

                        } else {
                            for (int i=0; i<mTernakku.size();i++){
                                totalStok = totalStok + mTernakku.get(i).getHarga();
                            }

                            Double hargaRupiah = Double.valueOf(totalStok);
                            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                            formatRp.setCurrencySymbol("Rp. ");
                            formatRp.setMonetaryDecimalSeparator(',');
                            formatRp.setGroupingSeparator('.');

                            kursIndonesia.setDecimalFormatSymbols(formatRp);
                            txtTotalStok.setText("Benefit pendapatan : "+kursIndonesia.format(hargaRupiah));
                            lvLapStok = (ListView) finalRootView1.findViewById(R.id.list_stok);
                            lapStokAdapter = new ListLapStokAdapter(getActivity(), mTernakku);
                            lvLapStok.setAdapter(lapStokAdapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<TernakkuResponse> call, Throwable t) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

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

            return rootView;
        }

        public void initAkun(){
//        for (int i=0 ; i < akunHelpers.size(); i++){
//            kodeKeluarga = akunHelpers.get(i).getKodeKeluarga();
//            noIdentitas = akunHelpers.get(i).getNoIdentitas();
//            username = akunHelpers.get(i).getUsername();
//            password = akunHelpers.get(i).getPassword();
//        }

            dbHelper = new SessionHelper(getContext());
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

        private ArrayList<ModelTernak> initModelTernak() {
            mTernak = new ArrayList<>();
            ModelTernak ternak = new ModelTernak("T001", "Domba", "Domba Garut", "Garut", "7000000", "Growing", "Aziz Firmansyah", "Opik Sutisna", "http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg", "http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg", "http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg", "http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg",
                    "https://youtu.be/RoNsKd3J6Ww", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt diam sed lectus tincidunt, quis congue purus aliquet. Morbi urna risus, consequat quis tincidunt non, semper eu neque. In dapibus, nulla ac euismod cursus, enim enim hendrerit nisi, ac consectetur ante tortor ac risus. Integer pellentesque luctus est, eu faucibus enim tempor vulputate. Nunc vel tincidunt erat. Ut sit amet nulla lacinia, fermentum quam sit amet, condimentum metus. Donec eu massa dolor.\n" +
                    "\n" +
                    "Suspendisse molestie tortor eget commodo mollis. Nam quis libero facilisis, pharetra sem sed, ultrices ante. Nulla in dui ac lorem consequat iaculis lobortis in risus. Quisque turpis lorem, varius a consequat blandit, sollicitudin elementum odio. Vestibulum in elementum magna. Curabitur lacus tortor, aliquet vitae urna non, auctor laoreet quam. Ut mauris lectus, finibus eget mi sit amet, suscipit tincidunt est. Aliquam quis consectetur est, consequat volutpat ligula. Ut lectus nulla, vehicula nec ultricies in, blandit eu ipsum.\n" +
                    "\n" +
                    "Donec semper nunc vestibulum massa tempor pellentesque at in metus. Praesent ut nulla metus. Quisque ac dignissim orci. Mauris pellentesque tortor vitae sagittis auctor. Pellentesque accumsan nulla quis erat aliquet, placerat scelerisque ipsum lacinia. Duis et convallis augue, vel placerat est. Integer cursus odio felis, sed mattis lacus tempor dictum.");
            mTernak.add(ternak);

            return mTernak;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Belanja";
                case 1:
                    return "Jual";
                case 2:
                    return "Perawatan";
                case 3:
                    return "Stok";
            }
            return null;
        }
    }
}
