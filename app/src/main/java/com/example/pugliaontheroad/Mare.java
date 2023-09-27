package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Mare extends AppCompatActivity {

    ListView lv_placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mare);
        lv_placeList = findViewById(R.id.lv_placeList);

        DatabaseH databaseH = new DatabaseH(Mare.this);
        //PlacesModel placesModel = new PlacesModel(-1, "Spiaggia di Montedarena", "Litoranea Salentina, 74026 Marina di Pulsano, Pulsano Italia", "Mare cristallino");
        //databaseH.addOneMare(placesModel);
        //databaseH.deleteMare();
        List<String> everyone = databaseH.getInfoMare();
        ArrayAdapter placeArrayAdapter = new ArrayAdapter<String>(Mare.this, android.R.layout.simple_list_item_1, everyone);
        lv_placeList.setAdapter(placeArrayAdapter);

    }

    public void Back(View view){
        startActivity(new Intent(Mare.this, MenuLuoghi.class));
    }

}