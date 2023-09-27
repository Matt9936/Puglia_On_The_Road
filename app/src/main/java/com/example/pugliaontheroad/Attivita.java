package com.example.pugliaontheroad;


public class Attivita {
    int id;
    String luogo, data, orario, info;


    public Attivita(int id, String luogo, String data, String orario, String info) {
        this.id = id;
        this.luogo = luogo;
        this.data = data;
        this.orario = orario;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getData() {
        return data;
    }

    public String getOrario() {
        return orario;
    }

    public String getInfo() {
        return info;
    }
}




















