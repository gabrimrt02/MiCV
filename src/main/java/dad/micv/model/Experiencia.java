package dad.micv.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Experiencia {
    
    private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<>();
    
    private StringProperty denominacion = new SimpleStringProperty();
    private StringProperty empleador = new SimpleStringProperty();

    // GETTERS DE LAS PROPERTIES

    public ObjectProperty<LocalDate> desdeProperty() {
        return desde;
    }

    public ObjectProperty<LocalDate> hastaProperty() {
        return hasta;
    }

    public StringProperty denominacionProperty() {
        return denominacion;
    }

    public StringProperty empleadorProperty() {
        return empleador;
    }

    // GETTERS Y SETTERS DE LOS VALORES DE LAS PROPERTIES

    public LocalDate getDesde() {
        return desde.get();
    }

    public void setDesde(LocalDate newDesde) {
        desde.set(newDesde);
    }

    public LocalDate getHasta() {
        return hasta.get();
    }

    public void setHasta(LocalDate newHasta) {
        desde.set(newHasta);
    }

    public String getDenominacion() {
        return denominacion.get();
    }

    public void setDenominacion(String newDenominacion) {
        denominacion.set(newDenominacion);
    }

    public String getEmpleador() {
        return empleador.get();
    }

    public void setEmpleador(String newEmpleador) {
        empleador.set(newEmpleador);
    }

}
