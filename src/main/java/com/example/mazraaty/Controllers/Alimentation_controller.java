package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Alimentation;
import com.example.mazraaty.Models.Produit;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void print(ActionEvent event) throws IOException, SQLException, DocumentException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        LocalDateTime now = LocalDateTime.now();

        String hash_name =hashCode()+"-"+dtf.format(now);
        String file_name = "src/main/resources/PDF/Alimentation-"+hash_name+".pdf";
        Document document = new Document();

        PdfWriter.getInstance(document,new FileOutputStream(file_name));
        document.open();
        //add image
        Image img = Image.getInstance("src/main/resources/images/logo.png");
        document.add(img);

        //add paragraph
        Font f=new Font(Font.FontFamily.TIMES_ROMAN,25.0f,Font.UNDERLINE, BaseColor.RED);
        String p = "Stock Records : ";
        Paragraph para = new Paragraph(p,f);
        document.add(para);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("ID"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Produit"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantuté(Kg)"));
        table.addCell(c1);

        table.setHeaderRows(1);


        //changement la
        pst = c.prepareStatement("select * from stock");
        ResultSet rs = pst.executeQuery();
        {
            while (rs.next()) {
                table.addCell(rs.getString("ID"));
                table.addCell(rs.getString("produit"));
                table.addCell(rs.getString("quantite"));
            }
        }
        document.add(table);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        //add paragraph
        String p1 = "Stock Records : ";
        para = new Paragraph(p,f);
        document.add(para);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        table = new PdfPTable(5);
        c1 = new PdfPCell(new Phrase("ID"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Produit"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantite(Kg)"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Remarque"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date d'enregistrement"));
        table.addCell(c1);

        table.setHeaderRows(1);
        //changement la
        pst = c.prepareStatement("select * from alimentation ");
        rs = pst.executeQuery();
        if(rs.next()){
            table.addCell(rs.getString("ID_vache"));
            table.addCell(rs.getString("produit"));
            table.addCell(rs.getString("quantité"));
            table.addCell(rs.getString("remarque"));
            table.addCell(rs.getString("date_enrg"));
        }
        document.add(table);

        document.close();
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
        new Stage_controller().profil_img(photo);
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
            pst = c.prepareStatement("select * from stock_disp");
            ResultSet rs = pst.executeQuery();
            {

                while (rs.next()) {
                    Produit p = new Produit();
                    p.setprod(rs.getString("produit"));
                    p.setQuantite(rs.getString("quantite"));
                    series1.getData().add(new XYChart.Data(p.getprod(),Float.parseFloat(p.getQuantite())));

                }
                barchart.getData().add(series1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}

