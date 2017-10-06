package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 4/19/2017.
 */

public class User {
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("no_telepon")
    @Expose
    private String noTelepon;
    @SerializedName("nama_depan")
    @Expose
    private String namaDepan;
    @SerializedName("nama_belakang")
    @Expose
    private String namaBelakang;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("hak_akses")
    @Expose
    private String hakAKses;


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
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

    public String getFoto() {return foto;}

    public void setFoto(String foto) {this.foto = foto;}

    public String getHakAKses() {return hakAKses;}

    public void setHakAKses(String hakAKses) {this.hakAKses = hakAKses;}
}
