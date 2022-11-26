package dad.micv;

import dad.micv.controller.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{
    
    public static Stage primaryStage;
    private RootController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        App.primaryStage = primaryStage;

        controller = new RootController();

        primaryStage.setTitle("Mi CV");
        primaryStage.setScene(new Scene(controller.getView()));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/cv64x64.png")));
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
