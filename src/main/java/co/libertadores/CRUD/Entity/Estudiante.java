package co.libertadores.CRUD.Entity;

import co.libertadores.CRUD.Service.DTO.EstudianteDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/*
*  A Student
* */

@Entity
@Table(name = "estudiantes")
public class Estudiante implements Serializable {
    @Id
    private Long codigoEstudiante;

    @Column
    private String nombres;

    @Column
    private String apellidos;

    @Column
    private String celular;

    @Column
    private String cedula;



    //Constructors
    public Estudiante() {
    }

    public Estudiante(EstudianteDTO estudiante){
        this.codigoEstudiante = estudiante.getCodigoEstudiante();
        this.nombres = estudiante.getNombres();
        this.apellidos = estudiante.getApellidos();
        this.celular = estudiante.getCelular();
        this.cedula = estudiante.getCedula();
    }

    //Generate Get ang Set

    public Long getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(Long codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    //Generate equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(codigoEstudiante, that.codigoEstudiante) && Objects.equals(nombres, that.nombres) && Objects.equals(apellidos, that.apellidos) && Objects.equals(celular, that.celular) && Objects.equals(cedula, that.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoEstudiante, nombres, apellidos, celular, cedula);
    }


    //Generate toString

    @Override
    public String toString() {
        return "Estudiante{" +
                "codigoEstudiante=" + codigoEstudiante +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", celular='" + celular + '\'' +
                ", cedula='" + cedula + '\'' +
                '}';
    }
}
