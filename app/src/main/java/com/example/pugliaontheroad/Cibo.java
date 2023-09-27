package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import android.os.Bundle;

public class Cibo extends AppCompatActivity {

    ListView listViewCibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cibo);
        listViewCibo = findViewById(R.id.lv_placeList4);

        DatabaseH databaseH = new DatabaseH(Cibo.this);
        //PlacesModel placesModel = new PlacesModel(-1, "Ristorante Vecchie Storie", "Via Giuseppe Pisanelli, 15/17, 74016 Massafra TA", "Cucina di pesce tradizionale");
        //databaseH.addOneCibo(placesModel);
        List<String> everyone = databaseH.getEveryoneCibo();
        ArrayAdapter placeArrayAdapter = new ArrayAdapter<String>(Cibo.this, android.R.layout.simple_list_item_1, everyone);
        listViewCibo.setAdapter(placeArrayAdapter);
    }
    public void Back(View view){
        startActivity(new Intent(Cibo.this, MenuLuoghi.class));
    }
}