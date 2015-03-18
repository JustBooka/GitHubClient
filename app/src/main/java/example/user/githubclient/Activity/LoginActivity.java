package example.user.githubclient.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import example.user.githubclient.R;

/**
 * Created by User on 18.03.2015.
 */
public class LoginActivity extends Activity {

    private static final List<String> SCOPES = Arrays.asList("repo", "user", "gist");
    private static final String CLIENT_ID = "b89e0192e6b3c9bbd9b8";
    private static final String CLIENT_SECRET = "c3e6ed559361f528cb6c4e6e5040d31459236698";
    private static final String REDIRECT_URI = "https://your.callback/uri";

    public static String OAUTH_URL = "https://github.com/login/oauth/authorize";
    public static String TOKEN_URL = "https://github.com/login/oauth/access_token";
    public static String OA_URL =OAUTH_URL + "client_id=" + CLIENT_ID + "&scope=repo,user,gist";
    public static String TO_URL = TOKEN_URL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=";

    SharedPreferences sPref;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String url = OAUTH_URL + "?client_id=" + CLIENT_ID + "&scope=repo,user,gist";

        webview = (WebView) findViewById(R.id.wv_login);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String accessTokenFragment = "access_token=";
                String accessCodeFragment = "code=";

                if (url.contains(accessTokenFragment)) {
                    // the GET request contains directly the token
                    String accessToken = url.substring(url.indexOf(accessTokenFragment));
//                    TokenStorer.setAccessToken(accessToken);
                    sPref = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor ed= sPref.edit();
                    ed.putString(accessToken, url.substring(url.indexOf(accessTokenFragment)) );

                } else if(url.contains(accessCodeFragment)) {
                    // the GET request contains an authorization code
                    String accessCode = url.substring(url.indexOf(accessCodeFragment));
//                    TokenStorer.setAccessCode(accessCode);
                    sPref = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor ed= sPref.edit();
                    ed.putString(accessCode, url.substring(url.indexOf(accessCodeFragment)) );

                    String query = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + accessCode;
                    view.postUrl(TOKEN_URL, query.getBytes());

                    openMain();
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




