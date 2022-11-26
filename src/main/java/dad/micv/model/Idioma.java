package dad.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Idioma extends Conocimiento {
    
    private StringProperty denominacion = new SimpleStringProperty();
    private StringProperty certificado = new SimpleStringProperty();
    private Nivel nivel;

    public StringProperty certificadProperty() {
        return certificado;
    }

    public String getDenominacion() {
        return denominacion.get();
    }

    public void setDenominacion(String newDenominacion) {
        denominacion.set(newDenominacion);
    }

    public String getCertificado() {
        return certificado.get();
    }

    public void setCertificado(String nuevoCertificado) {
        certificado.set(nuevoCertificado);
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

}
