package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/11/2017.
 */

public class Visual {
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("spesies")
    @Expose
    private String spesies;
    @SerializedName("jenis_hewan")
    @Expose
    private String jenisHewan;
    @SerializedName("tgl_periksa")
    @Expose
    private String tglPeriksa;
    @SerializedName("bobot")
    @Expose
    private Integer bobot;
    @SerializedName("tinggi")
    @Expose
    private Integer tinggi;
    @SerializedName("panjang")
    @Expose
    private Integer panjang;
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
    @SerializedName("cara_ternak")
    @Expose
    private String caraTernak;

    public Integer getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(Integer idTernak) {
        this.idTernak = idTernak;
    }

    public String getSpesies() {
        return spesies;
    }

    public void setSpesies(String spesies) {
        this.spesies = spesies;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(String jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public String getTglPeriksa() {
        return tglPeriksa;
    }

    public void setTglPeriksa(String tglPeriksa) {
        this.tglPeriksa = tglPeriksa;
    }

    public Integer getBobot() {
        return bobot;
    }

    public void setBobot(Integer bobot) {
        this.bobot = bobot;
    }

    public Integer getTinggi() {
        return tinggi;
    }

    public void setTinggi(Integer tinggi) {
        this.tinggi = tinggi;
    }

    public Integer getPanjang() {
        return panjang;
    }

    public void setPanjang(Integer panjang) {
        this.panjang = panjang;
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

    public String getCaraTernak() {
        return caraTernak;
    }

    public void setCaraTernak(String caraTernak) {
        this.caraTernak = caraTernak;
    }

}
