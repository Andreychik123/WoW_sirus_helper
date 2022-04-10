package andrei.krupskiy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Vote extends AppCompatActivity {

    private long backpressedTime;

    ProgressBar progressBar1;
    EditText inputUrl1;
    WebView webView1;
    ImageButton sendButton1, forwardButton1, backButton1, refreshButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.web);



        progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        inputUrl1 = (EditText) findViewById(R.id.autoCompleteTextViewX4);
        webView1 = (WebView) findViewById(R.id.webView);
        sendButton1 = (ImageButton) findViewById(R.id.sendButton);
        forwardButton1 = (ImageButton) findViewById(R.id.forwardButton);
        backButton1 = (ImageButton) findViewById(R.id.backButton);
        refreshButton1 = (ImageButton) findViewById(R.id.refreshButton);

        webView1.setWebViewClient(new myWebClient());

        webView1.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar1.setProgress(newProgress);
                if(newProgress==100)
                    progressBar1.setVisibility(View.GONE);
                else
                    progressBar1.setVisibility(View.VISIBLE);
            }
        });

        WebSettings webset = webView1.getSettings();
        webset.setJavaScriptEnabled(true);

        webView1.loadUrl("https://wow.mmotop.ru/servers/5130/votes/new");

        sendButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = inputUrl1.getText().toString();

                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }
                webView1.loadUrl(url);

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(webView1.getWindowToken(), 0);
            }
        });

        forwardButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView1.canGoForward())
                    webView1.goForward();
            }
        });



        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView1.canGoBack())
                    webView1.goBack();
            }
        });

        refreshButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView1.reload();
            }
        });



    }





    @Override
    public void onBackPressed() {

        if (backpressedTime + 2000 > System.currentTimeMillis()){ //если текущее время + 2с болье текущего время, то...
            Intent intent = new Intent(Vote.this, MainActivity.class);
            // есть намеринеие перейти из GameLevels в MainActivity
            startActivity(intent);
            finish();
        }else{

            if (webView1.canGoBack())
                webView1.goBack();

            Toast.makeText(getBaseContext(), "Нажмите ещё раз, что бы перейти в Главное меню",Toast.LENGTH_SHORT).show(); //выдает текст

        }

        backpressedTime = System.currentTimeMillis(); //засекли время нажатия на кнопку
    }
}
