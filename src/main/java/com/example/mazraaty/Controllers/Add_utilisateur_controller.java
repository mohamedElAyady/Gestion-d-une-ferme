package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Utilisateur;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.mazraaty.Main.c;

public class Add_utilisateur_controller {
    public PreparedStatement pst;
    //Infos input
    @FXML
    private ChoiceBox<String> choise;

    private String[] status = {"3amel", "KHayat"};
    @FXML
    private TextField JC;
    @FXML
    private TextField nom;
    @FXML
    private TextField num;
    @FXML
    private TextField email;
    @FXML
    private Label display;

    @FXML
    void submit(ActionEvent event) throws IOException {
        String JC,nom, num,email;

        JC = this.JC.getText();
        nom = this.nom.getText();
        num = this.num.getText();
        email =this.email.getText();
        if (JC.isEmpty() || nom.isEmpty() || num.isEmpty() || email.isEmpty()   ){
            display.setText("vérifier vos informations !");
        }else {
            try {
                pst = c.prepareStatement("insert into utilisateur(Cin,Nom,Num,Email,Type)values(?,?,?,?,'employee')");
                pst.setString(1, JC);
                pst.setString(2, nom);
                pst.setString(3, num);
                pst.setString(4, email);
                pst.executeUpdate();

                //get the data from Production_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                new Utilisateur_controller().refrech_table(((ObservableList<Utilisateur>) stage.getUserData()));

                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Utilisateur infos");
                alert.setHeaderText("Utilisateur informations");
                alert.setContentText("Bien ajouter !");
                close_btn(event);
                alert.showAndWait();


            } catch (SQLException ee) {
                ee.getMessage();
            }
        }


    }
    //close btn
    @FXML
    void close_btn(ActionEvent event) throws IOException {
        Stage stage1 ;
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage1.close();
    }

}
