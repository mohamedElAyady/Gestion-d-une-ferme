package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
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

public class Add_milk_controller {
    public PreparedStatement pst;
    //Infos input
    @FXML
    private TextField txt_field1;
    @FXML
    private TextField txt_field2;
    @FXML
    private DatePicker date_picker;
    @FXML
    private TextField txt_field3;
    @FXML
    private TextField txt_field4;
    @FXML
    private Label display;
    @FXML
    private TextField recherche;

    @FXML
    void submit(ActionEvent event) throws IOException {
        //changement la
        String ID,prix, total,litres;
        LocalDate date;

        ID = this.txt_field1.getText();
        prix = this.txt_field2.getText();
        total = this.txt_field4.getText();
        litres =this.txt_field3.getText();
        date = this.date_picker.getValue();
        if (ID.isEmpty() || prix.isEmpty() || total.isEmpty() || litres.isEmpty() || date == null ){
            display.setText("vérifier vos informations !");
        }else {
            try {

                //changement la
                pst = c.prepareStatement("insert into production(ID_vache,litres,prix_litre,total,date_enrg)values(?,?,?,?,?)");
                pst.setString(1, ID);
                pst.setString(2, litres);
                pst.setString(3, prix);
                pst.setString(4, total);
                pst.setString(5, date.toString());
                pst.executeUpdate();

                //get the data from Production_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                new Production_controller().refrech_table(((ObservableList<Production>) stage.getUserData()));

                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                //changer cet valeurs
                alert.setTitle("Production infos");
                alert.setHeaderText("Production informations");
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
