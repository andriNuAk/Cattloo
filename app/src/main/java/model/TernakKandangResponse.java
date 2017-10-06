package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 7/28/2017.
 */

public class TernakKandangResponse {
    @SerializedName("TernakKandang")
    @Expose
    private List<TernakKandang> ternakKandang = null;

    public List<TernakKandang> getTernakKandang() {
        return ternakKandang;
    }

    public void setTernakKandang(List<TernakKandang> ternakKandang) {
        this.ternakKandang = ternakKandang;
    }
}
