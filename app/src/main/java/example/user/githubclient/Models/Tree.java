package example.user.githubclient.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 23.03.2015.
 */
public class Tree {

    private String sha;
    private String url;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}