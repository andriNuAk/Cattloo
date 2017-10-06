package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/15/2017.
 */

public class PerawatanResponse {
    @SerializedName("Perawatan")
    @Expose
    private List<Perawatan> perawatan = null;

    public List<Perawatan> getPerawatan() {
        return perawatan;
    }

    public void setPerawatan(List<Perawatan> perawatan) {
        this.perawatan = perawatan;
    }
}
