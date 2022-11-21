package com.example.mazraaty.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.mazraaty.Main.c;

public class Log_in_controller {
    @FXML
    private AnchorPane homePane;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label display;
    @FXML
    private Button submit;

    String user;
    String pass;
    String user1,pass1;
    PreparedStatement pst;

    public void log_in(ActionEvent event) throws SQLException {
        user = (String) username.getText();
        pass = password.getText();
        Stage loginS = new Stage();
        loginS = (Stage) homePane.getScene().getWindow();

        pst = c.prepareStatement("SELECT * FROM admin where ID = (select MAX(ID) from admin)");
        ResultSet rs = pst.executeQuery();

        while (rs.next()){
             user1 = rs.getString(9);
             pass1 = rs.getString(4);

        }

        //open Window 2
        try {
            if (user.equals(user1) && pass.equals(pass1) && !user.trim().isEmpty() && !pass.trim().isEmpty()) {

                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/tableau_de_board.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Tableau de board");
                //manage maximize, minimize and close button
                primaryStage.initStyle(StageStyle.TRANSPARENT);
                primaryStage.show();
                loginS.close();
            } else {
                display.setText("fause informations !");
            }
        } catch (IOException e) {

        }


    }

}
