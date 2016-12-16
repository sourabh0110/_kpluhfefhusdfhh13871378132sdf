package companyname.com.kpl.recycler_listviews_adapters;

/**
 * Created by admin on 12/16/2016.
 */
public class Championship {
    private String tm_name,tm_image;
    private int tm_id;

    public Championship(String tm_image,int tm_id,String tm_name)
    {
        this.setTm_image(tm_image);
        this.setTm_name(tm_name);
        this.setTm_id(tm_id);

    }

    public String getTm_name() {
        return tm_name;
    }

    public void setTm_name(String tm_name) {
        this.tm_name = tm_name;
    }

    public String getTm_image() {
        return tm_image;
    }

    public void setTm_image(String tm_image) {
        this.tm_image = tm_image;
    }

    public int getTm_id() {
        return tm_id;
    }

    public void setTm_id(int tm_id) {
        this.tm_id = tm_id;
    }
}
