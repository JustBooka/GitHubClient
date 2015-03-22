package example.user.githubclient.api;

import java.util.List;

import example.user.githubclient.models.Repo;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by alexey.bukin on 18.03.2015.
 */
public interface RepositoriesAPI {

    @GET("/user/repos")
    public void getRepos(Callback<List<Repo>> response);
}
