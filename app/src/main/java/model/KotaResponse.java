package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/4/2017.
 */

public class KotaResponse {
    @SerializedName("Kota")
    @Expose
    private List<Kota> kota = null;

    public List<Kota> getKota() {
        return kota;
    }

    public void setKota(List<Kota> kota) {
        this.kota = kota;
    }
}
