package andrei.krupskiy.myapplication;




import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import android.app.NotificationChannel;
import android.app.Notification;
import android.app.NotificationManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private long backpressedTime;
    public int visibility;
    public long when;


    //уведомления начало
    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    Button b1;
    Handler h;
    //уведомление конец

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





        //уведомления начало
        b1=findViewById(R.id.buttonprogolosovat);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {



                    Intent intent1 = new Intent(getApplicationContext(), Vote.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder notificationBuilder =
                            new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                    .setAutoCancel(false)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_foreground)) // большая картинка
                                    .setWhen(System.currentTimeMillis())
                                    .setContentIntent(pendingIntent)
                                    .setContentTitle("Ты можежешь проголосовать !")
                                    .setContentText("Чтобы проголосовать, нами на уведомление")
                                    .setDefaults(Notification.DEFAULT_SOUND)
                                    .setDefaults(Notification.DEFAULT_VIBRATE)
                                    .setPriority(PRIORITY_HIGH);

                        createChannelIfNeeded(notificationManager);
                        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());









                    try {
                        //переход на другую сцену
                        Intent intent = new Intent(MainActivity.this, Vote.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), "Уведомление не работает(",Toast.LENGTH_SHORT).show();
                    }






                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Уведомление не работает(",Toast.LENGTH_SHORT).show();
                }




            }


        });








        //уведомления конец

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



//        Button buttonprogolosovat = (Button)findViewById(R.id.buttonprogolosovat);  // код переброски на другую сцену
//        buttonprogolosovat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    Intent intent = new Intent(MainActivity.this, Vote.class);
//
//                    startActivity(intent);finish();
//
//                } catch (Exception e) {
//                }
//
//            }
//        });







        Button buttonBg = (Button)findViewById(R.id.buttonBg);  // код переброски на другую сцену
        buttonBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, Bg.class);

                    startActivity(intent);finish();

                } catch (Exception e) {
                }

            }
        });

        Button buttonArena = (Button)findViewById(R.id.buttonArena);  // код переброски на другую сцену
        buttonArena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, Arena.class);

                    startActivity(intent);finish();

                } catch (Exception e) {
                }

            }
        });

        Button buttonX4 = (Button)findViewById(R.id.buttonX4);  // код переброски на другую сцену
        buttonX4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, ArmoryX4.class);

                    startActivity(intent);finish();

                } catch (Exception e) {
                }

            }
        });

        Button buttonX2 = (Button)findViewById(R.id.buttonX2);  // код переброски на другую сцену
        buttonX2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, ArmoryX2.class);

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


    //уведомления начало
    public static void createChannelIfNeeded(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    //уведомление конец




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