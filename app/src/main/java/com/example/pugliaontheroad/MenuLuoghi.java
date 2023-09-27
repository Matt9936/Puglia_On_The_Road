package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuLuoghi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_luoghi);
    }
    public void goToNatura(View view){
        startActivity(new Intent (MenuLuoghi.this, Natura.class));
    }
    public void goToCultura(View view){
        startActivity(new Intent (MenuLuoghi.this, Cultura.class));
    }
    public void goToMare(View view){
        startActivity(new Intent (MenuLuoghi.this, Mare.class));
    }
    public void goToCibo(View view){
        startActivity(new Intent (MenuLuoghi.this, Cibo.class));
    }
    public void Back(View view){
        startActivity(new Intent(MenuLuoghi.this, MainActivity.class));
    }

}