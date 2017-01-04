package companyname.com.kpl.recycler_listviews_adapters;

/**
 * Created by admin on 1/3/2017.
 */
public class Referee {
    private String ref_name;
    private String ref_profile_pic;
    private int ref_id;
    public boolean isSelected;

    public Referee(String ref_name,String ref_profile_pic,int ref_id)
    {
        this.setRef_name(ref_name);
        this.setRef_profile_pic(ref_profile_pic);
        this.setRef_id(ref_id);

    }

    public String getRef_name() {
        return ref_name;
    }

    public void setRef_name(String ref_name) {
        this.ref_name = ref_name;
    }

    public String getRef_profile_pic() {
        return ref_profile_pic;
    }

    public void setRef_profile_pic(String ref_profile_pic) {
        this.ref_profile_pic = ref_profile_pic;
    }

    public int getRef_id() {
        return ref_id;
    }

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
    }
}
