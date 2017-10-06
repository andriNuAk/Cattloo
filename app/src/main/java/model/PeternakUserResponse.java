package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 7/20/2017.
 */

public class PeternakUserResponse {
    @SerializedName("PeternakUser")
    @Expose
    private List<PeternakUser> peternakUser = null;

    public List<PeternakUser> getPeternakUser() {
        return peternakUser;
    }

    public void setPeternakUser(List<PeternakUser> peternakUser) {
        this.peternakUser = peternakUser;
    }
}
