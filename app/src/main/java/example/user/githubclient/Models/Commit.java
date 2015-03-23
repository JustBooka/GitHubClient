package example.user.githubclient.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 23.03.2015.
 */
public class Commit {

    private Author author;
    private Committer committer;
    private String message;
    private Tree tree;
    private String url;
    private Integer commentCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return The committer
     */
    public Committer getCommitter() {
        return committer;
    }

    /**
     * @param committer The committer
     */
    public void setCommitter(Committer committer) {
        this.committer = committer;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The tree
     */
    public Tree getTree() {
        return tree;
    }

    /**
     * @param tree The tree
     */
    public void setTree(Tree tree) {
        this.tree = tree;
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
     * @return The commentCount
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * @param commentCount The comment_count
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
