package dad.micv.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.micv.model.Conocimiento;
import dad.micv.model.Nivel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;

public class NuevoConocimientoDialog extends Dialog<Conocimiento> implements Initializable {

    // model
    private StringProperty denominacion = new SimpleStringProperty();
    private ObjectProperty<Nivel> nivel = new SimpleObjectProperty<>();

    // view

    @FXML
    private TextField denominacionText;

    @FXML
    private ComboBox<Nivel> nivelesCombo;

    @FXML
    private Button resetButton;

    @FXML
    private GridPane view;

    // Constructor

    public NuevoConocimientoDialog() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/NuevoConocimientoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Conocimiento onResultConverter(ButtonType button) {
        if (button.getButtonData() == ButtonData.OK_DONE) {
            Conocimiento conocimiento = new Conocimiento();
            conocimiento.setDenominacion(getDenominacion());
            conocimiento.setNivel(getNivel());
            return conocimiento;
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // init dialog

        ButtonType crearButtonType = new ButtonType("Crear", ButtonData.OK_DONE);

        setTitle("Nuevo conocimiento");
        getDialogPane().setContent(view);
        getDialogPane().getButtonTypes().addAll(crearButtonType, ButtonType.CANCEL);

        setResultConverter(this::onResultConverter);

        // load combo

        nivelesCombo.getItems().setAll(Nivel.values());

        // bindings

        denominacion.bind(denominacionText.textProperty());
        nivel.bind(nivelesCombo.getSelectionModel().selectedItemProperty());

        // disable crear button

        Button crearButton = (Button) getDialogPane().lookupButton(crearButtonType);
        crearButton.disableProperty().bind(denominacion.isEmpty().or(nivel.isNull()));

    }

    // funciones de la view

    @FXML
    private void onResetAction(ActionEvent event) {
        nivelesCombo.setValue(null);
    }

    public String getDenominacion() {
        return denominacion.get();
    }

    public Nivel getNivel() {
        return nivel.get();
    }

}
