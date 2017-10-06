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
 * Created by drag me to hell on 4/11/2017.
 */

public class ListProfilAdapter extends BaseAdapter {

    Context context;
    //ArrayList<BeritaModel> beritaList;
    ArrayList<ModelProfil> profilList;
    LayoutInflater inflater;

    public ListProfilAdapter(Context context, ArrayList<ModelProfil> profilList) {
        this.context = context;
        this.profilList = profilList;
    }

    @Override
    public int getCount() {
        return profilList.size();
    }

    @Override
    public Object getItem(int i) {
        return profilList.get(i);
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
            view = inflater.inflate(R.layout.list_profil, viewGroup, false);
        }

        ImageView imgIcon = (ImageView) view.findViewById(R.id.iconProfil);
        TextView txtLabel = (TextView) view.findViewById(R.id.txtLabel);
        TextView txtValue = (TextView) view.findViewById(R.id.txtValue);

        String icon = profilList.get(i).getImg();
        String label = profilList.get(i).getTextLabel();
        String value = profilList.get(i).getValue();
        // ini pake picasso
//        Picasso.with(context.getApplicationContext()).load(gambar).into(imgGambar);
//        System.out.println(gambar);
//        DownloadImageWithURLTask downloadTask = new DownloadImageWithURLTask(imgGambar);
//        downloadTask.execute(gambar);
//
        //ini pake drawable
        int id = context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
        imgIcon.setImageResource(id);
        txtLabel.setText(label);
        txtValue.setText(value);
        //imgGambar.setImageResource(R.drawable.gedung_sate_bandung);

        return view;
    }
}
