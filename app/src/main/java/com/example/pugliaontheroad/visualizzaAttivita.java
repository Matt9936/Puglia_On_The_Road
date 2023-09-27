package com.example.pugliaontheroad;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class visualizzaAttivita extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    List<Attivita> attivitaList;
    ListView listViewAttivita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_attivita);

        ImageButton btnBack=(ImageButton)findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // definisco l'intenzione
                Intent openItin = new Intent(visualizzaAttivita.this, inserisciAttivita.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openItin);
            }
        });
        mDatabase = openOrCreateDatabase(inserisciAttivita.DATABASE_NAME, MODE_PRIVATE, null);
        attivitaList = new ArrayList<>();
        listViewAttivita = (ListView) findViewById(R.id.listViewAttivita);

        loadAttivitaFromDatabase();
    }

    private void loadAttivitaFromDatabase(){
        String sql ="SELECT * FROM attivita";
        Cursor cursor = mDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do {
                attivitaList.add(new Attivita(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
        }

        AttivitaAdapter adapter = new AttivitaAdapter(this, R.layout.list_layout_attivita, attivitaList, mDatabase);
        listViewAttivita.setAdapter(adapter);

    }//loadAttività

    public void goToCalendario(View goToCalendario) {
        startActivity(new Intent (visualizzaAttivita.this, Calendario.class));
    }

} //classe