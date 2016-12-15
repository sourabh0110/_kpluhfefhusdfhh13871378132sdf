package companyname.com.kpl.admin_files;

/**
 * Created by admin on 12/13/2016.
 */
public class News {
    private String name,title,desc,content,news_image;
    private int id;
    private String news_date;
    /*
    * ORIGINAL WS
    * public News(String name,String title,int id,String desc,String content,String news_image,String news_date)
        {
            this.setName(name);
            this.setTitle(title);
            this.setId(id);
            this.setContent(content);
            this.setDesc(desc);
            this.setNews_date(news_date);
            this.setNews_image(news_image);
        }
    * */
    public News(String news_image,int id,String name,String title,String desc)
    {
        this.setName(name);
        this.setTitle(title);
        this.setId(id);
        this.setContent(content);
        this.setDesc(desc);
        this.setNews_date(news_date);
        this.setNews_image(news_image);
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


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNews_image() {
        return news_image;
    }

    public void setNews_image(String news_image) {
        this.news_image = news_image;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

