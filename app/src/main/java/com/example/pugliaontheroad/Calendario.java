package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Calendario extends AppCompatActivity {

    Button home; //crea il tasto
    TextView userLoggedTV;

    public static Profilo userLogged; //L'utente loggato
    public final static String fileUserLogged = "userLogged.txt"; //file in cui è salvato l'utente loggato

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario); //associa la classe alla schermata

        userLoggedTV = findViewById(R.id.userText);
        home = (Button) findViewById(R.id.homeCal); //associa il tasto Java al tasto XML
        home.setOnClickListener(new View.OnClickListener() { //per eseguire il click
            @Override
            public void onClick(View v) {
                //per passare all'altra schermata
                startActivity(new Intent(Calendario.this, MainActivity.class));
            } //onClick
        }); //setOnClickListener


        userLogged = this.createUserLogged(this.readUserLogged("; "));

        if (userLogged!= null) {
            userLoggedTV.setText(userLogged.username);
        } else {
            userLoggedTV.setText("Nessuno");
        }


    } //onCreate

    public void goToVisualizzaAttivita(View goToVisualizzaAttività) {
        startActivity(new Intent(Calendario.this, visualizzaAttivita.class));
    }

    public void goToInserisciAttivita(View goToInserisciAttivita) {
        Intent intent = new Intent(Calendario.this, inserisciAttivita.class);
        startActivity(intent);
    }

    public void goToLogIn (View goToLogIn) {
        //andrebbe fatta una if che controlla se l'utente è loggato, ma per ora fingiamo che non lo sia

        startActivity(new Intent(Calendario.this, LogIn.class));
    }//goToLogIn

    public String readUserLogged(String separatore) {
        FileInputStream fis = null;
        String output="";
        try {
            fis = openFileInput(fileUserLogged);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine())!=null) {
                sb.append(text).append(separatore);
            }//while
            output = sb.toString();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "IOException 1", Toast.LENGTH_LONG).show();
        } finally {
            if (fis!= null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    Toast.makeText(this, "IOException 2", Toast.LENGTH_LONG).show();
                }//try catch
            }//if
        } //finally
        return output;
    } //carica userLogged

    public Profilo createUserLogged(String lettura ) { //dai dati estrapolati dal file (una String) restituisce l'utente Profilo
        Profilo user;
        if (lettura.equals("") | lettura==null) {
            //Toast.makeText(this, "Nessun utente loggato", Toast.LENGTH_SHORT).show();
            user = null;
        } else {
            Scanner scan = new Scanner(lettura);
            scan.useDelimiter("; ");
            int id = scan.nextInt();
            String email = scan.next();
            String username = scan.next();
            String password = scan.next();
            boolean tourist;
            if(scan.next().equals("turista")) {
                tourist = true;
            } else {
                tourist = false;
            } //if
            scan.close();
            user = new Profilo(id, email, username, password, tourist);
        } //if else

        return user;
    } //create user

} //classe