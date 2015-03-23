package example.user.githubclient.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 23.03.2015.
 */
public class Commits {

    private String sha;
    private Commit commit;
    private String url;
    private String htmlUrl;
    private String commentsUrl;
    private Object author;
    private Object committer;
    private List<Object> parents = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The sha
     */
    public String getSha() {
        return sha;
    }

    /**
     * @param sha The sha
     */
    public void setSha(String sha) {
        this.sha = sha;
    }

    /**
     * @return The commit
     */
    public Commit getCommit() {
        return commit;
    }

    /**
     * @param commit The commit
     */
    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * @param htmlUrl The html_url
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * @return The commentsUrl
     */
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * @param commentsUrl The comments_url
     */
    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    /**
     * @return The author
     */
    public Object getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Object author) {
        this.author = author;
    }

    /**
     * @return The committer
     */
    public Object getCommitter() {
        return committer;
    }

    /**
     * @param committer The committer
     */
    public void setCommitter(Object committer) {
        this.committer = committer;
    }

    /**
     * @return The parents
     */
    public List<Object> getParents() {
        return parents;
    }

    /**
     * @param parents The parents
     */
    public void setParents(List<Object> parents) {
        this.parents = parents;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}