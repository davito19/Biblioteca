package co.com.davito.biblioteca.services;

import static org.mockito.ArgumentMatchers.any;

import co.com.davito.biblioteca.dtos.RecursoDto;
import co.com.davito.biblioteca.mapper.RecursoMapper;
import co.com.davito.biblioteca.models.Recurso;
import co.com.davito.biblioteca.repositories.RecursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@SpringBootTest
class RecursoServiceTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private RecursoService service;

    @Test
    @DisplayName("Test FindAll Success")
    void obtenerTodos() {
        var dato1 = new Recurso();
        dato1.setId("xxxx");
        dato1.setNombre("yyyy");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());
        dato1.setTipo("libro");
        dato1.setTematica("ciencia");
        var dato2 = new Recurso();
        dato2.setId("zzzz");
        dato2.setNombre("wwww");
        dato2.setPrestado(false);
        dato2.setFechaPrestamo(LocalDate.now());
        dato2.setTipo("libro");
        dato2.setTematica("ciencia");
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(recursoRepository.findAll()).thenReturn(lista);

        var resultado = service.obtenerTodos();
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(dato1.getId(), resultado.get(0).getId());
        Assertions.assertEquals(dato2.getId(), resultado.get(1).getId());
        Assertions.assertEquals(dato1.getNombre(), resultado.get(0).getNombre());
        Assertions.assertEquals(dato2.getNombre(), resultado.get(1).getNombre());
        Mockito.verify(recursoRepository).findAll();


    }

    @Test
    @DisplayName("Test FindById Success")
    void obtenerPorId() {
        var dato1 = new Recurso();
        dato1.setId("xxxx");
        dato1.setNombre("yyyy");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());
        dato1.setTipo("libro");
        dato1.setTematica("ciencia");
        Mockito.when(recursoRepository.findById("xxxx")).thenReturn(Optional.of(dato1));


        var resultado = service.obtenerPorId("xxxx");
        Assertions.assertEquals(dato1.getId(), resultado.getId());
        Assertions.assertEquals(dato1.getNombre(), resultado.getNombre());
        Assertions.assertEquals(dato1.getTematica(), resultado.getTematica());
        Assertions.assertEquals(dato1.getTipo(), resultado.getTipo());
        Mockito.verify(recursoRepository).findById("xxxx");

    }

    @Test
    @DisplayName("Test FindByThematic Success")
    void obtenerPorTematica() {
        var dato1 = new Recurso();
        dato1.setId("xxxx");
        dato1.setNombre("yyyy");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());
        dato1.setTipo("libro");
        dato1.setTematica("ciencia");
        var dato2 = new Recurso();
        dato2.setId("zzzz");
        dato2.setNombre("wwww");
        dato2.setPrestado(false);
        dato2.setFechaPrestamo(LocalDate.now());
        dato2.setTipo("libro");
        dato2.setTematica("ciencia");
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(recursoRepository.findByTematica("ciencia")).thenReturn(lista);


        var resultado = service.obtenerPorTematica("ciencia");
        Assertions.assertEquals(dato1.getId(), resultado.get(0).getId());
        Assertions.assertEquals(dato1.getNombre(), resultado.get(0).getNombre());
        Assertions.assertEquals(dato1.getTematica(), resultado.get(0).getTematica());
        Assertions.assertEquals(dato1.getTipo(), resultado.get(0).getTipo());
        Assertions.assertEquals(dato2.getId(), resultado.get(1).getId());
        Assertions.assertEquals(dato2.getNombre(), resultado.get(1).getNombre());
        Assertions.assertEquals(dato2.getTematica(), resultado.get(1).getTematica());
        Assertions.assertEquals(dato2.getTipo(), resultado.get(1).getTipo());
        Mockito.verify(recursoRepository).findByTematica("ciencia");
    }

    @Test
    @DisplayName("Test FindByType Success")
    void obtenerPorTipo() {
        var dato1 = new Recurso();
        dato1.setId("xxxx");
        dato1.setNombre("yyyy");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());
        dato1.setTipo("libro");
        dato1.setTematica("ciencia");
        var dato2 = new Recurso();
        dato2.setId("zzzz");
        dato2.setNombre("wwww");
        dato2.setPrestado(false);
        dato2.setFechaPrestamo(LocalDate.now());
        dato2.setTipo("libro");
        dato2.setTematica("ciencia");
        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(recursoRepository.findByTipo("libro")).thenReturn(lista);


        var resultado = service.obtenerPorTipo("libro");
        Assertions.assertEquals(dato1.getId(), resultado.get(0).getId());
        Assertions.assertEquals(dato1.getNombre(), resultado.get(0).getNombre());
        Assertions.assertEquals(dato1.getTematica(), resultado.get(0).getTematica());
        Assertions.assertEquals(dato1.getTipo(), resultado.get(0).getTipo());
        Assertions.assertEquals(dato2.getId(), resultado.get(1).getId());
        Assertions.assertEquals(dato2.getNombre(), resultado.get(1).getNombre());
        Assertions.assertEquals(dato2.getTematica(), resultado.get(1).getTematica());
        Assertions.assertEquals(dato2.getTipo(), resultado.get(1).getTipo());
        Mockito.verify(recursoRepository).findByTipo("libro");
    }

    @Test
    @DisplayName("Test save Success")
    void crear() {
        var dato1 = new Recurso();
        dato1.setId("xxxx");
        dato1.setNombre("yyyy");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());
        dato1.setTipo("libro");
        dato1.setTematica("ciencia");
        var dato2 = new RecursoDto();
        dato2.setId("xxxx");
        dato2.setNombre("yyyy");
        dato2.setPrestado(true);
        dato2.setFechaPrestamo(LocalDate.now());
        dato2.setTipo("libro");
        dato2.setTematica("ciencia");

        Mockito.when(recursoRepository.save(any())).thenReturn(dato1);

        var resultado = service.crear(dato2);

        Assertions.assertNotNull(resultado, "el valor debe ser no null");

        Assertions.assertEquals(dato1.getId(), resultado.getId());
        Assertions.assertEquals(dato1.getNombre(), resultado.getNombre());

        Mockito.verify(recursoRepository).save(any());

    }

    @Test
    void actualizar() {
        var dato1 = new Recurso();
        dato1.setId("xxxx");
        dato1.setNombre("yyyy");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());
        dato1.setTipo("libro");
        dato1.setTematica("ciencia");
        var dato2 = new Recurso();
        dato2.setId("xxxx");
        dato2.setNombre("zzzz");
        dato2.setPrestado(true);
        dato2.setFechaPrestamo(LocalDate.now());
        dato2.setTipo("libro");
        dato2.setTematica("ciencia");

        Mockito.when(recursoRepository.findById("xxxx")).thenReturn(Optional.of(dato1));
        Mockito.when(recursoRepository.save(any())).thenReturn(dato2);
        var mapper = new RecursoMapper();
        var resultado = service.actualizar(mapper.fromCollection(dato2));

        Assertions.assertNotNull(resultado, "el valor debe ser no null");

        Assertions.assertEquals(dato1.getId(), resultado.getId());
        Assertions.assertNotEquals(dato1.getNombre(), resultado.getNombre());

        Mockito.verify(recursoRepository).save(any());
        Mockito.verify(recursoRepository).findById("xxxx");

    }

}