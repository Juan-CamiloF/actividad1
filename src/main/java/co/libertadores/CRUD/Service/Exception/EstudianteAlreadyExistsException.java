package co.libertadores.CRUD.Service.Exception;

public class EstudianteAlreadyExistsException extends RuntimeException {
    public EstudianteAlreadyExistsException(String message){
        super(message);
    }
}
