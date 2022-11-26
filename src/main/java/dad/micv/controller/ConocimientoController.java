package dad.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.micv.model.Conocimiento;
import dad.micv.model.Idioma;
import dad.micv.model.Nivel;
import dad.micv.ui.NuevoConocimientoDialog;
import dad.micv.ui.NuevoIdiomaDialog;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;

public class ConocimientoController implements Initializable {

    // MODEL
    private ListProperty<Conocimiento> listaConocimientos = new SimpleListProperty<>();

    // VIEW

    @FXML
    private TableView<Conocimiento> conocimientosTable;

    @FXML
    private TableColumn<Conocimiento, String> denominacionColumn;

    @FXML
    private TableColumn<Conocimiento, Nivel> nivelColumn;

    @FXML
    private Button addConocimientoButton;

    @FXML
    private Button addIdiomaButton;

    @FXML
    private Button removeButton;

    @FXML
    private BorderPane view;

    // CONSTRUCTOR

    public ConocimientoController() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/conocimientosView.fxml"));
            loader.setController(this);
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // BINDINGS
        listaConocimientos.bindBidirectional(conocimientosTable.itemsProperty());

    }

    public BorderPane getView() {
        return view;
    }

    // FUNCIONES DE LA VIEW

    @FXML
    void onAddConocimientoAction(ActionEvent event) {

        NuevoConocimientoDialog dialog = new NuevoConocimientoDialog();

        Optional<Conocimiento> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {

            Conocimiento nuevoConocimiento = new Conocimiento(dialog.getDenominacion(),
                    dialog.getNivel());

            conocimientosTable.getItems().add(nuevoConocimiento);

            for (Conocimiento c : conocimientosTable.getItems()) {

                denominacionColumn.setCellValueFactory(v -> c.denominacionProperty());
                nivelColumn.setCellValueFactory(v -> c.nivelProperty());

            }

        }

    }

    @FXML
    void onAddIdiomaAction(ActionEvent event) {

        NuevoIdiomaDialog dialog = new NuevoIdiomaDialog();

        Optional<Idioma> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {

            Conocimiento nuevoConocimiento = new Conocimiento(dialog.getDenominacion(),
                    dialog.getNivel());

            conocimientosTable.getItems().add(nuevoConocimiento);

            for (Conocimiento c : conocimientosTable.getItems()) {

                denominacionColumn.setCellValueFactory(v -> c.denominacionProperty());
                nivelColumn.setCellValueFactory(v -> c.nivelProperty());

            }

        }

    }

    @FXML
    private void onRemoveAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar titulo");
        alert.setHeaderText("¿Seguro que desea eliminar este tiutlo?");
        alert.setContentText(
                "Se eliminará el siguiente titulo: " + conocimientosTable.getSelectionModel().getSelectedItem().getDenominacion());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            conocimientosTable.getItems().remove(conocimientosTable.getSelectionModel().getSelectedItem());
        }
    }

    public void clear() {
        listaConocimientos.clear();
    }

}
