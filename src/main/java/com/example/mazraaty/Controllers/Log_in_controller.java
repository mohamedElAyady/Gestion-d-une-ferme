package com.example.mazraaty.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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

    public void log_in(ActionEvent event) {
        user = (String) username.getText();
        pass = password.getText();
        Stage loginS = new Stage();
        loginS = (Stage) homePane.getScene().getWindow();
        //open Window 2
        try {
            if (user.equals("admin") && pass.equals("admin") && !user.trim().isEmpty() && !pass.trim().isEmpty()) {

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
