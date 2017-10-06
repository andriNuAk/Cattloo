package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/15/2017.
 */

public class PenjualanResponse {
    @SerializedName("Penjualan")
    @Expose
    private List<Penjualan> penjualan = null;

    public List<Penjualan> getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(List<Penjualan> penjualan) {
        this.penjualan = penjualan;
    }
}
