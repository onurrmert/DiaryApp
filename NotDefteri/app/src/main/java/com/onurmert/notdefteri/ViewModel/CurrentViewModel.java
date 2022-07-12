package com.onurmert.notdefteri.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.onurmert.notdefteri.Database.DiaryDatabaseHelper;
import com.onurmert.notdefteri.Database.IMyDatabase;
import com.onurmert.notdefteri.Models.DiaryDbModel;

import java.util.ArrayList;

public class CurrentViewModel extends ViewModel {

    //gözlenecek olan veriyi hazırladık
    //sakın = new MutableLiveData demeyi unutma
    private MutableLiveData<ArrayList<DiaryDbModel>> _liste = new MutableLiveData<>();

    //veritabanı sınıfı için değişken oluşturduk
    //sonradan değiştirmek kolay olacak
    private IMyDatabase iMyDatabase;

    //burada db'den veri çekip UI kısmının gözlemlemesini sağlıyoruz
    public LiveData<ArrayList<DiaryDbModel>> read(Context context, String read_sql_query){

        iMyDatabase = new DiaryDatabaseHelper(context);

        _liste.setValue(iMyDatabase.dbRead(read_sql_query));

        return _liste;
    }

    public static class UpdateViewModel extends ViewModel {

        private final MutableLiveData<ArrayList<DiaryDbModel>> _liste =  new MutableLiveData<>();

        private IMyDatabase iMyDatabase;

        public LiveData<ArrayList<DiaryDbModel>> readOrUpdate(Context context, String sql_read_query){

            iMyDatabase = new DiaryDatabaseHelper(context);

            _liste.setValue(iMyDatabase.dbRead(sql_read_query));

            return _liste;
        }

        public void update(Context context, DiaryDbModel notDefteriDbModel){

            iMyDatabase =  new DiaryDatabaseHelper(context);

            iMyDatabase.dbUpdate(notDefteriDbModel);
        }

    }
}
