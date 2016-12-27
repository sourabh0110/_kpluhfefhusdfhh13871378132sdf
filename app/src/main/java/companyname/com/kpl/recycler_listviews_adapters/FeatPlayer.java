package companyname.com.kpl.recycler_listviews_adapters;

/**
 * Created by admin on 12/27/2016.
 */
public class FeatPlayer {

    private int id,matches_played,goals_scored,yellow_cards,red_cards,assists;
    private String player_name,player_pos,player_team,image;


    public FeatPlayer(int id,int matches_played,int goals_scored,int yellow_cards,int red_cards,int assists,String player_name,String player_pos,String player_team,String image)
    {
        this.setId(id);
        this.setMatches_played(matches_played);
        this.setGoals_scored(goals_scored);
        this.setYellow_cards(yellow_cards);
        this.setRed_cards(red_cards);
        this.setAssists(assists);
        this.setPlayer_name(player_name);
        this.setPlayer_pos(player_pos);
        this.setPlayer_team(player_team);
        this.setImage(image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatches_played() {
        return matches_played;
    }

    public void setMatches_played(int matches_played) {
        this.matches_played = matches_played;
    }

    public int getGoals_scored() {
        return goals_scored;
    }

    public void setGoals_scored(int goals_scored) {
        this.goals_scored = goals_scored;
    }

    public int getYellow_cards() {
        return yellow_cards;
    }

    public void setYellow_cards(int yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public int getRed_cards() {
        return red_cards;
    }

    public void setRed_cards(int red_cards) {
        this.red_cards = red_cards;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_pos() {
        return player_pos;
    }

    public void setPlayer_pos(String player_pos) {
        this.player_pos = player_pos;
    }

    public String getPlayer_team() {
        return player_team;
    }

    public void setPlayer_team(String player_team) {
        this.player_team = player_team;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
