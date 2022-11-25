package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Vache;
import com.example.mazraaty.Models.Vente_vache;
import javafx.collections.ObservableList;

public class pass_vente_vache {
    int id;
    ObservableList<Vente_vache>vente_vaches ;

    public pass_vente_vache(int id, ObservableList<Vente_vache>vente_vaches) {
        this.id = id;
        this.vente_vaches = vente_vaches;
    }
}