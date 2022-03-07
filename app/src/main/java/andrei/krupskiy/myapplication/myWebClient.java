
package andrei.krupskiy.myapplication;

import android.webkit.WebView;
import android.webkit.WebViewClient;


public class myWebClient extends WebViewClient {


    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}




