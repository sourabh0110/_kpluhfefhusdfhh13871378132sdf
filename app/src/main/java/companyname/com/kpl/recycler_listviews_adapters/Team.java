package companyname.com.kpl.recycler_listviews_adapters;

/**
 * Created by admin on 12/29/2016.
 */
public class Team {
    private String name,image;

    public Team(String name,String image)
    {
            this.name=name;
        this.image=image;
    }

    /*public News(String name,String title,int id)
    {
        this.setName(name);
        this.setTitle(title);
        this.setId(id);
        this.setContent(content);
        this.setDesc(desc);
        this.setNews_date(news_date);
        this.setNews_image(news_image);
    }
*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
