package example.user.githubclient.api;

import java.util.List;

import example.user.githubclient.Models.Commits;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by User on 23.03.2015.
 */
public interface CommitsAPI {

    @GET("/{nameOwner}/{repoName}/commits?sha")
    public void getCommits(@Path("nameOwner") String nameOwner, @Path("repoName") String repoName, @Query("access_token") String accessToken,
                           Callback<List<Commits>> response);
}
