package co.com.davito.biblioteca.controllers;

import co.com.davito.biblioteca.dtos.RecursoDto;
import co.com.davito.biblioteca.services.RecursoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
class RecursoControllerTest {

    @MockBean
    private RecursoService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get /biblioteca success")
    void obtenerTodos() throws Exception {
        var dato1 = new RecursoDto();
        dato1.setId("xxxx");
        dato1.setNombre("yyyy");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());
        dato1.setTipo("libro");
        dato1.setTematica("ciencia");
        var dato2 = new RecursoDto();
        dato2.setId("zzzz");
        dato2.setNombre("wwww");
        dato2.setPrestado(false);
        dato2.setFechaPrestamo(LocalDate.now());
        dato2.setTipo("libro");
        dato2.setTematica("ciencia");
        var lista = new ArrayList<RecursoDto>();
        lista.add(dato1);
        lista.add(dato2);

        Mockito.when(service.obtenerTodos()).thenReturn(lista);

        mockMvc.perform(get("/biblioteca"))
                .andExpect(status().isOk());
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON));
                //.andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void obtnerPorID() {
    }

    @Test
    void recomendacionesTematicas() {
    }

    @Test
    void recomendacionesTipo() {
    }

    @Test
    void recursoDisponible() {
    }

    @Test
    void prestarRecurso() {
    }

    @Test
    void decolverRecurso() {
    }

    @Test
    void crear() {
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }
}