package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Vaccine;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.mazraaty.Main.c;

public class Dashboard_controller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    PreparedStatement pst;

    @FXML
    private Label date;

    @FXML
    private LineChart<Integer, Integer> linechart;

    @FXML
    private Circle photo;

    @FXML
    private PieChart piechart;

    @FXML
    private BarChart<String, Float> barchart;

    @FXML
    private Label pro_label;

    @FXML
    private Label emp_label;

    @FXML
    private Label tot_label;

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

    public void close_btn(ActionEvent event) throws IOException {
        new Stage_controller().close_btn(event);
    }

    public void log_out(ActionEvent event) throws IOException {
        new Stage_controller().log_out(event);

    }

    public void print(ActionEvent event) throws IOException, DocumentException {

        System.out.println("not working here !");

    }

    public void initialize() throws SQLException {
        new Stage_controller().profil_img(photo);
        init_linechart();
        init_piechart();
        init_statics();
        new Stage_controller().init_date(date);
        new Alimentation_controller().chart(this.barchart);

    }

    public void init_linechart() throws SQLException {
        Float s = null;
        XYChart.Series series = new XYChart.Series();
        series.setName("Production par mois");
        LocalDateTime d = LocalDateTime.now();
        for (int j = 1; j <= d.getMonthValue() ; j++) {
            if (j<10) {
                pst = c.prepareStatement("SELECT SUM(litres) FROM production WHERE date_enrg LIKE '2022-0" + j + "%'; ");

            }else{
                pst = c.prepareStatement("SELECT SUM(litres) FROM production WHERE date_enrg LIKE '2022-" + j + "%'; ");
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                s =rs.getFloat(1);

            }
            series.getData().add(new XYChart.Data(String.valueOf(j),s));
        }
        linechart.getData().add(series);

    }

    public void init_piechart(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("type1",60),
                        new PieChart.Data("type2",4),
                        new PieChart.Data("type3",10),
                        new PieChart.Data("type4",26)
                );
        piechart.setData(pieChartData);
        piechart.setTitle("Percentage of cows type");
    }

    public void init_statics() throws SQLException {
        pst = c.prepareStatement("SELECT COUNT(ID_vache) FROM vaches AS nb_vaches");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            tot_label.setText(rs.getString(1));
        }
        pst = c.prepareStatement("SELECT SUM(litres) FROM `production`");
        rs = pst.executeQuery();
        while (rs.next()) {
            pro_label.setText(rs.getString(1));
        }
        pst = c.prepareStatement("SELECT COUNT(ID) FROM `utilisateur`");
        rs = pst.executeQuery();
        while (rs.next()) {
            emp_label.setText(rs.getString(1));
        }






    }


}
