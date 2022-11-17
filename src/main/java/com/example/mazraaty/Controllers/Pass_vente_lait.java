package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Vente_lait;
import javafx.collections.ObservableList;

public class Pass_vente_lait {
    int id;
    ObservableList<Vente_lait> vente_laits;

    public Pass_vente_lait(int id, ObservableList<Vente_lait> vente_laits) {
        this.id = id;
        this.vente_laits = vente_laits;
    }
}
