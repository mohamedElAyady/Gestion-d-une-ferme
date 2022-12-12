package com.example.mazraaty.Controllers;


import com.example.mazraaty.Models.Vache;
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

public class Add_vache_controller {
    public PreparedStatement pst;
    //Infos input
    @FXML
    private TextField txt_field1;
    @FXML
    private TextField txt_field2;
    @FXML
    private DatePicker date_Picker;



    @FXML
    private Label display;
    @FXML
    private TextField recherche;
    @FXML
    private ComboBox<String> choisebox;
    String[] options={"Vache latière", "Petit vache","Taurea", "Mère vache" };
    @FXML
    private ComboBox<String> choisebox2;
    String[] options2={"non disponible", " disponible"};

    @FXML
    void submit(ActionEvent event) throws IOException {
        String ID,sexe,type,status;
        LocalDate date_Picker;



        ID = this.txt_field1.getText();
        sexe = this.txt_field2.getText();
        type = this.choisebox.getValue();
        date_Picker = this.date_Picker.getValue();
        status = this.choisebox2.getValue();

        if (ID.isEmpty() || sexe.isEmpty() || type == null  || status == null   || date_Picker == null || (!(ID.matches("[0-9+11-100]")))  ||   (!(sexe.matches("^[a-zA-Z]*$"))))  {

            display.setText("vérifier vos informations !");
        }else {
            try {
                pst = c.prepareStatement("insert into vaches (ID_vache,sexe,type,statut,date_naiss)values(?,?,?,?,?)");
                pst.setString(1, ID);
                pst.setString(2, sexe);
                pst.setString(3, type);
                pst.setString(4, status);
                pst.setString(5, date_Picker.toString());
                pst.executeUpdate();



                //get the data from Vache_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                new Vache_controller().refrech_table(((ObservableList<Vache>) stage.getUserData()));





                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" vaches infos");
                alert.setHeaderText(" vaches informations");
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
        stage1.
                close();
    }
    public void initialize(){
        choisebox.getItems().addAll(options);
        choisebox2.getItems().addAll(options2);

}

}
