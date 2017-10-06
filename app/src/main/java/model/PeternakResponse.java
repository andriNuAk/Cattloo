package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 7/20/2017.
 */

public class PeternakResponse {
    @SerializedName("Peternak")
    @Expose
    private List<Peternak> peternak = null;

    public List<Peternak> getPeternak() {
        return peternak;
    }

    public void setPeternak(List<Peternak> peternak) {
        this.peternak = peternak;
    }
}
