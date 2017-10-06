package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/11/2017.
 */

public class Ternakku {
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
    @SerializedName("cara_ternak")
    @Expose
    private String caraTernak;
    @SerializedName("status_pasar")
    @Expose
    private Integer statusPasar;
    @SerializedName("nama_depan_peternak")
    @Expose
    private String namaDepanPeternak;
    @SerializedName("nama_belakang_peternak")
    @Expose
    private String namaBelakangPeternak;
    @SerializedName("nama_kandang")
    @Expose
    private String namaKandang;
    @SerializedName("nama_depan_asisten")
    @Expose
    private String namaDepanAsisten;
    @SerializedName("nama_belakang_asisten")
    @Expose
    private String namaBelakangAsisten;

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

    public String getCaraTernak() {
        return caraTernak;
    }

    public void setCaraTernak(String caraTernak) {
        this.caraTernak = caraTernak;
    }

    public Integer getStatusPasar() {
        return statusPasar;
    }

    public void setStatusPasar(Integer statusPasar) {
        this.statusPasar = statusPasar;
    }

    public String getNamaDepanPeternak() {
        return namaDepanPeternak;
    }

    public void setNamaDepanPeternak(String namaDepanPeternak) {
        this.namaDepanPeternak = namaDepanPeternak;
    }

    public String getNamaBelakangPeternak() {
        return namaBelakangPeternak;
    }

    public void setNamaBelakangPeternak(String namaBelakangPeternak) {
        this.namaBelakangPeternak = namaBelakangPeternak;
    }

    public String getNamaKandang() {
        return namaKandang;
    }

    public void setNamaKandang(String namaKandang) {
        this.namaKandang = namaKandang;
    }

    public String getNamaDepanAsisten() {
        return namaDepanAsisten;
    }

    public void setNamaDepanAsisten(String namaDepanAsisten) {
        this.namaDepanAsisten = namaDepanAsisten;
    }

    public String getNamaBelakangAsisten() {
        return namaBelakangAsisten;
    }

    public void setNamaBelakangAsisten(String namaBelakangAsisten) {
        this.namaBelakangAsisten = namaBelakangAsisten;
    }
}
