package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Vaccine;
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

public class Add_vaccin_controller {
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
    private DatePicker date_picker1;
    @FXML
    private Label display;

    @FXML
    void submit(ActionEvent event) throws IOException {
        //changement la
        String ID, vaccin, remarque;
        LocalDate date1,date2;

        ID = this.txt_field1.getText();
        vaccin = this.txt_field2.getText();
        remarque = this.txt_field3.getText();
        date1 = this.date_picker.getValue();
        date2 = this.date_picker1.getValue();
        if (ID.isEmpty() || vaccin.isEmpty() || date2==null || date1 == null ){
            display.setText("vérifier vos informations !");
        }else {
            try {

                //changement la
                pst = c.prepareStatement("insert into vaccine(ID_vache,date_next_vacc,vaccine,remarque,date_enrg)values(?,?,?,?,?)");
                pst.setString(1, ID);
                pst.setString(2, date2.toString());
                pst.setString(3, vaccin);
                pst.setString(4, remarque);
                pst.setString(5, date1.toString());
                pst.executeUpdate();

                //get the data from Production_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                new Vaccine_controller().refrech_table(((ObservableList<Vaccine>) stage.getUserData()));

                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                //changer cet valeurs
                alert.setTitle("Vaccination infos");
                alert.setHeaderText("Bien ajouter !");
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
