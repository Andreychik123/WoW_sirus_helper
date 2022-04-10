package andrei.krupskiy.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

import android.text.format.DateFormat;
import java.util.List;

public class Chat extends AppCompatActivity {

    private static int SIGN_IN_CODE = 1;
    private RelativeLayout chat;
    private FirebaseListAdapter<Massage> adapter;
    private ImageView sendBtn;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_CODE) {
            if(resultCode == RESULT_OK) {
                Snackbar.make(chat,"вы авторизованны", Snackbar.LENGTH_LONG).show();
//                displayAllMassager();

            }else {
                Snackbar.make(chat,"вы не авторизованны", Snackbar.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        chat = findViewById(R.id.chat11);
        sendBtn = findViewById(R.id.imageViewSendMass);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textField = findViewById(R.id.textSms);
                if(textField.getText().toString() == "")
                    return;
                FirebaseDatabase.getInstance().getReference().push().setValue(
                        new Massage(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                textField.getText().toString()
                        )
                );
                textField.setText("");
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE);
        else {
            Snackbar.make(chat, "вы авторизованны", Snackbar.LENGTH_LONG).show();
//            displayAllMassager();
        }
    }





//    private void displayAllMassager() {
//        ListView listOFMessages = findViewById(R.id.list_massages);
//        adapter = new FirebaseListAdapter<Massage>(Chat.this, Massage.class,
//                R.layout.list_itam_chat, FirebaseDatabase.getInstance().getReference()) {
//            @Override
//            protected void populateView(View v, Massage model, int position) {
//                TextView mess_user, mess_time, mess_text;
//                mess_user = v.findViewById(R.id.itemChatName);
//                mess_time = v.findViewById(R.id.itemTime);
//                mess_text = v.findViewById(R.id.itemChat);
//
//                mess_user.setText(model.getUserName());
//                mess_text.setText(model.getTextMassage());
//                mess_time.setText(DateFormat.format("dd-mm-yyyy HH:mm:ss", model.getMassageTime()));
//
//            }
//        };
//        listOFMessages.setAdapter(adapter);
//    }


    @Override
    public void onBackPressed() {

        long backpressedTime = System.currentTimeMillis();

        if (backpressedTime + 2000 > System.currentTimeMillis()){ //если текущее время + 2с болье текущего время, то...
            Intent intent = new Intent(Chat.this, MainActivity.class);
            // есть намеринеие перейти из GameLevels в MainActivity
            startActivity(intent);
            finish();
        }else{

            Toast.makeText(getBaseContext(), "Нажмите ещё раз, что бы перейти в Главное меню",Toast.LENGTH_SHORT).show(); //выдает текст

        }

        backpressedTime = System.currentTimeMillis(); //засекли время нажатия на кнопку
    }

}

