package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/5/2017.
 */

public class Pesanan {
    @SerializedName("kode_pesan")
    @Expose
    private Integer kodePesan;
    @SerializedName("kode_sampel")
    @Expose
    private Integer kodeSampel;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("tgl_pesan")
    @Expose
    private String tglPesan;
    @SerializedName("status_pesan")
    @Expose
    private String statusPesan;
    @SerializedName("jumlah_ekor")
    @Expose
    private Integer jumlahEkor;
    @SerializedName("foto_1")
    @Expose
    private String foto1;
    @SerializedName("jenis_hewan")
    @Expose
    private String jenisHewan;
    @SerializedName("spesies")
    @Expose
    private String spesies;

    public Integer getKodePesan() {
        return kodePesan;
    }

    public void setKodePesan(Integer kodePesan) {
        this.kodePesan = kodePesan;
    }

    public Integer getKodeSampel() {
        return kodeSampel;
    }

    public void setKodeSampel(Integer kodeSampel) {
        this.kodeSampel = kodeSampel;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTglPesan() {
        return tglPesan;
    }

    public void setTglPesan(String tglPesan) {
        this.tglPesan = tglPesan;
    }

    public String getStatusPesan() {
        return statusPesan;
    }

    public void setStatusPesan(String statusPesan) {
        this.statusPesan = statusPesan;
    }

    public Integer getJumlahEkor() {
        return jumlahEkor;
    }

    public void setJumlahEkor(Integer jumlahEkor) {
        this.jumlahEkor = jumlahEkor;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(String jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public String getSpesies() {
        return spesies;
    }

    public void setSpesies(String spesies) {
        this.spesies = spesies;
    }
}
