package edu.virginia.sde.javafx.tacos;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class HotDogBarGraphController {
    @FXML
    public BarChart<String, Number> barChart;

    @FXML
    public NumberAxis numberAxis;

    @FXML
    public CategoryAxis categoryAxis;

    private HotDogVotes hotDogVotes;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setHotDogVotes(HotDogVotes hotDogVotes) {
        this.hotDogVotes = hotDogVotes;

        barChart.getData().clear();
        var series = new XYChart.Series<String, Number>();
        series.setName("Votes Received");
        var seriesData = series.getData();
        for (String choice : HotDogVotes.CHOICES) {
            var data = new XYChart.Data<String, Number>(choice, hotDogVotes.getVotes(choice));
            seriesData.add(data);
        }
        var chartData = barChart.getData();
        chartData.add(series);
    }

    public void handleBackButton() {
        try {
            var fxmlLoader = new FXMLLoader(HotDogBarGraphController.class.getResource("taco-vote.fxml"));
            var newScene = new Scene(fxmlLoader.load());
            var controller = (HotDogVotesController) fxmlLoader.getController();
            controller.setPrimaryStage(primaryStage);
            controller.setHotDogVotes(hotDogVotes);
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
