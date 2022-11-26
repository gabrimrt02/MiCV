package dad.micv.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import dad.micv.App;
import dad.micv.model.Personal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RootController implements Initializable {

    // CONTROLLERS
    private PersonalController personal = new PersonalController();
    private ContactoController contacto = new ContactoController();
    private FormacionController formacion = new FormacionController();
    private ExperienciaController experiencia = new ExperienciaController();
    private ConocimientoController conocimiento = new ConocimientoController();

    // VIEW

    // TABS

    @FXML
    private Tab personalTab;

    @FXML
    private Tab contactoTab;

    @FXML
    private Tab formacionTab;

    @FXML
    private Tab experienciaTab;

    @FXML
    private Tab conocimientosTab;

    // MENU

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem nuevoMenuItem;

    @FXML
    private MenuItem abrirMenuItem;

    @FXML
    private MenuItem guardarMenuItem;

    @FXML
    private BorderPane view;

    // CONSTRUCTOR
    public RootController() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rootView.fxml"));
            loader.setController(this);
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BorderPane getView() {
        return view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        personalTab.setContent(personal.getView());
        contactoTab.setContent(contacto.getView());
        formacionTab.setContent(formacion.getView());
        experienciaTab.setContent(experiencia.getView());
        conocimientosTab.setContent(conocimiento.getView());

        // Bindings

    }

    @FXML
    private void onNuevoAction(ActionEvent event) {

        personal.clear();
        contacto.clear();
        formacion.clear();
        experiencia.clear();
        conocimiento.clear();

    }

    @FXML
    private void onAbrirAction(ActionEvent event) {

        // FileChooser fileChooser = new FileChooser();

        // File fichero = fileChooser.showOpenDialog(App.primaryStage);

        /*
         * TODO
         * 
         * Implementar el completado de los campos con los datos que provengan de un
         * fichero en el que se almacene un cv
         */

    }

    @FXML
    private void onGuardarAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichero Curriculum Vitae", ".cv"));

        File ficheroGuardado = fileChooser.showSaveDialog(App.primaryStage);

        /*
         * TODO
         * 
         * Implementar el guardado de los campos en un fichero que pueda almacenar los
         * datos de un CV
         */

        Gson gson = FxGson.create();

        String json = gson.toJson(personal);

        System.out.println(json);
        
    }

    @FXML
    private void onGuardarComoAction(ActionEvent event) {

        // FileChooser fileChooser = new FileChooser();

        // fileChooser.showSaveDialog(App.primaryStage);

        // onGuardarAction(event);

    }

    @FXML
    private void onSalirAction(ActionEvent event) {

        App.primaryStage.close();

    }

}
