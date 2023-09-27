//package com.example.pugliaontheroad;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*public class LocaliDB extends SQLiteOpenHelper {

    private static final int DATABSE_VERSION = 2;
    private static final String DATABSE_NAME= "localidb";
    private static final String DATABSE_TABLE = "localitable";

    private static final String KEY_ID="id";
    private static final String KEY_NOME="nome";
    private static final String KEY_INDIRIZZO="indirizzo";
    private static final String KEY_TELEFONO="telefono";
    private static final String KEY_EMAIL="email";
    private static final String KEY_ORARI="orari";
    private static final String KEY_SITO="sito";
    private static final String KEY_SERVIZI="servizi";

    LocaliDB(Context context){
        super(context,DATABSE_NAME,null,DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ DATABSE_TABLE +"("+KEY_ID+" INT PRIMARY KEY,"+
                KEY_NOME+" TEXT,"+
                KEY_INDIRIZZO+" TEXT,"+
                KEY_ORARI+" TEXT,"+
                KEY_SITO+" TEXT,"+
                KEY_EMAIL+" TEXT,"+
                KEY_TELEFONO+" TEXT,"+
                KEY_SERVIZI+" TEXT"+")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion>=newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS "+ DATABSE_TABLE);
        onCreate(db);

    }
    public long EditLocali(Locali locale){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_NOME,locale.getNome());
        c.put(KEY_INDIRIZZO,locale.getIndirizzo());
        c.put(KEY_EMAIL,locale.getEmail());
        c.put(KEY_ORARI,locale.getOrari());
        c.put(KEY_SERVIZI,locale.getServizi());
        c.put(KEY_SITO,locale.getSito());
        c.put(KEY_TELEFONO,locale.getTelefono());

        long ID = db.insert(DATABSE_TABLE,null,c);
        Log.d("Aggiunto","ID--> "+ID);
        return ID;
    }

    public Locali getLocale(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABSE_TABLE,new String[]{KEY_NOME,KEY_INDIRIZZO,KEY_ORARI,KEY_TELEFONO,KEY_EMAIL,KEY_SITO,KEY_ID,KEY_SERVIZI},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);
        if(cursor != null)
            cursor.moveToFirst();

        return new Locali(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
    }

    public List<Locali> getLocali(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Locali> tuttiLocali = new ArrayList<>();
        String query = "SELECT * FROM "+ DATABSE_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Locali locale = new Locali();
                locale.setID((cursor.getLong(0)));
                locale.setEmail(cursor.getString(1));
                locale.setIndirizzo(cursor.getString(2));
                locale.setNome(cursor.getString(3));
                locale.setOrari(cursor.getString(4));
                locale.setServizi(cursor.getString(5));
                locale.setSito(cursor.getString(6));
                locale.setTelefono(cursor.getString(7));

                tuttiLocali.add(locale);

            }while(cursor.moveToNext());
        }
        return tuttiLocali;

    }

    public List<String> getInfoLocali(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> tuttiLocali = new ArrayList<>();
        String query = "SELECT * FROM "+ DATABSE_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Locali locale = new Locali();
                locale.setID((cursor.getLong(0)));
                locale.setEmail(cursor.getString(1));
                locale.setIndirizzo(cursor.getString(2));
                locale.setNome(cursor.getString(3));
                locale.setOrari(cursor.getString(4));
                locale.setServizi(cursor.getString(5));
                locale.setSito(cursor.getString(6));
                locale.setTelefono(cursor.getString(7));

                tuttiLocali.add(locale.getInfo());

            }while(cursor.moveToNext());
        }
        return tuttiLocali;

    }
}*/
