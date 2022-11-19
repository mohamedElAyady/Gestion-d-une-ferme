package com.example.mazraaty.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Vente_lait {
    private final StringProperty ID;
    private final StringProperty nom;
    private final StringProperty prix;
    private final StringProperty total;
    private final StringProperty litres;
    private final StringProperty date;
    private final StringProperty key;



    public Vente_lait() {


        ID = new SimpleStringProperty(this, "ID");
        nom = new SimpleStringProperty(this, "nom");
        prix = new SimpleStringProperty(this, "prix");
        total = new SimpleStringProperty(this, "total");
        litres = new SimpleStringProperty(this, "litres");
        date = new SimpleStringProperty(this, "date");
        key = new SimpleStringProperty(this, "key");

    }

    public StringProperty keyProperty() { return key; }
    public String getKey() { return key.get(); }
    public void setKey(String newId) { key.set(newId); }


    public StringProperty idProperty() { return ID; }
    public String getId() { return ID.get(); }
    public void setId(String newId) { ID.set(newId); }

    public StringProperty nomProperty() { return nom; }
    public String getNom() { return nom.get(); }
    public void setNom(String newId) { nom.set(newId); }

    public StringProperty PrixProperty() { return prix; }
    public String getPrix() { return prix.get(); }
    public void setPrix(String newPrix) { prix.set(newPrix); }

    public StringProperty totalProperty() { return total; }
    public String getTotal() { return total.get(); }
    public void setTotal(String newId) { total.set(newId); }

    public StringProperty litresProperty() { return litres; }
    public String getLitres() { return litres.get(); }
    public void setLitres(String newId) { litres.set(newId); }

    public StringProperty dateProperty() { return date; }
    public String getDate() { return date.get(); }
    public void setDate(String newId) { date.set(newId); }

}
