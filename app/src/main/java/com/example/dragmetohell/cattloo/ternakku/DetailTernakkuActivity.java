package com.example.dragmetohell.cattloo.ternakku;

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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragmetohell.cattloo.DetailHewanActivity;
import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.history.HistoryActivity;
import com.squareup.picasso.Picasso;

import me.relex.circleindicator.CircleIndicator;
import model.UpdateStatusPasar;
import model.UpdateStatusPasarResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTernakkuActivity extends AppCompatActivity {
    TextView txtKodeTernak, txtJenisSpesies, txtVideo, txtCaraTernak, txtNamaPeternak, txtNamaAsisten;
    String images[];
    ViewPager viewPager;
    ListGambarTernakAdapter gambarTernakAdapter;
    Button btnHistory, btnPosting;
    private static final String TAG = DetailTernakkuActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ternakku);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtKodeTernak = (TextView) findViewById(R.id.txtKodeTernak);
        txtJenisSpesies = (TextView) findViewById(R.id.txtJenisSpesies);
        txtVideo = (TextView) findViewById(R.id.txtVideoURL);
        txtCaraTernak = (TextView) findViewById(R.id.txtCaraTernak);
        txtNamaPeternak = (TextView) findViewById(R.id.txtNamaPeternak);
        txtNamaAsisten = (TextView) findViewById(R.id.txtNamaAsisten);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnPosting = (Button) findViewById(R.id.buttonPosting);
        btnPosting.setBackground(getResources().getDrawable(R.drawable.button_border_pressed_blue));
        btnHistory.setBackground(getResources().getDrawable(R.drawable.button_border_pressed));


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String gambarUtama,gambar1,gambar2,gambar3;
            txtKodeTernak.setText(bundle.getString("KodeTernak"));
            txtJenisSpesies.setText(bundle.getString("JenisHewan")+" / "+bundle.getString("Spesies"));
            txtVideo.setText(bundle.getString("Video"));
            txtCaraTernak.setText(bundle.getString("CaraTernak"));
            txtNamaPeternak.setText(bundle.getString("NamaPeternak"));
            txtNamaAsisten.setText(bundle.getString("NamaAsisten"));
            String status = bundle.getString("Status");
            if (status.equalsIgnoreCase("0")){
                btnPosting.setText("Posting di pasar");
                btnPosting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                        apiService.updateStatus(1, Integer.valueOf(bundle.getString("KodeTernak"))).enqueue(new Callback<UpdateStatusPasarResponse>() {
                            @Override
                            public void onResponse(Call<UpdateStatusPasarResponse> call, Response<UpdateStatusPasarResponse> response) {
                                Log.i(TAG, "post submitted to API." + response.body().toString());
                                System.out.println("Data berhasil dikirim");
                                Toast.makeText(getApplicationContext(), "Berhasil memosting dipasar", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), TernakkuActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<UpdateStatusPasarResponse> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Berhasil memosting dipasar", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), TernakkuActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                });
            } else {
                btnPosting.setText("Batalkan Posting");
                btnPosting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                        apiService.updateStatus(0, Integer.valueOf(bundle.getString("KodeTernak"))).enqueue(new Callback<UpdateStatusPasarResponse>() {
                            @Override
                            public void onResponse(Call<UpdateStatusPasarResponse> call, Response<UpdateStatusPasarResponse> response) {
                                Log.i(TAG, "post submitted to API." + response.body().toString());
                                System.out.println("Data berhasil dikirim");
                                Toast.makeText(getApplicationContext(), "Berhasil membatalkan posting dipasar", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), TernakkuActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<UpdateStatusPasarResponse> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Berhasil membatalkan posting dipasar", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), TernakkuActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                });

            }

            btnHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                    intent.putExtra("IdTernak",bundle.getString("KodeTernak"));
                    startActivity(intent);
                }
            });

            txtVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("Video"))));
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Plase install Youtube before running the app", Toast.LENGTH_SHORT).show();
                    }
//                    Intent videoIntent = new Intent(Intent.ACTION_VIEW);
//                    videoIntent.setData(Uri.parse(bundle.getString("LinkVideo")));
//                    videoIntent.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
//                    startActivity(videoIntent);
                }
            });

            gambarUtama = bundle.getString("ImgUtama");
            gambar1 = bundle.getString("Img1");
            gambar2 = bundle.getString("Img2");
            images = new String[]{gambarUtama, gambar1, gambar2};
            for (int i = 0; i < images.length; i++){
                System.out.println("Index ke"+ i +",isinya :"+images[i]);
            }
            viewPager = (ViewPager) findViewById(R.id.viewPagerTernakku);
            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
            gambarTernakAdapter = new ListGambarTernakAdapter();
            viewPager.setAdapter(gambarTernakAdapter);
            indicator.setViewPager(viewPager);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class ListGambarTernakAdapter extends PagerAdapter {

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
            Context context = DetailTernakkuActivity.this;
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
