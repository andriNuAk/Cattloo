package helper;

/**
 * Created by drag me to hell on 4/25/2017.
 */

public class AkunHelper {
    int idUser;
    String username;
    String password;
    String email;
    String noTelepon;
    String namaDepan;
    String namaBelakang;

    public AkunHelper(int idUser, String username, String password, String email, String noTelepon, String namaDepan, String namaBelakang) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.noTelepon = noTelepon;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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
}
