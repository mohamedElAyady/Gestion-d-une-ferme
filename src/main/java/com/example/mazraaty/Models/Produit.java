package com.example.mazraaty.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Produit {
    private final StringProperty ID_pro;
    private final StringProperty produit;
    private final StringProperty quantite;




    public Produit() {

        ID_pro = new SimpleStringProperty(this, "ID_pro");
        produit = new SimpleStringProperty(this, "produit");
        quantite = new SimpleStringProperty(this, "quantite");


    }

    public StringProperty ID_proProperty() { return ID_pro; }
    public String getID_pro() { return ID_pro.get(); }
    public void setID_pro(String newId) { ID_pro.set(newId); }

    public StringProperty proProperty() { return produit; }
    public String getprod() { return produit.get(); }
    public void setprod(String newId) { produit.set(newId); }

    public StringProperty QuantiteProperty() { return quantite; }
    public String getQuantite() { return quantite.get(); }
    public void setQuantite(String newPrix) { quantite.set(newPrix); }



}
