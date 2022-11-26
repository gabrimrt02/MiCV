package dad.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmailModel {
    
    private StringProperty direccion = new SimpleStringProperty();

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String newDireccion) {
        direccion.set(newDireccion);
    }

}
