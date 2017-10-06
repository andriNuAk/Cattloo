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
 * Created by drag me to hell on 3/29/2017.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private ArrayList<Pasar> pasar;
    private Context context;

    public HomeAdapter(Context context, ArrayList<Pasar> pasar) {
        this.pasar = pasar;
        this.context = context;
    }




    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.spesies.setText(pasar.get(position).getSpesies());
        holder.lokasi.setText(pasar.get(position).getNamaDaerah());
        holder.harga.setText(pasar.get(position).getHarga());
        Picasso.with(context).load(pasar.get(position).getFoto1()).into(holder.gambarUtama);


    }

    @Override
    public int getItemCount() {
        return pasar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView spesies;
        private TextView lokasi;
        private TextView harga;
        private ImageView gambarUtama;
        public ViewHolder(View view) {
            super(view);

            spesies = (TextView) view.findViewById(R.id.txtSpesies);
            lokasi = (TextView) view.findViewById(R.id.txtLokasi);
            harga = (TextView) view.findViewById(R.id.txtLinkVideo);
            gambarUtama = (ImageView) view.findViewById(R.id.imgUtama);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
