package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Production;
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

public class Production_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int id = -1;


    //changement la
    ObservableList<Production> productions = FXCollections.observableArrayList();


    @FXML
    private Button modifier_btn;

    @FXML
    private TextField search_by_id;

    @FXML
    private DatePicker date_picker;

    //changement la
    @FXML
    private TableView<Production> table;

    @FXML
    private TableColumn<Production, String> col5;

    @FXML
    private TableColumn<Production, String> col3;

    @FXML
    private TableColumn<Production, String> col4;

    @FXML
    private TableColumn<Production, String> col1;

    @FXML
    private TableColumn<Production, String> col2;

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
    void close_btn(ActionEvent event) throws IOException, SQLException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        c.close();
        System.out.println("Connection closed !");
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
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(productions);
    }

    @FXML
    public void table_search(ActionEvent event){


        ResultSet rs=null;

        try {
            if(date_picker.getValue() == null){
                String id = search_by_id.getText();

                //changement la
                productions.clear();

                //changement la
                pst = c.prepareStatement("select date_enrg,ID_vache,litres,prix_litre,total from production WHERE ID_vache = ? ");
                pst.setString(1, id);
                rs = pst.executeQuery();
            }else if(search_by_id.getText().isEmpty()){
                String  date =date_picker.getValue().toString();

                //un changement la
                productions.clear();

                //changement la
                pst = c.prepareStatement("select date_enrg,ID_vache,litres,prix_litre,total from production WHERE date_enrg = ? ");
                pst.setString(1, date);
                rs = pst.executeQuery();
            }
            else if (!date_picker.getValue().toString().isEmpty()&& !search_by_id.getText().isEmpty()){
                String id = search_by_id.getText();
                String  date =date_picker.getValue().toString();
                //changement la
                productions.clear();

                //changement la
                pst = c.prepareStatement("select date_enrg,ID_vache,litres,prix_litre,total from production WHERE ID_vache = ? AND  date_enrg = ? ");
                pst.setString(1, id);
                pst.setString(2, date);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Production p = new Production();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_enrg"));
                p.setLitres(rs.getString("litres"));
                p.setPrix(rs.getString("prix_litre"));
                p.setTotal(rs.getString("total"));

                //changement la
                productions.add(p);
            }
            //changement la
            table.setItems(productions);
            col1.setCellValueFactory(f -> f.getValue().idProperty());
            col5.setCellValueFactory(f -> f.getValue().dateProperty());
            col3.setCellValueFactory(f -> f.getValue().litresProperty());
            col4.setCellValueFactory(f -> f.getValue().totalProperty());
            col2.setCellValueFactory(f -> f.getValue().PrixProperty());

            modifier_btn.setDisable(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(Production_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        //changement la
        productions.clear();
        initialize();
        modifier_btn.setDisable(false);
    }

    @FXML
    public void update(ActionEvent event) throws IOException{
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        if (id == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error !");
            alert.setHeaderText("Sélectionnez un item !");
            alert.showAndWait();
        }else {
            //changement la
            Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/update_milk_infos.fxml"));

            Scene scene1 = new Scene(root1);
            primaryStage.setScene(scene1);
            primaryStage.setTitle("Modifier");
            //manage maximize, minimize and close button
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //specify modality for the new window
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            //pass id to update controller
            Pass_production t = new Pass_production(id, productions);

            primaryStage.setUserData(t);
            primaryStage.show();
        }
    }

    public void initialize() throws SQLException {
        table();
    }

    public void refrech_table(ObservableList<Production> productions){
        try{
            productions.clear();

            //changement la
            pst = c.prepareStatement("select * from production");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //changement la
                Production p = new Production();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_enrg"));
                p.setLitres(rs.getString("litres"));
                p.setPrix(rs.getString("prix_litre"));
                p.setTotal(rs.getString("total"));
                productions.add(p);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {


        try {

            //changement la
            pst = c.prepareStatement("select ID,date_enrg,ID_vache,litres,prix_litre,total from production");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    //changement la
                    Production p = new Production();
                    p.setId(rs.getString("ID_vache"));
                    p.setDate(rs.getString("date_enrg"));
                    p.setLitres(rs.getString("litres"));
                    p.setPrix(rs.getString("prix_litre"));
                    p.setTotal(rs.getString("total"));
                    p.setKey(rs.getString("ID"));
                    //changement la
                    productions.add(p);

                }
            }
            //changement la
            table.setItems(productions);
            col1.setCellValueFactory(f -> f.getValue().idProperty());
            col5.setCellValueFactory(f -> f.getValue().dateProperty());
            col3.setCellValueFactory(f -> f.getValue().litresProperty());
            col4.setCellValueFactory(f -> f.getValue().totalProperty());
            col2.setCellValueFactory(f -> f.getValue().PrixProperty());
        } catch (SQLException ex)
        {
            Logger.getLogger(Production_controller.class.getName()).log(Level.SEVERE, null, ex);
        }



        table.setRowFactory( tv -> {
            TableRow<Production> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getKey()));

                }
            });
            return myRow;
        });



    }



}

