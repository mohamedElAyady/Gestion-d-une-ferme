package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Vente_lait;
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

public class Add_vente_lait_controller {
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
        String client,prix, total,litres;
        LocalDate date;

        client = this.txt_field1.getText();
        prix = this.txt_field2.getText();
        total = this.txt_field4.getText();
        litres =this.txt_field3.getText();
        date = this.date_picker.getValue();
        if (client.isEmpty() || prix.isEmpty() || total.isEmpty() || litres.isEmpty() || date == null ){
            display.setText("vérifier vos informations !");
        }else {
            try {

                //changement la
                pst = c.prepareStatement("insert into vente_lait(name_client,litres,prix_litre,date_enrg,total)values(?,?,?,?,?)");
                pst.setString(1, client);
                pst.setString(2, litres);
                pst.setString(3, prix);
                pst.setString(4, date.toString());
                pst.setString(5, total);

                pst.executeUpdate();

                //get the data from Production_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                new Vente_lait_controller().refrech_table(((ObservableList<Vente_lait>) stage.getUserData()));

                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                //changer cet valeurs
                alert.setTitle("les information de vente");
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
