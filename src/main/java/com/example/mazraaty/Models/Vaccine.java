package com.example.mazraaty.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Vaccine {
    private final StringProperty ID_vache;
    private final StringProperty vaccine;
    private final StringProperty date_enrg;
    private final StringProperty remarque;
    private final StringProperty date_next_vacc;
    private final StringProperty key;



    public Vaccine() {


        ID_vache = new SimpleStringProperty(this, "ID_vache");
        vaccine = new SimpleStringProperty(this, "vaccine");
        date_enrg = new SimpleStringProperty(this, "date_enrg");
        remarque = new SimpleStringProperty(this, "remarque");
        date_next_vacc = new SimpleStringProperty(this, "date_next_vacc");
        key = new SimpleStringProperty(this, "key");

    }

    public StringProperty keyProperty() { return key; }
    public String getKey() { return key.get(); }
    public void setKey(String newId) { key.set(newId); }


    public StringProperty ID_vacheProperty() { return ID_vache; }
    public String getID_vache() { return ID_vache.get(); }
    public void setID_vache(String newID_vache) { ID_vache.set(newID_vache); }

    public StringProperty vaccineProperty() { return vaccine; }
    public String getVaccine() { return vaccine.get(); }
    public void setVaccine(String newId) { vaccine.set(newId); }

    public StringProperty date_enrgProperty() { return date_enrg; }
    public String getDate_enrg() { return date_enrg.get(); }
    public void setDate_enrg(String newId) { date_enrg.set(newId); }

    public StringProperty date_next_vaccProperty() { return this.date_next_vacc; }
    public String getdate_next_vacc() { return date_next_vacc.get(); }
    public void setdate_next_vacc(String newId) { date_next_vacc.set(newId); }

    public StringProperty remarqueProperty() { return remarque; }
    public String getRemarque() { return remarque.get(); }
    public void setRemarque(String newId) { remarque.set(newId); }

}
