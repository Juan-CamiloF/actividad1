package co.libertadores.CRUD.Controller;

import co.libertadores.CRUD.Entity.Estudiante;
import co.libertadores.CRUD.Service.DTO.EstudianteDTO;
import co.libertadores.CRUD.Service.EstudianteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
 *  REST controller for managing {@Link  co.libertadores.CRUD.Entity.Estudiante } Estudiante
 * */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class EstudianteController {

    /*
     * Logger class
     * */
    private static final Logger LOGGER = LoggerFactory.getLogger(EstudianteController.class);

    /*
     * Services
     * */
    public EstudianteService estudianteService;

    /*
     * Autowired for services
     */
    @Autowired
    public EstudianteController(final EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    /**
     * {@code POST/Estudiante}: Create a new Estuadiante.
     *
     * @param estudianteDTO the Estudiante to create.
     * @return the {@link ResponseEntity} with status {@code 201(Created)} and with body the new Estudiante,
     * or with status {@code 400(Bad request)} if the Estudiante is invalid.
     */
    @RequestMapping(value = "/estudiante", method = RequestMethod.POST)
    public ResponseEntity<Estudiante> createEstudiante(@RequestBody @Valid EstudianteDTO estudianteDTO) {
        LOGGER.info("Received request to create the {}", estudianteDTO);
        Estudiante newEstudiante = new Estudiante(estudianteDTO);
        Estudiante estudiante = estudianteService.save(newEstudiante);
        return ResponseEntity.ok().body(estudiante);
    }

    /**
     * {@code GET/Estudiante}: Get a Estudiante.
     *
     * @param codigoEstudiante of Estudiante
     * @return the {@link ResponseEntity} with status {@code 200(Accept)} and with body the Estudiante,
     * or with status {@code 404(Not found)} if the codigoEstudiante is not found.
     */
    @RequestMapping(value = "/estudiante/{codigoEstudiante}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Estudiante>> getEstudiante(@PathVariable Long codigoEstudiante) {
        LOGGER.info("Received request to get Estudiante by id: {}", codigoEstudiante);
        Optional<Estudiante> estudiante = estudianteService.getOne(codigoEstudiante);
        return ResponseEntity.ok().body(estudiante);
    }

    /**
     * {@code GET/Estudiante}: Get Estudiantes paged.
     *
     * @return the {@link ResponseEntity} with status {@code 200(Accept)} and the list of Estudiantes paged.
     */
    @RequestMapping(value = "/estudiantespages", method = RequestMethod.GET)
    public ResponseEntity<Page<Estudiante>> getEstudiantesPaged(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        LOGGER.info("Received request to get EstudiantesPaged");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Estudiante> pageEstudiante = estudianteService.getAllPaged(pageable);
        return ResponseEntity.ok().body(pageEstudiante);
    }

    /*
     * {@code GET/Estudiante}: Get Estudiantes.
     *
     * @return the {@Link responseEntity} with status {@code 200(Accept)} and the list of Estudiantes.
     * */
    @RequestMapping(value = "/estudiantes", method = RequestMethod.GET)
    public ResponseEntity<List<Estudiante>> getEstudiantes() {
        LOGGER.info("Received request to get Estudiante");
        List<Estudiante> estudiantes = estudianteService.getAll();
        return ResponseEntity.ok().body(estudiantes);
    }

    /**
     * {@code DELETE/Estudiante}: Delete Estudiante.
     *
     * @return the {@Link responseEntity} with status {@code 200(Accept)}
     */
    @RequestMapping(value = "/estudiante/{codigoEstudiante}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEstudiante(@PathVariable Long codigoEstudiante) {
        LOGGER.info("Received request to delete Estudiante with codigoEstudiante {}", codigoEstudiante);
        return ResponseEntity.ok().body(estudianteService.delete(codigoEstudiante));
    }

    /**
     * {@code PUT/Estudiante}: udpate Estudiante.
     *
     * @param codigoEstudiante of Estudiante
     * @return the {@Link responseEntity} with statuc {@code 200(Accept)}
     */
    @RequestMapping(value = "/estudiante/{codigoEstudiante}", method = RequestMethod.PUT)
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Long codigoEstudiante,
                                                       @RequestBody EstudianteDTO estudianteDTO) {
        LOGGER.info("Received request to update Estudiante with codigoEstudiante {}", codigoEstudiante);
        Estudiante estudiante = estudianteService.
                update(codigoEstudiante, estudianteDTO);
        return ResponseEntity.ok().body(estudiante);
    }


}
