package model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragmetohell.cattloo.DetailAlumniActivity;
import com.example.dragmetohell.cattloo.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

/**
 * Created by drag me to hell on 5/17/2017.
 */

public class ListAlumniAdapter extends BaseAdapter{
    Context context;
    ArrayList<Penjualan> mTernak;
    LayoutInflater inflater;

    public ListAlumniAdapter(Context context, ArrayList<Penjualan> mTernak) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = inflater.inflate(R.layout.card_alumni_list, viewGroup, false);
        }

        TextView txtSpesies = (TextView) view.findViewById(R.id.txtSpesies);
        TextView txtKodeTernak= (TextView) view.findViewById(R.id.txtHargaJual);
        TextView txtNamaPeternak = (TextView) view.findViewById(R.id.txtTglJual);
        ImageView imgGambarUtama = (ImageView) view.findViewById(R.id.imgTernakUtama);
        Button btnDetail = (Button) view.findViewById(R.id.btnAlumniDetail);

        String spesies = mTernak.get(i).getSpesies();
        String kodeTernak = mTernak.get(i).getHargaJual().toString();
        String namaPeternak = mTernak.get(i).getTanggalJual();
        String imgURL = mTernak.get(i).getFoto1();

        //format rupiah
        Double hargaRupiah = Double.valueOf(kodeTernak);
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Picasso.with(context.getApplicationContext()).load(imgURL).into(imgGambarUtama);
        txtSpesies.setText(spesies);
        txtKodeTernak.setText("Harga jual : "+kursIndonesia.format(hargaRupiah));
        txtNamaPeternak.setText("Tanggal jual : "+namaPeternak);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailAlumniActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("IdTernak", mTernak.get(i).getIdTernak().toString());
                intent.putExtra("Foto1", mTernak.get(i).getFoto1().toString());
                intent.putExtra("Foto2", mTernak.get(i).getFoto2().toString());
                intent.putExtra("Foto3", mTernak.get(i).getFoto3().toString());
                intent.putExtra("HargaJual", mTernak.get(i).getHargaJual().toString());
                intent.putExtra("Margin", mTernak.get(i).getMargin().toString());
                intent.putExtra("Untung", mTernak.get(i).getUntung().toString());
                intent.putExtra("Spesies", mTernak.get(i).getJenisHewan()+"/"+mTernak.get(i).getSpesies());
                intent.putExtra("Tanggal", mTernak.get(i).getTanggalJual().toString());
                intent.putExtra("Video", mTernak.get(i).getUrlVideo().toString());
                context.startActivity(intent);
            }
        });

        return view;
    }
}
