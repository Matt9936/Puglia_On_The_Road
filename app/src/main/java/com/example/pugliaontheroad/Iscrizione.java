package com.example.pugliaontheroad;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



import java.util.HashMap;

public class Iscrizione extends AppCompatActivity {

    private Button annulla;
    protected static boolean userIsTour; //vera se è un turista, falsa se è gestore
    protected EditText emailET, nameET, pwET, cfPwET;
    protected TextView finishMex;
    //protected ProfiloDBH profilesDB;
    protected ProgressDialog loadingBar;
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iscrizione);

        emailET = (EditText) findViewById(R.id.email);
        nameET = (EditText) findViewById(R.id.username);
        pwET = (EditText) findViewById(R.id.writePassword);
        cfPwET = (EditText) findViewById(R.id.confirmPassword);
        finishMex = (TextView) findViewById(R.id.finishMex);
        //profilesDB = new ProfiloDBH(this);



        loadingBar = new ProgressDialog(this); //attendi caricamento del profilo nel database
        Log.d("Creazione loadingBar", "Completata");

        mAuth = FirebaseAuth.getInstance();
        Log.d("Creazione mAuth", "Completata");

        annulla = (Button) findViewById(R.id.annullaIscrizione);
        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAnnIscr = new Intent (Iscrizione.this, MainActivity.class);
                startActivity(intentAnnIscr);
            }//onClick
        });// setOnClickListener
        Log.d("OnCreate", "Completato");
    } //onCreate

    public void creaProfilo(View view) {
        //crea gli editText per poterli leggere


        String email = emailET.getText().toString().trim();
        String username = nameET.getText().toString().trim();
        String pw = pwET.getText().toString().trim();
        String cfPw = cfPwET.getText().toString().trim();

        //Log.d("password", pw);
        //Log.d("conferma", cfPw);

        if (TextUtils.isEmpty(username) | TextUtils.isEmpty(email)) { //se manca l'email o lo username
            Toast.makeText(this, "E-mail o username assente", Toast.LENGTH_SHORT).show();
            return;
        } //if

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email inserita non valida", Toast.LENGTH_SHORT).show();
            return;
        }

        int minChar = 2;
        if (pw.length()<minChar) { // password troppo corta
            Toast.makeText(this, "La password deve contenere almeno "+ minChar + " caratteri", Toast.LENGTH_SHORT).show();
            return;
        } //if

        if (!pw.equals(cfPw)) { //se le password non coincidono
            Toast.makeText(this, "Password inserite non coincidenti", Toast.LENGTH_SHORT).show();
            return;
        }//if delle password coincidenti

        //se passa indenne da tutte le if, allora possiamo crearlo

        Log.d("If", "Superate");

        loadingBar.setTitle("Creazione account");
        loadingBar.setMessage("Attendi, stiamo controllando le credenziali");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Log.d("Loading Bar", "Superata");

        mAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Profilo acquirente = new Profilo(email, username, pw);

                    FirebaseDatabase.getInstance().getReference("Utenti").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(acquirente).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Iscrizione.this, "La registrazione è avvenuta con successo", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                            }else{
                                loadingBar.dismiss();
                                Toast.makeText(Iscrizione.this, "Errore di rete, riprova", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    String mex = task.getException().toString();
                    loadingBar.dismiss();
                    Toast.makeText(Iscrizione.this, "Error: " + mex, Toast.LENGTH_SHORT).show();
                }
            }
        });




    } // creaProfilo



    public void isTourist (View view) {
        userIsTour = true;
    }

    public void isManager (View view) {
        userIsTour = false;
    }

    public void goToLogIn(View goToLogIn) {
        startActivity(new Intent (Iscrizione.this, LogIn.class));
    }

} //classe
