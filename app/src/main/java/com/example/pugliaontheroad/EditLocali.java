package com.example.pugliaontheroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.content.Intent;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditLocali extends AppCompatActivity {

    FirebaseDatabase localiDB;

    DatabaseReference ref;

    EditText nome,indirizzo,telefono,email,orari,sito,servizi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_locali);

        /*getSupportActionBar().setTitle("Nuovo Locale");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        nome=findViewById(R.id.txtName);
        indirizzo=findViewById(R.id.txtIndirizzo);
        telefono=findViewById(R.id.txtTelefono);
        email=findViewById(R.id.txtEmail);
        orari=findViewById(R.id.txtOrari);
        sito=findViewById(R.id.txtSito);
        servizi=findViewById(R.id.txtServizi);
        localiDB=FirebaseDatabase.getInstance();
        ref=localiDB.getReference("Locali");














        /*nome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    getSupportActionBar().setTitle(s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

    } //onCreate
    /*public void inserisciLocale(String nome, String indirizzo, String email, String orari, String sito, String servizi, String telefono){
        Locali locale = new Locali(nome,indirizzo,email,orari,sito,servizi,telefono);
        ref=FirebaseDatabase.getInstance().getReference("Locali");
        String ID = ref.push().getKey();
        ref.child(ID).setValue(locale);

    }*/



    public void goToMenuLocali(View view) {
        startActivity(new Intent(EditLocali.this, MenuLocali.class));
    }

    public void save(View view){
        String nomeS, indS, emailS, orariS, sitoS, serviziS, telefonoS;
        nomeS = nome.getText().toString();
        indS = indirizzo.getText().toString();
        emailS = email.getText().toString();
        orariS = orari.getText().toString();
        sitoS = sito.getText().toString();
        serviziS=servizi.getText().toString();
        telefonoS = telefono.getText().toString();
        Locali locale = new Locali(nomeS, indS, emailS, orariS, sitoS, serviziS, telefonoS);
        String id = ref.push().getKey();
        OnCompleteListener ocl = new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Hai salvato: " + locale.getInfo(), Toast.LENGTH_LONG).show();
                }else{
                        Toast.makeText(getApplicationContext(),"Errore: "+task.getException().toString(),Toast.LENGTH_LONG).show();


                }

            }
        };
        ref.child(id).setValue(locale);
        Toast.makeText(getApplicationContext(), "Hai salvato: "+locale.getInfo(), Toast.LENGTH_LONG).show();











        //onBackPressed();
        //startActivity(new Intent(EditLocali.this, MenuLocali.class));

    }//save


    public void onBackPressed(){
        super.onBackPressed();
    }







}

