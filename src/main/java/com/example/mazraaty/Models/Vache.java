package com.example.mazraaty.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Vache {
    private final StringProperty ID;
    private final StringProperty type;
    private final StringProperty status;
    private final StringProperty date;
    private final StringProperty sexe;
    private final StringProperty key;



    public Vache() {


        ID = new SimpleStringProperty(this, "ID");
        type = new SimpleStringProperty(this, "type");
        status = new SimpleStringProperty(this, "status");
        date = new SimpleStringProperty(this, "date");
        sexe = new SimpleStringProperty(this, "sexe");
        key = new SimpleStringProperty(this, "key");

    }

    public StringProperty keyProperty() { return key; }
    public String getKey() { return key.get(); }
    public void setKey(String newId) { key.set(newId); }


    public StringProperty idProperty() { return ID; }
    public String getId() { return ID.get(); }
    public void setId(String newId) { ID.set(newId); }

    public StringProperty typeProperty() { return type; }
    public String getType() { return type.get(); }
    public void setType(String newType) { type.set(newType); }

    public StringProperty statusProperty() { return status; }
    public String getStatus() { return status.get(); }
    public void setStatus(String newId) { status.set(newId); }

    public StringProperty sexeProperty() { return sexe; }
    public String getSexe() { return sexe.get(); }
    public void setSexe(String newId) { sexe.set(newId); }



    public StringProperty dateProperty() { return date; }
    public String getDate() { return date.get(); }
    public void setDate(String newId) { date.set(newId); }

}
