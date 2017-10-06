package com.example.dragmetohell.cattloo.history;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.ternakku.DetailTernakkuActivity;
import com.squareup.picasso.Picasso;

import me.relex.circleindicator.CircleIndicator;

public class DetailHistoryActivity extends AppCompatActivity {

    TextView txtTanggalVisual, txtSpesies, txtBerat, txtTinggi, txtPanjang, txtVideo, txtCaraTernak;
    String images[];
    ViewPager viewPager;
    ListGambarVisualAdapter gambarVisualAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtTanggalVisual = (TextView) findViewById(R.id.txtTanggal);
        txtSpesies = (TextView) findViewById(R.id.txtSpesies);
        txtCaraTernak = (TextView) findViewById(R.id.txtCaraTernak);
        txtBerat = (TextView) findViewById(R.id.txtBerat);
        txtTinggi = (TextView) findViewById(R.id.txtTinggi);
        txtPanjang = (TextView) findViewById(R.id.txtPanjang);
        txtVideo = (TextView) findViewById(R.id.txtVideo);
//
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String gambarUtama,gambar1,gambar2,gambar3;
            txtTanggalVisual.setText(bundle.getString("TanggalVisual"));
            txtSpesies.setText(bundle.getString("JenisTernak")+"/"+bundle.getString("Spesies"));
            txtCaraTernak.setText(bundle.getString("CaraTernak"));
            txtTinggi.setText(bundle.getString("Tinggi"));
            txtPanjang.setText(bundle.getString("Panjang"));
            txtBerat.setText(bundle.getString("Berat"));
            txtVideo.setText(bundle.getString("LinkVideo"));
            txtVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("LinkVideo"))));
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Plase install Youtube before running the app", Toast.LENGTH_SHORT).show();
                    }
//                    Intent videoIntent = new Intent(Intent.ACTION_VIEW);
//                    videoIntent.setData(Uri.parse(bundle.getString("LinkVideo")));
//                    videoIntent.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
//                    startActivity(videoIntent);
                }
            });

            gambar1 = bundle.getString("Img1");
            gambar2 = bundle.getString("Img2");
            gambar3 = bundle.getString("Img3");
            images = new String[]{gambar1, gambar2, gambar3};
            for (int i = 0; i < images.length; i++){
                System.out.println("Index ke"+ i +",isinya :"+images[i]);
            }
            viewPager = (ViewPager) findViewById(R.id.viewPagerVisual);
            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
            gambarVisualAdapter = new ListGambarVisualAdapter();
            viewPager.setAdapter(gambarVisualAdapter);
            indicator.setViewPager(viewPager);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class ListGambarVisualAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = DetailHistoryActivity.this;
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(context).load(images[position]).into(imageView);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}
