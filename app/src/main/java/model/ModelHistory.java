package model;

/**
 * Created by drag me to hell on 4/6/2017.
 */

public class ModelHistory {
    String kodeVisual;
    String tglVisual;
    String kodeTernak;
    String jenisHewan;
    String spesies;
    String caraTernak;
    String imgUtama;
    String img1;
    String img2;
    String img3;
    String videoURL;
    String namaAsisten;
    String namaPeternak;

    public ModelHistory(String kodeVisual, String tglVisual, String kodeTernak, String jenisHewan, String spesies, String caraTernak, String imgUtama, String img1, String img2, String img3, String videoURL, String namaAsisten, String namaPeternak) {
        this.kodeVisual = kodeVisual;
        this.tglVisual = tglVisual;
        this.kodeTernak = kodeTernak;
        this.jenisHewan = jenisHewan;
        this.spesies = spesies;
        this.caraTernak = caraTernak;
        this.imgUtama = imgUtama;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.videoURL = videoURL;
        this.namaAsisten = namaAsisten;
        this.namaPeternak = namaPeternak;
    }

    public String getKodeVisual() {
        return kodeVisual;
    }

    public void setKodeVisual(String kodeVisual) {
        this.kodeVisual = kodeVisual;
    }

    public String getTglVisual() {
        return tglVisual;
    }

    public void setTglVisual(String tglVisual) {
        this.tglVisual = tglVisual;
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

    public String getCaraTernak() {
        return caraTernak;
    }

    public void setCaraTernak(String caraTernak) {
        this.caraTernak = caraTernak;
    }

    public String getImgUtama() {
        return imgUtama;
    }

    public void setImgUtama(String imgUtama) {
        this.imgUtama = imgUtama;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getNamaAsisten() {
        return namaAsisten;
    }

    public void setNamaAsisten(String namaAsisten) {
        this.namaAsisten = namaAsisten;
    }

    public String getNamaPeternak() {
        return namaPeternak;
    }

    public void setNamaPeternak(String namaPeternak) {
        this.namaPeternak = namaPeternak;
    }
}
