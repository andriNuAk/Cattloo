package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/10/2017.
 */

public class LaporanBelanjaResponse {
    @SerializedName("LaporanBelanja")
    @Expose
    private List<LaporanBelanja> laporanBelanja = null;

    public List<LaporanBelanja> getLaporanBelanja() {
        return laporanBelanja;
    }

    public void setLaporanBelanja(List<LaporanBelanja> laporanBelanja) {
        this.laporanBelanja = laporanBelanja;
    }
}
