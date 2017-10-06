package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragmetohell.cattloo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by drag me to hell on 4/6/2017.
 */

public class ListHistoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Visual> historyList;
    LayoutInflater inflater;

    public ListHistoryAdapter(Context context, ArrayList<Visual> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int i) {
        return historyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = inflater.inflate(R.layout.card_history_list, viewGroup, false);
        }

        TextView txtSpesies = (TextView) view.findViewById(R.id.txtSpesies);
        TextView txtKodeVisual= (TextView) view.findViewById(R.id.txtKodeVisual);
        TextView txtTanggalVisual = (TextView) view.findViewById(R.id.txtTanggalVisual);
        ImageView imgGambarUtama = (ImageView) view.findViewById(R.id.imgUtama);

        String spesies = historyList.get(i).getSpesies();
        String kodeVisual = historyList.get(i).getIdTernak().toString();
        String tanggalVisual = historyList.get(i).getTglPeriksa();
        String imgURL = historyList.get(i).getFoto1();

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
        txtSpesies.setText(spesies);
        txtKodeVisual.setText(kodeVisual);
        txtTanggalVisual.setText(tanggalVisual);

        return view;
    }
}
