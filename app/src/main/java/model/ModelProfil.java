package model;

/**
 * Created by drag me to hell on 4/11/2017.
 */

public class ModelProfil {
    String img;
    String textLabel;
    String value;

    public ModelProfil(String img, String textLabel, String value) {
        this.img = img;
        this.textLabel = textLabel;
        this.value = value;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(String textLabel) {
        this.textLabel = textLabel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
