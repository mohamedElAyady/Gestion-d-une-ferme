package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import javafx.collections.ObservableList;

public class Pass_production {
    int id;
    ObservableList<Production> productions;

    public Pass_production(int id, ObservableList<Production> productions) {
        this.id = id;
        this.productions = productions;
    }
}
