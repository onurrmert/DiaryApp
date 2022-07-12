package com.onurmert.notdefteri.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.onurmert.notdefteri.Models.DiaryDbModel;
import com.onurmert.notdefteri.R;
import com.onurmert.notdefteri.ToastMessage;
import com.onurmert.notdefteri.ViewModel.CurrentViewModel;

import java.util.ArrayList;

public class UpdatePage extends AppCompatActivity {

    private EditText title_update_edit;
    private EditText note_update_edit;

    CurrentViewModel.UpdateViewModel updateViewModel;

    private final ArrayList<DiaryDbModel> db_list = new ArrayList<DiaryDbModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateViewModel = new ViewModelProvider(this).get(CurrentViewModel.UpdateViewModel.class);

        readNotes(getId1());
    }

    private void init(){
        title_update_edit = (EditText) findViewById(R.id.edit_title_update);
        note_update_edit = (EditText) findViewById(R.id.edit_note_update);
    }

    private int getId1(){
        Intent intent = getIntent();

        return intent.getIntExtra("id", 0);
    }

    public void buttonUpdate(View view){

        String title = title_update_edit.getText().toString().trim();

        String note = note_update_edit.getText().toString().trim();

        if (!note.equals("") && !title.equals("")){
            DiaryDbModel diaryDbModel =  new DiaryDbModel(getId1(), title, note);

            updateViewModel.update(this, diaryDbModel);

            ToastMessage.myToastMessage(this, "Güncellendi");

            onBackPressed();

        }else {
            ToastMessage.myToastMessage(this, "Boş yerleri doldurun");
        }
    }

    private void readNotes(int id){

        db_list.clear();

        updateViewModel
                .readOrUpdate(this, "SELECT * FROM " + "notess_save" + " WHERE id = " + id)
                .observe(this, it ->{
                    db_list.addAll(it);
                });

        title_update_edit.setText(db_list.get(0).get_titlee());

        note_update_edit.setText(db_list.get(0).get_notess());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =  new Intent(this, CurrentPage.class);
        startActivity(intent);
        UpdatePage.this.finish();
    }
}