package dad.micv.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.micv.model.Experiencia;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;

public class NuevaExperienciaDialog extends Dialog<Experiencia> implements Initializable {

    // model

    private StringProperty denominacion = new SimpleStringProperty();
    private StringProperty empleador = new SimpleStringProperty();
    private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<>();

    // view

    @FXML
    private DatePicker desdeDate;

    @FXML
    private TextField denominacionText;

    @FXML
    private DatePicker hastaDate;

    @FXML
    private TextField empleadorText;

    @FXML
    private GridPane view;

    // contructor

    public NuevaExperienciaDialog() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/NuevaExperieniaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Experiencia onResultConverter(ButtonType button) {
        if (button.getButtonData() == ButtonData.OK_DONE) {
            Experiencia experiencia = new Experiencia();
            experiencia.setDenominacion(getDenominacion());
            experiencia.setEmpleador(getEmpleador());
            experiencia.setDesde(getDesde());
            experiencia.setHasta(getHasta());
            return experiencia;
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // init dialog

        ButtonType crearButtonType = new ButtonType("Crear", ButtonData.OK_DONE);

        setTitle("Nuevo título");
        getDialogPane().setContent(view);
        getDialogPane().getButtonTypes().setAll(crearButtonType, ButtonType.CANCEL);

        setResultConverter(this::onResultConverter);

        // bindings

        denominacion.bind(denominacionText.textProperty());
        empleador.bind(empleadorText.textProperty());
        desde.bind(desdeDate.valueProperty());
        hasta.bind(hastaDate.valueProperty());

        // disable crear button

        Button crearButton = (Button) getDialogPane().lookupButton(crearButtonType);
        crearButton.disableProperty()
                .bind(denominacion.isEmpty().or(empleador.isEmpty().or(desde.isNull().or(hasta.isNull()))));

    }

    public String getDenominacion() {
        return denominacion.get();
    }

    public String getEmpleador() {
        return empleador.get();
    }

    public LocalDate getDesde() {
        return desde.get();
    }

    public LocalDate getHasta() {
        return hasta.get();
    }

}
