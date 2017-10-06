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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

/**
 * Created by drag me to hell on 4/5/2017.
 */

public class ListTernakkuAdapter extends BaseAdapter {

    Context context;
    ArrayList<Ternakku> ternakList;
    LayoutInflater inflater;

    public ListTernakkuAdapter(Context context, ArrayList<Ternakku> ternakList) {
        this.context = context;
        this.ternakList = ternakList;
    }

    @Override
    public int getCount() {
        return ternakList.size();
    }

    @Override
    public Object getItem(int i) {
        return ternakList.get(i);
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
            view = inflater.inflate(R.layout.card_ternakku_list, viewGroup, false);
        }

        TextView txtSpesies = (TextView) view.findViewById(R.id.txtSpesies);
        ImageView imgGambarUtama = (ImageView) view.findViewById(R.id.imgTernakUtama);

        String spesies = ternakList.get(i).getSpesies();
        String kodeTernak = ternakList.get(i).getIdTernak().toString();
        String namaPeternak = ternakList.get(i).getCaraTernak();
        String imgURL = ternakList.get(i).getFoto1();

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
        txtSpesies.setText("Kode Ternak : "+kodeTernak+" ("+spesies+")");
        return view;
    }
}
