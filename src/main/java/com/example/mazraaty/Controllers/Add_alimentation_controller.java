package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Alimentation;
import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.mazraaty.Main.c;

public class Add_alimentation_controller {
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
    private ComboBox<String> choisebox;

    ObservableList<String> options= FXCollections.observableArrayList();

    @FXML
    void submit(ActionEvent event) throws IOException {
        //changement la
        String ID,produit, quantite, remarque;
        LocalDate date;

        ID = this.txt_field1.getText();
        produit = choisebox.getValue();
        quantite = this.txt_field3.getText();
        remarque =this.txt_field4.getText();
        date = this.date_picker.getValue();


        if (ID.isEmpty() || produit==null || quantite.isEmpty() || date == null){
            display.setText("vérifier vos informations !");
        }else {
            try {

                //changement la
                pst = c.prepareStatement("insert into alimentation(ID_vache,remarque,quantité,produit,date_enrg)values(?,?,?,?,?)");
                pst.setString(1, ID);
                pst.setString(2, remarque);
                pst.setString(3, quantite);
                pst.setString(4, produit);
                pst.setString(5, date.toString());
                pst.executeUpdate();

                //get the data from Production_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                new Alimentation_controller().refrech_table(((ObservableList<Alimentation>) stage.getUserData()));

                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                //changer cet valeurs
                alert.setTitle("Alimentation infos");
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

    public void initialize(){

        try {

            //changement la
            pst = c.prepareStatement("select produit from stock");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                if (options.contains(rs.getString("produit"))) continue;
                options.add(rs.getString("produit"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        choisebox.getItems().addAll(options);
    }

    void update_produit_table(String produit,float Q){

    }


}
