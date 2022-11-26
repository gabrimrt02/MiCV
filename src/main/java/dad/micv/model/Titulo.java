package dad.micv.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Titulo {
    
    private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<>();
    
    private StringProperty denominacion = new SimpleStringProperty();
    private StringProperty organizador = new SimpleStringProperty();

    public Titulo(String denominacion, String organizador, LocalDate desde, LocalDate hasta) {

        this.denominacion.set(denominacion);
        this.organizador.set(organizador);
        this.desde.set(desde);
        this.hasta.set(hasta);

    }

    public Titulo() {
        // CONSTRUCTOR VACIO
    }

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

    public StringProperty organizadorProperty() {
        return organizador;
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

    public String getOrganizador() {
        return organizador.get();
    }

    public void setOrganizador(String newOrganizador) {
        organizador.set(newOrganizador);
    }

}
