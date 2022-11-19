package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Vache;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.mazraaty.Main.c;

public class Vache_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int id;
    ObservableList<Vache> vaches = FXCollections.observableArrayList();


    @FXML
    private Button modifier_btn;

    @FXML
    private TextField search_by_id;

    @FXML
    private DatePicker date_picker;

    @FXML
    private TableView<Vache> table;

    @FXML
    private TableColumn<Vache, String> col_date;

    @FXML
    private TableColumn<Vache, String> col_type;

    @FXML
    private TableColumn<Vache, String> col_status;

    @FXML
    private TableColumn<Vache, String> id_col;



    @FXML
    void tableaudeboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/tableau_de_board.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tableau de board");
        stage.show();
    }

    @FXML
    void utilisateur(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/utilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Utilisateur");
        stage.show();
    }

    @FXML
    void vaccine(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vaccine.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Surveillance des vaccins");
        stage.show();
    }

    @FXML
    void vente_vache(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vente_vache.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vente de vaches");
        stage.show();
    }

    @FXML
    void categorie_vache(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/categorie_vache.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Categorie de vache");
        stage.show();
    }

    @FXML
    void alimentation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/alimentation.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("L'alimentation");
        stage.show();
    }

    @FXML
    void production(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/production.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Production");
        stage.show();
    }

    @FXML
    void vente_lait(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vente_lait.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vente de lait");
        stage.show();
    }

    @FXML
    void vache(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vache.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vache");
        stage.show();
    }

    @FXML
    void settings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/settings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();
    }

    @FXML
    void search(ActionEvent event) throws IOException {
        System.out.println("this function is not working yet !!");
    }

    @FXML
    void print(ActionEvent event) throws IOException {
        System.out.println("this function is not working yet !!");
    }

    @FXML
    void close_btn(ActionEvent event) throws IOException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.close();
    }

    @FXML
    void log_out(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage log_stage = new Stage();
        Main log_in = new Main();
        stage.close();
        log_in.start(log_stage);

    }

    @FXML
    void add_infos(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_vache_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(vaches);
    }

    @FXML
    public void table_search(ActionEvent event){


        ResultSet rs=null;

        try {
            if(date_picker.getValue() == null){
                String id = search_by_id.getText();
                vaches.clear();
                pst = c.prepareStatement("select date_naiss,ID_vache,type,statut from vaches WHERE ID_vache = ? ");
                pst.setString(1, id);
                rs = pst.executeQuery();
            }else if(search_by_id.getText().isEmpty()){
                String  date =date_picker.getValue().toString();
                vaches.clear();
                pst = c.prepareStatement("select date_naiss,ID_vache,type,statut from vaches WHERE date_naiss = ? ");
                pst.setString(1, date);
                rs = pst.executeQuery();
            }
            else if (!date_picker.getValue().toString().isEmpty()&& !search_by_id.getText().isEmpty()){
                String id = search_by_id.getText();
                String  date =date_picker.getValue().toString();
                vaches.clear();
                pst = c.prepareStatement("select date_naiss,ID_vache,type,statut from vaches WHERE ID_vache = ? AND  date_naiss = ? ");
                pst.setString(1, id);
                pst.setString(2, date);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Vache p = new Vache();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_naiss"));
                p.setType(rs.getString("type"));
                p.setStatus(rs.getString("statut"));

                vaches.add(p);
            }
            table.setItems(vaches);
            id_col.setCellValueFactory(f -> f.getValue().idProperty());
            col_date.setCellValueFactory(f -> f.getValue().dateProperty());
            col_type.setCellValueFactory(f -> f.getValue().typeProperty());
            col_status.setCellValueFactory(f -> f.getValue().statusProperty());


            modifier_btn.setDisable(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(Vache_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        vaches.clear();
        initialize();
        modifier_btn.setDisable(false);
    }
    @FXML
   public void update(ActionEvent event) throws IOException{
        if (id==-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE !!");
            alert.setHeaderText("Choisire une item");
            alert.showAndWait();
        }else {
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/update_vache_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Modifier");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        //pass id to update controller
            TEST2_vache t = new TEST2_vache(id,vaches);

        primaryStage.setUserData(t);
        primaryStage.show();}

    }

    public void initialize() throws SQLException {
        id =-1;
        table();
    }

    public void refrech_table(ObservableList<Vache> vaches){
        try{
            vaches.clear();
            pst = c.prepareStatement("select * from vaches");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Vache p = new Vache();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_naiss"));
                p.setType(rs.getString("type"));
                p.setStatus(rs.getString("statut"));

                vaches.add(p);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {


        try {
            pst = c.prepareStatement("select ID_vache,date_naiss,type,statut from vaches");
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
            table.setItems(vaches);
            id_col.setCellValueFactory(f -> f.getValue().idProperty());
            col_date.setCellValueFactory(f -> f.getValue().dateProperty());
           col_type.setCellValueFactory(f -> f.getValue().typeProperty());
           col_status.setCellValueFactory(f -> f.getValue().statusProperty());

        } catch (SQLException ex)
        {
            Logger.getLogger(Vache_controller.class.getName()).log(Level.SEVERE, null, ex);
        }



        table.setRowFactory( tv -> {
            TableRow<Vache> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

                }
            });
            return myRow;
        });



    }



}

