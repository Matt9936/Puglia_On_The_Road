package com.example.pugliaontheroad;

import android.widget.Toast;

import java.io.*;
import java.util.Scanner;

public class Profilo {

    protected int id;
    protected String email;
    protected String username;
    protected String password;

    protected Tipo genere;


    protected enum Tipo {
        turista,
        gestore;
    }//enum


    public Profilo(String email, String username, String password, boolean userIsTour) {
        this.email = email;
        this.username = username;
        this.password = password;

        if (userIsTour) {
            this.genere = Tipo.valueOf("turista");
        } else {
            this.genere = Tipo.valueOf("gestore");
        }
        // sarà già stato verificato che la stringa type sia ammissibile
    } //creazione Account

    Profilo(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Profilo(int id, String email, String username, String password, boolean userIsTour) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;

        if (userIsTour) {
            this.genere = Tipo.valueOf("turista");
        } else {
            this.genere = Tipo.valueOf("gestore");
        }
        // sarà già stato verificato che la stringa type sia ammissibile
    } //creazione Account

    public String getInfo() {
        String mex = "Username: " + this.username +
                "\nEmail: " + this.email +
                "\nPassword: " + this.password +
                "\nTipo: " + this.genere;
        return mex;
    }//getInfo con l'accapo

    public String getInfoSint() {
        String mex = "ID = " +id + "; Username = "+ username;
        return mex;
    }//getInfo sintetico

    public String getInfoLine() { //info su una sola riga
        String sep = "; "; //separatore
        String mex = id + sep + email+ sep+ username + sep + password + sep + genere;
        return mex;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Tipo getGenere() {
        return genere;
    }

    public void setGenere(Tipo genere) {
        this.genere = genere;
    }
} //classe
