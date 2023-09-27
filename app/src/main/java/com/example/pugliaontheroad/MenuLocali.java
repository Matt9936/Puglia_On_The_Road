package com.example.pugliaontheroad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuLocali extends AppCompatActivity {

    ListView listView;
    DatabaseReference mRef;
    List<Locali> locali;
    Button delete;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_locali);
        mRef = FirebaseDatabase.getInstance().getReference();
        delete.findViewById(R.id.vediLuoghi);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.removeValue();


            }
        });






    listView = findViewById(R.id.listaLocali);
        aggiornaLista();
    }


    public void aggiornaLista() {
        ArrayAdapter localiAA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1); //db.getInfoLocali());
        listView.setAdapter(localiAA);

        mRef= FirebaseDatabase.getInstance().getReference();
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Locali value = snapshot.getValue(Locali.class);
                localiAA.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                localiAA.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }










    public void goToEditLocali(View view) {
        startActivity(new Intent(this,EditLocali.class));
    }

    public void goToHome(View view) {
        startActivity(new Intent(MenuLocali.this, MainActivity.class));
    }

}//classe