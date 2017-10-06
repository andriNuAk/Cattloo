package model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.HomeActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.pesan.DetailPesanActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by drag me to hell on 5/2/2017.
 */

public class ListSampelAdapter extends RecyclerView.Adapter<ListSampelAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClickListener(Sampel sampel);
    }

    private ArrayList<Sampel> sampel;
    private Context context;
    private final OnItemClickListener listener;


    public ListSampelAdapter(Context context,ArrayList<Sampel> sampel, OnItemClickListener listener) {
        this.context = context;
        this.sampel = sampel;
        this.listener = listener;
    }

    @Override
    public ListSampelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sampel_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListSampelAdapter.ViewHolder holder, final int position) {
        //kurs uang
        Double hargaRupiah = Double.valueOf(sampel.get(position).getHarga().toString());
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
//        String url = sampel.get(position).getFoto1();
        Picasso.with(context).load(sampel.get(position).getFoto1()).resize(80, 80).into(holder.imgUtama);
        holder.idSampel.setText("Kode Sampel : "+sampel.get(position).getKodeSampel().toString()+"( "+sampel.get(position).getCaraTernak()+" )");
        holder.jenisHewan.setText(sampel.get(position).getJenisHewan()+"/"+sampel.get(position).getSpesies());
        holder.harga.setText(kursIndonesia.format(hargaRupiah));
        holder.btnSampelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Clicked "+sampel.get(position).getHarga(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context.getApplicationContext(), DetailPesanActivity.class);
                intent.putExtra("KodeSampel", sampel.get(position).getKodeSampel().toString());
                intent.putExtra("Harga", sampel.get(position).getHarga().toString());
                intent.putExtra("Foto1", sampel.get(position).getFoto1().toString());
                intent.putExtra("Foto2", sampel.get(position).getFoto2().toString());
                intent.putExtra("Foto3", sampel.get(position).getFoto3().toString());
                intent.putExtra("Video", sampel.get(position).getUrlVideo().toString());
                intent.putExtra("Spesies", sampel.get(position).getJenisHewan().toString()+" / "+sampel.get(position).getSpesies().toString());
                intent.putExtra("Berat", sampel.get(position).getBobot().toString());
                intent.putExtra("Panjang", sampel.get(position).getPanjang().toString());
                intent.putExtra("Tinggi", sampel.get(position).getTinggi().toString());
                intent.putExtra("Deskripsi", sampel.get(position).getDeskripsi().toString());

                context.startActivity(intent);

            }
        });
        //holder.bind(sampel.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return sampel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgUtama;
        private TextView idSampel, jenisHewan, harga;
        private Button btnSampelDetail;
        public ViewHolder(View view) {
            super(view);
            imgUtama = (ImageView) view.findViewById(R.id.imgUtama);
            idSampel = (TextView) view.findViewById(R.id.txtKodeSampel);
            jenisHewan = (TextView) view.findViewById(R.id.txtJenisHewan);
            harga = (TextView) view.findViewById(R.id.txtHarga);
            btnSampelDetail = (Button) view.findViewById(R.id.btnSampelDetail);
        }

        public void bind(final Sampel sampel, final OnItemClickListener listener) {
            //kurs uang
            Double hargaRupiah = Double.valueOf(sampel.getHarga().toString());
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');

            kursIndonesia.setDecimalFormatSymbols(formatRp);
            //name.setText(item.name);
            Picasso.with(itemView.getContext()).load(sampel.getFoto1()).into(imgUtama);
            idSampel.setText("Kode Sampel : "+sampel.getKodeSampel().toString());
            jenisHewan.setText(sampel.getJenisHewan()+"/"+sampel.getSpesies());
            harga.setText(kursIndonesia.format(hargaRupiah));
            harga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Clicked!!", Toast.LENGTH_LONG);
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    listener.onItemClickListener(sampel);
//                    Toast.makeText(itemView.getContext(), "Clicekd!!", Toast.LENGTH_LONG);
//                }
//            });
        }

    }
}
