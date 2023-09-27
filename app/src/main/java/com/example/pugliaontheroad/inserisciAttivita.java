package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class inserisciAttivita extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "myitinerariodatabase";
    SQLiteDatabase mDatabase;
    EditText editTextLuogo, editTextData, editTextOrario, editTextInfo;
    TextView textViewItinerario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci_attivita);

        ImageButton btnBackHome=(ImageButton)findViewById(R.id.backtohome);
        btnBackHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // definisco l'intenzione
                Intent openItin = new Intent(inserisciAttivita.this, MainActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openItin);
            }
        });
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTable();
        textViewItinerario=(TextView)findViewById(R.id.textViewItinerario);
        editTextLuogo=(EditText)findViewById(R.id.editTextLuogo);
        editTextData=(EditText)findViewById(R.id.editTextData);
        editTextOrario=(EditText)findViewById(R.id.editTextOrario);
        editTextInfo=(EditText)findViewById(R.id.editTextInfo);

        findViewById(R.id.imageButtonSalva).setOnClickListener(this);
        findViewById(R.id.textViewItinerario).setOnClickListener(this);
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS attivita (\n" +
                "    id INTEGER NOT NULL CONSTRAINT attivita_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    luogo varchar(200) NOT NULL,\n" +
                "    data varchar(50) NOT NULL,\n" +
                "    orario varchar(50) NOT NULL,\n" +
                "    info varchar(500) NOT NULL\n" +
                ");";

        mDatabase.execSQL(sql);
    }


    private void addAttivita() {

        String luogo = editTextLuogo.getText().toString().trim();
        String data = editTextData.getText().toString().trim();
        String orario = editTextOrario.getText().toString().trim();
        String info = editTextInfo.getText().toString().trim();

        if (luogo.isEmpty()) {
            editTextLuogo.setError("Inserisci un luogo");
            editTextLuogo.requestFocus();
            return;
        }

        if (data.isEmpty() ) {
            editTextData.setError("Inserisci una data");
            editTextData.requestFocus();
            return;
        }

        String sql = "INSERT INTO attivita(luogo, data, orario, info)" +
                "VALUES(?,?,?,?)";

        mDatabase.execSQL(sql, new String[]{luogo, data, orario, info});
        Toast.makeText(this, "Attività aggiunta", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonSalva:
                addAttivita();
                break;
            case R.id.textViewItinerario:
                startActivity(new Intent(this, visualizzaAttivita.class));
                break;

        }
    }

    public void goToCalendario(View goToCalendario) {
        startActivity(new Intent(inserisciAttivita.this, Calendario.class));
    }// goToCalendario

} //classe