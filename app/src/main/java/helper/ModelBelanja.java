package helper;

/**
 * Created by drag me to hell on 5/9/2017.
 */

public class ModelBelanja {
    int idBelanja;
    String tanggalBelanja;
    int harga;
    int statusBeli;
    int idTernak;
    String foto1;

    public ModelBelanja(int idBelanja, String tanggalBelanja, int harga, int statusBeli, int idTernak, String foto1) {
        this.idBelanja = idBelanja;
        this.tanggalBelanja = tanggalBelanja;
        this.harga = harga;
        this.statusBeli = statusBeli;
        this.idTernak = idTernak;
        this.foto1 = foto1;
    }

    public int getIdBelanja() {
        return idBelanja;
    }

    public void setIdBelanja(int idBelanja) {
        this.idBelanja = idBelanja;
    }

    public String getTanggalBelanja() {
        return tanggalBelanja;
    }

    public void setTanggalBelanja(String tanggalBelanja) {
        this.tanggalBelanja = tanggalBelanja;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStatusBeli() {
        return statusBeli;
    }

    public void setStatusBeli(int statusBeli) {
        this.statusBeli = statusBeli;
    }

    public int getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(int idTernak) {
        this.idTernak = idTernak;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }
}
