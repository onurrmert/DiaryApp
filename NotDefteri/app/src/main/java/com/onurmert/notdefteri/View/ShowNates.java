package com.onurmert.notdefteri.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.onurmert.notdefteri.Models.DiaryDbModel;
import com.onurmert.notdefteri.R;
import com.onurmert.notdefteri.ViewModel.NoteViewModel;

import java.util.ArrayList;

public class ShowNates extends AppCompatActivity {

    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        writeNote();
    }

    private int getId1(){
        final Intent intent = getIntent();

        return intent.getIntExtra("id",0);
    }

    private void writeNote(){

        ArrayList<DiaryDbModel> _liste = new ArrayList<DiaryDbModel>();

        _liste.clear();

        noteViewModel
                .readNot(this, "SELECT * FROM " + "notess_save" + " WHERE id = " + getId1())
                .observe(this, it ->{
                    _liste.addAll(it);
        });

        final TextView not_text = (TextView)findViewById(R.id.text_not);

        final TextView title_text = (TextView)findViewById(R.id.text_title_not);
        not_text.setText(_liste.get(0).get_notess());

        title_text.setText(_liste.get(0).get_titlee()+": ");
    }

    public void deleteButton(View view){

        final AlertDialog.Builder alertdialog =  new AlertDialog.Builder(this);

        alertdialog.setMessage("Silinsin mi?");

        alertdialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteDatabase();
            }
        });

        alertdialog.show();
    }

    private void deleteDatabase(){

        noteViewModel.deleteViewModel(this, getId1());
        ShowNates.this.finish();
    }

    @Override
    public void onBackPressed() {
        ShowNates.this.finish();
    }
}