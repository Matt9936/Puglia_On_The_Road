package com.example.pugliaontheroad;

import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class Locali {

    public Locali(String nome, String indirizzo, String email, EditText orari, String sito, String servizi, String telefono) {
    }

    public String getInfo(){
        String sep = ", ";
        String mex= this.getNome() + sep + this.getIndirizzo() + sep + getOrari() + sep + getSito() +
                sep + this.getEmail() + sep + this.getTelefono() + sep + this.getServizi();
        return mex;
    }


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrari() {
        return orari;
    }

    public void setOrari(String orari) {
        this.orari = orari;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    public String getServizi() {
        return servizi;
    }

    public void setServizi(String servizi) {
        this.servizi = servizi;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private long ID;
    private String nome;
    private String orari;
    private String indirizzo;
    private String sito;
    private String servizi;
    private String telefono;
    private String email;

    Locali(){

    }
    Locali(String nome,String indirizzo,String email,String orari,String sito,String servizi,String telefono){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.email=email;
        this.orari=orari;
        this.sito=sito;
        this.servizi=servizi;
        this.telefono=telefono;
    }
    Locali(long id,String nome,String indirizzo,String email,String orari,String sito,String servizi,String telefono){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.email=email;
        this.orari=orari;
        this.sito=sito;
        this.servizi=servizi;
        this.telefono=telefono;
        this.ID=id;
    }
    public void inserisciLocale(String nome,String indirizzo,String email,String orari,String sito,String servizi,String telefono){
        Locali locale = new Locali(nome,indirizzo,email,orari,sito,servizi,telefono);
        FirebaseDatabase.getInstance().getReference("Locali");
    }
}
