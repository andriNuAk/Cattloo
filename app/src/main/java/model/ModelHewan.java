package model;

/**
 * Created by drag me to hell on 3/30/2017.
 */
public class ModelHewan {
    String namaPemilik;
    String jenisHewan;
    String spesies;
    String lokasi;
    String harga;
    String nomoKontak;
    String imgUtama;
    String imgDetail1;
    String imgDetail2;
    String imgDetail3;
    String urlVIdeo;
    String deskripsi;


    public ModelHewan(String namaPemilik, String jenisHewan, String spesies, String lokasi, String harga, String nomoKontak, String imgUtama, String imgDetail1, String imgDetail2, String imgDetail3, String urlVIdeo, String deskripsi) {
        this.namaPemilik = namaPemilik;
        this.jenisHewan = jenisHewan;
        this.spesies = spesies;
        this.lokasi = lokasi;
        this.harga = harga;
        this.nomoKontak = nomoKontak;
        this.imgUtama = imgUtama;
        this.imgDetail1 = imgDetail1;
        this.imgDetail2 = imgDetail2;
        this.imgDetail3 = imgDetail3;
        this.urlVIdeo = urlVIdeo;
        this.deskripsi = deskripsi;
    }


    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
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

    public String getNomoKontak() {
        return nomoKontak;
    }

    public void setNomoKontak(String nomoKontak) {
        this.nomoKontak = nomoKontak;
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
