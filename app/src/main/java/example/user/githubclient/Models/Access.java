package example.user.githubclient.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Just on 3/21/2015.
 */
public class Access {

//
    @SerializedName("access_token")
    public String access_token;
    @SerializedName("scope")
    public String scope;
    @SerializedName("token_type")
    public String token_type;

}
