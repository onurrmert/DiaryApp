package com.onurmert.notdefteri.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.onurmert.notdefteri.Database.DiaryDatabaseHelper;
import com.onurmert.notdefteri.Database.IMyDatabase;
import com.onurmert.notdefteri.Models.DiaryDbModel;

public class SaveViewModel extends ViewModel {

    private IMyDatabase iMyDatabase;

    public void saveVM(Context context, DiaryDbModel diaryDbModel){

        iMyDatabase = new DiaryDatabaseHelper(context);

        iMyDatabase.dbSave(diaryDbModel);
    }
}
