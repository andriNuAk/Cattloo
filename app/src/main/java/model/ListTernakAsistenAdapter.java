package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragmetohell.cattloo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by drag me to hell on 8/1/2017.
 */

public class ListTernakAsistenAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<TernakAsisten> ternakList;

    public ListTernakAsistenAdapter(Context context, ArrayList<TernakAsisten> ternakList) {
        this.context = context;
        this.ternakList = ternakList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_ternak_peternak, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        String gambar = ternakList.get(position).getFoto1();
        String nama = "Ternak "+ternakList.get(position).getIdTernak();


        //int id = context.getResources().getIdentifier(gambar, "mipmap", context.getPackageName());
        //System.out.println(anggotaList.get(position).getNama());

        //holder.imgAnggota.setImageResource(id);
        Picasso.with(context.getApplicationContext()).load(gambar).into(holder.imgTernak);
        holder.txtTernak.setText(nama);
    }

    @Override
    public int getItemCount() {
        return ternakList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgTernak;
        TextView txtTernak;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgTernak = (ImageView) itemView.findViewById(R.id.imgTernakPeternak);
            txtTernak = (TextView) itemView.findViewById(R.id.txtTernakPeternak);
        }


    }
}
