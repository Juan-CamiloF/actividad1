package co.libertadores.CRUD.Service.Exception.ExceptionTranslator;

import co.libertadores.CRUD.Service.Exception.BadRequestMessage;
import co.libertadores.CRUD.Service.Exception.EstudianteAlreadyExistsException;
import co.libertadores.CRUD.Service.Exception.EstudianteDoesNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/*
 * Exception Translator
 * */
@RestControllerAdvice
public class ExceptionTranslator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionTranslator.class);

    /*
     * Exception Estudiante already exists.
     * */
    @ExceptionHandler(value = { EstudianteAlreadyExistsException.class})
    public ResponseEntity<Object> handleEstudianteAlreadyExistsException(EstudianteAlreadyExistsException e){
        LOGGER.info("Exception = Estudiante Already Exists");
        BadRequestMessage exception =
                new BadRequestMessage("ERROR",
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(),
                        "/api/estudiante");
        return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
    }

    /*
     * Exception Estudiante does not exists.
     * */
    @ExceptionHandler(value = { EstudianteDoesNotExistsException.class})
    public ResponseEntity<Object> handleEstudianteDoesNotExistsException(EstudianteDoesNotExistsException e){
        LOGGER.info("Exception = Estudiante Does Not Exists");
        BadRequestMessage exception =
                new BadRequestMessage("ERROR",
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(),
                        "/api/estudiante/{}");
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    /*
     * Exception invalid fields.
     * */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
