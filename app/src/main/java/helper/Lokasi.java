package helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 5/18/2017.
 */

public class Lokasi {
    @SerializedName("nama_daerah")
    @Expose
    private String namaDaerah;

    public String getNamaDaerah() {
        return namaDaerah;
    }

    public void setNamaDaerah(String namaDaerah) {
        this.namaDaerah = namaDaerah;
    }

    @Override
    public String toString(){
        return namaDaerah;
    }
}
