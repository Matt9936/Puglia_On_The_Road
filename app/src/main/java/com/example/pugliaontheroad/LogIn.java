package com.example.pugliaontheroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class LogIn extends AppCompatActivity {


    public static Profilo userLogged; //L'utente loggato

    private Button annulla, conferma, logOutB;
    protected EditText usernameET, passwordET;
    protected TextView intestazione;

    public final static String fileUserLogged = "userLogged.txt"; //file in cui è salvato l'utente loggato

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        conferma = (Button) findViewById(R.id.confermaLogIn);
        annulla = (Button) findViewById(R.id.annullaLogIn);
        logOutB = (Button) findViewById(R.id.logOut);
        usernameET = (EditText) findViewById(R.id.LogInUsername);
        passwordET = (EditText) findViewById(R.id.LogInPassword);
        intestazione = (TextView) findViewById(R.id.logInIntest);

        userLogged = createUserLogged(readUserLogged("; "));
        if (userLogged == null) {
            intestazione.setText("Log In");
        } else {
            intestazione.setText("Salve, "+ userLogged.username);
            logOutB.setVisibility(View.VISIBLE);
        } // if 

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnnLogIn = new Intent (LogIn.this, MainActivity.class);
                startActivity(intentAnnLogIn);
            } //onClick
        }); // setOnClickListener
    } // onCreate

    public void registrati(View view) { //va alla schermata di registrazione

        startActivity(new Intent(LogIn.this, Iscrizione.class));

    } //registrati

    public void logIn(View confermaLogIn) { //effettua il login dell'utente
        ProfiloDBH dbUsers = new ProfiloDBH(this);
        List<Profilo> users = dbUsers.elencaUtenti();

        String username, password;
        username = this.usernameET.getText().toString();
        password = this.passwordET.getText().toString();

        boolean found = false; //ne ha trovato uno uguale
        for (int i=0; i < users.size(); i++) {

            if (username.equals(users.get(i).getUsername()) //&
                 & password.equals(users.get(i).getPassword())) {

                found = true;
                //esegue il login

                userLogged = users.get(i);
                saveUserLogged(userLogged.getInfoLine(), true); //salva l'utente loggato nel file

                logOutB.setVisibility(View.VISIBLE);
                intestazione.setText("Salve, " + userLogged.getUsername() + ".");

                usernameET.setText("");
                passwordET.setText("");

                break;
            } //if

        }//for

        if(!found) {
            Toast.makeText(this, "Nessun profilo con questi username e password. Riprovare", Toast.LENGTH_LONG).show();
        }

    }//logIn

    public void logOut(View logOut) {

        userLogged = null;
        logOutB.setVisibility(View.INVISIBLE);
        intestazione.setText("Log In");

        String userOut = readUserLogged(", ");
        Toast.makeText(this, "L'utente "+ userOut + " è uscito", Toast.LENGTH_LONG).show();
        saveUserLogged("", false);

    }//logOut()

    public void saveUserLogged (String testo, boolean toastWanted) {
        FileOutputStream fos = null;
        try  {
            fos = openFileOutput(fileUserLogged, MODE_PRIVATE);
            fos.write(testo.getBytes());
            if (toastWanted) {
                Toast.makeText(this, "Salvato in "+getFilesDir()+"/"+fileUserLogged, Toast.LENGTH_LONG).show();
            } //if

        } catch (FileNotFoundException err) {
            Toast.makeText(this, "File not found", Toast.LENGTH_LONG).show();
        }//try catch
        catch (IOException e) {
            Toast.makeText(this, "IOException 1", Toast.LENGTH_LONG).show();
        } finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Toast.makeText(this, "IOException 2", Toast.LENGTH_LONG).show();
                } //try-catch
            } //if
        }//finally
    } //saveUserLogged

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
            Toast.makeText(this, "Nessun utente loggato", Toast.LENGTH_LONG).show();
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