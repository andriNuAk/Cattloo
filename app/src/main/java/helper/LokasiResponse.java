package helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/18/2017.
 */

public class LokasiResponse {
    @SerializedName("Lokasi")
    @Expose
    private List<Lokasi> lokasi = null;

    public List<Lokasi> getLokasi() {
        return lokasi;
    }

    public void setLokasi(List<Lokasi> lokasi) {
        this.lokasi = lokasi;
    }
}
