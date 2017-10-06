package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 4/28/2017.
 */

public class PasarResponse {
    @SerializedName("Pasar")
    @Expose
    private List<Pasar> pasar = null;

    public List<Pasar> getPasar() {
        return pasar;
    }

    public void setPasar(List<Pasar> pasar) {
        this.pasar = pasar;
    }
}
