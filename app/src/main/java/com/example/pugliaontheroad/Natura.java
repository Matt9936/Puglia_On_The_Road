package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import android.os.Bundle;

public class Natura extends AppCompatActivity {

    ListView lv_natura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natura);
        lv_natura = findViewById(R.id.lv_placeList2);

        DatabaseH databaseH = new DatabaseH(Natura.this);
        // PlacesModel placesModel1 = new PlacesModel(-1,"Bosco delle Pianelle", "Martina Franca", "Molto bello");
        //databaseH.addOnNatura(placesModel1);
        //databaseH.deleteNatura();
        List<String> everyone = databaseH.getEveryoneNatura();
        ArrayAdapter placeArrayAdapter = new ArrayAdapter<String>(Natura.this, android.R.layout.simple_list_item_1, everyone);
        lv_natura.setAdapter(placeArrayAdapter);

    }
    public void Back(View view){
        startActivity(new Intent(Natura.this, MenuLuoghi.class));
    }
}