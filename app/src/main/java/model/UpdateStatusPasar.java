package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/11/2017.
 */

public class UpdateStatusPasar {
    @SerializedName("id_ternak")
    @Expose
    private Integer idTernak;
    @SerializedName("status_pasar")
    @Expose
    private Integer statusPasar;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getIdTernak() {
        return idTernak;
    }

    public void setIdTernak(Integer idTernak) {
        this.idTernak = idTernak;
    }

    public Integer getStatusPasar() {
        return statusPasar;
    }

    public void setStatusPasar(Integer statusPasar) {
        this.statusPasar = statusPasar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UpdateStatusPasar(Integer idTernak, Integer statusPasar, String status) {
        this.idTernak = idTernak;
        this.statusPasar = statusPasar;
        this.status = status;
    }
}
