package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 7/6/2017.
 */

public class DeleteBelanja {
    @SerializedName("id_belanja")
    @Expose
    private Integer idBelanja;

    public Integer getIdBelanja() {
        return idBelanja;
    }

    public void setIdBelanja(Integer idBelanja) {
        this.idBelanja = idBelanja;
    }

    public DeleteBelanja(Integer idBelanja) {
        this.idBelanja = idBelanja;
    }
}
