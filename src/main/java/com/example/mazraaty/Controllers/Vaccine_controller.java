package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Vaccine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
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

public class Vaccine_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int id = -1;


    //changement la
    ObservableList<Vaccine> vaccines = FXCollections.observableArrayList();


    @FXML
    private Button modifier_btn;

    @FXML
    private TextField search_by_id;

    @FXML
    private DatePicker date_picker;

    //changement la
    @FXML
    private TableView<Vaccine> table;

    @FXML
    private TableColumn<Vaccine, String> col5;

    @FXML
    private TableColumn<Vaccine, String> col3;

    @FXML
    private TableColumn<Vaccine, String> col4;

    @FXML
    private TableColumn<Vaccine, String> col1;

    @FXML
    private TableColumn<Vaccine, String> col2;


    @FXML
    private Circle photo;

    public void tableaudeboard(ActionEvent event) throws IOException {
        new Stage_controller().tableaudeboard(event);
    }

    public void utilisateur(ActionEvent event) throws IOException {
        new Stage_controller().utilisateur(event);
    }

    public void vaccine(ActionEvent event) throws IOException {
        new Stage_controller().vaccine(event);
    }

    public void vente_vache(ActionEvent event) throws IOException {
        new Stage_controller().vente_vache(event);
    }

    public void categorie_vache(ActionEvent event) throws IOException {
        new Stage_controller().categorie_vache(event);
    }

    public void alimentation(ActionEvent event) throws IOException {
        new Stage_controller().alimentation(event);
    }

    public void production(ActionEvent event) throws IOException {
        new Stage_controller().production(event);
    }

    public void vente_lait(ActionEvent event) throws IOException {
        new Stage_controller().vente_lait(event);
    }

    public void vache(ActionEvent event) throws IOException {
        new Stage_controller().vache(event);
    }

    public void settings(ActionEvent event) throws IOException {
        new Stage_controller().settings(event);
    }

    public void search(ActionEvent event) throws IOException {
        new Stage_controller().search(event);
    }

    public void print(ActionEvent event) throws IOException {
        System.out.println("this function is not working yet !!");
    }

    public void close_btn(ActionEvent event) throws IOException {
        new Stage_controller().close_btn(event);
    }

    public void log_out(ActionEvent event) throws IOException {
        new Stage_controller().log_out(event);

    }

    @FXML
    void add_infos(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_vaccin_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(vaccines);
    }

    @FXML
    public void table_search(ActionEvent event){


        ResultSet rs=null;

        try {
            if(date_picker.getValue() == null){
                String id = search_by_id.getText();

                //changement la
                vaccines.clear();

                //changement la
                pst = c.prepareStatement("select ID_vache,date_enrg,vaccine,remarque,date_next_vacc from vaccine WHERE ID_vache = ?");
                pst.setString(1, id);
                rs = pst.executeQuery();
            }else if(search_by_id.getText().isEmpty()){
                String  date =date_picker.getValue().toString();

                //un changement la
                vaccines.clear();

                //changement la
                pst = c.prepareStatement("select ID_vache,date_enrg,vaccine,remarque,date_next_vacc from vaccine WHERE date_enrg = ? ");
                pst.setString(1, date);
                rs = pst.executeQuery();
            }
            else if (!date_picker.getValue().toString().isEmpty()&& !search_by_id.getText().isEmpty()){
                String id = search_by_id.getText();
                String  date =date_picker.getValue().toString();
                //changement la
                vaccines.clear();

                //changement la
                pst = c.prepareStatement("select ID_vache,date_enrg,vaccine,remarque,date_next_vacc from vaccine WHERE ID_vache = ? AND  date_enrg = ? ");
                pst.setString(1, id);
                pst.setString(2, date);
                rs = pst.executeQuery();
            }
            long k = 0;
            float p_t = 0;
            while (rs.next()) {
                Vaccine p = new Vaccine();
                p.setID_vache(rs.getString("ID_vache"));
                p.setDate_enrg(rs.getString("date_enrg"));
                p.setVaccine(rs.getString("vaccine"));
                p.setRemarque(rs.getString("remarque"));
                p.setdate_next_vacc(rs.getString("date_next_vacc"));

                //changement la
                vaccines.add(p);
            }
            //changement la
            table.setItems(vaccines);
            col1.setCellValueFactory(f -> f.getValue().ID_vacheProperty());
            col5.setCellValueFactory(f -> f.getValue().date_next_vaccProperty());
            col3.setCellValueFactory(f -> f.getValue().date_enrgProperty());
            col4.setCellValueFactory(f -> f.getValue().remarqueProperty());
            col2.setCellValueFactory(f -> f.getValue().vaccineProperty());
            modifier_btn.setDisable(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(Vaccine_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        //changement la
        vaccines.clear();
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
            Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/update_vaccin_infos.fxml"));

            Scene scene1 = new Scene(root1);
            primaryStage.setScene(scene1);
            primaryStage.setTitle("Modifier");
            //manage maximize, minimize and close button
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //specify modality for the new window
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            //pass id to update controller
            Pass_vaccine t = new Pass_vaccine(id, vaccines);

            primaryStage.setUserData(t);
            primaryStage.show();
        }
    }

    public void initialize() throws SQLException {
        new Stage_controller().profil_img(photo);
        id = -1;
        table();
    }

    public void refrech_table(ObservableList<Vaccine> vaccines){
        try{
            vaccines.clear();

            //changement la
            pst = c.prepareStatement("select * from vaccine");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //changement la
                Vaccine p = new Vaccine();
                p.setID_vache(rs.getString("ID_vache"));
                p.setDate_enrg(rs.getString("date_enrg"));
                p.setVaccine(rs.getString("vaccine"));
                p.setRemarque(rs.getString("remarque"));
                p.setdate_next_vacc(rs.getString("date_next_vacc"));
                vaccines.add(p);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {

        try {

            //changement la
            pst = c.prepareStatement("select ID,ID_vache,date_enrg,vaccine,remarque,date_next_vacc from vaccine");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    //changement la
                    Vaccine p = new Vaccine();
                    p.setID_vache(rs.getString("ID_vache"));
                    p.setDate_enrg(rs.getString("date_enrg"));
                    p.setVaccine(rs.getString("vaccine"));
                    p.setRemarque(rs.getString("remarque"));
                    p.setdate_next_vacc(rs.getString("date_next_vacc"));
                    p.setKey(rs.getString("ID"));
                    //changement la
                    vaccines.add(p);

                }
            }
            //changement la
            table.setItems(vaccines);
            col1.setCellValueFactory(f -> f.getValue().ID_vacheProperty());
            col5.setCellValueFactory(f -> f.getValue().date_next_vaccProperty());
            col3.setCellValueFactory(f -> f.getValue().date_enrgProperty());
            col4.setCellValueFactory(f -> f.getValue().remarqueProperty());
            col2.setCellValueFactory(f -> f.getValue().vaccineProperty());
        } catch (SQLException ex)
        {
            Logger.getLogger(Vaccine_controller.class.getName()).log(Level.SEVERE, null, ex);
        }



        table.setRowFactory( tv -> {
            TableRow<Vaccine> myRow = new TableRow<>();
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

