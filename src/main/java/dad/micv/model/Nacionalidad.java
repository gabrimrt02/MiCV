package dad.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nacionalidad {

    private StringProperty denominacion = new SimpleStringProperty();

    public Nacionalidad(String denominacion) {
        this.denominacion.set(denominacion);
    }

    public StringProperty denominacionProperty() {
        return denominacion;
    }

    public String getDenominacion() {
        return denominacion.get();
    }

}
