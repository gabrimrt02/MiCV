package dad.micv.model;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Personal {
    
    private StringProperty identificacion = new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty apellidos = new SimpleStringProperty();
    private ObjectProperty<LocalDate> fechaNacimiento = new SimpleObjectProperty<>();
    private StringProperty direccion = new SimpleStringProperty();
    private StringProperty codigoPostal = new SimpleStringProperty();
    private StringProperty localidad = new SimpleStringProperty();
    private StringProperty pais = new SimpleStringProperty();
    private ListProperty<String> nacionalidad = new SimpleListProperty<>(FXCollections.observableArrayList());

    // Getters de las properties
    
    public StringProperty identificacionProperty() {
        return identificacion;
    }
    
    public StringProperty nombreProperty() {
        return nombre;
    }
    
    public StringProperty apellidosProperty() {
        return apellidos;
    }
    
    public ObjectProperty<LocalDate> fechaNacimientoProperty() {
        return fechaNacimiento;
    }
    
    public StringProperty direccionpProperty() {
        return direccion;
    }
    
    public StringProperty codigoPostalProperty() {
        return codigoPostal;
    }
    
    public StringProperty localidadProperty() {
        return localidad;
    }
    
    public StringProperty paisProperty() {
        return pais;
    }
    
    public ListProperty<String> nacionalidadProperty() {
        return nacionalidad;
    }


    // Getters de los valores
    
    public String getIdentificacion() {
        return identificacion.get();
    }
    
    public String getNombre() {
        return nombre.get();
    }
    
    public String getApellidos() {
        return apellidos.get();
    }
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento.get();
    }
    
    public String getDireccion() {
        return direccion.get();
    }
    
    public String getCodigoPostal() {
        return codigoPostal.get();
    }
    
    public String getLocalidad() {
        return localidad.get();
    }
    
    public String getPais() {
        return pais.get();
    }
    
    public Nacionalidad[] getNacionalidad() {
        return (Nacionalidad[]) (nacionalidad.toArray());
    }

    // Setters de los valores

    public void setIdentificacion(String identificacion) {
        this.identificacion.set(identificacion);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }
    
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento.set(fechaNacimiento);
    }
    
    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }
    
    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }
    
    public void setPais(String pais) {
        this.pais.set(pais);
    }
    
    public void setNacionalidad(String newNacionalidad) {
        nacionalidad.add(new Nacionalidad(newNacionalidad).getDenominacion());
    }

}
