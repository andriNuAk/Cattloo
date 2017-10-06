package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/2/2017.
 */

public class SampelResponse {
    @SerializedName("Sampel")
    @Expose
    private List<Sampel> sampel = null;

    public List<Sampel> getSampel() {
        return sampel;
    }

    public void setSampel(List<Sampel> sampel) {
        this.sampel = sampel;
    }
}
