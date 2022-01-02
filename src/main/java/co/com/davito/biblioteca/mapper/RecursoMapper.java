package co.com.davito.biblioteca.mapper;

import co.com.davito.biblioteca.dtos.RecursoDto;
import co.com.davito.biblioteca.models.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDto dto){
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setNombre(dto.getNombre());
        recurso.setPrestado(dto.isPrestado());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        recurso.setTipo(dto.getTipo());
        recurso.setTematica(dto.getTematica());
        return recurso;
    }

    public RecursoDto fromCollection(Recurso collection){
        RecursoDto recursoDto = new RecursoDto();
        recursoDto.setId(collection.getId());
        recursoDto.setNombre(collection.getNombre());
        recursoDto.setPrestado(collection.isPrestado());
        recursoDto.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDto.setTipo(collection.getTipo());
        recursoDto.setTematica(collection.getTematica());
        return recursoDto;
    }

    public List<RecursoDto> fromCollectionsList(List<Recurso> collections) {
        if (collections == null) return null;
        List<RecursoDto> list = new ArrayList<>(collections.size());
        Iterator listTracks = collections.iterator();

        while (listTracks.hasNext()){
            Recurso recurso = (Recurso) listTracks.next();
            list.add(fromCollection(recurso));
        }

        return list;
    }
}
