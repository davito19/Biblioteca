package co.com.davito.biblioteca.controllers;

import co.com.davito.biblioteca.dtos.RecursoDto;
import co.com.davito.biblioteca.services.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class RecursoController {

    @Autowired
    RecursoService service;

    @GetMapping()
    public ResponseEntity<List<RecursoDto>> obtenerTodos(){
        return new ResponseEntity<>(service.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDto> obtnerPorID(@PathVariable("id") String id){
        return new ResponseEntity<>(service.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/tematica/{tematica}")
    public ResponseEntity<List<RecursoDto>> recomendacionesTematicas(@PathVariable("tematica") String tematica){
        return new ResponseEntity<>(service.obtenerPorTematica(tematica), HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<RecursoDto>> recomendacionesTipo(@PathVariable("tipo") String tipo){
        return new ResponseEntity<>(service.obtenerPorTipo(tipo), HttpStatus.OK);
    }

    @GetMapping("/disponible/{id}")
    public ResponseEntity<String> recursoDisponible(@PathVariable("id") String id){
        if (service.disponible(id))
            return new ResponseEntity<>("Recurso disponible", HttpStatus.OK);

        return new ResponseEntity<>("Recurso no disponible", HttpStatus.OK);
    }

    @GetMapping("/prestar/{id}")
    public ResponseEntity<String> prestarRecurso(@PathVariable("id") String id){
        if (service.recursoPrestar(id))
            return new ResponseEntity<>("Prestamo realizado", HttpStatus.OK);

        return new ResponseEntity<>("No se puede prestar el recurso", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/devolver/{id}")
    public ResponseEntity<String> decolverRecurso(@PathVariable("id") String id){
        if(service.recursoDevolver(id))
            return new ResponseEntity<>("Recurso devuelto", HttpStatus.OK);

        return new ResponseEntity<>("Recurso no se pudo devolver", HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<RecursoDto> crear(@RequestBody RecursoDto recursoDto){
        return new ResponseEntity<>(service.crear(recursoDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RecursoDto> actualizar(@RequestBody RecursoDto recursoDto){
        return new ResponseEntity<>(service.actualizar(recursoDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") String id){
        service.eliminar(id);
    }
}
