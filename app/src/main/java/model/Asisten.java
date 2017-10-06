package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 7/31/2017.
 */

public class Asisten {
    @SerializedName("asisten")
    @Expose
    private Integer asisten;
    @SerializedName("nama_depan")
    @Expose
    private String namaDepan;
    @SerializedName("nama_belakang")
    @Expose
    private String namaBelakang;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("video")
    @Expose
    private Object video;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("jumlah_merawat")
    @Expose
    private Integer jumlahMerawat;

    public Integer getAsisten() {
        return asisten;
    }

    public void setAsisten(Integer asisten) {
        this.asisten = asisten;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJumlahMerawat() {
        return jumlahMerawat;
    }

    public void setJumlahMerawat(Integer jumlahMerawat) {
        this.jumlahMerawat = jumlahMerawat;
    }
}
