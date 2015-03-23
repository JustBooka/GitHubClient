package example.user.githubclient.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import example.user.githubclient.R;
import example.user.githubclient.api.AccessAPI;
import example.user.githubclient.Models.Access;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 18.03.2015.
 */
public class LoginActivity extends Activity {

    private static final List<String> SCOPES = Arrays.asList("repo", "user", "gist");
    private static final String CLIENT_ID = "b89e0192e6b3c9bbd9b8";
    private static final String CLIENT_SECRET = "c3e6ed559361f528cb6c4e6e5040d31459236698";
    public static String ACCESS_TOKEN;

    public static String OAUTH_URL = "https://github.com/login/oauth/authorize";
    public static String TOKEN_URL = "https://github.com/login/oauth/access_token";
    public static String TOKEN_URL2 = "https://github.com/login/oauth";
    public static String TO_URL = TOKEN_URL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&";

    ProgressBar pb;
    SharedPreferences sPref;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setVisibility(View.INVISIBLE);

        String url = OAUTH_URL + "?client_id=" + CLIENT_ID + "&scope=repo,user,gist";
        if (isOnline()) {
            webview = (WebView) findViewById(R.id.wv_login);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(url);
            webview.setWebViewClient(new WebViewClient() {

                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    pb.setVisibility(View.VISIBLE);
                    String accessTokenFragment = "access_token=";
                    String accessCodeFragment = "code=";

                    if (url.contains(accessCodeFragment) && url.startsWith("http://localhost:4567/callback")) {
                        // the GET request contains an authorization code
                        int codeStart = url.indexOf(accessCodeFragment) + accessCodeFragment.length();
                        int codeEnd = url.indexOf('&', codeStart) == -1 ? url.length() : url.indexOf('&', codeStart);
                        String accessCode = url.substring(codeStart, codeEnd);

//                  URL for access token
                        String accessURL = TO_URL + accessCode;

                        RestAdapter restAdapter = new RestAdapter.Builder()
                                .setEndpoint(TOKEN_URL2)
                                .build();

                        AccessAPI api = restAdapter.create(AccessAPI.class);
                        api.GetAccess(CLIENT_ID, CLIENT_SECRET, accessCode,
                                new Callback<Access>() {
                                    @Override
                                    public void success(Access accesses, Response response) {

//                                        Toast.makeText(getBaseContext(), "access token is: " + accesses.access_token, Toast.LENGTH_LONG).show();

                                        sPref = getApplicationContext().getSharedPreferences("My_PREFERENCE",
                                                MODE_PRIVATE);
                                        SharedPreferences.Editor ed = sPref.edit();
                                        ed.putString(ACCESS_TOKEN, String.valueOf(accesses.access_token));
                                        ed.apply();
                                        openMain();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Log.d("retrofit", error.toString());
                                    }
                                });


                    } else if (url.contains(accessTokenFragment) && url.contains(accessCodeFragment)) {
                        // the GET request contains directly the token
                        String accessToken = url.substring(url.indexOf(accessTokenFragment));
                        sPref = getApplicationContext().getSharedPreferences("My_PREFERENCE",
                                MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(accessToken, url.substring(url.indexOf(accessTokenFragment)));
                        ed.commit();
                    }

                }


            });

        } else {
            Toast.makeText(this, "Network isn`t available", Toast.LENGTH_LONG).show();
        }

    }

    private void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        pb.setVisibility(View.INVISIBLE);

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

}




