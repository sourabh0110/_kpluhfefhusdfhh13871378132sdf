package companyname.com.kpl.recycler_listviews_adapters;

/**
 * Created by admin on 12/18/2016.
 */

public class Player {
    private String tm_name;
    private String player_image;
    private String player_dob;
    private int tm_code;
    private String mobno;
    private String player_name;
    private int player_id;

    public Player(String player_image,int tm_code,String tm_name,String mobno,String player_name,int player_id,String player_dob)
    {
        this.setPlayer_image(player_image);
        this.setTm_code(tm_code);
        this.setTm_name(tm_name);
        this.setMobno(mobno);
        this.setPlayer_name(player_name);
        this.setPlayer_dob(player_dob);
        this.setPlayer_id(player_id);

    }

    public String getTm_name() {
        return tm_name;
    }

    public void setTm_name(String tm_name) {
        this.tm_name = tm_name;
    }

    public String getPlayer_image() {
        return player_image;
    }

    public void setPlayer_image(String player_image) {
        this.player_image = player_image;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_dob() {
        return player_dob;
    }

    public void setPlayer_dob(String player_dob) {
        this.player_dob = player_dob;
    }

    public int getTm_code() {
        return tm_code;
    }

    public void setTm_code(int tm_code) {
        this.tm_code = tm_code;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }
}
