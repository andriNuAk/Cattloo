package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 4/17/2017.
 */

public class HewanResponse {
    @SerializedName("Hewan")
    @Expose
    private List<Hewan> hewan = null;

    public List<Hewan> getHewan() {
        return hewan;
    }

    public void setHewan(List<Hewan> hewan) {
        this.hewan = hewan;
    }
}
