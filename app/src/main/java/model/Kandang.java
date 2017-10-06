package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 7/27/2017.
 */

public class Kandang {
    @SerializedName("kandang")
    @Expose
    private Integer kandang;
    @SerializedName("nama_kandang")
    @Expose
    private String namaKandang;
    @SerializedName("kapasitas")
    @Expose
    private Integer kapasitas;
    @SerializedName("pemilik_kandang")
    @Expose
    private String pemilikKandang;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("jumlah_terisi")
    @Expose
    private Integer jumlahTerisi;

    public Integer getKandang() {
        return kandang;
    }

    public void setKandang(Integer kandang) {
        this.kandang = kandang;
    }

    public String getNamaKandang() {
        return namaKandang;
    }

    public void setNamaKandang(String namaKandang) {
        this.namaKandang = namaKandang;
    }

    public Integer getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getPemilikKandang() {
        return pemilikKandang;
    }

    public void setPemilikKandang(String pemilikKandang) {
        this.pemilikKandang = pemilikKandang;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJumlahTerisi() {
        return jumlahTerisi;
    }

    public void setJumlahTerisi(Integer jumlahTerisi) {
        this.jumlahTerisi = jumlahTerisi;
    }
}
