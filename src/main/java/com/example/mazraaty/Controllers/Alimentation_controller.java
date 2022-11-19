package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Alimentation;
import com.example.mazraaty.Models.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
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

public class Alimentation_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex, myindex2;
    int id = -1;
    int id2 = -1;


    //changement la
    ObservableList<Alimentation> alimentations = FXCollections.observableArrayList();

    ObservableList<Produit> produits = FXCollections.observableArrayList();

    @FXML
    private BarChart<String, Float> barchart;

    @FXML
    private Button modifier_btn;

    @FXML
    private TextField search_by_id;

    @FXML
    private DatePicker date_picker;

    //changement la
    @FXML
    private TableView<Alimentation> table;

    @FXML
    private TableView<Produit> table_prod;

    @FXML
    private TableColumn<Alimentation, String> col5;

    @FXML
    private TableColumn<Alimentation, String> col3;

    @FXML
    private TableColumn<Alimentation, String> col4;

    @FXML
    private TableColumn<Alimentation, String> col1;

    @FXML
    private TableColumn<Alimentation, String> col2;

    @FXML
    private TableColumn<Produit, String> col_pro1;

    @FXML
    private TableColumn<Produit, String> col_pro2;


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
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_alimentation_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(alimentations);
    }

    @FXML
    public void table_search(ActionEvent event) {


        ResultSet rs = null;

        try {
            if (date_picker.getValue() == null) {
                String id = search_by_id.getText();

                //changement la
                alimentations.clear();

                //changement la
                pst = c.prepareStatement("select * from alimentation WHERE ID_vache = ? ");
                pst.setString(1, id);
                rs = pst.executeQuery();
            } else if (search_by_id.getText().isEmpty()) {
                String date = date_picker.getValue().toString();

                //un changement la
                alimentations.clear();

                //changement la
                pst = c.prepareStatement("select * from alimentation WHERE date_enrg = ? ");
                pst.setString(1, date);
                rs = pst.executeQuery();
            } else if (!date_picker.getValue().toString().isEmpty() && !search_by_id.getText().isEmpty()) {
                String id = search_by_id.getText();
                String date = date_picker.getValue().toString();
                //changement la
                alimentations.clear();

                //changement la
                pst = c.prepareStatement("select * from alimentation WHERE ID_vache = ? AND  date_enrg = ? ");
                pst.setString(1, id);
                pst.setString(2, date);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                Alimentation p = new Alimentation();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_enrg"));
                p.setProduit(rs.getString("produit"));
                p.setQuantité(rs.getString("quantité"));
                p.setRemarque(rs.getString("remarque"));


                //changement la
                alimentations.add(p);
            }
            //changement la
            table.setItems(alimentations);
            col1.setCellValueFactory(f -> f.getValue().idProperty());
            col5.setCellValueFactory(f -> f.getValue().dateProperty());
            col3.setCellValueFactory(f -> f.getValue().ProduitProperty());
            col4.setCellValueFactory(f -> f.getValue().quantitéProperty());
            col2.setCellValueFactory(f -> f.getValue().remarqueProperty());
            modifier_btn.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(Alimentation_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        //changement la
        alimentations.clear();
        produits.clear();
        initialize();
        modifier_btn.setDisable(false);
    }

    @FXML
    public void update(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        if (id == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error !");
            alert.setHeaderText("Sélectionnez un item !");
            alert.showAndWait();
        } else {
            //changement la
            Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/update_alimentation_infos.fxml"));

            Scene scene1 = new Scene(root1);
            primaryStage.setScene(scene1);
            primaryStage.setTitle("Modifier");
            //manage maximize, minimize and close button
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //specify modality for the new window
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            //pass id to update controller
            Pass_alimentation t = new Pass_alimentation(id, alimentations);

            primaryStage.setUserData(t);
            primaryStage.show();
        }
    }

    public void initialize() throws SQLException {
        id = -1;
        table();
        pro_table();
        chart();
    }

    public void refrech_table(ObservableList<Alimentation> alimentations) {
        try {
            alimentations.clear();

            //changement la
            pst = c.prepareStatement("select * from alimentation");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //changement la
                Alimentation p = new Alimentation();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_enrg"));
                p.setProduit(rs.getString("produit"));
                p.setQuantité(rs.getString("quantité"));
                p.setRemarque(rs.getString("remarque"));
                p.setKey(rs.getString("ID"));
                alimentations.add(p);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {


        try {

            //changement la
            pst = c.prepareStatement("select ID,date_enrg,ID_vache,produit,quantité,remarque from alimentation");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    //changement la
                    Alimentation p = new Alimentation();
                    p.setId(rs.getString("ID_vache"));
                    p.setDate(rs.getString("date_enrg"));
                    p.setProduit(rs.getString("produit"));
                    p.setQuantité(rs.getString("quantité"));
                    p.setRemarque(rs.getString("remarque"));
                    p.setKey(rs.getString("ID"));
                    //changement la
                    alimentations.add(p);

                }
            }
            //changement la
            table.setItems(alimentations);
            col1.setCellValueFactory(f -> f.getValue().idProperty());
            col5.setCellValueFactory(f -> f.getValue().dateProperty());
            col3.setCellValueFactory(f -> f.getValue().ProduitProperty());
            col4.setCellValueFactory(f -> f.getValue().quantitéProperty());
            col2.setCellValueFactory(f -> f.getValue().remarqueProperty());
        } catch (SQLException ex) {
            Logger.getLogger(Alimentation_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


        table.setRowFactory(tv -> {
            TableRow<Alimentation> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getKey()));

                }
            });
            return myRow;
        });


    }


    public void pro_table() throws SQLException {
        try {

            //changement la
            pst = c.prepareStatement("select * from stock");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    //changement la
                    Produit p = new Produit();
                    p.setID_pro(rs.getString("ID"));
                    p.setprod(rs.getString("produit"));
                    p.setQuantite(rs.getString("quantite"));
                    //changement la
                    produits.add(p);
                }
            }
            table_prod.setItems(produits);
            col_pro1.setCellValueFactory(f -> f.getValue().proProperty());
            col_pro2.setCellValueFactory(f -> f.getValue().QuantiteProperty());
        } catch (SQLException ex) {
            Logger.getLogger(Alimentation_controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        table_prod.setRowFactory(tv -> {
            TableRow<Produit> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myindex2 = table_prod.getSelectionModel().getSelectedIndex();
                    id2 = Integer.parseInt(String.valueOf(table_prod.getItems().get(myindex2).getID_pro()));
                }
            });
            return myRow;
        });


    }


    public void add(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_produit_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(alimentations);

    }


    @FXML
    void edit(ActionEvent event) {

    }


    @FXML
    void remove(ActionEvent event) throws SQLException {
        PreparedStatement st = c.prepareStatement("DELETE FROM stock WHERE ID = '" + id2 + "';");
        st.executeUpdate();
        refrech_prod();
        barchart.getData().clear();
        chart();
    }

    @FXML
    void refrech_prod() throws SQLException {
            produits.clear();
            barchart.getData().clear();
            chart();
            pro_table();
    }

    public void chart() {
        PreparedStatement pst2;
        XYChart.Series<String, Float> series1 = new XYChart.Series<String, Float>();
        series1.setName("Stock des aliments");
        try {

            //changement la
            pst = c.prepareStatement("select * from stock");
            ResultSet rs = pst.executeQuery();
            {

                while (rs.next()) {
                    Produit p = new Produit();
                    p.setprod(rs.getString("produit"));
                    p.setQuantite(rs.getString("quantite"));
                    pst2 = c.prepareStatement("SELECT SUM(quantite) AS somme FROM stock WHERE produit = '"+p.getprod()+"'");
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {
                        series1.getData().add(new XYChart.Data(p.getprod().toString(),rs2.getFloat("somme")));
                    }
                }
                barchart.getData().add(series1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}

