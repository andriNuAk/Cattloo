package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 7/20/2017.
 */

public class Peternak {
    @SerializedName("peternak")
    @Expose
    private Integer peternak;
    @SerializedName("nama_depan")
    @Expose
    private String namaDepan;
    @SerializedName("nama_belakang")
    @Expose
    private String namaBelakang;
    @SerializedName("url_foto")
    @Expose
    private String urlFoto;
    @SerializedName("kapasitas_ternak")
    @Expose
    private Integer kapasitasTernak;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("jumlah_terisi")
    @Expose
    private Integer jumlahTerisi;

    public Integer getPeternak() {
        return peternak;
    }

    public void setPeternak(Integer peternak) {
        this.peternak = peternak;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Integer getKapasitasTernak() {
        return kapasitasTernak;
    }

    public void setKapasitasTernak(Integer kapasitasTernak) {
        this.kapasitasTernak = kapasitasTernak;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getJumlahTerisi() {
        return jumlahTerisi;
    }

    public void setJumlahTerisi(Integer jumlahTerisi) {
        this.jumlahTerisi = jumlahTerisi;
    }
}
