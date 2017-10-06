package com.example.dragmetohell.cattloo.konfirmbelanja;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.HomeActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.laporan.LaporanActivity;
import com.example.dragmetohell.cattloo.ternakku.TernakkuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.Future;

import model.BelanjaResponse;
import model.InvoiceResponse;
import model.UpdateStatusPasarResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
//import retrofit2.Response;

public class KonfirmActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private EditText txtNamaPemilikRekening, txtBankPengirim, txtTglBayar, txtNominal;
    private int year, month, day, kodeInvoice, nominal;
    private ImageView imageBukti;
    private Button btnUpload, btnKirim;
    String buktiText, path, idBelanja, tglBayar, namaPemilikRekening, bankPengirim, idTernak, fotoFile;
    private static final String TAG = KonfirmActivity.class.getSimpleName();
    APIInterface service;
    public static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtTglBayar = (EditText) findViewById(R.id.txtTglBayar);
        txtNamaPemilikRekening = (EditText) findViewById(R.id.txtNamaPemilik);
        txtBankPengirim = (EditText) findViewById(R.id.txtNamaBank);
        txtNominal = (EditText) findViewById(R.id.txtNominal);
        txtTglBayar.setKeyListener((KeyListener) txtTglBayar.getTag());
        imageBukti = (ImageView) findViewById(R.id.imageBukti);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnKirim = (Button) findViewById(R.id.btnKirim);
        imageBukti.setVisibility(View.GONE);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idBelanja = bundle.getString("IdBelanja");
            idTernak = bundle.getString("IdTernak");
        }

        txtTglBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
                //akan menampilkan teks ketika kalendar muncul setelah menekan tombol

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadTransaksi();
//                Toast.makeText(getApplicationContext(), "File in "+path+" id belanja is "+idBelanja, Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), "Nama Rekening: "+txtNamaPemilikRekening.getText()+" Nama Bank: "+txtBankPengirim.getText()+" Tanggal Bayar: "+txtTglBayar.getText()+" Nominal: Rp."+txtNominal.getText()+" Foto: "+path, Toast.LENGTH_LONG).show();
                APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                apiService.addInvoice( kodeInvoice , "", txtTglBayar.getText().toString(), Integer.valueOf(txtNominal.getText().toString()), Integer.valueOf(idBelanja) , txtNamaPemilikRekening.getText().toString(), txtBankPengirim.getText().toString()).enqueue(new Callback<InvoiceResponse>() {
                    @Override
                    public void onResponse(Call<InvoiceResponse> call, retrofit2.Response<InvoiceResponse> response) {
                        Log.i(TAG, "post submitted to API." + response.body().toString());
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        //Toast.makeText(getApplicationContext(), "Berhasil melakukan pembelian", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<InvoiceResponse> call, Throwable t) {
                        t.printStackTrace();
                        Intent intent = new Intent(getApplicationContext(), LaporanActivity.class);
                        startActivity(intent);
                    }
                });
//
                apiService.updateTransaksi(0, "2", Integer.valueOf(idTernak)).enqueue(new Callback<UpdateStatusPasarResponse>() {
                    @Override
                    public void onResponse(Call<UpdateStatusPasarResponse> call, retrofit2.Response<UpdateStatusPasarResponse> response) {
                        Log.i(TAG, "post submitted to API." + response.body().toString());
                        System.out.println("Data berhasil dikirim");
                        //Toast.makeText(getApplicationContext(), "Berhasil membatalkan posting dipasar", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), TernakkuActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UpdateStatusPasarResponse> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Berhasil membatalkan posting dipasar", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        txtTglBayar.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(day));
    }
    

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    path = getPathFromURI(data.getData());
                    imageBukti.setImageURI(data.getData());
                    imageBukti.setVisibility(View.VISIBLE);

                }
        }
    }

    private String getPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void uploadTransaksi(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

// Change base URL to your upload server URL.
//        service = new Retrofit.Builder().baseUrl("http://192.168.1.16:5500").client(client).build().create(APIInterface.class); // (lokasl)
        service = new Retrofit.Builder().baseUrl("http://101.50.1.2:49485").client(client).build().create(APIInterface.class); // (vps)


        File file = new File(path);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
        final RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

        retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(body, name);
        req.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Upload berhasil, nama file = "+name.toString()+" Tanggal Bayar : "+txtTglBayar.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LaporanActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
