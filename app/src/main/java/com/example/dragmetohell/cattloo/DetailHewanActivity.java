package com.example.dragmetohell.cattloo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.history.HistoryActivity;
import com.example.dragmetohell.cattloo.pesan.PesanActivity;
import com.example.dragmetohell.cattloo.ternakku.DetailTernakkuActivity;
import com.example.dragmetohell.cattloo.ternakku.TernakkuActivity;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import helper.AkunHelper;
import helper.SessionHelper;
import me.relex.circleindicator.CircleIndicator;
import model.Belanja;
import model.BelanjaResponse;
import model.ListProfilAdapter;
import model.ModelProfil;
import model.PasarResponse;
import model.PesanResponse;
import model.UpdateStatusPasarResponse;
import model.UserResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHewanActivity extends AppCompatActivity {
    String txtPemilik, txtLinkVideo, txtJenisSpesies, txtLokasi, txtHarga, txtKontak, deskripsi;
    String[] images;
    ViewPager viewPager;
    ListGambarAdapter gambarHewanAdapter;
    Button btnKandangkan;
    ListView lvDetailPasar;
    ArrayList<ModelProfil> listProfil;
    ListProfilAdapter listProfilAdapter;
    TextView txtDeskripsi;
    int idTernak;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, inKodeSampel, currDate;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser, inIdLokasi, id_belanja, pemilik;
    private static final String TAG = DetailHewanActivity.class.getSimpleName();
    ArrayList<Belanja> mBelanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hewan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnKandangkan = (Button) findViewById(R.id.btnKandangkan);
        lvDetailPasar = (ListView) findViewById(R.id.listDetailPasar);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskrips);

        initAkun();

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //format uang
            Double hargaRupiah = Double.valueOf(bundle.getString("Harga"));
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');

            kursIndonesia.setDecimalFormatSymbols(formatRp);

            //get current date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            currDate = dateFormat.format(date);



            String gambar1,gambar2,gambar3;
            txtPemilik = bundle.getString("NamaPemilik");
            txtJenisSpesies = bundle.getString("Jenis")+" / "+bundle.getString("Spesies");
            txtLokasi = bundle.getString("Lokasi");
            txtHarga = kursIndonesia.format(hargaRupiah);
            txtKontak = bundle.getString("NomorKontak");
            txtLinkVideo = bundle.getString("LinkVideo");
            deskripsi = bundle.getString("Deskripsi") ;
            idTernak = Integer.valueOf(bundle.getString("IdTernak"));
            pemilik = Integer.valueOf(bundle.getString("Pemilik"));

//            txtLinkVideo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    try {
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("LinkVideo"))));
//                    } catch (Exception e){
//                        Toast.makeText(getApplicationContext(), "Plase install Youtube before running the app", Toast.LENGTH_SHORT).show();
//                    }
////                    Intent videoIntent = new Intent(Intent.ACTION_VIEW);
////                    videoIntent.setData(Uri.parse(bundle.getString("LinkVideo")));
////                    videoIntent.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
////                    startActivity(videoIntent);
//                }
//            });
            //coba di pindah ke adapter
            gambar1 = bundle.getString("Gambar1");
            gambar2 = bundle.getString("Gambar2");
            gambar3 = bundle.getString("Gambar3");
            images = new String[]{gambar1, gambar2, gambar3};
            for (int i = 0; i < images.length; i++){
                System.out.println("Index ke"+ i +",isinya :"+images[i]);
            }
            viewPager = (ViewPager) findViewById(R.id.viewPager);
            gambarHewanAdapter = new ListGambarAdapter();
            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
            viewPager.setAdapter(gambarHewanAdapter);
            indicator.setViewPager(viewPager);

        }

        setDetail();
        listProfilAdapter = new ListProfilAdapter(this, listProfil);
        lvDetailPasar.setAdapter(listProfilAdapter);
        setListViewHeightBasedOnChildren(lvDetailPasar);
        txtDeskripsi.setText(deskripsi);
        btnKandangkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "ID Ternak : "+idTernak,Toast.LENGTH_LONG).show();
//                sendPost(id_belanja, currDate, Integer.valueOf(bundle.getString("Harga")), idTernak, 0, inIdUser);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailHewanActivity.this);
                alertDialog.setTitle("Informasi");
                alertDialog.setMessage("Ternak yang anda kandangkan akan tampil pada menu laporan belanja. Apakah anda yakin akan mengkandangkan ternak ini?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        APIInterface apiService2 = APIClient.getURL().create(APIInterface.class);
                        retrofit2.Call<BelanjaResponse> call = apiService2.getBelanja(inIdUser, idTernak);
                        call.enqueue(new Callback<BelanjaResponse>() {
                            @Override
                            public void onResponse(Call<BelanjaResponse> call, Response<BelanjaResponse> response) {
                                ArrayList<Belanja> belanjas = (ArrayList<Belanja>) response.body().getBelanja();
                                mBelanja = belanjas;
                                //System.out.println("EWWWWW : "+mBelanja);
                                if (pemilik == inIdUser){
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailHewanActivity.this);
                                    alertDialog.setTitle("Peringatan !!!");
                                    alertDialog.setMessage("Ternak yang anda pilih adalah ternak anda");
                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                                    alertDialog.show();
                                } else if (mBelanja == null){
                                    APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                                    apiService.addBelanja( id_belanja , currDate, Integer.valueOf(bundle.getString("Harga")), idTernak, 0 , inIdUser).enqueue(new Callback<BelanjaResponse>() {
                                        @Override
                                        public void onResponse(Call<BelanjaResponse> call, Response<BelanjaResponse> response) {
                                            Log.i(TAG, "post submitted to API." + response.body().toString());
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intent);
                                            //Toast.makeText(getApplicationContext(), "Berhasil melakukan pembelian", Toast.LENGTH_LONG).show();
                                        }

                                        @Override
                                        public void onFailure(Call<BelanjaResponse> call, Throwable t) {

                                        }
                                    });
                                    apiService.updateTransaksi(0, "1", idTernak).enqueue(new Callback<UpdateStatusPasarResponse>() {
                                        @Override
                                        public void onResponse(Call<UpdateStatusPasarResponse> call, Response<UpdateStatusPasarResponse> response) {
                                            Log.i(TAG, "post submitted to API." + response.body().toString());
                                            System.out.println("Data berhasil dikirim");
                                            //Toast.makeText(getApplicationContext(), "Berhasil membatalkan posting dipasar", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), TernakkuActivity.class);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<UpdateStatusPasarResponse> call, Throwable t) {
                                            t.printStackTrace();
                                            //Toast.makeText(getApplicationContext(), "Berhasil membatalkan posting dipasar", Toast.LENGTH_LONG).show();

                                        }
                                    });
                                } else{
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailHewanActivity.this);
                                    alertDialog.setTitle("Peringatan !!!");
                                    alertDialog.setMessage("Anda sudah memesan ternak ini, mohon tunggu konfirmasi selanjutnya");
                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                                    alertDialog.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BelanjaResponse> call, Throwable t) {

                            }
                        });

                        Toast.makeText(getApplicationContext(), "Berhasil mengkandangkan ternak", Toast.LENGTH_LONG).show();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });

                alertDialog.show();





//
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private ArrayList<ModelProfil> setDetail(){
        listProfil = new ArrayList<>();
        ModelProfil modelProfil = new ModelProfil("profil_icon_money", "Harga", txtHarga);
        listProfil.add(modelProfil);

        modelProfil = new ModelProfil("profil_icon_cow", "Jenis / Spesies", txtJenisSpesies);
        listProfil.add(modelProfil);

        modelProfil = new ModelProfil("profil_icon_name", "Nama Pemilik", txtPemilik);
        listProfil.add(modelProfil);

        modelProfil = new ModelProfil("profil_icon_location", "Lokasi", txtLokasi);
        listProfil.add(modelProfil);

        modelProfil = new ModelProfil("profil_icon_phone", "No Telepon", txtKontak);
        listProfil.add(modelProfil);

        modelProfil = new ModelProfil("profil_icon_tv", "Video", txtLinkVideo);
        listProfil.add(modelProfil);
//
//        modelProfil = new ModelProfil("profil_icon_file", "Deskripsi", deskripsi);
//        listProfil.add(modelProfil);

        return listProfil;
    }

    private class ListGambarAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = DetailHewanActivity.this;
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(context).load(images[position]).into(imageView);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
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

    private void sendPost(int idBelanja, String date, int harga, int idTernak, int statusBeli, int idUser) {
        System.out.println("Yang itu  : "+idBelanja +" : "+date+" : "+harga+" : "+idTernak+" : "+0+" : "+idUser);
        //Toast.makeText(getApplicationContext(),"Yang itu  : "+kodePesan +" : "+kodeSampel+" : "+idUser+" : "+tglPesan+" : "+statusPesan, Toast.LENGTH_LONG).show();

    }




}
