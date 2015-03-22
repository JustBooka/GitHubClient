package example.user.githubclient.api;

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
    @GET("/access_token")
    public void GetAccess(@Query("client_id") String clientId, @Query("client_secret") String clientSecret,
                          @Query("code") String code,
                          Callback<Access> response);
}
