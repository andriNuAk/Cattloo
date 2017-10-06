package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/10/2017.
 */

public class LaporanBelanja {
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("id_belanja")
    @Expose
    private Integer idBelanja;
    @SerializedName("tanggal_belanja")
    @Expose
    private String tanggalBelanja;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("pembeli")
    @Expose
    private String pembeli;
    @SerializedName("foto_1")
    @Expose
    private String foto1;
    @SerializedName("status_beli")
    @Expose
    private Integer statusBeli;

    public Integer getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(Integer idTernak) {
        this.idTernak = idTernak;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public Integer getStatusBeli() {
        return statusBeli;
    }

    public void setStatusBeli(Integer statusBeli) {
        this.statusBeli = statusBeli;
    }
}
