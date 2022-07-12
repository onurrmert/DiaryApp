package com.onurmert.notdefteri.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.onurmert.notdefteri.Models.DiaryDbModel;

import java.util.ArrayList;

public class DiaryDatabaseHelper extends SQLiteOpenHelper implements IMyDatabase {

    public DiaryDatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    private static final String databaseName = "savee.db";

    private static final int databaseVersion = 1;

    private static final String tableName = "notess_save";

    private static final String id_key = "id";

    private static final String title_key = "title";

    private static final String note_key = "note1";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            String create_table = "CREATE TABLE IF NOT EXISTS " + tableName +" ( " +
                    id_key + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    title_key + " VARCHAR, " +
                    note_key +" VARCHAR" +
                    " )";

            sqLiteDatabase.execSQL(create_table);

        }catch (Exception error){
            System.out.println("tablo oluşurken hata oldu");
            System.out.println(error.getLocalizedMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }

    public void dbSave(DiaryDbModel diaryDbModel){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        try {

            final ContentValues contentValues = new ContentValues();

            contentValues.put(title_key, diaryDbModel.get_titlee());

            contentValues.put(note_key, diaryDbModel.get_notess());

            //time == 1 succesfull
            final long time = sqLiteDatabase.insert(tableName, null, contentValues);
            System.out.println("kaydedildi");
        }catch (Exception error) {
            System.out.println("Kaydedilirken hata oluştu");
            System.out.println(error.getLocalizedMessage());
        }finally {
            sqLiteDatabase.close();
        }
    }

    public void dbDelete(int id_delete){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try{

            sqLiteDatabase.delete(tableName, id_key + " =? ", new String[]{String.valueOf(id_delete)});
            System.out.println("silindi");
        } catch (Exception error) {
            System.out.println("Silinirken hata oluştu");
            System.out.println(error.getLocalizedMessage());
        }finally {
            sqLiteDatabase.close();
        }
    }

    public ArrayList<DiaryDbModel> dbRead(String select_query1){

        final ArrayList <DiaryDbModel> lists =  new ArrayList<DiaryDbModel>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try  {

            final Cursor cursor = sqLiteDatabase.rawQuery(select_query1, null);

            final int id_index = cursor.getColumnIndex(id_key);
            final int title_index = cursor.getColumnIndex(title_key);
            final int note_index = cursor.getColumnIndex(note_key);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    final int id = cursor.getInt(id_index);
                    final String title = cursor.getString(title_index);
                    final String note = cursor.getString(note_index);

                    final DiaryDbModel notDefteriDbModel = new DiaryDbModel(id, title, note);

                    lists.add(notDefteriDbModel);
                }
            } else {
                System.out.println("database veri yok");
            }
            cursor.close();
            sqLiteDatabase.close();
        } catch (Exception error) {
            System.out.println("okunurken hata oldu");
            System.out.println(error.getLocalizedMessage());
        }
        return lists;
    }

    public void dbUpdate(DiaryDbModel diaryDbModel){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(title_key, diaryDbModel.get_titlee());
            contentValues.put(note_key, diaryDbModel.get_notess());

            sqLiteDatabase.update(
                    tableName,
                    contentValues,
                    id_key + " = ? ",
                    new String[]{String.valueOf(diaryDbModel.get_id())}//id değerine güncelliyeceğiz
            );

            System.out.println("guncellendi");
        }catch (Exception exception){
            System.out.println("guncellenirken hata oldu");
            System.out.println(exception.getLocalizedMessage());
        }finally {
            sqLiteDatabase.close();
        }
    }
}
