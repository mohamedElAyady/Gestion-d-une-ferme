package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Vaccine;
import javafx.collections.ObservableList;

public class Pass_vaccine {
    int id;
    ObservableList<Vaccine> vaccines;

    public Pass_vaccine(int id, ObservableList<Vaccine> vaccines) {
        this.id = id;
        this.vaccines = vaccines;
    }
}
