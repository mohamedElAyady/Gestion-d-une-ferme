package com.example.mazraaty.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.example.mazraaty.Main.c;

public class Add_produit_controller {
    public PreparedStatement pst;
    public PreparedStatement pst2;
    //Infos input
    @FXML
    private TextField txt_field1;
    @FXML
    private TextField txt_field2;
    @FXML
    private Label display;

    @FXML
    void submit(ActionEvent event) throws IOException, SQLException {
        //changement la
        String produit, quantite;

        produit = this.txt_field1.getText();
        quantite = this.txt_field2.getText();

        if (produit.isEmpty() || quantite.isEmpty()){
            display.setText("vérifier vos informations !");
        }else {
            try {

                //changement la
                pst = c.prepareStatement("insert into stock(produit,quantite)values(?,?)");
                pst.setString(1, produit);
                pst.setString(2, quantite);
                pst.executeUpdate();
                fill_stock(produit,quantite);
                //get the data from Production_Controller using getUserData() method
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();


                close_btn(event);


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



    void fill_stock(String pr , String q) throws SQLException {
        pst2 = c.prepareStatement("select COUNT(produit) from stock_disp WHERE produit  = ?");
        pst2.setString(1, pr);
        ResultSet rs1 = pst2.executeQuery();

        while (rs1.next()){
            if (rs1.getInt(1) > 0){
                pst2 = c.prepareStatement("UPDATE stock_disp SET quantite= ?  WHERE produit= ? ");
                pst2.setString(1, q);
                pst2.setString(2, pr);
                pst2.executeUpdate();
                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                //changer cet valeurs
                alert.setTitle("Produit");
                alert.setHeaderText("Bien Augmenter !");
                alert.showAndWait();
            }else{
                pst = c.prepareStatement("insert into stock_disp(produit,quantite)values(?,?)");
                pst.setString(1, pr);
                pst.setString(2, String.valueOf(q));
                pst.executeUpdate();
                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                //changer cet valeurs
                alert.setTitle("Produit");
                alert.setHeaderText("Bien ajouter !");
                alert.showAndWait();
            }
        }
        }
    }


/*
                //changement la



                float f=0;
                while (rs.next()){System.out.println(rs.getString("produit"));
                    if (rs.getString("produit")==produit) {
                        f = rs.getFloat("quantite");
                        System.out.println(f);
                    }
                }
                if (f==0){
                    pst = c.prepareStatement("insert into stock_disp(produit,quantite)values(?,?)");
                    pst.setString(1, produit);
                    pst.setString(2, quantite);
                    pst.executeUpdate();
                }else{
                    quantite += f;
                    //changement la
                    pst = c.prepareStatement("insert into stock_disp(produit,quantite)values(?,?)");
                    pst.setString(1, produit);
                    pst.setString(2, quantite);
                    pst.executeUpdate();
                }*/

