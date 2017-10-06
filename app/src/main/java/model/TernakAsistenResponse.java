package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 8/1/2017.
 */

public class TernakAsistenResponse {
    @SerializedName("TernakAsisten")
    @Expose
    private List<TernakAsisten> ternakAsisten = null;

    public List<TernakAsisten> getTernakAsisten() {
        return ternakAsisten;
    }

    public void setTernakAsisten(List<TernakAsisten> ternakAsisten) {
        this.ternakAsisten = ternakAsisten;
    }
}
