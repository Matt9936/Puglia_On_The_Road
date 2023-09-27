package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import android.os.Bundle;

public class Cultura extends AppCompatActivity {

    ListView listViewCultura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultura);
        listViewCultura = findViewById(R.id.lv_placelist3);

        DatabaseH databaseH = new DatabaseH(Cultura.this);
        //PlacesModel placesModel = new PlacesModel(-1, "Museo Ipogeo Spartano di Taranto", "Corso Vittorio Emanuele II, 39, 74123 Taranto TA", "Storico museo ipogeo sul mare con visite guidate e informazioni storiche.");
        //databaseH.addOneCultura(placesModel);
        List<String> everyone = databaseH.getEveryoneCultura();
        ArrayAdapter placeArrayAdapter = new ArrayAdapter<String>(Cultura.this, android.R.layout.simple_list_item_1, everyone);
        listViewCultura.setAdapter(placeArrayAdapter);
    }
    public void Back(View view){
        startActivity(new Intent(Cultura.this, MenuLuoghi.class));
    }
}