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

import java.util.ArrayList;

/**
 * Created by drag me to hell on 7/27/2017.
 */

public class ListKandangAdapter extends BaseAdapter {

    Context context;
    ArrayList<Kandang> kandang;
    LayoutInflater inflater;

    public ListKandangAdapter(Context context, ArrayList<Kandang> kandang) {
        this.context = context;
        this.kandang = kandang;
    }

    @Override
    public int getCount() {
        return kandang.size();
    }

    @Override
    public Object getItem(int position) {
        return kandang.get(position);
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

        txtNama.setText(kandang.get(position).getNamaKandang());
        //Picasso.with(context.getApplicationContext()).load(peternak.get(position).getUrlFoto()).into(imgGambarUtama);
        imgGambarUtama.setImageResource(R.drawable.home);
        txtKapasitas.setText("Kapasitas Ternak : "+kandang.get(position).getJumlahTerisi()+"/"+kandang.get(position).getKapasitas());
        progressKapasitas.setMax(kandang.get(position).getKapasitas());
        progressKapasitas.setProgress(kandang.get(position).getJumlahTerisi());


        return convertView;
    }
}
