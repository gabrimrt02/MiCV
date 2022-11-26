package dad.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.micv.App;
import dad.micv.model.EmailModel;
import dad.micv.model.Telefono;
import dad.micv.model.TipoTelefono;
import dad.micv.model.WebModel;
import dad.micv.ui.NuevoTelefonoDialog;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public class ContactoController implements Initializable {

    // MODEL
    private ListProperty<Telefono> listaTelefonos = new SimpleListProperty<>();
    private ListProperty<EmailModel> listaEmails = new SimpleListProperty<>();
    private ListProperty<WebModel> listaWebs = new SimpleListProperty<>();

    // VIEW

    // TELEFONO

    @FXML
    private TableView<Telefono> telefonoList;

    @FXML
    private TableColumn<Telefono, TipoTelefono> tipoTelefonoColumn;

    @FXML
    private TableColumn<Telefono, String> numeroTelefonoColumn;

    @FXML
    private Button addTelefonoButton;

    @FXML
    private Button removeTelefonoButton;

    // EMAIL

    @FXML
    private TableView<EmailModel> emailList;

    @FXML
    private TableColumn<EmailModel, String> emailColumn;

    @FXML
    private Button addEmailButton;

    @FXML
    private Button removeEmailButton;

    // WEB

    @FXML
    private TableView<WebModel> webList;

    @FXML
    private TableColumn<WebModel, String> urlColumn;

    @FXML
    private Button addWebButton;

    @FXML
    private Button removeWebButton;

    // VIEW

    @FXML
    private SplitPane view;

    // CONSTRUCTOR

    public ContactoController() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/contactoView.fxml"));
            loader.setController(this);
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // BINDINGS

        // TELEFONO
        // numeroTelefonoColumn.textProperty().bindBidirectional(telefonoModel.numeroProperty());
        // tipoTelefonoColumn.textProperty().bind();
        listaTelefonos.bindBidirectional(telefonoList.itemsProperty());

        // EMAIL
        // emailColumn.textProperty().bindBidirectional(emailModel.direccionProperty());
        listaEmails.bindBidirectional(emailList.itemsProperty());

        // WEB
        // urlColumn.textProperty().bindBidirectional(webModel.urlProperty());
        listaWebs.bindBidirectional(webList.itemsProperty());

    }

    public SplitPane getView() {
        return view;
    }

    // FUNCIONES DE LA VIEW

    // TELEFONO

    @FXML
    private void onAddTelefonoAction(ActionEvent event) {

        NuevoTelefonoDialog dialog = new NuevoTelefonoDialog();

        Optional<Telefono> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {

            Telefono nuevoTelefono = new Telefono();
            nuevoTelefono.setNumero(dialog.getNumero());
            nuevoTelefono.setTipoTelefono(dialog.getTipo());

            telefonoList.getItems().add(nuevoTelefono);

            for (Telefono t : telefonoList.getItems()) {
                numeroTelefonoColumn.setCellValueFactory(v -> t.numeroProperty());
                tipoTelefonoColumn.setCellValueFactory(v -> t.tipoTelefonoProperty());
            }

        }

    }

    @FXML
    private void onRemoveTelefonoAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Borra teléfono");
        alert.setHeaderText("¿Seguro que desea eliminar este número de teléfono?");
        alert.setContentText(
                "Se eliminará el número de teléfono: " + telefonoList.getSelectionModel().getSelectedItem().toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            telefonoList.getItems().remove(telefonoList.getSelectionModel().getSelectedItem());
        }
    }

    // EMAIL

    @FXML
    private void onAddEmailAction(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo E-Mail");
        dialog.setHeaderText("Crear una nueva dirección de correo.");
        dialog.setContentText("E-mail: ");

        // AÑADIR ICONO
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.primaryStage.getIcons());

        Optional<String> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            EmailModel email = new EmailModel();
            email.setDireccion(dialog.getEditor().getText());
            emailList.getItems().add(email);

            for (EmailModel e : emailList.getItems()) {
                emailColumn.setCellValueFactory(v -> e.direccionProperty());
            }
        }

    }

    @FXML
    private void onRemoveEmailAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Borra email");
        alert.setHeaderText("¿Seguro que desea eliminar esta dirección de correo?");
        alert.setContentText(
                "Se eliminará la dirección de correo: " + emailList.getSelectionModel().getSelectedItem().getDireccion());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            emailList.getItems().remove(emailList.getSelectionModel().getSelectedItem());
        }
    }

    // WEB

    @FXML
    private void onAddWebAction(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog("http://");
        dialog.setTitle("Nueva web");
        dialog.setHeaderText("Crear una nueva dirección web.");
        dialog.setContentText("URL: ");

        // AÑADIR ICONO
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.primaryStage.getIcons());

        Optional<String> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            WebModel web = new WebModel();
            web.setUrl(dialog.getEditor().getText());
            ;
            webList.getItems().add(web);

            for (WebModel w : webList.getItems()) {
                urlColumn.setCellValueFactory(v -> w.urlProperty());
            }
        }

    }

    @FXML
    private void onRemoveWebAction(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Borra web");
        alert.setHeaderText("¿Seguro que desea eliminar esta web?");
        alert.setContentText(
                "Se eliminará la web: " + webList.getSelectionModel().getSelectedItem().getUrl());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            telefonoList.getItems().remove(telefonoList.getSelectionModel().getSelectedItem());
        }
    }

    public void clear() {
        listaTelefonos.clear();
        listaEmails.clear();
        listaWebs.clear();
    }

}
