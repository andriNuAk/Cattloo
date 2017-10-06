package model;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragmetohell.cattloo.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

/**
 * Created by drag me to hell on 3/30/2017.
 */
public class ListHewanAdapter extends BaseAdapter {
    Context context;
    ArrayList<Pasar> pasar;
    LayoutInflater inflater;

    public ListHewanAdapter(Context context, ArrayList<Pasar> pasar) {
        this.context = context;
        this.pasar = pasar;
    }

    @Override
    public int getCount() {
        return pasar.size();
    }

    @Override
    public Object getItem(int i) {
        return pasar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = inflater.inflate(R.layout.card_home_list, viewGroup, false);
        }

        TextView txtSpesies = (TextView) view.findViewById(R.id.txtSpesies);
        TextView txtLokasi = (TextView) view.findViewById(R.id.txtLokasi);
        TextView txtHarga = (TextView) view.findViewById(R.id.txtLinkVideo);
        ImageView imgGambarUtama = (ImageView) view.findViewById(R.id.imgUtama);

        String spesies = pasar.get(i).getSpesies();
        String lokasi = pasar.get(i).getNamaDaerah();
        int harga = pasar.get(i).getHarga();
        String imgURL = pasar.get(i).getFoto1();

        //format rupiah
        Double hargaRupiah = Double.valueOf(harga);
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Picasso.with(context.getApplicationContext()).load(imgURL).into(imgGambarUtama);
        txtSpesies.setText(spesies);
        txtLokasi.setText("Lokasi : "+lokasi);
        txtHarga.setText(kursIndonesia.format(hargaRupiah));
        return view;
    }
}
