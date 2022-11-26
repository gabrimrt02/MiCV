package dad.micv.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.micv.model.Nacionalidad;
import dad.micv.model.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

    // Model
    private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();

    // View

    // Formulario

    @FXML
    private TextField dniTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField apellidosTextField;

    @FXML
    private DatePicker fechaNacimientoDate;

    @FXML
    private TextField direccionTextField;

    @FXML
    private TextField codigoPostalTextField;

    @FXML
    private TextField localidadTextField;

    @FXML
    private ComboBox<String> paisesCombo;

    // Lista de Nacionalidades

    @FXML
    private ListView<String> nacionalidadesList;

    @FXML
    private Button addNacionalidadButton;

    @FXML
    private Button restNacionalidadButton;

    @FXML
    private GridPane view;

    public PersonalController() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/personalView.fxml"));
            loader.setController(this);
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // BINDINGS
        personal.set(new Personal());

        dniTextField.textProperty().bindBidirectional(personal.get().identificacionProperty());
        nombreTextField.textProperty().bindBidirectional(personal.get().nombreProperty());
        apellidosTextField.textProperty().bindBidirectional(personal.get().apellidosProperty());
        fechaNacimientoDate.valueProperty().bindBidirectional(personal.get().fechaNacimientoProperty());
        direccionTextField.textProperty().bindBidirectional(personal.get().direccionpProperty());
        localidadTextField.textProperty().bindBidirectional(personal.get().localidadProperty());
        paisesCombo.getEditor().textProperty().bindBidirectional(personal.get().paisProperty());
        nacionalidadesList.itemsProperty().bindBidirectional(personal.get().nacionalidadProperty());
        codigoPostalTextField.textProperty().bindBidirectional(personal.get().codigoPostalProperty());

        paisesCombo.setItems(FXCollections.observableArrayList(loadPaises()));

        /*
         * La siguiente función se encarga de cambiar la disponibilidad del botón de
         * eliminar
         * nacionalidad, comparando con si el elemento que se encuentra seleccionado es
         * igual
         * a -1, que representa que no hay ningún elemento seleccionado.
         * 
         * Si son iguales, el botón se encuentra deshabilitado, mientras que si son
         * diferentes,
         * el botón volverá a estar activo
         */

        restNacionalidadButton.disableProperty().bind(
                nacionalidadesList.getSelectionModel().selectedIndexProperty().isEqualTo(-1));

    }

    public GridPane getView() {
        return view;
    }

    @FXML
    private void onAddNacionalidadAction(ActionEvent event) {

        List<Nacionalidad> nacionalidades = loadNacionalidades();

        ChoiceDialog<String> dialog = new ChoiceDialog<>();
        dialog.setTitle("Agregar Nacionalidad");
        dialog.setHeaderText("Elige una nacionalidad");
        dialog.setContentText("Nacionalidad: ");
        // dialog.getItems().addAll(nacionalidades);

        for (Nacionalidad n : nacionalidades) {
            dialog.getItems().add(n.getDenominacion());
        }

        Optional<String> resultado = dialog.showAndWait();

        if (resultado.isPresent())
            nacionalidadesList.getItems().add(dialog.getSelectedItem());
    }

    @FXML
    private void onRestNacionalidadAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Borra nacionalidad");
        alert.setHeaderText("¿Seguro que desea eliminar esta nacionalidad?");
        alert.setContentText(
                "Se eliminará la nacionalidad: " + nacionalidadesList.getSelectionModel().getSelectedItem());
        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            nacionalidadesList.getItems().remove(nacionalidadesList.getSelectionModel().getSelectedItem());
        }

    }

    private ArrayList<String> loadPaises() {

        ArrayList<String> paises = new ArrayList<>();
        BufferedReader bf;

        try {
            bf = new BufferedReader(new FileReader(getClass().getResource("/csv/paises.csv").getPath()));
            String linea;
            linea = bf.readLine();

            while (linea != null) {
                paises.add(linea);
                linea = bf.readLine();
            }
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return paises;

    }

    private List<Nacionalidad> loadNacionalidades() {

        List<Nacionalidad> nacionalidades = new ArrayList<>();
        BufferedReader bf;

        try {

            bf = new BufferedReader(new FileReader(getClass().getResource("/csv/nacionalidades.csv").getPath()));
            String linea;
            linea = bf.readLine();

            while (linea != null) {
                nacionalidades.add(new Nacionalidad(linea));
                linea = bf.readLine();
            }
            bf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nacionalidades;

    }

    public ObjectProperty<Personal> personalProperty() {
        return personal;
    }

    public void setPersonal(Personal nuevoPersonal) {
        personal.setValue(nuevoPersonal);
    }

    public Personal getPersonal() {
        return personal.get();
    }

    public void clear() {
    personal.get().nombreProperty().setValue(null);
    personal.get().apellidosProperty().setValue(null);
    personal.get().identificacionProperty().setValue(null);
    personal.get().fechaNacimientoProperty().setValue(null);
    personal.get().direccionpProperty().setValue(null);
    personal.get().localidadProperty().setValue(null);
    personal.get().nacionalidadProperty().clear();
    personal.get().codigoPostalProperty().setValue(null);

    paisesCombo.setValue(null);

    // Problemas al borrar el contenido

    }
}
