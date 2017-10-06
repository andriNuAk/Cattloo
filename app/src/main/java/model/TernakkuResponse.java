package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/11/2017.
 */

public class TernakkuResponse {
    @SerializedName("Ternakku")
    @Expose
    private List<Ternakku> ternakku = null;

    public List<Ternakku> getTernakku() {
        return ternakku;
    }

    public void setTernakku(List<Ternakku> ternakku) {
        this.ternakku = ternakku;
    }

}
