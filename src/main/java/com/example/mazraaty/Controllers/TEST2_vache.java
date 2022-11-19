package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Vache;
import javafx.collections.ObservableList;

public class TEST2_vache {
    int id;
    ObservableList<Vache>vaches ;

    public TEST2_vache(int id, ObservableList<Vache> vaches) {
        this.id = id;
        this.vaches = vaches;
    }
}