package dad.micv.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Conocimiento {

    private StringProperty denominacion = new SimpleStringProperty();
    private ObjectProperty<Nivel> nivel = new SimpleObjectProperty<>();

    public Conocimiento(String denominacion, Nivel nivel) {
        this.denominacion.set(denominacion);
        this.nivel.set(nivel);
    }

    public Conocimiento() {
        // CONSTRUCTOR VACIO
    }
    
    public StringProperty denominacionProperty() {
        return denominacion;
    }

    public ObjectProperty<Nivel> nivelProperty() {
        return nivel;
    }

    public String getDenominacion() {
        return denominacion.get();
    }

    public Nivel getNivel() {
        return nivel.get();
    }

    public void setDenominacion(String newDenominacion) {
        denominacion.set(newDenominacion);
    }

    public void setNivel(Nivel newnivel) {
        nivel.set(newnivel);
    }

}
