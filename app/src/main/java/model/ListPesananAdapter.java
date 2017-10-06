package model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.HomeActivity;
import com.example.dragmetohell.cattloo.LoginActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.pesan.PesanActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import helper.SessionHelper;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by drag me to hell on 5/5/2017.
 */

public class ListPesananAdapter extends RecyclerView.Adapter<ListPesananAdapter.ViewHolder>  {

    private ArrayList<Pesanan> pesan;
    private Context context;

    public ListPesananAdapter(ArrayList<Pesanan> pesan, Context context) {
        this.pesan = pesan;
        this.context = context;
    }

    @Override
    public ListPesananAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pesanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListPesananAdapter.ViewHolder holder, final int position) {
        final String TAG = PesanActivity.class.getSimpleName();
        Picasso.with(context).load(pesan.get(position).getFoto1()).resize(80, 80).into(holder.imgUtama);
        holder.txtSpesies.setText(pesan.get(position).getJenisHewan()+" / "+pesan.get(position).getSpesies()+" ("+pesan.get(position).getJumlahEkor()+" ekor)");
        holder.txtTglPesan.setText(pesan.get(position).getTglPesan());
        holder.txtStatusPesan.setText(pesan.get(position).getStatusPesan());
        if (pesan.get(position).getStatusPesan().equalsIgnoreCase("Konfirmasi")){
            holder.btnStatus.setBackgroundResource(R.drawable.button_pressed_red);
            holder.btnStatus.setText("Batalkan Pemesanan");
            holder.btnStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(holder.itemView.getContext());

                    // Setting Dialog Title
                    alertDialog.setTitle("Batalkan pemesanan");

                    // Setting Dialog Message
                    alertDialog.setMessage("Apakah anda yakin untuk membatalkan pemesanan?");

                    // Setting Icon to Dialog
                    //alertDialog.setIcon(R.drawable.delete);

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("Batalkan", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                            // Write your code here to invoke YES event
//                            final APIInterface apiService3 = APIClient.getURL().create(APIInterface.class);
//                            apiService3.deletePesan(pesan.get(position).getKodePesan()).enqueue(new Callback<PesananResponse>() {
//                                @Override
//                                public void onResponse(Call<PesananResponse> call, Response<PesananResponse> response) {
//                                    Toast.makeText(holder.itemView.getContext(), "Data berhasil dihapus",Toast.LENGTH_LONG).show();
//                                }
//
//                                @Override
//                                public void onFailure(Call<PesananResponse> call, Throwable t) {
//                                    Toast.makeText(holder.itemView.getContext(), "Data gagal dihapus",Toast.LENGTH_LONG).show();
//                                    t.printStackTrace();
//                                }
//                            });
                            APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
                            apiInterface.deletePesan(pesan.get(position).getKodePesan()).enqueue(new Callback<PesanResponse>() {
                                @Override
                                public void onResponse(Call<PesanResponse> call, Response<PesanResponse> response) {
                                    Log.i(TAG, "post submitted to API." + response.body().toString());
                                    Intent intent = new Intent(context, PesanActivity.class);
                                    context.startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<PesanResponse> call, Throwable t) {

                                }
                            });

                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("Tetap pesan", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            Toast.makeText(holder.itemView.getContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();
                }
            });
        } else if (pesan.get(position).getStatusPesan().equalsIgnoreCase("Dimiliki")){
            holder.btnStatus.setBackgroundResource(R.drawable.button_pressed_blue);
            holder.btnStatus.setText("Dimiliki");
        }
    }

    @Override
    public int getItemCount() {
        return pesan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgUtama;
        private TextView txtSpesies, txtTglPesan, txtStatusPesan;
        private Button btnStatus;
        public ViewHolder(final View itemView) {
            super(itemView);
            imgUtama = (ImageView) itemView.findViewById(R.id.imgUtama);
            txtSpesies = (TextView) itemView.findViewById(R.id.txtSpesies);
            txtTglPesan = (TextView) itemView.findViewById(R.id.txtTglPesan);
            txtStatusPesan = (TextView) itemView.findViewById(R.id.txtStatus);
            btnStatus = (Button) itemView.findViewById(R.id.btnStatus);





        }
    }


}
