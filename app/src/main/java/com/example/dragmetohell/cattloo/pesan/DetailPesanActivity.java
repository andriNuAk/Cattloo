package com.example.dragmetohell.cattloo.pesan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.DetailHewanActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.RegistActivity;
import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import helper.AkunHelper;
import helper.SessionHelper;
import me.relex.circleindicator.CircleIndicator;
import model.Kota;
import model.KotaResponse;
import model.ListSampelAdapter;
import model.PesanResponse;
import model.Sampel;
import model.SampelResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class DetailPesanActivity extends AppCompatActivity {
    TextView txtTest;
    MaterialBetterSpinner materialBetterSpinner ;
    String txtPemilik, txtLinkVideo, txtJenisSpesies, txtLokasi, txtHarga, txtKontak, deskripsi, txtkota;
    ViewPager viewPager;
    ListGambarAdapter gambarHewanAdapter;
    TextView textHarga,textSpesies, textBerat, textPanjang, textTinggi, textVideo, textDeskripsi, textKetentuan;
    CheckBox checkKetentuan;
    EditText editEkor;
    Button btnPesan;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser, inIdLokasi, kodePesan;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang, inKodeSampel, currDate;
    ArrayList<Kota> mKota;
    private ListSampelAdapter adapter;
    private static final String TAG = DetailPesanActivity.class.getSimpleName();


    String[] SPINNER_DATA = {"Bandung","Kab.Bandung Barat","Cimahi","Kab.Bandung"};
    String[] images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textHarga = (TextView) findViewById(R.id.txtHarga);
        textSpesies = (TextView) findViewById(R.id.txtSpesies);
        textBerat = (TextView) findViewById(R.id.txtBerat);
        textPanjang = (TextView) findViewById(R.id.txtPanjang);
        textTinggi = (TextView) findViewById(R.id.txtTinggi);
        textVideo = (TextView) findViewById(R.id.txtVideo);
        textDeskripsi = (TextView) findViewById(R.id.txtDeskrips);
        editEkor = (EditText) findViewById(R.id.txtJumlahEkor);
        btnPesan = (Button) findViewById(R.id.btnPesan);
        textKetentuan = (TextView) findViewById(R.id.txtKetentuan);
        checkKetentuan = (CheckBox) findViewById(R.id.checkKetentuan);
        //txtTest = (TextView) findViewById(R.id.txtDetail);
        initAkun();
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //kurs uang
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

            //txtTest.setText(bundle.getString("Harga"));
            String gambar1,gambar2,gambar3;
            System.out.println("iniiiiiiiiii harga "+bundle.getString("Harga"));
            gambar1 = bundle.getString("Foto1");
            gambar2 = bundle.getString("Foto2");
            gambar3 = bundle.getString("Foto3");
            images = new String[]{gambar1, gambar2, gambar3};
            for (int i = 0; i < images.length; i++){
                System.out.println("Index ke"+ i +",isinya :"+images[i]);
            }
            viewPager = (ViewPager) findViewById(R.id.viewPagerPesan);
            gambarHewanAdapter = new DetailPesanActivity.ListGambarAdapter();
            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
            viewPager.setAdapter(gambarHewanAdapter);
            indicator.setViewPager(viewPager);

            inKodeSampel = bundle.getString("KodeSampel");
            textHarga.setText(kursIndonesia.format(hargaRupiah));
            textSpesies.setText(bundle.getString("Spesies"));
            textBerat.setText(bundle.getString("Berat")+" Kg");
            textPanjang.setText(bundle.getString("Panjang")+" cm");
            textTinggi.setText(bundle.getString("Tinggi")+" cm");
            textVideo.setText(bundle.getString("Video"));
            textDeskripsi.setText(bundle.getString("Deskripsi"));
        }

        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<KotaResponse> call = apiService.getKota();
        call.enqueue(new Callback<KotaResponse>() {
            @Override
            public void onResponse(Call<KotaResponse> call, Response<KotaResponse> response) {
                ArrayList<Kota> kota = (ArrayList<Kota>) response.body().getKota();
                mKota = kota;
                materialBetterSpinner = (MaterialBetterSpinner)findViewById(R.id.spinnerKota);
                ArrayAdapter<Kota> adapter = new ArrayAdapter<Kota>(DetailPesanActivity.this, android.R.layout.simple_dropdown_item_1line, mKota);
                materialBetterSpinner.setAdapter(adapter);
                materialBetterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = parent.getItemAtPosition(position).toString();
                        txtkota = item;
                        int index = mKota.indexOf(parent.getItemAtPosition(position));
                        inIdLokasi = mKota.get(index).getIdLokasi();
                    }
                });
            }

            @Override
            public void onFailure(Call<KotaResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });



        editEkor.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                editEkor.setError(null);
            }
        });


        checkKetentuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkKetentuan.setError(null);
                    checkKetentuan.clearFocus();
                }
            }
        });

        textKetentuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DetailPesanActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Ketentuan pemesanan");
                Button dialgButton = (Button) dialog.findViewById(R.id.btnKentuanOK);
                dialgButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEkor.getText().toString().equals("")){
                    editEkor.setError("Jumlah ekor harus diisi");
                    editEkor.requestFocus();
                    System.out.println("wooooyyy "+txtkota);
                } else if (txtkota == "" || txtkota == null){
                    materialBetterSpinner.setError("Kota belum dipilih");
                    materialBetterSpinner.requestFocus();
                } else if (checkKetentuan.isChecked() != true){
                    checkKetentuan.setError("Anda belum menyetujui ketentuan");
                    checkKetentuan.requestFocus();
                } else{
                    //Toast.makeText(getApplicationContext(), "Id User = "+inIdUser+" , Kode Sampel = "+inKodeSampel+" Item : "+txtkota+" Id Lokasi : "+inIdLokasi, Toast.LENGTH_LONG).show();

                    sendPost(kodePesan, Integer.valueOf(inKodeSampel), inIdUser, currDate,"Konfirmasi", Integer.valueOf(editEkor.getText().toString()));
                }
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            Context context = DetailPesanActivity.this;
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

    private void sendPost(int kodePesan, int kodeSampel, int idUser, String tglPesan, String statusPesan, int jumlahEkor) {
        System.out.println("Yang itu  : "+kodePesan +" : "+kodeSampel+" : "+idUser+" : "+tglPesan+" : "+statusPesan);
        //Toast.makeText(getApplicationContext(),"Yang itu  : "+kodePesan +" : "+kodeSampel+" : "+idUser+" : "+tglPesan+" : "+statusPesan, Toast.LENGTH_LONG).show();
        final APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        apiService.addPesan(kodePesan, kodeSampel, idUser, tglPesan, statusPesan, jumlahEkor).enqueue(new Callback<PesanResponse>() {
            @Override
            public void onResponse(Call<PesanResponse> call, Response<PesanResponse> response) {
                Log.i(TAG, "post submitted to API." + response.body().toString());
                Toast.makeText(getApplicationContext(), "Data berhasil terdaftar", Toast.LENGTH_LONG).show();
                System.out.println("Data berhasil dikirim");
            }

            @Override
            public void onFailure(Call<PesanResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data gagal dikirim", Toast.LENGTH_LONG);
                Intent intent = new Intent(getApplicationContext(), PesanActivity.class);
                startActivity(intent);
            }
        });
    }

}
