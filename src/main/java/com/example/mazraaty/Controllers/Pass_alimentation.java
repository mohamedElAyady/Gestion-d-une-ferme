package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Alimentation;
import com.example.mazraaty.Models.Production;
import javafx.collections.ObservableList;

public class Pass_alimentation {
    int id;
    ObservableList<Alimentation> alimentations;

    public Pass_alimentation(int id, ObservableList<Alimentation> alimentations) {
        this.id = id;
        this.alimentations = alimentations;
    }
}
