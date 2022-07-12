package com.onurmert.notdefteri.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.onurmert.notdefteri.Adapter.CurrentPageAdapter;
import com.onurmert.notdefteri.Models.DiaryDbModel;
import com.onurmert.notdefteri.R;
import com.onurmert.notdefteri.ViewModel.CurrentViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class CurrentPage extends AppCompatActivity {

    CurrentViewModel dbViewModel;

    private final String tableName = "notess_save";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_page);

    }

    @Override
    protected void onResume() {

        dbViewModel = new ViewModelProvider(this).get(CurrentViewModel.class);

        createRecycler();
        super.onResume();
    }

    private void createRecycler(){

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ArrayList<DiaryDbModel> liste = new ArrayList<DiaryDbModel>();

        liste = readList();

        final CurrentPageAdapter adapter =  new CurrentPageAdapter(this, liste);

        final LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private ArrayList<DiaryDbModel> readList(){

        ArrayList<DiaryDbModel> liste = new ArrayList<DiaryDbModel>();

        try{
            dbViewModel
                    .read(this,"SELECT * FROM " + tableName)
                    .observe(this, it ->{
                liste.addAll(it);
            });
        }catch (Exception error){
            System.out.println(error.getLocalizedMessage());
        }
        Collections.reverse(liste);

        return liste ;
    }

    public void goSavePage(View view){
        final Intent intent =  new Intent(this, SavePage.class);
        startActivity(intent);
    }
}