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
 * Created by drag me to hell on 4/10/2017.
 */

public class ListLapStokAdapter extends BaseAdapter {
    Context context;
    ArrayList<Ternakku> mTernak;
    LayoutInflater inflater;

    public ListLapStokAdapter(Context context, ArrayList<Ternakku> mTernak) {
        this.context = context;
        this.mTernak = mTernak;
    }

    @Override
    public int getCount() {
        return mTernak.size();
    }

    @Override
    public Object getItem(int i) {
        return mTernak.get(i);
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
            view = inflater.inflate(R.layout.card_stok_list, viewGroup, false);
        }

        TextView txtSpesies = (TextView) view.findViewById(R.id.txtSpesies);
        TextView txtKodeTernak= (TextView) view.findViewById(R.id.txtKodeTernak);
        TextView txtNamaPeternak = (TextView) view.findViewById(R.id.txtNamaPeternak);
        ImageView imgGambarUtama = (ImageView) view.findViewById(R.id.imgUtamaBeli);

        String spesies = mTernak.get(i).getSpesies();
        String kodeTernak = mTernak.get(i).getIdTernak().toString();
        String namaPeternak = mTernak.get(i).getHarga().toString();
        String imgURL = mTernak.get(i).getFoto1();

        //format rupiah
        Double hargaRupiah = Double.valueOf(namaPeternak);
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Picasso.with(context.getApplicationContext()).load(imgURL).into(imgGambarUtama);
        txtSpesies.setText("Kode Ternak : "+kodeTernak);
        txtKodeTernak.setText(spesies);
        txtNamaPeternak.setText(kursIndonesia.format(hargaRupiah));
        return view;
    }
}
