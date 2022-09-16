package co.libertadores.CRUD.Service;


/*
* Estudiante Services interface
* */

import co.libertadores.CRUD.Entity.Estudiante;
import co.libertadores.CRUD.Service.DTO.EstudianteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    /*
    * Save a Estudiante
    * */
    Estudiante save(Estudiante estudiante);

    /*
    * List Estudiante paged
    * */
    Page<Estudiante> getAllPaged(Pageable pageable);

    /*
    * List Estudiante
    * */
    List<Estudiante> getAll();

    /*
    * Get a Estudiante
    * */
    Optional<Estudiante> getOne(Long codigoEstudiante);

    /*
    * Update a Estudiante
    * */
    Estudiante update(Long codigoEstudiante, EstudianteDTO estudianteDTO);

    /*
    * Delete af Estudiante
    * */
    String delete(Long codigoEstudiante);

}
