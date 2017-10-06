package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/15/2017.
 */

public class Penjualan {
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("id_penjualan")
    @Expose
    private Integer idPenjualan;
    @SerializedName("tanggal_jual")
    @Expose
    private String tanggalJual;
    @SerializedName("harga_jual")
    @Expose
    private Integer hargaJual;
    @SerializedName("margin")
    @Expose
    private Integer margin;
    @SerializedName("untung")
    @Expose
    private Integer untung;
    @SerializedName("nama_belakang")
    @Expose
    private String namaBelakang;
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("jenis_hewan")
    @Expose
    private String jenisHewan;
    @SerializedName("spesies")
    @Expose
    private String spesies;
    @SerializedName("foto_1")
    @Expose
    private String foto1;
    @SerializedName("foto_2")
    @Expose
    private String foto2;
    @SerializedName("foto_3")
    @Expose
    private String foto3;
    @SerializedName("url_video")
    @Expose
    private String urlVideo;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(Integer idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public String getTanggalJual() {
        return tanggalJual;
    }

    public void setTanggalJual(String tanggalJual) {
        this.tanggalJual = tanggalJual;
    }

    public Integer getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Integer hargaJual) {
        this.hargaJual = hargaJual;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Integer getUntung() {
        return untung;
    }

    public void setUntung(Integer untung) {
        this.untung = untung;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public Integer getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(Integer idTernak) {
        this.idTernak = idTernak;
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

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

}
