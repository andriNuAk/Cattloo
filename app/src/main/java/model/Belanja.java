package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/9/2017.
 */

public class Belanja {
    @SerializedName("id_belanja")
    @Expose
    private Integer idBelanja;
    @SerializedName("tanggal_belanja")
    @Expose
    private String tanggalBelanja;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("status_beli")
    @Expose
    private Integer statusBeli;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;

    public Integer getIdBelanja() {
        return idBelanja;
    }

    public void setIdBelanja(Integer idBelanja) {
        this.idBelanja = idBelanja;
    }

    public String getTanggalBelanja() {
        return tanggalBelanja;
    }

    public void setTanggalBelanja(String tanggalBelanja) {
        this.tanggalBelanja = tanggalBelanja;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(Integer idTernak) {
        this.idTernak = idTernak;
    }

    public Integer getStatusBeli() {
        return statusBeli;
    }

    public void setStatusBeli(Integer statusBeli) {
        this.statusBeli = statusBeli;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
