package co.libertadores.CRUD.Repository;

import co.libertadores.CRUD.Entity.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;
/*
* The repository for Estudiante
* */

public interface EstudianteRepository extends JpaRepositoryImplementation<co.libertadores.CRUD.Entity.Estudiante,Long> {

    /*
    * Exists by Codigo Estudiante
    * */
    boolean existsByCodigoEstudiante(Long codigoEstudiante);

    /*
    * Find by Codigo Estudiante
    * */
    Optional<Estudiante> findByCodigoEstudiante(Long codigoEstudiante);

    /*
    * Exist by Cedula
    * */
    boolean existsByCedula(String cedula);

    /*
    * Get Estudiantes paged
    * */
}
