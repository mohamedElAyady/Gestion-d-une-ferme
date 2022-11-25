package com.example.mazraaty.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateur {
    private final StringProperty JC;
    private final StringProperty nom;
    private final StringProperty key;

    private final StringProperty num;
    private final StringProperty email;


    public Utilisateur() {


        JC = new SimpleStringProperty(this, "JC");
        nom = new SimpleStringProperty(this, "nom");
        num = new SimpleStringProperty(this, "num");
        email = new SimpleStringProperty(this, "email");
        key = new SimpleStringProperty(this, "key");

    }

    public StringProperty keyProperty() { return key; }
    public String getKey() { return key.get(); }
    public void setKey(String newId) { key.set(newId); }


    public StringProperty jcProperty() { return JC; }
    public String getJC() { return JC.get(); }
    public void setJC(String newId) { JC.set(newId); }

    public StringProperty nomProperty() { return nom; }
    public String getNom() { return nom.get(); }
    public void setNom(String newNom) { nom.set(newNom); }

    public StringProperty numProperty() { return num; }
    public String getNum() { return num.get(); }
    public void setNum(String newId) { num.set(newId); }

    public StringProperty emailProperty() { return email; }
    public String getEmail() { return email.get(); }
    public void setEmail(String newId) { email.set(newId); }

}
