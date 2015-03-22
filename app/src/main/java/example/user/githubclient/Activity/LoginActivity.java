package example.user.githubclient.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    private static final String REDIRECT_URI = "https://your.callback/uri";
    public static String SECRETID = "?client_id=b89e0192e6b3c9bbd9b8&client_secret=c3e6ed559361f528cb6c4e6e5040d31459236698&";

    public static String OAUTH_URL = "https://github.com/login/oauth/authorize";
    public static String TOKEN_URL = "https://github.com/login/oauth/access_token";
    public static String TOKEN_URL2 = "https://github.com/login/oauth";
    public static String OA_URL = OAUTH_URL + "client_id=" + CLIENT_ID + "&scope=repo,user,gist";
    public static String TO_URL = TOKEN_URL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&";

    SharedPreferences sPref;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String url = OAUTH_URL + "?client_id=" + CLIENT_ID + "&scope=repo,user,gist";

        webview = (WebView) findViewById(R.id.wv_login);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String accessTokenFragment = "access_token=";
                String accessCodeFragment = "code=";

                if (url.contains(accessCodeFragment) && url.startsWith("http://localhost:4567/callback")) {
                    // the GET request contains an authorization code
                    String accessCode = url.substring(url.indexOf(accessCodeFragment));
//                    TokenStorer.setAccessCode(accessCode);
                    sPref = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(accessCode, url.substring(url.indexOf(accessCodeFragment)));

//           URL for access token
//                    String accessURL = TO_URL + accessCode;

                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setEndpoint(TOKEN_URL2)
                            .build();

                    AccessAPI api = restAdapter.create(AccessAPI.class);
                    api.GetAccess(CLIENT_ID, CLIENT_SECRET, accessCode,
                            new Callback<Access>() {
                                @Override
                                public void success(Access accesses, Response response) {

                                    Toast.makeText(getBaseContext(), "access token geted " + accesses.access_token, Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.d("retrofit", error.toString());
                                }
                            });


                } else if (url.contains(accessTokenFragment) && url.contains(accessCodeFragment)) {
                    // the GET request contains directly the token
                    String accessToken = url.substring(url.indexOf(accessTokenFragment));
//                    TokenStorer.setAccessToken(accessToken);
                    sPref = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(accessToken, url.substring(url.indexOf(accessTokenFragment)));

//                    openMain();
                }

            }


        });
        webview.loadUrl(url);
    }

    private void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("value", String.valueOf(sPref));
        startActivity(intent);

    }

}


//        webview.clearCache(true);
//        webview.clearFormData();
//        webview.clearHistory();
//        webview.clearMatches();
//        webview.clearSslPreferences();
//
//        webview.getSettings().setUseWideViewPort(true);
//
//        Login();
//    }
//
//    private void Login() {
//        webview.loadUrl(OA_URL);
//    }
//
//
//    private class MyWebViewClient extends WebViewClient {
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            String accessTokenFragment = "access_token=";
//            String accessCodeFragment = "code=";
//            if (url != null) {
//                if (url.contains(accessCodeFragment)) {
//                    int codeStart = url.indexOf("code=") + "code=".length();
//                    int codeEnd = url.indexOf('&', codeStart) == -1 ? url.length() : url.indexOf('&', codeStart);
//                    Uri uri = Uri.parse(url.substring(codeStart, codeEnd));
//                    webview.loadUrl(TO_URL + uri);
//
//                }
//            }
//
//        }
//    }
//}




