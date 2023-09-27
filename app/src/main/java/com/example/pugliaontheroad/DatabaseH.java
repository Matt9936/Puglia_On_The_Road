package com.example.pugliaontheroad;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseH extends SQLiteOpenHelper {

    public static final String MARE_TABLE = "MARE_TABLE";
    public static final String COLUMN_PLACE_LOCALITA = "PLACE_LOCALITA";
    public static final String COLUMN_PLACE_INDIRIZZO = "PLACE_INDIRIZZO";
    public static final String COLUMN_PLACE_INFO = "PLACE_INFO";
    public static final String COLUMN_ID = "ID";
    public static final String NATURA_TABLE = "NATURA_TABLE";
    public static final String COLUMN_NATURA_ID = "NATURA_ID";
    public static final String COLUMN_NATURA_LOCALITA = "NATURA_LOCALITA";
    public static final String COLUMN_NATURA_INDIRIZZO = "NATURA_INDIRIZZO";
    public static final String COLUMN_NATURA_INFO = "NATURA_INFO";
    public static final String CULTURA_TABLE = "CULTURA_TABLE";
    public static final String COLUMN_CULTURA_ID = "CULTURA_ID";
    public static final String COLUMN_CULTURA_LOCALITA = "CULTURA_LOCALITA";
    public static final String COLUMN_CULTURA_INDIRIZZO = "CULTURA_INDIRIZZO";
    public static final String COLUMN_CULTURA_INFO = "CULTURA_INFO";
    public static final String CIBO_TABLE = "CIBO_TABLE";
    public static final String COLUMN_CIBO_ID = "CIBO_ID";
    public static final String COLUMN_CIBO_LOCALITA = "CIBO_LOCALITA";
    public static final String COLUMN_CIBO_INDIRIZZO = "CIBO_INDIRIZZO";
    public static final String COLUMN_CIBO_INFO = "CIBO_INFO";


    public DatabaseH(@Nullable Context context) {
        super(context, "places.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableMare = "CREATE TABLE " + MARE_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PLACE_LOCALITA + " TEXT, " + COLUMN_PLACE_INDIRIZZO + " TEXT, " + COLUMN_PLACE_INFO + " TEXT)";
        db.execSQL(createTableMare);
        String createTableNatura = "CREATE TABLE " + NATURA_TABLE + "(" + COLUMN_NATURA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NATURA_LOCALITA + " TEXT, " + COLUMN_NATURA_INDIRIZZO + " TEXT, " + COLUMN_NATURA_INFO + " TEXT)";
        db.execSQL(createTableNatura);
        String createTableCultura = "CREATE TABLE " + CULTURA_TABLE + "(" + COLUMN_CULTURA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CULTURA_LOCALITA + " TEXT, " + COLUMN_CULTURA_INDIRIZZO + " TEXT, " + COLUMN_CULTURA_INFO + " TEXT)";
        db.execSQL(createTableCultura);
        String createTableCibo = "CREATE TABLE " + CIBO_TABLE + "(" + COLUMN_CIBO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CIBO_LOCALITA + " TEXT, " + COLUMN_CIBO_INDIRIZZO + " TEXT, " + COLUMN_CIBO_INFO + " TEXT)";
        db.execSQL(createTableCibo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void addOneMare(PlacesModel placesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PLACE_LOCALITA, placesModel.getLocalità());
        cv.put(COLUMN_PLACE_INDIRIZZO, placesModel.getIndirizzo());
        cv.put(COLUMN_PLACE_INFO, placesModel.getInfo());

        db.insert(MARE_TABLE, null, cv);
    }
    public List<String> getInfoMare() {
        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + MARE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int placeID = cursor.getInt(0);
                String placeLocalita = cursor.getString(1);
                String placeIndirizzo = cursor.getString(2);
                String placeInfo = cursor.getString(3);
                PlacesModel newPlace = new PlacesModel(placeID, placeLocalita, placeIndirizzo, placeInfo);
                returnList.add(newPlace.getLocalità());
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }
    public void addOnNatura(PlacesModel placesModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NATURA_LOCALITA, placesModel.getLocalità());
        cv.put(COLUMN_NATURA_INDIRIZZO, placesModel.getIndirizzo());
        cv.put(COLUMN_NATURA_INFO, placesModel.getInfo());

        db.insert(NATURA_TABLE, null, cv);
    }
    public List<String> getEveryoneNatura(){
        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + NATURA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int placeID = cursor.getInt(0);
                String placeLocalita = cursor.getString(1);
                String placeIndirizzo = cursor.getString(2);
                String placeInfo = cursor.getString(3);
                PlacesModel newPlace = new PlacesModel(placeID, placeLocalita, placeIndirizzo, placeInfo);
                returnList.add(newPlace.getLocalità());
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }
    public void addOneCultura(PlacesModel placesModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CULTURA_LOCALITA, placesModel.getLocalità());
        cv.put(COLUMN_CULTURA_INDIRIZZO, placesModel.getIndirizzo());
        cv.put(COLUMN_CULTURA_INFO, placesModel.getInfo());

        db.insert(CULTURA_TABLE, null, cv);
    }
    public List<String> getEveryoneCultura(){
        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CULTURA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int placeID = cursor.getInt(0);
                String placeLocalita = cursor.getString(1);
                String placeIndirizzo = cursor.getString(2);
                String placeInfo = cursor.getString(3);
                PlacesModel newPlace = new PlacesModel(placeID, placeLocalita, placeIndirizzo, placeInfo);
                returnList.add(newPlace.getLocalità());
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }
    public void addOneCibo (PlacesModel placesModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CIBO_LOCALITA, placesModel.getLocalità());
        cv.put(COLUMN_CIBO_INDIRIZZO, placesModel.getIndirizzo());
        cv.put(COLUMN_CIBO_INFO, placesModel.getInfo());

        db.insert(CIBO_TABLE, null, cv);
    }
    public List<String> getEveryoneCibo(){
        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CIBO_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int placeID = cursor.getInt(0);
                String placeLocalita = cursor.getString(1);
                String placeIndirizzo = cursor.getString(2);
                String placeInfo = cursor.getString(3);
                PlacesModel newPlace = new PlacesModel(placeID, placeLocalita, placeIndirizzo, placeInfo);
                returnList.add(newPlace.getLocalità());
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
    public void deleteMare(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MARE_TABLE, null, null);
    }
    public void deleteNatura(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NATURA_TABLE, null,null);
    }


}
