package com.example.dragmetohell.cattloo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragmetohell.cattloo.ternakku.DetailTernakkuActivity;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.relex.circleindicator.CircleIndicator;

public class DetailAlumniActivity extends AppCompatActivity {
    String foto1,foto2,foto3,idTernak,hargaJual,margin,untung,spesies,tanggalJual,video,currDate;
    TextView txtHarga,txtMargin,txtUntung,txtSpesies,txtTglJual,txtVIdeo;
    String[] fotos;
    ViewPager viewPager;
    ListGambarAdapter gambarHewanAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alumni);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtHarga = (TextView) findViewById(R.id.txtHarga);
        txtMargin = (TextView) findViewById(R.id.txtMargin);
        txtUntung = (TextView) findViewById(R.id.txtUntung);
        txtSpesies = (TextView) findViewById(R.id.txtSpesies);
        txtTglJual = (TextView) findViewById(R.id.txtTanggal);
        txtVIdeo = (TextView) findViewById(R.id.txtVideo);


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //format uang
            Double hargaRupiah = Double.valueOf(bundle.getString("HargaJual"));
            Double inmargin = Double.valueOf(bundle.getString("Margin"));
            Double inuntung = Double.valueOf(bundle.getString("Untung"));
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');

            kursIndonesia.setDecimalFormatSymbols(formatRp);

            //get current date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            currDate = dateFormat.format(date);


            String gambar1, gambar2, gambar3;
            hargaJual = String.valueOf(kursIndonesia.format(hargaRupiah));
            margin = String.valueOf(kursIndonesia.format(inmargin));
            untung = String.valueOf(kursIndonesia.format(inuntung));
            spesies = bundle.getString("Spesies");
            tanggalJual = bundle.getString("Tanggal");
            video = bundle.getString("Video");

            txtHarga.setText(hargaJual);
            txtMargin.setText(margin);
            txtUntung.setText(untung);
            txtSpesies.setText(spesies);
            txtTglJual.setText(tanggalJual);
            txtVIdeo.setText(video);


//            txtLinkVideo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    try {
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("LinkVideo"))));
//                    } catch (Exception e){
//                        Toast.makeText(getApplicationContext(), "Plase install Youtube before running the app", Toast.LENGTH_SHORT).show();
//                    }
////                    Intent videoIntent = new Intent(Intent.ACTION_VIEW);
////                    videoIntent.setData(Uri.parse(bundle.getString("LinkVideo")));
////                    videoIntent.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
////                    startActivity(videoIntent);
//                }
//            });
            //coba di pindah ke adapter
            gambar1 = bundle.getString("Foto1");
            gambar2 = bundle.getString("Foto2");
            gambar3 = bundle.getString("Foto3");
            fotos = new String[]{gambar1, gambar2, gambar3};
            for (int i = 0; i < fotos.length; i++) {
                System.out.println("Index ke" + i + ",isinya :" + fotos[i]);
            }
            viewPager = (ViewPager) findViewById(R.id.viewPagerPesan);
            gambarHewanAdapter = new ListGambarAdapter();
            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
            viewPager.setAdapter(gambarHewanAdapter);
            indicator.setViewPager(viewPager);

        }

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class ListGambarAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return fotos.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = DetailAlumniActivity.this;
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(context).load(fotos[position]).into(imageView);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

}
