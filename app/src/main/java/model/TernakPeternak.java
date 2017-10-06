package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 7/25/2017.
 */

public class TernakPeternak {
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("nama_spesies_hewan")
    @Expose
    private String namaSpesiesHewan;
    @SerializedName("nama_jenis_hewan")
    @Expose
    private String namaJenisHewan;
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
    @SerializedName("bobot")
    @Expose
    private Integer bobot;
    @SerializedName("tinggi")
    @Expose
    private Integer tinggi;
    @SerializedName("panjang")
    @Expose
    private Integer panjang;
    @SerializedName("id_peternak")
    @Expose
    private Integer idPeternak;
    @SerializedName("nama_depan")
    @Expose
    private String namaDepan;
    @SerializedName("nama_belakang")
    @Expose
    private String namaBelakang;
    @SerializedName("url_foto")
    @Expose
    private String urlFoto;

    public Integer getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(Integer idTernak) {
        this.idTernak = idTernak;
    }

    public String getNamaSpesiesHewan() {
        return namaSpesiesHewan;
    }

    public void setNamaSpesiesHewan(String namaSpesiesHewan) {
        this.namaSpesiesHewan = namaSpesiesHewan;
    }

    public String getNamaJenisHewan() {
        return namaJenisHewan;
    }

    public void setNamaJenisHewan(String namaJenisHewan) {
        this.namaJenisHewan = namaJenisHewan;
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

    public Integer getIdPeternak() {
        return idPeternak;
    }

    public void setIdPeternak(Integer idPeternak) {
        this.idPeternak = idPeternak;
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
}
