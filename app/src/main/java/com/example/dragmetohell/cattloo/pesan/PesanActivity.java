package com.example.dragmetohell.cattloo.pesan;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.dragmetohell.cattloo.profil.ProfilActivity;
import com.example.dragmetohell.cattloo.ternakku.TernakkuActivity;

import java.util.ArrayList;

import helper.AkunHelper;
import helper.SessionHelper;
import model.ListPesananAdapter;
import model.ListSampelAdapter;
import model.Pesanan;
import model.PesananResponse;
import model.Sampel;
import model.SampelResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ArrayList<Sampel> mSampel;
    ArrayList<Pesanan> mPesanan;
    private RecyclerView recyclerView, recyclerViewPesanan;
    private ListSampelAdapter adapter;
    private ListPesananAdapter pesananAdapter;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser, inIdLokasi, kodePesan;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, inKodeSampel, currDate;
    private TextView txtNoData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new PesanActivity.SectionsPagerAdapter(getSupportFragmentManager());

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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(PesanActivity.this);

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
                    SessionHelper dbHelper =  new SessionHelper(PesanActivity.this);
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
        } else if (id == R.id.refresh){
            //set table pesanan
            recyclerViewPesanan = (RecyclerView) findViewById(R.id.recyclerPesanan);
            txtNoData = (TextView) findViewById(R.id.txtNoData);

            initAkun();

            APIInterface apiService = APIClient.getURL().create(APIInterface.class);
            retrofit2.Call<PesananResponse> call = apiService.getPesanan(inIdUser);
            call.enqueue(new Callback<PesananResponse>() {
                @Override
                public void onResponse(Call<PesananResponse> call, Response<PesananResponse> response) {
                    ArrayList<Pesanan> pesanan = (ArrayList<Pesanan>) response.body().getPesanan();
                    mPesanan = pesanan;
                    System.out.println("Pesanan = "+pesanan);

                    recyclerViewPesanan.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerViewPesanan.setLayoutManager(layoutManager);
                    pesananAdapter = new ListPesananAdapter(mPesanan, getApplicationContext());
                    if (mPesanan != null){
                        txtNoData.setVisibility(View.INVISIBLE);
                        recyclerViewPesanan.setAdapter(pesananAdapter);

                    } else {
                        Toast.makeText(getApplicationContext(),"Tidak ada data pemesanan", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<PesananResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Tidak ada data", Toast.LENGTH_LONG).show();
                }
            });


            //setsample
            recyclerView = (RecyclerView) findViewById(R.id.recyclerSampel);
            APIInterface apiService2 = APIClient.getURL().create(APIInterface.class);
            retrofit2.Call<SampelResponse> call2 = apiService2.getSampel();
            call2.enqueue(new Callback<SampelResponse>() {
                @Override
                public void onResponse(Call<SampelResponse> call, Response<SampelResponse> response) {
                    ArrayList<Sampel> sampel = (ArrayList<Sampel>) response.body().getSampel();
                    mSampel = sampel;
                    if (mSampel == null){
                        System.out.println("sampel tidak ada ");
                    } else {
                        System.out.println("sampel : "+sampel);
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager2);
                        adapter = new ListSampelAdapter(getApplicationContext(), mSampel, new ListSampelAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClickListener(Sampel sampel) {
                                Toast.makeText(getApplicationContext(),"Clicked !!!",Toast.LENGTH_LONG);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(Call<SampelResponse> call, Throwable t) {
                    Log.d("Error",t.getMessage());
                }
            });
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




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        ArrayList<Sampel> mSampel;
        ArrayList<Pesanan> mPesanan;
        private RecyclerView recyclerView, recyclerViewPesanan;
        private ListSampelAdapter adapter;
        private ListPesananAdapter pesananAdapter;
        protected SessionHelper dbHelper;
        protected SQLiteDatabase db;
        ArrayList<AkunHelper> akunHelper;
        int inIdUser, inIdLokasi, kodePesan;
        String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, inKodeSampel, currDate;
        private TextView txtNoData;

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



            View rootView = null;
            if (section == 1){
                rootView = inflater.inflate(R.layout.fragment_list_pesan, container, false);
                recyclerViewPesanan = (RecyclerView) rootView.findViewById(R.id.recyclerPesanan);
                txtNoData = (TextView) rootView.findViewById(R.id.txtNoData);

                initAkun();

                APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                retrofit2.Call<PesananResponse> call = apiService.getPesanan(inIdUser);
                final View finalRootView = rootView;
                call.enqueue(new Callback<PesananResponse>() {
                    @Override
                    public void onResponse(Call<PesananResponse> call, Response<PesananResponse> response) {
                        ArrayList<Pesanan> pesanan = (ArrayList<Pesanan>) response.body().getPesanan();
                        mPesanan = pesanan;
                        System.out.println("Pesanan = "+pesanan);

                        recyclerViewPesanan.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerViewPesanan.setLayoutManager(layoutManager);
                        pesananAdapter = new ListPesananAdapter(mPesanan, getContext());
                        if (mPesanan != null){
                            txtNoData.setVisibility(finalRootView.INVISIBLE);
                            recyclerViewPesanan.setAdapter(pesananAdapter);

                        } else {
                            Toast.makeText(getContext(),"Tidak ada data pemesanan", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<PesananResponse> call, Throwable t) {
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
                rootView = inflater.inflate(R.layout.fragment_pesan, container, false);
                recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerSampel);

                initSampel();

            }
            return rootView;
        }

        public void initSampel(){
            APIInterface apiService = APIClient.getURL().create(APIInterface.class);
            retrofit2.Call<SampelResponse> call = apiService.getSampel();
            call.enqueue(new Callback<SampelResponse>() {
                @Override
                public void onResponse(Call<SampelResponse> call, Response<SampelResponse> response) {
                    ArrayList<Sampel> sampel = (ArrayList<Sampel>) response.body().getSampel();
                    mSampel = sampel;
                    System.out.println("sampel : "+sampel);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager2);
                    adapter = new ListSampelAdapter(getContext(), mSampel, new ListSampelAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClickListener(Sampel sampel) {
                            Toast.makeText(getContext(),"Clicked !!!",Toast.LENGTH_LONG);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<SampelResponse> call, Throwable t) {
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

        public void initPesanan(){

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
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Pesananku";
                case 1:
                    return "Lihat Sampel";
            }
            return null;
        }
    }
}
