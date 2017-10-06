package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 7/31/2017.
 */

public class AsistenResponse {
    @SerializedName("Asisten")
    @Expose
    private List<Asisten> asisten = null;

    public List<Asisten> getAsisten() {
        return asisten;
    }

    public void setAsisten(List<Asisten> asisten) {
        this.asisten = asisten;
    }

}
