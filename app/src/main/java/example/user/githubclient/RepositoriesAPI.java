package example.user.githubclient;

import java.util.List;

import example.user.githubclient.Models.Repo;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by alexey.bukin on 18.03.2015.
 */
public interface RepositoriesAPI {

    @GET("/user/repos")
    public void getRepos(Callback<List<Repo>> response);
}
