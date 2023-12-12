package edu.virginia.cs.threelayer.presentation;

import edu.virginia.cs.threelayer.business.BestSellersService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class GUIApplication extends Application {
    private BestSellersService service;

    @Override
    public void start(Stage stage) throws Exception {
        URL view = GUIApplication.class.getResource("/edu.virginia.cs.threelayer.presentation/nytimeslist.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(view);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
