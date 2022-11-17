package com.example.mazraaty;

import com.example.mazraaty.Models.Con_infos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    public static Connection c;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tableau_de_board.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In!");
        stage.setScene(scene);
        stage.show();
    }
    public static void Connect() {

        Con_infos c1 = new Con_infos();
        try {
            c = c1.connect();
            System.out.println("Database is Connected !");
        }catch (SQLException ee){
            System.out.println(ee.getMessage());
        }
    }

    public static void main(String[] args) {
        Connect();
        launch();
    }
}