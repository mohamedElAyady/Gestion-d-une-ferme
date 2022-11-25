package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Utilisateur;
import javafx.collections.ObservableList;

public class Pass_utilisateur {
    int id;
    ObservableList<Utilisateur>utilisateurs ;

    public Pass_utilisateur(int id, ObservableList<Utilisateur> utilisateurs) {
        this.id = id;
        this.utilisateurs = utilisateurs;
    }
}