package dad.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.micv.model.Titulo;
import dad.micv.ui.NuevoTituloDialog;
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

public class FormacionController implements Initializable {

    // MODEL
    private ListProperty<Titulo> listaTitulos = new SimpleListProperty<>();

    // VIEW

    @FXML
    private TableView<Titulo> titulosTable;

    @FXML
    private TableColumn<Titulo, String> denominacionColumn;

    @FXML
    private TableColumn<Titulo, LocalDate> desdeColumn;

    @FXML
    private TableColumn<Titulo, LocalDate> hastaColumn;

    @FXML
    private TableColumn<Titulo, String> organizadorColumn;

    @FXML
    private Button addTituloButton;

    @FXML
    private Button removeTituloButton;

    @FXML
    private BorderPane view;

    public FormacionController() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/formacionView.fxml"));
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

        // BINDINGS
        // desdeColumn.cellValueFactoryProperty().bindBidirectional();
        listaTitulos.bindBidirectional(titulosTable.itemsProperty());

    }

    @FXML
    private void onAddTituloAction(ActionEvent event) {

        NuevoTituloDialog dialog = new NuevoTituloDialog();

        Optional<Titulo> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {

            String denominacion = dialog.getDenominacion();
            String organizador = dialog.getOrganizador();
            LocalDate desde = dialog.getDesde();
            LocalDate hasta = dialog.getHasta();

            Titulo nuevoTitulo = new Titulo(denominacion, organizador, desde, hasta);
            titulosTable.getItems().add(nuevoTitulo);

            for (Titulo t : titulosTable.getItems()) {

                denominacionColumn.setCellValueFactory(v -> t.denominacionProperty());
                organizadorColumn.setCellValueFactory(v -> t.organizadorProperty());
                desdeColumn.setCellValueFactory(v -> t.desdeProperty());
                hastaColumn.setCellValueFactory(v -> t.hastaProperty());

            }
        }
    }

    @FXML
    private void onRemoveTituloAction(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar titulo");
        alert.setHeaderText("¿Seguro que desea eliminar este tiutlo?");
        alert.setContentText(
                "Se eliminará el siguiente titulo: " + titulosTable.getSelectionModel().getSelectedItem().getDenominacion());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            titulosTable.getItems().remove(titulosTable.getSelectionModel().getSelectedItem());
        }
    }

    public void clear() {
        listaTitulos.clear();
    }

}
