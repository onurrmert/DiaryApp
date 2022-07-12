package com.onurmert.notdefteri.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.onurmert.notdefteri.R;
import com.onurmert.notdefteri.ToastMessage;

public class OpenPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

    }

    public void buttonGirisYap(View view){

        final EditText user_name_edit = (EditText) findViewById(R.id.edit_user_name);

        final EditText password_edit = (EditText) findViewById(R.id.edit_password);

        final String password = password_edit.getText().toString().trim();

        final String user_name = user_name_edit.getText().toString().trim().toLowerCase();

        if (!password.equals("") && !user_name.equals("")){

            if (password.equals("1234") && user_name.equals("onurmert") ){

                final Intent intent =  new Intent(this, CurrentPage.class);

                startActivity(intent);

                ToastMessage.myToastMessage(this, "Hoşgeldin onurmert");

                OpenPage.this.finish();
            }else{
                ToastMessage.myToastMessage(this,"Kullanıcı adı veya şifre yanlış");
            }
        }else{
            ToastMessage.myToastMessage(this, "Boş yerleri doldurun");
        }
    }
}