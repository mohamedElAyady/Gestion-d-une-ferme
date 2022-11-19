package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Vente_lait;
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

public class Update_vente_lait_controller {
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
        Pass_vente_lait t = (Pass_vente_lait) stage.getUserData();
        int id = t.id;
                String client, prix, total, litres;
                LocalDate date;
                client = this.txt_field1.getText();
                prix = this.txt_field2.getText();
                total = this.txt_field4.getText();
                litres = this.txt_field3.getText();
                date = this.date_picker.getValue();

                if (client.isEmpty() || prix.isEmpty() || total.isEmpty() || litres.isEmpty() || date == null) {
                    display.setText("vérifier vos informations !");
                } else {

                    //changement la
                    pst = c.prepareStatement("update vente_lait set name_client = ?,litres = ? ,date_enrg= ? ,prix_litre = ?,total=? where ID = ? ");
                    pst.setString(1, client);
                    pst.setString(2, litres);
                    pst.setString(3, date.toString());
                    pst.setString(4, prix);
                    pst.setString(5, total);
                    pst.setInt(6, id);
                    pst.executeUpdate();

                    //get the data from Production_Controller using getUserData() method
                    new Vente_lait_controller().refrech_table(t.vente_laits);

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
        Pass_vente_lait t = (Pass_vente_lait) stage.getUserData();
        int id = t.id;

        //changement la
        pst = c.prepareStatement("select * from vente_lait WHERE ID = ? ");
        pst.setString(1, String.valueOf(id));

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            //changement la
            Vente_lait p = new Vente_lait();
            p.setId(rs.getString("ID"));
            p.setNom(rs.getString("name_client"));
            p.setLitres(rs.getString("litres"));
            p.setPrix(rs.getString("prix_litre"));
            p.setDate(rs.getString("date_enrg"));
            p.setTotal(rs.getString("total"));
            p.setKey(rs.getString("ID"));
            txt_field1.setText(p.getId());
            txt_field3.setText(p.getLitres());
            txt_field2.setText(p.getPrix());
            txt_field4.setText(p.getTotal());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(p.getDate(), formatter);
            date_picker.setValue(localDate);
        }
    }




}
