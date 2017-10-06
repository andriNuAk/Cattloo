package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/2/2017.
 */

public class Sampel {
    @SerializedName("kode_sampel")
    @Expose
    private Integer kodeSampel;
    @SerializedName("foto_1")
    @Expose
    private String foto1;
    @SerializedName("bobot")
    @Expose
    private Integer bobot;
    @SerializedName("panjang")
    @Expose
    private Integer panjang;
    @SerializedName("tinggi")
    @Expose
    private Integer tinggi;
    @SerializedName("foto_2")
    @Expose
    private String foto2;
    @SerializedName("foto_3")
    @Expose
    private String foto3;
    @SerializedName("url_video")
    @Expose
    private String urlVideo;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("jenis_hewan")
    @Expose
    private String jenisHewan;
    @SerializedName("spesies")
    @Expose
    private String spesies;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("cara_ternak")
    @Expose
    private String caraTernak;

    public Integer getKodeSampel() {
        return kodeSampel;
    }

    public void setKodeSampel(Integer kodeSampel) {
        this.kodeSampel = kodeSampel;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public Integer getBobot() {
        return bobot;
    }

    public void setBobot(Integer bobot) {
        this.bobot = bobot;
    }

    public Integer getPanjang() {
        return panjang;
    }

    public void setPanjang(Integer panjang) {
        this.panjang = panjang;
    }

    public Integer getTinggi() {
        return tinggi;
    }

    public void setTinggi(Integer tinggi) {
        this.tinggi = tinggi;
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getCaraTernak() {
        return caraTernak;
    }

    public void setCaraTernak(String caraTernak) {
        this.caraTernak = caraTernak;
    }
}
