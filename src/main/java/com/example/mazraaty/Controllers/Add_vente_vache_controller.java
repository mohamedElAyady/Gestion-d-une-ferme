package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Vente_vache;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.mazraaty.Main.c;

public class Add_vente_vache_controller {
    public PreparedStatement pst;
    //Infos input
    @FXML
    private TextField ID_vache;
    @FXML
    private TextField prix_vente;
    @FXML
    private DatePicker date_vente;
    @FXML
    private TextField mobile;
    @FXML
    private TextField name_client;


    @FXML
    private Label display;
    @FXML
    private TextField recherche;

    @FXML
    void submit(ActionEvent event) throws IOException {
        String ID,name_client,prix_vente,mobile;
        LocalDate date_vente;



        ID = this.ID_vache.getText();
        name_client = this.name_client.getText();
        mobile = this.mobile.getText();
        date_vente = this.date_vente.getValue();
        prix_vente = this.prix_vente.getText();

        if (ID.isEmpty() || name_client.isEmpty() || prix_vente == null  || mobile == null   || date_vente == null ){
            display.setText("vérifier vos informations !");
        }else {
            try {
                pst = c.prepareStatement("insert into vente_vaches (ID_vache,mobile,prix_vente,name_client ,date_vente)values(?,?,?,?,?)");
                pst.setString(1, ID);
                pst.setString(2, mobile);
                pst.setString(3, prix_vente);
                pst.setString(4, name_client);
                pst.setString(5, date_vente.toString());
                pst.executeUpdate();



                //get the data from Vache_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                new Vente_vache_controller().refrech_table(((ObservableList<Vente_vache>) stage.getUserData()));


                pst = c.prepareStatement("update vaches set statut = 'vendu' where ID_vache = ?" );
                pst.setString(1, ID);
                pst.executeUpdate();


                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vente_vache infos");
                alert.setHeaderText("Vente_vache informations");
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
