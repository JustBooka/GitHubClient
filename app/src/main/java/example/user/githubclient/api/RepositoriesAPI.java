package example.user.githubclient.api;

import java.util.List;

import example.user.githubclient.Models.Repositories;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by alexey.bukin on 18.03.2015.
 */
public interface RepositoriesAPI {

    @GET("/user/repos")
    public void getRepos(@Query("access_token") String accessToken,
                         Callback<List<Repositories>> response);
}
