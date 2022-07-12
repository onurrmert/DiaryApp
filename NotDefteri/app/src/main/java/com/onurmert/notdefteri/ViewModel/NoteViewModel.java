package com.onurmert.notdefteri.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.onurmert.notdefteri.Database.DiaryDatabaseHelper;
import com.onurmert.notdefteri.Database.IMyDatabase;
import com.onurmert.notdefteri.Models.DiaryDbModel;

import java.util.ArrayList;

public class NoteViewModel extends ViewModel {

    private MutableLiveData<ArrayList<DiaryDbModel>> _liste = new MutableLiveData<>();

    private IMyDatabase iMyDatabase;

    public LiveData<ArrayList<DiaryDbModel>> readNot(Context context, String sql_read_query){

        iMyDatabase =  new DiaryDatabaseHelper(context);

        _liste.setValue(iMyDatabase.dbRead(sql_read_query));

        return _liste;

    }

    public void deleteViewModel(Context context, int id){
        iMyDatabase =  new DiaryDatabaseHelper(context);
        iMyDatabase.dbDelete(id);
    }

}
