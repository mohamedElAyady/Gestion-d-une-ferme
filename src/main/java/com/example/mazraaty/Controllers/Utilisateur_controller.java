package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Utilisateur;
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

public class Utilisateur_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int id;
    ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();


    @FXML
    private Button modifier_btn;

    @FXML
    private TextField search_by_id;

    @FXML
    private TableView<Utilisateur> table1;

    @FXML
    private TableColumn<Utilisateur, String> col_cin;

    @FXML
    private TableColumn<Utilisateur, String> col_nom;

    @FXML
    private TableColumn<Utilisateur, String> col_num;

    @FXML
    private TableColumn<Utilisateur, String> col_email;

    @FXML
    private TableColumn<Utilisateur, String> col_type;


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
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_utilisateur.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(utilisateurs);
    }

    @FXML
    public void table_search(ActionEvent event){


        ResultSet rs=null;
        try {
        if(!search_by_id.getText().isEmpty()){
                String id = search_by_id.getText();
                utilisateurs.clear();
                pst = c.prepareStatement("select Cin,Nom,Num,Email,Type from utilisateur WHERE Cin = ? ");
                pst.setString(1, id);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setJC(rs.getString("Cin"));
                p.setNom(rs.getString("Nom"));
                p.setNum(rs.getString("Num"));
                p.setEmail(rs.getString("Email"));
                p.setType(rs.getString("Type"));
                utilisateurs.add(p);
            }
            table1.setItems(utilisateurs);
            col_cin.setCellValueFactory(f -> f.getValue().jcProperty());
            col_nom.setCellValueFactory(f -> f.getValue().nomProperty());
            col_num.setCellValueFactory(f -> f.getValue().numProperty());
            col_email.setCellValueFactory(f -> f.getValue().emailProperty());
            col_type.setCellValueFactory(f -> f.getValue().typeProperty());

            modifier_btn.setDisable(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(Utilisateur_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        utilisateurs.clear();
        initialize();
        modifier_btn.setDisable(false);
    }

    @FXML
    public void update(ActionEvent event) throws IOException{
        Stage primaryStage = new Stage();
        if (id==-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE !!");
            alert.setHeaderText("Choisire une item");
            alert.showAndWait();
        }else {

            //open new stage
            //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
            Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/update_utilisateur.fxml"));
            Scene scene1 = new Scene(root1);
            primaryStage.setScene(scene1);
            primaryStage.setTitle("Modifier");
            //manage maximize, minimize and close button
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //specify modality for the new window
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            //pass id to update controller
            Pass_utilisateur t = new Pass_utilisateur(id,utilisateurs);

            primaryStage.setUserData(t);
            primaryStage.show();}

    }
    @FXML
    public void suprimer(ActionEvent event) throws IOException{
        if (id==-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE !!");
            alert.setHeaderText("Choisire une item");
            alert.showAndWait();
        }else {
            try {
            pst = c.prepareStatement("delete from utilisateur where ID=?");
            pst.setInt(1, id);
            pst.executeUpdate();
                refrech();
            //get the data from Production_Controller using getUserData() method

            //open the confirmation window
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Utilisateur infos");
            alert.setHeaderText("Utilisateur informations");
            alert.setContentText("Bien ajouter !");
            alert.showAndWait();



            } catch (SQLException ee) {
                System.out.println(ee.getMessage());
        }

           }

    }

    public void initialize() throws SQLException {
        id =-1;
        table();
    }


    public void refrech_table(ObservableList<Utilisateur> utilisateurs){
        try{
            utilisateurs.clear();
            pst = c.prepareStatement("select * from utilisateur");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setJC(rs.getString("Cin"));
                p.setNom(rs.getString("Nom"));
                p.setNum(rs.getString("Num"));
                p.setEmail(rs.getString("Email"));
                p.setType(rs.getString("Type"));
                utilisateurs.add(p);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {


        try {
            pst = c.prepareStatement("select ID,Cin,Nom,Num,Email,Type from utilisateur");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Utilisateur p = new Utilisateur();
                    p.setJC(rs.getString("Cin"));
                    p.setNom(rs.getString("Nom"));
                    p.setNum(rs.getString("Num"));
                    p.setEmail(rs.getString("Email"));
                    p.setKey(rs.getString("ID"));
                    p.setType(rs.getString("Type"));
                    utilisateurs.add(p);

                }
            }
            table1.setItems(utilisateurs);
            col_cin.setCellValueFactory(f -> f.getValue().jcProperty());
            col_nom.setCellValueFactory(f -> f.getValue().nomProperty());
            col_num.setCellValueFactory(f -> f.getValue().numProperty());
            col_email.setCellValueFactory(f -> f.getValue().emailProperty());
            col_type.setCellValueFactory(f -> f.getValue().typeProperty());
        } catch (SQLException ex)
        {
            Logger.getLogger(Utilisateur_controller.class.getName()).log(Level.SEVERE, null, ex);
        }



        table1.setRowFactory( tv -> {
            TableRow<Utilisateur> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table1.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table1.getItems().get(myIndex).getKey()));

                }
            });
            return myRow;
        });



    }



}


