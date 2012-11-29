package au.com.atebyte.bamboo.plugins.model;

/**
 * Github Download Request One Representation
 * User: tom romano
 * Date: 28/11/12
 * Time: 8:41 PM
 */
public class GithubDownloadRequestOne {

    private String name;
    private int size;
    private String description;
    private String content_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String toJSON() {
        return "{" +
                "\"name\":'" + name + '\'' +
                ", \"size\":" + size +
                ", \"description\":'" + description + '\'' +
                ", \"content_type\":'" + content_type + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "GithubDownloadRequestOne{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", description='" + description + '\'' +
                ", content_type='" + content_type + '\'' +
                '}';
    }
}
