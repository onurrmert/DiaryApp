package com.onurmert.notdefteri.Database;

import com.onurmert.notdefteri.Models.DiaryDbModel;

import java.util.ArrayList;

public interface IMyDatabase {

    void dbSave(DiaryDbModel diaryDbModel);

    void dbDelete(int id_delete);

    ArrayList<DiaryDbModel> dbRead(String select_query1);

    void dbUpdate(DiaryDbModel diaryDbModel);
}