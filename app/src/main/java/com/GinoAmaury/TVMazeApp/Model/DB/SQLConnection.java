package com.GinoAmaury.TVMazeApp.Model.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.GinoAmaury.TVMazeApp.Model.Object.Image;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Util.Utility;

import java.util.ArrayList;
import java.util.Arrays;

import static com.GinoAmaury.TVMazeApp.Util.Utility.ATRIBUTE_SHOW_GENRE;
import static com.GinoAmaury.TVMazeApp.Util.Utility.ATRIBUTE_SHOW_ID;
import static com.GinoAmaury.TVMazeApp.Util.Utility.ATRIBUTE_SHOW_IMAGE;
import static com.GinoAmaury.TVMazeApp.Util.Utility.ATRIBUTE_SHOW_NAME;
import static com.GinoAmaury.TVMazeApp.Util.Utility.ATRIBUTE_SHOW_SUMMARY;
import static com.GinoAmaury.TVMazeApp.Util.Utility.CREATE_TABLE_FAV;
import static com.GinoAmaury.TVMazeApp.Util.Utility.TABLE_FAVORITE;


public class SQLConnection  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favDatabase";
    private static final int DATABASE_VERSION = 4;


    private static SQLConnection sInstance;

    public static synchronized SQLConnection getInstance(Context context){
        if (sInstance == null) {
            sInstance = new SQLConnection(context.getApplicationContext());
        }
        return sInstance;
    }

    private SQLConnection (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS favorite");
            onCreate(db);
        }
    }

    public boolean addFav (Show show){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        String url = getUrlImage(show);
        try {
            ContentValues values = new ContentValues();
            values.put(ATRIBUTE_SHOW_ID, show.getId());
            values.put(ATRIBUTE_SHOW_NAME, show.getName());
            values.put(ATRIBUTE_SHOW_IMAGE,url);
            values.put(ATRIBUTE_SHOW_SUMMARY,show.getSummary());
            values.put(ATRIBUTE_SHOW_GENRE,show.getGenres().toString());
            db.insertOrThrow(TABLE_FAVORITE, ATRIBUTE_SHOW_ID, values);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
           return false;
        } finally {
            db.endTransaction();
        }
    }

    public String getUrlImage (Show show){
        String urlImage = "";
        if(show.getImage()!= null){
            if(show.getImage().getMedium()!=null && show.getImage().getOriginal()!=null ){
                urlImage = show.getImage().getMedium();
            }else if(show.getImage().getMedium()==null && show.getImage().getOriginal()!=null ){
                urlImage = show.getImage().getOriginal();
            }else if(show.getImage().getMedium()!=null && show.getImage().getOriginal()==null ){
                urlImage = show.getImage().getMedium();
            }
        }else{
           urlImage ="none";
        }
        return urlImage;
    }

    public boolean deleteFav (Show show){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try{
            String where = ATRIBUTE_SHOW_ID+"=?";
            String whereArgs [] ={String.valueOf(show.getId())};
            db.delete(TABLE_FAVORITE,where,whereArgs);
            db.setTransactionSuccessful();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            Log.d("ERROR", "DELETE FAV");
            return false;
        }finally {
            db.endTransaction();
        }
    }

    public boolean ifExist (Show show){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_FAVORITE + " WHERE "
                + ATRIBUTE_SHOW_ID + " = " + show.getId();

        Cursor c = db.rawQuery(selectQuery, null);

        try{
            if (c.moveToFirst()) {
                do {
                    Show s = new Show();
                    s.setId(Integer.parseInt(c.getString(c.getColumnIndex(ATRIBUTE_SHOW_ID)))) ;
                    if(s.getId() == show.getId()){
                        return true;
                    }else{
                        return false;
                    }
                } while(c.moveToNext());
            }else{
                return false;
            }

        }catch (Exception e){
                return false;
        }

    }

    public ArrayList<Show> getFavorites(){
        ArrayList<Show> response = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_FAVORITE;

        Cursor c = db.rawQuery(selectQuery, null);

        try{
            if (c.moveToFirst()) {
                do {
                    Show s = new Show();
                    s.setId(Integer.parseInt(c.getString(c.getColumnIndex(ATRIBUTE_SHOW_ID)))) ;
                    s.setName(c.getString(c.getColumnIndex(ATRIBUTE_SHOW_NAME)));
                    s.setSummary(c.getString(c.getColumnIndex(ATRIBUTE_SHOW_SUMMARY)));
                    String genres = c.getString(c.getColumnIndex(ATRIBUTE_SHOW_GENRE));
                    ArrayList<String> myList = new ArrayList<String>(Arrays.asList(genres.split(",")));
                    s.setGenres(myList);
                    Image image = new Image();
                    image.setMedium(c.getString(c.getColumnIndex(ATRIBUTE_SHOW_IMAGE)));
                    s.setImage(image);
                    response.add(s);
                } while(c.moveToNext());
                return response;
            }else{
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
                return null;
        }

    }

}
