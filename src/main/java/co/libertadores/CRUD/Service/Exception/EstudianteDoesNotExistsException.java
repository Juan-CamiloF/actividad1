package co.libertadores.CRUD.Service.Exception;

public class EstudianteDoesNotExistsException extends RuntimeException{
    public EstudianteDoesNotExistsException(String message){
        super(message);
    }
}
