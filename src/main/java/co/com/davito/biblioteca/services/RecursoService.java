package co.com.davito.biblioteca.services;

import co.com.davito.biblioteca.dtos.RecursoDto;
import co.com.davito.biblioteca.mapper.RecursoMapper;
import co.com.davito.biblioteca.models.Recurso;
import co.com.davito.biblioteca.repositories.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository repository;
    RecursoMapper mapper = new RecursoMapper();

    public List<RecursoDto> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(recurso -> mapper.fromCollection(recurso))
                .collect(Collectors.toList());
    }

    public RecursoDto obtenerPorId(String id){
        return repository.findById(id)
                .stream()
                .map(recurso -> mapper.fromCollection(recurso))
                .findFirst()
                .orElseThrow();
    }

    public List<RecursoDto> obtenerPorTematica(String tematica){
        return repository.findByTematica(tematica)
                .stream()
                .map(recurso -> mapper.fromCollection(recurso))
                .collect(Collectors.toList());
    }

    public List<RecursoDto> obtenerPorTipo(String tipo){
        return repository.findByTipo(tipo)
                .stream()
                .map(recurso -> mapper.fromCollection(recurso))
                .collect(Collectors.toList());
    }

    public RecursoDto crear(RecursoDto recursoDto){
        Recurso recurso = mapper.fromDTO(recursoDto);
        return mapper.fromCollection(repository.save(recurso));
    }

    public RecursoDto actualizar(RecursoDto recursoDto){
        Recurso recurso = mapper.fromDTO(recursoDto);
        repository.findById(recurso.getId()).orElseThrow(
                () -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(repository.save(recurso));
    }

    public void eliminar(String id){
        repository.deleteById(id);
    }

    public boolean disponible(String id){
        try {
            return !this.obtenerPorId(id).isPrestado();
        }catch (Exception e){
            return false;
        }
    }

    public boolean recursoPrestar(String id){
        if (disponible(id)){
            RecursoDto recursoDto = obtenerPorId(id);
            recursoDto.setPrestado(true);
            recursoDto.setFechaPrestamo(LocalDate.now());
            actualizar(recursoDto);
            return true;
        }
        return false;
    }

    public Boolean recursoDevolver(String id){
        RecursoDto recursoDto = obtenerPorId(id);
        if (recursoDto.getId() != null){
            recursoDto.setPrestado(false);
            this.actualizar(recursoDto);
            return this.disponible(recursoDto.getId());
        }
        return this.disponible(recursoDto.getId());
    }
}
