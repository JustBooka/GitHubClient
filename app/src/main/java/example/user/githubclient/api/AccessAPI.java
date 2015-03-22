package example.user.githubclient.api;

import java.util.List;

import example.user.githubclient.Models.Access;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by Just on 3/21/2015.
 */
public interface AccessAPI {
    @Headers({
            "Accept: application/json"
    })
    @GET("/access_token{secretAndId}{code}")
    public void GetAccess(@Query("secrertAndId") String secretId, @Query("code") String code,
                          Callback<List<Access>> response);
}
