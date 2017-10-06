package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/9/2017.
 */

public class BelanjaResponse {

    @SerializedName("Belanja")
    @Expose
    private List<Belanja> belanja = null;

    public List<Belanja> getBelanja() {
        return belanja;
    }

    public void setBelanja(List<Belanja> belanja) {
        this.belanja = belanja;
    }
}
