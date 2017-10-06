package model;

/**
 * Created by drag me to hell on 4/5/2017.
 */

public class ModelTernak {
    String kodeTernak;
    String jenisHewan;
    String spesies;
    String lokasi;
    String harga;
    String caraTernak;
    String namaPeternak;
    String namaAsisten;
    String imgUtama;
    String imgDetail1;
    String imgDetail2;
    String imgDetail3;
    String urlVIdeo;
    String deskripsi;

    public ModelTernak(String kodeTernak, String jenisHewan, String spesies, String lokasi, String harga, String caraTernak, String namaPeternak, String namaAsisten, String imgUtama, String imgDetail1, String imgDetail2, String imgDetail3, String urlVIdeo, String deskripsi) {
        this.kodeTernak = kodeTernak;
        this.jenisHewan = jenisHewan;
        this.spesies = spesies;
        this.lokasi = lokasi;
        this.harga = harga;
        this.caraTernak = caraTernak;
        this.namaPeternak = namaPeternak;
        this.namaAsisten = namaAsisten;
        this.imgUtama = imgUtama;
        this.imgDetail1 = imgDetail1;
        this.imgDetail2 = imgDetail2;
        this.imgDetail3 = imgDetail3;
        this.urlVIdeo = urlVIdeo;
        this.deskripsi = deskripsi;
    }

    public String getKodeTernak() {
        return kodeTernak;
    }

    public void setKodeTernak(String kodeTernak) {
        this.kodeTernak = kodeTernak;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getCaraTernak() {
        return caraTernak;
    }

    public void setCaraTernak(String caraTernak) {
        this.caraTernak = caraTernak;
    }

    public String getNamaPeternak() {
        return namaPeternak;
    }

    public void setNamaPeternak(String namaPeternak) {
        this.namaPeternak = namaPeternak;
    }

    public String getNamaAsisten() {
        return namaAsisten;
    }

    public void setNamaAsisten(String namaAsisten) {
        this.namaAsisten = namaAsisten;
    }

    public String getImgUtama() {
        return imgUtama;
    }

    public void setImgUtama(String imgUtama) {
        this.imgUtama = imgUtama;
    }

    public String getImgDetail1() {
        return imgDetail1;
    }

    public void setImgDetail1(String imgDetail1) {
        this.imgDetail1 = imgDetail1;
    }

    public String getImgDetail2() {
        return imgDetail2;
    }

    public void setImgDetail2(String imgDetail2) {
        this.imgDetail2 = imgDetail2;
    }

    public String getImgDetail3() {
        return imgDetail3;
    }

    public void setImgDetail3(String imgDetail3) {
        this.imgDetail3 = imgDetail3;
    }

    public String getUrlVIdeo() {
        return urlVIdeo;
    }

    public void setUrlVIdeo(String urlVIdeo) {
        this.urlVIdeo = urlVIdeo;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
