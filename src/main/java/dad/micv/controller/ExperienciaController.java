package dad.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.micv.model.Experiencia;
import dad.micv.ui.NuevaExperienciaDialog;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;

public class ExperienciaController implements Initializable {

    // MODEL
    private ListProperty<Experiencia> listaExperiencias = new SimpleListProperty<>();

    // VIEW

    @FXML
    private TableView<Experiencia> experienciasTable;

    @FXML
    private TableColumn<Experiencia, String> denominacionColumn;

    @FXML
    private TableColumn<Experiencia, LocalDate> desdeColumn;

    @FXML
    private TableColumn<Experiencia, String> empleadorColumn;

    @FXML
    private TableColumn<Experiencia, LocalDate> hastaColumn;

    @FXML
    private Button addExperienciaButton;

    @FXML
    private Button removeExperienciaButton;

    @FXML
    private BorderPane view;

    // CONSTRUCTOR

    public ExperienciaController() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/experienciaView.fxml"));
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

        // BINDINGSs
        listaExperiencias.bindBidirectional(experienciasTable.itemsProperty());


    }

    @FXML
    private void onAddExperienciaAction(ActionEvent event) {

        NuevaExperienciaDialog dialog = new NuevaExperienciaDialog();

        Optional<Experiencia> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {

            String denominacion = dialog.getDenominacion();
            String empleador = dialog.getEmpleador();
            LocalDate desde = dialog.getDesde();
            LocalDate hasta = dialog.getHasta();

            Experiencia nuevaExperiencia = new Experiencia();
            nuevaExperiencia.setDenominacion(denominacion);
            nuevaExperiencia.setEmpleador(empleador);
            nuevaExperiencia.setDesde(desde);
            nuevaExperiencia.setHasta(hasta);
            
            experienciasTable.getItems().add(nuevaExperiencia);

            for (Experiencia t : experienciasTable.getItems()) {

                denominacionColumn.setCellValueFactory(v -> t.denominacionProperty());
                empleadorColumn.setCellValueFactory(v -> t.empleadorProperty());
                desdeColumn.setCellValueFactory(v -> t.desdeProperty());
                hastaColumn.setCellValueFactory(v -> t.hastaProperty());

            }
        }

    }

    @FXML
    void onRemoveExperienciaAction(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar titulo");
        alert.setHeaderText("¿Seguro que desea eliminar este tiutlo?");
        alert.setContentText(
                "Se eliminará el siguiente titulo: " + experienciasTable.getSelectionModel().getSelectedItem().getDenominacion());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            experienciasTable.getItems().remove(experienciasTable.getSelectionModel().getSelectedItem());
        }

    }

    public void clear() {
        listaExperiencias.clear();
    }

}
