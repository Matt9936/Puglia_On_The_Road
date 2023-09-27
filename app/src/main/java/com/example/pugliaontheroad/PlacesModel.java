package com.example.pugliaontheroad;

public class PlacesModel {

    private  int ID;
    private String località;
    private String indirizzo;
    private String info;

    public PlacesModel(int ID, String località, String indirizzo, String info) {
        this.ID = ID;
        this.località = località;
        this.indirizzo = indirizzo;
        this.info = info;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLocalità() {
        return località;
    }

    public void setLocalità(String località) {
        this.località = località;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
