package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 7/25/2017.
 */

public class TernakPeternakResponse {
    @SerializedName("TernakPeternak")
    @Expose
    private List<TernakPeternak> ternakPeternak = null;

    public List<TernakPeternak> getTernakPeternak() {
        return ternakPeternak;
    }

    public void setTernakPeternak(List<TernakPeternak> ternakPeternak) {
        this.ternakPeternak = ternakPeternak;
    }
}
