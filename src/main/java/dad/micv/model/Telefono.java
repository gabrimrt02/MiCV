package dad.micv.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Telefono {
    
    private StringProperty numero = new SimpleStringProperty();
    private ObjectProperty<TipoTelefono> tipoTelefono = new SimpleObjectProperty<>();

    public StringProperty numeroProperty() {
        return numero;
    }

    public ObjectProperty<TipoTelefono> tipoTelefonoProperty() {
        return tipoTelefono;
    }

    public void setNumero(String newNumero) {
        numero.set(newNumero);
    }

    public String getNumero() {
        return numero.get();
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono.set(tipoTelefono);
    }

    public TipoTelefono getTipoTelefono() {
        return tipoTelefono.get();
    }

    public String toString() {
        return getNumero() + "(" + getTipoTelefono() + ")";
    }

}
