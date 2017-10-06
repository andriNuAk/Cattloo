package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/11/2017.
 */

public class UpdateStatusPasarResponse {
    @SerializedName("UpdateStatusPasar")
    @Expose
    private List<UpdateStatusPasar> updateStatusPasar = null;

    public List<UpdateStatusPasar> getUpdateStatusPasar() {
        return updateStatusPasar;
    }

    public void setUpdateStatusPasar(List<UpdateStatusPasar> updateStatusPasar) {
        this.updateStatusPasar = updateStatusPasar;
    }
}
