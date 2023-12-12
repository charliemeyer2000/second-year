package edu.virginia.sde.javafx.tacos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HotDogVotesController {
    @FXML
    private Label sandwichCountLabel;

    @FXML
    private Label tacoCountLabel;

    @FXML
    private Label bothCountLabel;

    @FXML
    private Label neitherCountLabel;
    @FXML
    private RadioButton sandwichButton;
    @FXML
    private RadioButton tacoButton;
    @FXML
    private RadioButton bothButton;
    @FXML
    private RadioButton neitherButton;

    private ToggleGroup buttonGroup;

    private HotDogVotes hotDogVotes;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setHotDogVotes(HotDogVotes hotDogVotes) {
        this.hotDogVotes = hotDogVotes;
        refreshLabels();
    }

    public void initialize() {
        hotDogVotes = new HotDogVotes();

        buttonGroup = new ToggleGroup();
        sandwichButton.setToggleGroup(buttonGroup);
        tacoButton.setToggleGroup(buttonGroup);
        bothButton.setToggleGroup(buttonGroup);
        neitherButton.setToggleGroup(buttonGroup);
    }

    @FXML
    public void handleSubmitButton() {
        var toggle = (RadioButton) buttonGroup.getSelectedToggle();
        var choice = toggle.getText();
        hotDogVotes.addVote(choice);

        var service = new HotDogVotesService();
        service.save(hotDogVotes);

        refreshLabels();
    }

    private void refreshLabels() {
        sandwichCountLabel.setText(String.format("%d", hotDogVotes.getVotes("Sandwich")));
        tacoCountLabel.setText(String.format("%d", hotDogVotes.getVotes("Taco")));
        bothCountLabel.setText(String.format("%d", hotDogVotes.getVotes("Both")));
        neitherCountLabel.setText(String.format("%d", hotDogVotes.getVotes("Neither")));
    }

    @FXML
    public void handleGraphButton() {
        try {
            var fxmlLoader = new FXMLLoader(HotDogBarGraphController.class.getResource("taco-bar-graph.fxml"));
            var newScene = new Scene(fxmlLoader.load());
            var controller = (HotDogBarGraphController) fxmlLoader.getController();
            controller.setPrimaryStage(primaryStage);
            controller.setHotDogVotes(hotDogVotes);
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
