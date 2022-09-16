package co.libertadores.CRUD.Service.DTO;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/*
* DTO of Estudiante
* */
public class EstudianteDTO {

    @NotNull
    private Long codigoEstudiante;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    @Size(min = 10 , max = 10)
    private String celular;

    @NotBlank
    private String cedula;

    //Generate Get and Set

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

    /*
    * Method toString
    * */

    @Override
    public String toString() {
        return "EstudianteDTO{" +
                "codigoEstudiante=" + codigoEstudiante +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", celular='" + celular + '\'' +
                ", cedula='" + cedula + '\'' +
                '}';
    }
}
