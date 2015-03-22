package example.user.githubclient.api;

import java.util.List;

import example.user.githubclient.models.Access;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by Just on 3/21/2015.
 */
public interface AccessAPI {

    @GET("/access_token?client_id=b89e0192e6b3c9bbd9b8&client_secret=c3e6ed559361f528cb6c4e6e5040d31459236698&")
    public void GetAccess(
//                              @Path("clientId") String clientId,@Path("clientSecret") String clientSecret
             @Query("code") String code,
                          Callback<List<Access>> response);
}
