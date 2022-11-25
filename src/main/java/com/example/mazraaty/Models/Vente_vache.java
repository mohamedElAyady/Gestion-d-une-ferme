package com.example.mazraaty.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Vente_vache {
    private final StringProperty ID;
    private final StringProperty name_client;
    private final StringProperty prix_vente;
    private final StringProperty mobile;

    private final StringProperty date_vente;
    private final StringProperty key;



    public Vente_vache() {


        ID = new SimpleStringProperty(this, "ID");
        name_client = new SimpleStringProperty(this, "name_client");
        prix_vente = new SimpleStringProperty(this, "prix_vente");
        mobile = new SimpleStringProperty(this, "mobile");
        date_vente = new SimpleStringProperty(this, "date_vente");
        key = new SimpleStringProperty(this, "key");

    }

    public StringProperty keyProperty() { return key; }
    public String getKey() { return key.get(); }
    public void setKey(String newId) { key.set(newId); }


    public StringProperty idProperty() { return ID; }
    public String getId() { return ID.get(); }
    public void setId(String newId) { ID.set(newId); }

    public StringProperty name_clientProperty() { return name_client; }
    public String getName_client() { return name_client.get(); }
    public void setName_client(String newId) { name_client.set(newId); }

    public StringProperty prix_venteProperty() { return prix_vente; }
    public String getPrix_vente() { return prix_vente.get(); }
    public void setPrix_vente(String newPrix_vente) { prix_vente.set(newPrix_vente); }

    public StringProperty mobileProperty() { return mobile; }
    public String getMobile() { return mobile.get(); }
    public void setMobile(String newId) { mobile.set(newId); }


    public StringProperty date_venteProperty() { return date_vente; }
    public String getDate_vente() { return date_vente.get(); }
    public void setDate_vente(String newId) { date_vente.set(newId); }

}
