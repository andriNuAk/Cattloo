package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 4/17/2017.
 */

public class Hewan {
    @SerializedName("id_hewan")
    @Expose
    private Integer idHewan;
    @SerializedName("jenis_hewan")
    @Expose
    private String jenisHewan;
    @SerializedName("spesies")
    @Expose
    private String spesies;

    public Integer getIdHewan() {
        return idHewan;
    }

    public void setIdHewan(Integer idHewan) {
        this.idHewan = idHewan;
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


    public Hewan(Integer idHewan, String jenisHewan, String spesies) {
        this.idHewan = idHewan;
        this.jenisHewan = jenisHewan;
        this.spesies = spesies;
    }

    @Override
    public String toString(){
        return jenisHewan;
    }
}
