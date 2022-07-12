package com.onurmert.notdefteri.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.onurmert.notdefteri.Models.DiaryDbModel;
import com.onurmert.notdefteri.R;
import com.onurmert.notdefteri.ViewModel.SaveViewModel;

public class SavePage extends AppCompatActivity {

    SaveViewModel saveViewModel;

    EditText title_edit;

    EditText note_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_page);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        saveViewModel =  new ViewModelProvider(this).get(SaveViewModel.class);
    }

    private void init(){
        title_edit = (EditText) findViewById(R.id.edit_title);
        note_edit = (EditText) findViewById(R.id.edit_note);
    }

    public void saveButton(View view){

        final String title = title_edit.getText().toString().trim();

        final String note = note_edit.getText().toString().trim();

        if (!title.equals("") && !note.equals("")){

            DiaryDbModel diaryDbModel =  new DiaryDbModel(0, title, note);

            saveViewModel.saveVM(this, diaryDbModel);

            Toast.makeText(this, "Kaydedildi",Toast.LENGTH_SHORT).show();

            SavePage.this.finish();

        }else {
            Toast.makeText(this,
                    "Boş yerleri doldurun",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onBackPressed() {
        SavePage.this.finish();
    }
}