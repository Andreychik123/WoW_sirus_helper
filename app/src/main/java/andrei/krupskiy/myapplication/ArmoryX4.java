package andrei.krupskiy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ArmoryX4 extends AppCompatActivity {

    private long backpressedTime;

    ProgressBar progressBar;
    EditText inputUrl;
    WebView webView;
    ImageButton sendButton, forwardButton, backButton, refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armor);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        inputUrl = (EditText) findViewById(R.id.autoCompleteTextViewX4);
        webView = (WebView) findViewById(R.id.webView);
        sendButton = (ImageButton) findViewById(R.id.sendButton);
        forwardButton = (ImageButton) findViewById(R.id.forwardButton);
        backButton = (ImageButton) findViewById(R.id.backButton);
        refreshButton = (ImageButton) findViewById(R.id.refreshButton);

        webView.setWebViewClient(new myWebClient());

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress==100)
                    progressBar.setVisibility(View.GONE);
                else
                    progressBar.setVisibility(View.VISIBLE);
            }
        });

        WebSettings webset = webView.getSettings();
        webset.setJavaScriptEnabled(true);

        webView.loadUrl("");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = inputUrl.getText().toString();

                if (!url.startsWith("https://sirus.su/base/character/33/")) {
                    url = "https://sirus.su/base/character/33/" + url;
                }
                webView.loadUrl(url);

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(webView.getWindowToken(), 0);
            }
        });



        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });



    }






    @Override
    public void onBackPressed() {

        if (backpressedTime + 2000 > System.currentTimeMillis()){ //???????? ?????????????? ?????????? + 2?? ?????????? ???????????????? ??????????, ????...
            Intent intent = new Intent(ArmoryX4.this, MainActivity.class);
            // ???????? ???????????????????? ?????????????? ???? GameLevels ?? MainActivity
            startActivity(intent);
            finish();
        }else{

                if (webView.canGoBack())
                    webView.goBack();

            Toast.makeText(getBaseContext(), "?????????????? ?????? ??????, ?????? ???? ?????????????? ?? ?????????????? ????????",Toast.LENGTH_SHORT).show(); //???????????? ??????????

        }

        backpressedTime = System.currentTimeMillis(); //?????????????? ?????????? ?????????????? ???? ????????????
    }
}


