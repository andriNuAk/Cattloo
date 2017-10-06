package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/4/2017.
 */

public class Pesan {
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
    private String jumlahEkor;

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

    public String getJumlahEkor() {
        return jumlahEkor;
    }

    public void setJumlahEkor(String jumlahEkor) {
        this.jumlahEkor = jumlahEkor;
    }
}
