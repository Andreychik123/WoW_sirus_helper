package andrei.krupskiy.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private long backpressedTime;

    // бадяга с партингом
    private Elements content;
    public ArrayList<String> titleList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView lv;
    //бадяга с партингок конец

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // бадяга с партингом
        lv = (ListView) findViewById(R.id.onlaineText);
        new NewThread().execute();
        adapter = new ArrayAdapter<String>(this, R.layout.list_itam, R.id.pro_item, titleList);
        //бадяга с партингок конец


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ImageButton batton_logo_youtube = (ImageButton)findViewById(R.id.batton_logo_youtube);  // код переброски на другую сцену
        batton_logo_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/sirussutv"));
                    startActivity(browserIntent);

                } catch (Exception e) {
                }

            }
        });

        ImageButton batton_logo_dis = (ImageButton)findViewById(R.id.batton_logo_dis);  // код переброски на другую сцену
        batton_logo_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.com/invite/sirus"));
                    startActivity(browserIntent);

                } catch (Exception e) {
                }

            }
        });

        ImageButton batton_logo_vk = (ImageButton)findViewById(R.id.batton_logo_vk);  // код переброски на другую сцену
        batton_logo_vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/sirussu"));
                    startActivity(browserIntent);

                } catch (Exception e) {
                }

            }
        });



        Button buttonprogolosovat = (Button)findViewById(R.id.buttonprogolosovat);  // код переброски на другую сцену
        buttonprogolosovat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, Vote.class);

                    startActivity(intent);finish();

                } catch (Exception e) {
                }

            }
        });

        Button buttonprogoforum = (Button)findViewById(R.id.buttonprogoforum);  // код переброски на другую сцену
        buttonprogoforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, Forum.class);

                    startActivity(intent);finish();

                } catch (Exception e) {
                }

            }
        });







    }

    //бадяга с партингом
    public class NewThread extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            try {
                doc = Jsoup.connect("https://sirus.su/#/").get();
                content = doc.select(".players-online ");
                titleList.clear();
                for (Element contents: content) {
                    titleList.add(contents.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            lv.setAdapter(adapter);
        }


    }
    //бадяга с партингок конец


    //системная кнопка назад, закрыть приложение
    @Override
    public void onBackPressed() {

        if (backpressedTime + 2000 > System.currentTimeMillis()){ //если текущее время + 2с болье текущего время, то...
            super.onBackPressed(); //команда закрывает игру
            return;
        }else{
            Toast.makeText(getBaseContext(), "Нажмите ещё раз, что бы выйти",Toast.LENGTH_SHORT).show(); //выдает текст

        }

        backpressedTime = System.currentTimeMillis(); //засекли время нажатия на кнопку
    }
}