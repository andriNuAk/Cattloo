package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 7/27/2017.
 */

public class KandangResponse {
    @SerializedName("Kandang")
    @Expose
    private List<Kandang> kandang = null;

    public List<Kandang> getKandang() {
        return kandang;
    }

    public void setKandang(List<Kandang> kandang) {
        this.kandang = kandang;
    }
}
