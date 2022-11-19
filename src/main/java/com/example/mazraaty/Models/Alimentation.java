package com.example.mazraaty.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Alimentation {
    private final StringProperty ID;
    private final StringProperty produit;
    private final StringProperty quantité;
    private final StringProperty remarque;
    private final StringProperty date;
    private final StringProperty key;



    public Alimentation() {


        ID = new SimpleStringProperty(this, "ID");
        produit = new SimpleStringProperty(this, "produit");
        quantité = new SimpleStringProperty(this, "quantité");
        remarque = new SimpleStringProperty(this, "remarque");
        date = new SimpleStringProperty(this, "date");
        key = new SimpleStringProperty(this, "key");

    }

    public StringProperty keyProperty() { return key; }
    public String getKey() { return key.get(); }
    public void setKey(String newId) { key.set(newId); }


    public StringProperty idProperty() { return ID; }
    public String getId() { return ID.get(); }
    public void setId(String newId) { ID.set(newId); }

    public StringProperty ProduitProperty() { return produit; }
    public String getProduit() { return produit.get(); }
    public void setProduit(String newPrix) { produit.set(newPrix); }

    public StringProperty quantitéProperty() { return quantité; }
    public String getQuantité() { return quantité.get(); }
    public void setQuantité(String newId) { quantité.set(newId); }

    public StringProperty remarqueProperty() { return remarque; }
    public String getRemarque() { return remarque.get(); }
    public void setRemarque(String newId) { remarque.set(newId); }

    public StringProperty dateProperty() { return date; }
    public String getDate() { return date.get(); }
    public void setDate(String newId) { date.set(newId); }

}
