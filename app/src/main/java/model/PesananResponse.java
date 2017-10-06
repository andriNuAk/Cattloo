package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/5/2017.
 */

public class PesananResponse {
    @SerializedName("Pesanan")
    @Expose
    private List<Pesanan> pesanan = null;

    public List<Pesanan> getPesanan() {
        return pesanan;
    }

    public void setPesanan(List<Pesanan> pesanan) {
        this.pesanan = pesanan;
    }
}
