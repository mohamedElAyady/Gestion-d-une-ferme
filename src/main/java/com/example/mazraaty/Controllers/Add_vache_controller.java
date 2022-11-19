package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Vache;
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

public class Add_vache_controller {
    public PreparedStatement pst;
    //Infos input
    @FXML
    private TextField ID;
    @FXML
    private TextField type;
    @FXML
    private DatePicker date;
    @FXML
    private TextField status;

    @FXML
    private Label display;
    @FXML
    private TextField recherche;

    @FXML
    void submit(ActionEvent event) throws IOException {
        String ID, type, status;
        LocalDate date;

        ID = this.ID.getText();
        type = this.type.getText();
        status = this.status.getText();
        date = this.date.getValue();
        if (ID.isEmpty() || type.isEmpty() || status.isEmpty()  || date == null ){
            display.setText("vérifier vos informations !");
        }else {
            try {
                pst = c.prepareStatement("insert into vaches (ID_vache,type,statut ,date_naiss)values(?,?,?,?)");
                pst.setString(1, ID);
                pst.setString(2, type);
                pst.setString(3, status);
                pst.setString(4, date.toString());
                pst.executeUpdate();

                //get the data from Vache_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                new Vache_controller().refrech_table(((ObservableList<Vache>) stage.getUserData()));

                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vache infos");
                alert.setHeaderText("Vaxche informations");
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
