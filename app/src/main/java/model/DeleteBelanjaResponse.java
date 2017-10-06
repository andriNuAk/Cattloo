package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 7/6/2017.
 */

public class DeleteBelanjaResponse {
    @SerializedName("UpdateStatusPasar")
    @Expose
    private List<DeleteBelanja> deleteBelanja = null;

    public List<DeleteBelanja> getDeleteBelanja() {
        return deleteBelanja;
    }

    public void setDeleteBelanja(List<DeleteBelanja> deleteBelanja) {
        this.deleteBelanja = deleteBelanja;
    }
}
