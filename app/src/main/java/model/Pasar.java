package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 4/28/2017.
 */

public class Pasar {
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("nama_depan")
    @Expose
    private String namaDepan;
    @SerializedName("nama_belakang")
    @Expose
    private String namaBelakang;
    @SerializedName("no_telepon")
    @Expose
    private String noTelepon;
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("jenis_hewan")
    @Expose
    private String jenisHewan;
    @SerializedName("spesies")
    @Expose
    private String spesies;
    @SerializedName("nama_daerah")
    @Expose
    private String namaDaerah;
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
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    public Pasar(Integer idUser, String namaDepan, String namaBelakang, String noTelepon, Integer idTernak, String jenisHewan, String spesies, String namaDaerah, String foto1, String foto2, String foto3, String urlVideo, Integer harga, String deskripsi) {
        this.idUser = idUser;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.noTelepon = noTelepon;
        this.idTernak = idTernak;
        this.jenisHewan = jenisHewan;
        this.spesies = spesies;
        this.namaDaerah = namaDaerah;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.urlVideo = urlVideo;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
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

    public String getNamaDaerah() {
        return namaDaerah;
    }

    public void setNamaDaerah(String namaDaerah) {
        this.namaDaerah = namaDaerah;
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

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
