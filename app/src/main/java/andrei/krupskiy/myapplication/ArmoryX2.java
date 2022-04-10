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

public class ArmoryX2 extends AppCompatActivity {

    private long backpressedTime;

    ProgressBar progressBar;
    EditText inputUrl;
    WebView webView;
    ImageButton sendButton, forwardButton, backButton, refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.armor);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        inputUrl = (EditText) findViewById(R.id.autoCompleteTextViewX41);
        webView = (WebView) findViewById(R.id.webView1);
        sendButton = (ImageButton) findViewById(R.id.sendButton1);
        forwardButton = (ImageButton) findViewById(R.id.forwardButton1);
        backButton = (ImageButton) findViewById(R.id.backButton1);
        refreshButton = (ImageButton) findViewById(R.id.refreshButton1);

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

                if (!url.startsWith("https://sirus.su/base/character/9/")) {
                    url = "https://sirus.su/base/character/9/" + url;
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

        if (backpressedTime + 2000 > System.currentTimeMillis()){ //если текущее время + 2с болье текущего время, то...
            Intent intent = new Intent(ArmoryX2.this, MainActivity.class);
            // есть намеринеие перейти из GameLevels в MainActivity
            startActivity(intent);
            finish();
        }else{

                if (webView.canGoBack())
                    webView.goBack();

            Toast.makeText(getBaseContext(), "Нажмите ещё раз, что бы перейти в Главное меню",Toast.LENGTH_SHORT).show(); //выдает текст

        }

        backpressedTime = System.currentTimeMillis(); //засекли время нажатия на кнопку
    }
}


