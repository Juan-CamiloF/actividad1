package co.libertadores.CRUD.Service.Impl;

import co.libertadores.CRUD.Entity.Estudiante;
import co.libertadores.CRUD.Repository.EstudianteRepository;
import co.libertadores.CRUD.Service.DTO.EstudianteDTO;
import co.libertadores.CRUD.Service.EstudianteService;
import co.libertadores.CRUD.Service.Exception.EstudianteAlreadyExistsException;
import co.libertadores.CRUD.Service.Exception.EstudianteDoesNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstudianteServiceImpl.class);
    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository){
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    @Transactional
    public Estudiante save(final Estudiante estudiante) {
        boolean codeExists = estudianteRepository.existsByCodigoEstudiante(estudiante.getCodigoEstudiante());
        if(codeExists)
            throw new EstudianteAlreadyExistsException("There already exists a Estudiante with codigo=%s"+ estudiante.getCodigoEstudiante());
        boolean cedulaExists = estudianteRepository.existsByCedula(estudiante.getCedula());
        if(cedulaExists){
            throw new EstudianteAlreadyExistsException("There already exists a Estudiante with cedula=%s"+ estudiante.getCedula());
        }
        return estudianteRepository.save(estudiante);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Estudiante> getAllPaged(Pageable pageable){
        return estudianteRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Estudiante> getAll() {
        return estudianteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Estudiante> getOne(Long codigoEstudiante) {
        boolean codeExists = estudianteRepository.existsByCodigoEstudiante(codigoEstudiante);
        if(!codeExists)
            throw new EstudianteDoesNotExistsException("The codigoEstudiante=%s does not exists.");
        return estudianteRepository.findByCodigoEstudiante(codigoEstudiante);
    }

    @Override
    public Estudiante update(Long codigoEstudiante, EstudianteDTO estudianteDTO) {
        boolean codeExist = estudianteRepository.existsByCodigoEstudiante(codigoEstudiante);
        if(!codeExist)
            throw new EstudianteDoesNotExistsException("The codigoEstudiante=%s does not exists.");
        Optional<Estudiante> estudiante = estudianteRepository.findByCodigoEstudiante(codigoEstudiante);
        boolean differentCodes = !codigoEstudiante.equals(estudianteDTO.getCodigoEstudiante());
        if(differentCodes){
            boolean exists = estudianteRepository.existsByCodigoEstudiante(estudianteDTO.getCodigoEstudiante());
            if(exists)
                throw new EstudianteAlreadyExistsException("There already exists a Estudiante with codigoEstudiante=%s"+ estudianteDTO.getCodigoEstudiante());
        }
        if(estudiante.isPresent()){
            boolean differentCedulas = !(estudiante.get().getCedula()).equals(estudianteDTO.getCedula());
            if(differentCedulas){
                boolean exists = estudianteRepository.existsByCedula(estudianteDTO.getCedula());
                if(exists)
                    throw  new EstudianteAlreadyExistsException("There already exists a Estudiante with cedula=%s"+ estudianteDTO.getCedula());
            }
        }
        Estudiante updatedEstudiante = new Estudiante(estudianteDTO);
        return estudianteRepository.save(updatedEstudiante);
    }


    @Override
    public String delete(Long codigoEstudiante) {
        boolean codeExists = estudianteRepository.existsByCodigoEstudiante(codigoEstudiante);
        if(!codeExists)
            throw new EstudianteDoesNotExistsException("The codigoEstudiante=%s does not exists.");
        Optional<Estudiante> estudiante = estudianteRepository.findByCodigoEstudiante(codigoEstudiante);
        if(estudiante.isEmpty()){
            throw new EstudianteDoesNotExistsException("The Estudiante does not exists.");
        }
        estudianteRepository.deleteById(codigoEstudiante);
        return "The Estudiante was deleted";
    }
}
