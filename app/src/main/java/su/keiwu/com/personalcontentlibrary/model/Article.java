package su.keiwu.com.personalcontentlibrary.model;

/**
 * Created by kei on 11/20/15.
 */
public class Article {
    String id;
    String title;
    String image_path;
    String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Article(String title, String image_path, String content) {
        this.title = title;
        this.image_path = image_path;
        this.content = content;
    }





}
