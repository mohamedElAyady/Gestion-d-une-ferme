package com.example.mazraaty.Controllers;
import com.example.mazraaty.Models.Vache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.mazraaty.Main.c;

public class Vache_latière_controller {

    private PreparedStatement pst;
    @FXML
    private TableView<Vache> table1;

    @FXML
    private TableColumn<Vache, String> col_date;

    @FXML
    private TableColumn<Vache, String> col_type;

    @FXML
    private TableColumn<Vache, String> col_status;

    @FXML
    private TableColumn<Vache, String> id_col;

    ObservableList<Vache> vaches = FXCollections.observableArrayList();

    @FXML
    void close_btn(ActionEvent event) throws IOException {
        Stage stage1 ;
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage1.close();
    }
    public void table() {
        try {
            pst = c.prepareStatement("select ID_vache,date_naiss,type,statut from vaches WHERE type=?");
            pst.setString(1, "Vache latière");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Vache p = new Vache();
                    p.setId(rs.getString("ID_vache"));
                    p.setDate(rs.getString("date_naiss"));
                    p.setType(rs.getString("type"));
                    p.setStatus(rs.getString("statut"));

                    vaches.add(p);
                }
            }
            table1.setItems(vaches);
            id_col.setCellValueFactory(f -> f.getValue().idProperty());
            col_date.setCellValueFactory(f -> f.getValue().dateProperty());
            col_type.setCellValueFactory(f -> f.getValue().typeProperty());
            col_status.setCellValueFactory(f -> f.getValue().statusProperty());
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(Utilisateur_controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initialize() throws SQLException {

        table();
    }


}


