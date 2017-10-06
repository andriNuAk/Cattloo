package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by drag me to hell on 5/11/2017.
 */

public class VisualResponse {
    @SerializedName("Visual")
    @Expose
    private List<Visual> visual = null;

    public List<Visual> getVisual() {
        return visual;
    }

    public void setVisual(List<Visual> visual) {
        this.visual = visual;
    }
}
