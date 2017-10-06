package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/4/2017.
 */

public class PesanResponse {
    @SerializedName("Pesan")
    @Expose
    private List<Pesan> pesan = null;

    public List<Pesan> getPesan() {
        return pesan;
    }

    public void setPesan(List<Pesan> pesan) {
        this.pesan = pesan;
    }
}
