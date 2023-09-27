package com.example.pugliaontheroad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProfiloDBH extends SQLiteOpenHelper {
    public final static String TABELLA = "PROFILI";
    public final static String ID = "ID";
    public final static String EMAIL = "EMAIL";
    public final static String USERNAME = "USERNAME";
    public final static String PASSWORD = "PASSWORD";
    public final static String TIPO = "TIPO";

    public ProfiloDBH (@Nullable Context context) {
        super(context, "Profili.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE " + TABELLA + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMAIL + " TEXT, " +
                USERNAME + " TEXT, " +
                PASSWORD + " TEXT, " +
                TIPO + " BOOL)";

        db.execSQL(createTableSQL);
            }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(Profilo user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EMAIL, user.getEmail());
        cv.put(USERNAME, user.getUsername());
        cv.put(PASSWORD, user.getPassword());

        if(user.getGenere().equals(Profilo.Tipo.turista)) {
            cv.put(TIPO, 1); //1 se è un turista
        } else {
            cv.put(TIPO, 0); //0 se è un gestore.
        }//if

        long success = db.insert(TABELLA, null, cv);

        if(success== -1) {
            return false;
        } else {
            return true;
        } //if

    } //addUser

    public List<String> elenca() { //estrapola la lista di info degli utenti (formato String)
        List<String> listaOut = new ArrayList<>();

        String querySQL = "SELECT * FROM " + TABELLA;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querySQL, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String email, username, password;
                boolean tipoBool;
                email = cursor.getString(1);
                username = cursor.getString(2);
                password = cursor.getString(3);

                int tipoDB = cursor.getInt(4);
                if (tipoDB == 1) {
                    tipoBool = true; //turista
                } else { // (tipoDB == 0)
                    tipoBool = false; //gestore
                }

                Profilo newUser = new Profilo(id, email, username, password, tipoBool);
                listaOut.add(newUser.getInfoSint());

            } while (cursor.moveToNext());
        }//if

        cursor.close();
        db.close();
        return listaOut;

    }//elenca (String)

    public ArrayList<Profilo> elencaUtenti() { //estrapola una lista di profili dal database
        ArrayList<Profilo> output = new ArrayList<>();

        String querySQL = "SELECT * FROM " + TABELLA;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querySQL, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String email, username, password;
                boolean tipoBool;
                email = cursor.getString(1);
                username = cursor.getString(2);
                password = cursor.getString(3);
                int tipoDB = cursor.getInt(4);
                if (tipoDB == 1) {
                    tipoBool = true; //turista
                } else { // (tipoDB == 0)
                    tipoBool = false; //gestore
                }

                output.add(new Profilo(id, email, username, password, tipoBool));

            } while (cursor.moveToNext());
        }//if

        cursor.close();
        db.close();
        return output;
    }//elencaUtenti (Profilo)

}//classe
