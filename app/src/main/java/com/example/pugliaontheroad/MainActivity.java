package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnItin=(Button)findViewById(R.id.button4);
        btnItin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // definisco l'intenzione
                Intent openItin = new Intent(MainActivity.this, inserisciAttivita.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openItin);
            }
        });

    }

    public void goToCalendario (View goToCalendar) {
        startActivity(new Intent(MainActivity.this, Calendario.class));
    }

    public void goToLogIn(View goToLogIn) {
        startActivity(new Intent(MainActivity.this, LogIn.class));
    }

    public void goToLuoghi(View goToLuoghi) {
        startActivity(new Intent(MainActivity.this, MenuLuoghi.class));
    }

    public void goToMenuLocali(View goToMenuLocali) {
        startActivity(new Intent(MainActivity.this, MenuLocali.class));
    }




    /* private Button test0;
    private Button iscriviti; //per definire il button (potresti dover importare la classe)
    private Button logIn;
    private Button calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Per passare all'activity test
        test0 = (Button) findViewById(R.id.Test0);
        test0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test_0.class));
            } //onClick
        }); //setOnCL

        iscriviti = (Button) findViewById(R.id.tastoIscrizione); // per associare l'oggetto Java alla view XML
        iscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intentIscrizione = new Intent(MainActivity.this, Iscrizione.class); //chiama l'altra schermata
                startActivity(intentIscrizione);
            } //onClick
        });// metodo iscriviti.setOnClickListener

        logIn = (Button) findViewById(R.id.LogInMain);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogIn = new Intent(MainActivity.this, LogIn.class);
                startActivity(intentLogIn);
            } //onClick
        }); // setOnClickListener

        calendario = (Button) findViewById(R.id.CalendarMain);
        calendario.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                           startActivity(new Intent(MainActivity.this, Calendario.class));
                    } //onClick
                 }); //setOn

        //altri Tasti
    } //onCreate

    public void goToDB(View v) {
        startActivity(new Intent(MainActivity.this, DatabaseScreen.class));
    }


    public void goToTest1 (View Test1) {
        startActivity(new Intent(MainActivity.this, Test_1.class));
    } */



} //classe