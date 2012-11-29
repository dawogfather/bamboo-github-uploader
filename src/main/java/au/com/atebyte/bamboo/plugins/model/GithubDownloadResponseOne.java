package au.com.atebyte.bamboo.plugins.model;

/**
 * Github Download Response One Representation
 * User: tom romano
 * Date: 28/11/12
 * Time: 8:41 PM
 */
public class GithubDownloadResponseOne {

    private String url;
    private String html_url;
    private int id;
    private String name;
    private int size;
    private String description;
    private String content_type;
    private String policy;
    private String signature;
    private String bucket;
    private String accesskeyid;
    private String path;
    private String acl;
    private String expirationdate;
    private String prefix;
    private String mime_type;
    private boolean redirect;
    private String s3_url;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getAccesskeyid() {
        return accesskeyid;
    }

    public void setAccesskeyid(String accesskeyid) {
        this.accesskeyid = accesskeyid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public String getS3_url() {
        return s3_url;
    }

    public void setS3_url(String s3_url) {
        this.s3_url = s3_url;
    }

    @Override
    public String toString() {
        return "GithubDownloadResponseOne{" +
                "url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", description='" + description + '\'' +
                ", content_type='" + content_type + '\'' +
                ", policy='" + policy + '\'' +
                ", signature='" + signature + '\'' +
                ", bucket='" + bucket + '\'' +
                ", accesskeyid='" + accesskeyid + '\'' +
                ", path='" + path + '\'' +
                ", acl='" + acl + '\'' +
                ", expirationdate='" + expirationdate + '\'' +
                ", prefix='" + prefix + '\'' +
                ", mime_type='" + mime_type + '\'' +
                ", redirect=" + redirect +
                ", s3_url='" + s3_url + '\'' +
                '}';
    }
}
