package model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.DetailHewanActivity;
import com.example.dragmetohell.cattloo.HomeActivity;
import com.example.dragmetohell.cattloo.LoginActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.konfirmbelanja.KonfirmActivity;
import com.example.dragmetohell.cattloo.laporan.LaporanActivity;
import com.example.dragmetohell.cattloo.ternakku.TernakkuActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import helper.AkunHelper;
import helper.ModelBelanja;
import helper.SessionHelper;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by drag me to hell on 4/10/2017.
 */

public class ListLapBeliAdapter extends BaseAdapter {

    Context context;
    ArrayList<LaporanBelanja> mBelanja;
    LayoutInflater inflater;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<AkunHelper> akunHelper;
    int inIdUser;
    String inUsername, inPassword, inEmail, inNoTelepon, inNamaDepan, inNamaBelakang;
    private static final String TAG = DetailHewanActivity.class.getSimpleName();

    public ListLapBeliAdapter(Context context, ArrayList<LaporanBelanja> mBelanja) {
        this.context = context;
        this.mBelanja = mBelanja;
    }

    @Override
    public int getCount() {
        return mBelanja.size();
    }

    @Override
    public Object getItem(int i) {
        return mBelanja.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = inflater.inflate(R.layout.card_beli_list, viewGroup, false);
        }

        TextView txtSpesies = (TextView) view.findViewById(R.id.txtSpesies);
        TextView txtKodeTernak= (TextView) view.findViewById(R.id.txtKodeTernak);
        TextView txtNamaPeternak = (TextView) view.findViewById(R.id.txtNamaPeternak);
        ImageView imgGambarUtama = (ImageView) view.findViewById(R.id.imgUtamaBeli);
        Button btnStatus = (Button) view.findViewById(R.id.buttonStatus);
        Button btnKonfirm = (Button) view.findViewById(R.id.buttonKonfirm);

        Double hargaRupiah = Double.valueOf(mBelanja.get(i).getHarga());
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        String spesies = mBelanja.get(i).getTanggalBelanja();
        String kodeTernak = String.valueOf(kursIndonesia.format(hargaRupiah));
        String namaPeternak = String.valueOf(mBelanja.get(i).getStatus());
        String imgURL = mBelanja.get(i).getFoto1();

        dbHelper = new SessionHelper(view.getContext());
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
        //format rupiah
//        Double hargaRupiah = Double.valueOf(harga);
//        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
//        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
//
//        formatRp.setCurrencySymbol("Rp. ");
//        formatRp.setMonetaryDecimalSeparator(',');
//        formatRp.setGroupingSeparator('.');
//
//        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Picasso.with(context.getApplicationContext()).load(imgURL).into(imgGambarUtama);
        txtSpesies.setText("Tanggal Beli : "+spesies);
        txtKodeTernak.setText(kodeTernak);
        if (mBelanja.get(i).getStatusBeli() == 1){
            btnStatus.setBackground(view.getResources().getDrawable(R.drawable.button_border_pressed));
            btnStatus.setText("Hak Milik");
            btnKonfirm.setVisibility(View.GONE);
        } else {
            if (namaPeternak.equalsIgnoreCase("2")){
                btnStatus.setBackground(view.getResources().getDrawable(R.drawable.button_border_pressed_blue));
                btnStatus.setText("KONFIRM");
                btnKonfirm.setVisibility(View.GONE);
            } else {
                btnStatus.setBackground(view.getResources().getDrawable(R.drawable.button_border_pressed_red));
                btnStatus.setText("BATALKAN");
                btnKonfirm.setVisibility(View.VISIBLE);
                final int id = mBelanja.get(i).getIdTernak();
                final int idBelanja = mBelanja.get(i).getIdBelanja();
                final View finalView = view;
                btnStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(finalView.getRootView().getContext());
                        alertDialog.setTitle("Informasi");
                        alertDialog.setMessage("Apakah anda yakin akan membatalkan mengkandangkan ternak ini?");
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                                apiService.updateTransaksi(1, "0", id).enqueue(new Callback<UpdateStatusPasarResponse>() {
                                    @Override
                                    public void onResponse(Call<UpdateStatusPasarResponse> call, Response<UpdateStatusPasarResponse> response) {
                                        Log.i(TAG, "post submitted to API." + response.body().toString());
                                        System.out.println("Data berhasil dikirim");
                                        Toast.makeText(context.getApplicationContext(), "Berhasil membatalkan posting pembelian", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);
                                        context.startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<UpdateStatusPasarResponse> call, Throwable t) {
                                        t.printStackTrace();
                                        //Toast.makeText(context.getApplicationContext(), "Berhasil membatalkan posting dipasar", Toast.LENGTH_LONG).show();

                                    }
                                });

                                apiService.hapusBelanja(idBelanja).enqueue(new Callback<DeleteBelanjaResponse>() {
                                    @Override
                                    public void onResponse(Call<DeleteBelanjaResponse> call, Response<DeleteBelanjaResponse> response) {
                                        Log.i(TAG, "post submitted to API." + response.body().toString());
                                        Toast.makeText(context.getApplicationContext(), "Berhasil melakukan menghapus pembelian "+idBelanja, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);
                                        context.startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<DeleteBelanjaResponse> call, Throwable t) {
                                        t.printStackTrace();
                                        Toast.makeText(context.getApplicationContext(), "Gagal melakukan menghapus pembelian", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });

                        // Setting Negative "NO" Button
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
                    }
                });

                btnKonfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context.getApplicationContext(), KonfirmActivity.class);
                        String idTernak = String.valueOf(mBelanja.get(i).getIdTernak());
                        String idBelanja = String.valueOf(mBelanja.get(i).getIdBelanja());
                        intent.putExtra("IdBelanja", idBelanja);
                        intent.putExtra("IdTernak", idTernak);
                        context.startActivity(intent);
                    }
                });
            }

        }

        if (namaPeternak.equalsIgnoreCase("0")){
            txtNamaPeternak.setText("Idle");
        } else if (namaPeternak.equalsIgnoreCase("1")){
            txtNamaPeternak.setText("Order");
        } else if (namaPeternak.equalsIgnoreCase("2")){
            txtNamaPeternak.setText("Konfirm");
        }


        return view;
    }
}
