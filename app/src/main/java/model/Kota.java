package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/4/2017.
 */

public class    Kota {
    @SerializedName("id_lokasi")
    @Expose
    private Integer idLokasi;
    @SerializedName("nama_daerah")
    @Expose
    private String namaDaerah;
    @SerializedName("nama_provinsi")
    @Expose
    private String namaProvinsi;

    public Integer getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(Integer idLokasi) {
        this.idLokasi = idLokasi;
    }

    public String getNamaDaerah() {
        return namaDaerah;
    }

    public void setNamaDaerah(String namaDaerah) {
        this.namaDaerah = namaDaerah;
    }

    public String getNamaProvinsi() {
        return namaProvinsi;
    }

    public void setNamaProvinsi(String namaProvinsi) {
        this.namaProvinsi = namaProvinsi;
    }

    @Override
    public String toString(){
        return namaDaerah;
    }
}
