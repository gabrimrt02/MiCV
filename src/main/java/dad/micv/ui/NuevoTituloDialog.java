package dad.micv.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.micv.model.Titulo;
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

public class NuevoTituloDialog extends Dialog<Titulo> implements Initializable {

    // model

    private StringProperty denominacion = new SimpleStringProperty();
    private StringProperty organizador = new SimpleStringProperty();
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
    private TextField organizadorText;

    @FXML
    private GridPane view;

    // contructor

    public NuevoTituloDialog() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/NuevoTituloView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Titulo onResultConverter(ButtonType button) {
        if (button.getButtonData() == ButtonData.OK_DONE) {
            Titulo titulo = new Titulo();
            titulo.setDenominacion(getDenominacion());
            titulo.setOrganizador(getOrganizador());
            titulo.setDesde(getDesde());
            titulo.setHasta(getHasta());
            return titulo;
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
        organizador.bind(organizadorText.textProperty());
        desde.bind(desdeDate.valueProperty());
        hasta.bind(hastaDate.valueProperty());

        // disable crear button

        Button crearButton = (Button) getDialogPane().lookupButton(crearButtonType);
        crearButton.disableProperty()
                .bind(denominacion.isEmpty().or(organizador.isEmpty().or(desde.isNull().or(hasta.isNull()))));

    }

    public String getDenominacion() {
        return denominacion.get();
    }

    public String getOrganizador() {
        return organizador.get();
    }

    public LocalDate getDesde() {
        return desde.get();
    }

    public LocalDate getHasta() {
        return hasta.get();
    }

}
