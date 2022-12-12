package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Vente_vache;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.mazraaty.Main.c;

public class Update_vente_vache_controller {
    public PreparedStatement pst;
    //Infos input
    @FXML
    public TextField txt_field1;
    @FXML
    private TextField txt_field2;
    @FXML
    private DatePicker date_picker;
    @FXML
    private TextField txt_field3;
    @FXML
    private TextField txt_field4;
    @FXML
    private TextField txt_field5;
    @FXML
    private Label display;
    @FXML
    private TextField recherche;

    //close btn
    @FXML
    void close_btn(ActionEvent event) throws IOException {
        Stage stage1 ;
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage1.close();
    }

    @FXML
    void update(ActionEvent event) throws IOException, SQLException {
        //get the data from Production_Controller using getUserData() method
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        pass_vente_vache t = (pass_vente_vache) stage.getUserData();
        int id = t.id;
                String ID_vache, mobile, prix_vente, name_client;
                LocalDate date_vente;
                ID_vache = this.txt_field1.getText();
                 mobile = this.txt_field2.getText();
                name_client = this.txt_field4.getText();
                prix_vente = this.txt_field3.getText();
               date_vente = this.date_picker.getValue();

                if (ID_vache.isEmpty() || name_client.isEmpty() || mobile.isEmpty() || prix_vente.isEmpty() || date_vente == null) {
                    display.setText("vérifier vos informations !");
                } else {

                    //changement la
                    pst = c.prepareStatement("update vente_vaches set ID_vache = ?,mobile = ? ,date_vente= ? ,name_client = ?,prix_vente=? where ID = ? ");
                    pst.setString(1, ID_vache);
                    pst.setString(2, mobile);
                    pst.setString(3, date_vente.toString());
                    pst.setString(5, name_client);
                    pst.setString(4, prix_vente);
                    pst.setInt(6, id);
                    pst.executeUpdate();

                    //get the data from Production_Controller using getUserData() method
                    new Vente_vache_controller().refrech_table(t.vente_vaches);

                    //open the confirmation window
                    //changement la
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("vente infos");
                    alert.setHeaderText("Bien Modifier !");
                    close_btn(event);
                    alert.showAndWait();
                }

    }

    @FXML
    public void get(ActionEvent e) throws SQLException {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        pass_vente_vache t = (pass_vente_vache) stage.getUserData();
        int id = t.id;

        //changement la
        pst = c.prepareStatement("select * from vente_vaches WHERE ID = ? ");
        pst.setString(1, String.valueOf(id));

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            //changement la
            Vente_vache p = new Vente_vache();
            p.setId(rs.getString("ID_vache"));
            p.setMobile(rs.getString("mobile"));
            p.setPrix_vente(rs.getString("prix_vente"));
            p.setName_client(rs.getString("name_client"));
            p.setDate_vente(rs.getString("date_vente"));

            p.setKey(rs.getString("ID"));
            txt_field1.setText(p.getId());
            txt_field2.setText(p.getMobile());
            txt_field3.setText(p.getName_client());
            txt_field4.setText(p.getPrix_vente());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(p.getDate_vente(), formatter);
            date_picker.setValue(localDate);
        }
    }




}
