package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dragmetohell.cattloo.R;
import com.example.dragmetohell.cattloo.ternakku.DetailTernakkuActivity;

import java.util.ArrayList;

import model.ListHistoryAdapter;
import model.ListLapBeliAdapter;
import model.ListTernakkuAdapter;
import model.ModelHistory;
import model.ModelTernak;

/**
 * A simple {@link Fragment} subclass.
 */
public class LapBeliFragment extends Fragment {

    private ArrayList<ModelTernak> mTernak;
    private String kategori;
    ListView lvLapBeli;
    ListLapBeliAdapter lapBeliAdapter;


    public LapBeliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lap_beli, container, false);
    }

    public void initView(){
//        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        RecyclerView.Adapter adapter = new HomeAdapter(getApplicationContext(), mHewan);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setOnClickListener(new AdapterView.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        lvLapBeli = (ListView) getActivity().findViewById(R.id.list_beli);
//        lapBeliAdapter = new ListLapBeliAdapter(getActivity().getApplicationContext(), mTernak);
//        lvLapBeli.setAdapter(lapBeliAdapter);
//        lvTernak.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new  Intent(getApplicationContext(), DetailTernakkuActivity.class);
//                String kodeTernak = mTernak.get(i).getKodeTernak();
//                String jenisHewan = mTernak.get(i).getJenisHewan();
//                String spesies = mTernak.get(i).getSpesies();
//                String caraTernak = mTernak.get(i).getCaraTernak();
//                String namaPeternak = mTernak.get(i).getNamaPeternak();
//                String namaAsisten = mTernak.get(i).getNamaAsisten();
//                String imgUtama = mTernak.get(i).getImgUtama();
//                String img1 = mTernak.get(i).getImgDetail1();
//                String img2 = mTernak.get(i).getImgDetail2();
//                String img3 = mTernak.get(i).getImgDetail3();
//                String video = mTernak.get(i).getUrlVIdeo();
//
//                intent.putExtra("KodeTernak", kodeTernak);
//                intent.putExtra("JenisHewan", jenisHewan);
//                intent.putExtra("Spesies", spesies);
//                intent.putExtra("CaraTernak", caraTernak);
//                intent.putExtra("NamaPeternak", namaPeternak);
//                intent.putExtra("NamaAsisten", namaAsisten);
//                intent.putExtra("ImgUtama", imgUtama);
//                intent.putExtra("Img1", img1);
//                intent.putExtra("Img2", img2);
//                intent.putExtra("Img3", img3);
//                intent.putExtra("Video", video);
//
//
//                startActivity(intent);
//            }
//        });
    }

    private ArrayList<ModelTernak> initModelTernak(){
        mTernak = new ArrayList<>();
        ModelTernak ternak = new ModelTernak("T001","Domba","Domba Garut", "Garut","7000000","Growing","Aziz Firmansyah","Opik Sutisna","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg","http://www.infopeternakan.com/wp-content/uploads/2015/04/Mengenal-jenis-domba-garut-beserta-cirinya.jpg",
                "https://youtu.be/RoNsKd3J6Ww","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt diam sed lectus tincidunt, quis congue purus aliquet. Morbi urna risus, consequat quis tincidunt non, semper eu neque. In dapibus, nulla ac euismod cursus, enim enim hendrerit nisi, ac consectetur ante tortor ac risus. Integer pellentesque luctus est, eu faucibus enim tempor vulputate. Nunc vel tincidunt erat. Ut sit amet nulla lacinia, fermentum quam sit amet, condimentum metus. Donec eu massa dolor.\n" +
                "\n" +
                "Suspendisse molestie tortor eget commodo mollis. Nam quis libero facilisis, pharetra sem sed, ultrices ante. Nulla in dui ac lorem consequat iaculis lobortis in risus. Quisque turpis lorem, varius a consequat blandit, sollicitudin elementum odio. Vestibulum in elementum magna. Curabitur lacus tortor, aliquet vitae urna non, auctor laoreet quam. Ut mauris lectus, finibus eget mi sit amet, suscipit tincidunt est. Aliquam quis consectetur est, consequat volutpat ligula. Ut lectus nulla, vehicula nec ultricies in, blandit eu ipsum.\n" +
                "\n" +
                "Donec semper nunc vestibulum massa tempor pellentesque at in metus. Praesent ut nulla metus. Quisque ac dignissim orci. Mauris pellentesque tortor vitae sagittis auctor. Pellentesque accumsan nulla quis erat aliquet, placerat scelerisque ipsum lacinia. Duis et convallis augue, vel placerat est. Integer cursus odio felis, sed mattis lacus tempor dictum.");
        mTernak.add(ternak);


        return mTernak;
    }

}
