package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Alimentation;
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

public class Update_alimentation_controller {
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
        Pass_alimentation t = (Pass_alimentation) stage.getUserData();
        int id = t.id;
                String ID, produit, quantite, remarque;
                LocalDate date;
                ID = this.txt_field1.getText();
                produit = this.txt_field2.getText();
                remarque = this.txt_field4.getText();
                quantite = this.txt_field3.getText();
                date = this.date_picker.getValue();

                if (ID.isEmpty() || produit.isEmpty() || quantite.isEmpty() || date == null) {
                    display.setText("vérifier vos informations !");
                } else {

                    //changement la
                    pst = c.prepareStatement("update alimentation set ID_vache = ?,produit = ? ,date_enrg= ? ,quantité = ?,remarque=? where ID = ? ");
                    pst.setString(1, ID);
                    pst.setString(2, produit);
                    pst.setString(3, date.toString());
                    pst.setString(4, quantite);
                    pst.setString(5, remarque);
                    pst.setInt(6, id);
                    pst.executeUpdate();

                    //get the data from Production_Controller using getUserData() method
                    new Alimentation_controller().refrech_table(t.alimentations);

                    //open the confirmation window
                    //changement la
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alimentation infos");
                    alert.setHeaderText("Bien Modifier !");
                    close_btn(event);
                    alert.showAndWait();
                }

    }
    @FXML
    public void get(ActionEvent e) throws SQLException {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Pass_alimentation t = (Pass_alimentation) stage.getUserData();
        int id = t.id;

        //changement la
        pst = c.prepareStatement("select * from alimentation WHERE ID = ? ");
        pst.setString(1, String.valueOf(id));

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Alimentation p = new Alimentation();
            p.setId(rs.getString("ID_vache"));
            p.setDate(rs.getString("date_enrg"));
            p.setProduit(rs.getString("produit"));
            p.setQuantité(rs.getString("quantité"));
            p.setRemarque(rs.getString("remarque"));
            txt_field1.setText(p.getId());
            txt_field2.setText(p.getProduit());
            txt_field3.setText(p.getQuantité());
            txt_field4.setText(p.getRemarque());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(p.getDate(), formatter);
            date_picker.setValue(localDate);
        }
    }




}
