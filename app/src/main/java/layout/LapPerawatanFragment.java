package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dragmetohell.cattloo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LapPerawatanFragment extends Fragment {


    public LapPerawatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lap_perawatan, container, false);
    }

}