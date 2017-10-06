package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dragmetohell.cattloo.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

/**
 * Created by drag me to hell on 7/20/2017.
 */

public class ListPeternakAdapter extends BaseAdapter {
    Context context;
    ArrayList<Peternak> peternak;
    LayoutInflater inflater;
    int terisi;

    public ListPeternakAdapter(Context context, ArrayList<Peternak> peternak) {
        this.context = context;
        this.peternak = peternak;
    }

    @Override
    public int getCount() {
        return peternak.size();
    }

    @Override
    public Object getItem(int position) {
        return peternak.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.card_peternak, parent, false);
        }


        TextView txtKapasitas = (TextView) convertView.findViewById(R.id.txtKapasitasTernak);
        TextView txtNama = (TextView) convertView.findViewById(R.id.txtNama);
        ImageView imgGambarUtama = (ImageView) convertView.findViewById(R.id.imgUtama);
        ProgressBar progressKapasitas = (ProgressBar) convertView.findViewById(R.id.progresStatus);

        txtNama.setText(peternak.get(position).getNamaDepan()+" "+peternak.get(position).getNamaBelakang());
        Picasso.with(context.getApplicationContext()).load(peternak.get(position).getUrlFoto()).into(imgGambarUtama);
        txtKapasitas.setText("Kapasitas Ternak : "+peternak.get(position).getJumlahTerisi()+"/"+peternak.get(position).getKapasitasTernak());
        progressKapasitas.setMax(peternak.get(position).getKapasitasTernak());
        progressKapasitas.setProgress(peternak.get(position).getJumlahTerisi());

//        String spesies = pasar.get(i).getSpesies();
//        String lokasi = pasar.get(i).getNamaDaerah();
//        int harga = pasar.get(i).getHarga();
//        String imgURL = pasar.get(i).getFoto1();
//
//        //format rupiah
//        Double hargaRupiah = Double.valueOf(harga);
//        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
//        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
//
//        formatRp.setCurrencySymbol("Rp. ");
//        formatRp.setMonetaryDecimalSeparator(',');
//        formatRp.setGroupingSeparator('.');
//
//        kursIndonesia.setDecimalFormatSymbols(formatRp);
//
//        Picasso.with(context.getApplicationContext()).load(imgURL).into(imgGambarUtama);
//        txtSpesies.setText(spesies);
//        txtLokasi.setText("Lokasi : "+lokasi);
//        txtHarga.setText(kursIndonesia.format(hargaRupiah));
//
        return convertView;
    }
}
