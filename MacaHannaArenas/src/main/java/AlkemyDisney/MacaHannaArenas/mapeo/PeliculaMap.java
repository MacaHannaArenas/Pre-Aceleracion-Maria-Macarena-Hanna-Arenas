package AlkemyDisney.MacaHannaArenas.mapeo;


import AlkemyDisney.MacaHannaArenas.dto.GeneroDTO;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDtoBasico;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDTO;
import AlkemyDisney.MacaHannaArenas.entidad.Genero;
import AlkemyDisney.MacaHannaArenas.entidad.Pelicula;
import AlkemyDisney.MacaHannaArenas.entidad.Personaje;
import AlkemyDisney.MacaHannaArenas.excepcion.PeliculaExc;
import AlkemyDisney.MacaHannaArenas.excepcion.msje.MsjeExc;

import AlkemyDisney.MacaHannaArenas.validaciones.dtoVal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PeliculaMap {

    @Lazy
    @Autowired
    private PersonajeMap personajeMap;

    @Autowired
    private GeneroMap generoMap;

    @Autowired
    private dtoVal valDTO;

    public Pelicula pelDTO2Ent(PeliculaDTO peliculaDto, boolean cargaPersonaje) {

            Pelicula peliculaEnt = new Pelicula();
            pelRefrescarEnt(peliculaEnt, peliculaDto);
            return peliculaEnt;
    }

   
    public PeliculaDTO pelEnt2DTO(Pelicula peliculaEnt, boolean cargaPersonaje) {

        if (peliculaEnt != null) {
            
            PeliculaDTO peliculaDto = new PeliculaDTO();
            pelRefrescarDTO(peliculaDto, peliculaEnt, cargaPersonaje);
            return peliculaDto;

        } else {
            throw new PeliculaExc(MsjeExc.ENTIDAD_INVALIDA);
        }
    }

   
    public PeliculaDtoBasico pelEntBas2DtoBas(Pelicula peliculaEnt) {

        if (peliculaEnt != null) {
            PeliculaDtoBasico peliculaDtoBas = new PeliculaDtoBasico();
            pelRefrescarDtoBas(peliculaDtoBas, peliculaEnt);
            return peliculaDtoBas;

        } else {
            throw new PeliculaExc(MsjeExc.ENTIDAD_INVALIDA);
        }
    }


    public List<PeliculaDTO> pelListEnt2ListDTO(List<Pelicula> entities, boolean load) {

        List<PeliculaDTO> peliculaDtoList = new ArrayList<>();

        for (Pelicula entity : entities) {
            peliculaDtoList.add(pelEnt2DTO(entity, load));
        }
        return peliculaDtoList;
    }
    /*
    public List<PeliculaDTO> pelListEnt2ListDTO(Collection<Pelicula> entidadSet, boolean pelCarga) {
         return entidadSet.stream().map(pelicula -> pelEnt2DTO(pelicula, pelCarga)).collect(Collectors.toList());

    }
*/    


    public List<Pelicula> pelListDTO2ListEnt(List<PeliculaDTO> peliculaDtoList, boolean cargaPersonaje) {

        List<Pelicula> movieEntitiesList = new ArrayList<>();

        for (PeliculaDTO dto : peliculaDtoList) {
            movieEntitiesList.add(pelDTO2Ent(dto, cargaPersonaje));
        }
        return movieEntitiesList;
    }
    /*
public Set<Pelicula> pelListDTO2ListEnt(List<PeliculaDTO> peliculaDtoList, boolean cargaPersonaje) {
        return peliculaDtoList.stream().map(peliculaDto->pelDTO2Ent(peliculaDto, cargaPersonaje)).collect(Collectors.toSet());
  }*/

    public List<PeliculaDtoBasico> pelListEntBasc2ListDtoBas(List<Pelicula> entities) {

        List<PeliculaDtoBasico> peliculaDtoBasList = new ArrayList<>();

        if (entities != null) {
            for (Pelicula entity : entities) {
                peliculaDtoBasList.add(pelEntBas2DtoBas(entity));
            }
        }
        return peliculaDtoBasList;
    }
 
    private LocalDate string2LocalDate(String stringDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate formattedDate = LocalDate.parse(stringDate, formatter);

        return formattedDate;
    }


    private String localDate2String(LocalDate localDate) {

        String formatDate = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        return formatDate;
    }


    public void pelRefrescarEnt(Pelicula peliculaEnt, PeliculaDTO peliculaDto) {

        peliculaEnt.setImagen(peliculaDto.getImagen());
        peliculaEnt.setTitulo(peliculaDto.getTitulo());


        String date = peliculaDto.getLanzamiento();
        peliculaEnt.setLanzamiento(string2LocalDate(date));

        peliculaEnt.setValoracion(peliculaDto.getValoracion());

        
        List<Personaje> personajeEntList = personajeMap.perListDto2ListEnt(peliculaDto.getPersonajes());
        peliculaEnt.setPersonajes(personajeEntList);

        
        List<Genero> generoEntList = generoMap.genListDTO2ListEnt(peliculaDto.getGeneros());
        peliculaEnt.setGeneros(generoEntList);

    }

    
    public void pelRefrescarDTO(PeliculaDTO peliculaDto, Pelicula peliculaEnt, boolean cargaPersonaje) {

        peliculaDto.setPeliculaId(peliculaEnt.getId());
        peliculaDto.setImagen(peliculaEnt.getImagen());
        peliculaDto.setTitulo(peliculaEnt.getTitulo());

        
        LocalDate date = peliculaEnt.getLanzamiento();
        peliculaDto.setLanzamiento(localDate2String(date));

        peliculaDto.setValoracion(peliculaEnt.getValoracion());

        if (cargaPersonaje) {
         
            peliculaDto.setPersonajes((List<PersonajeDTO>) personajeMap.perListEnt2ListDto((List<Personaje>) peliculaEnt.getPersonajes(), true));
      
            peliculaDto.setGeneros((List<GeneroDTO>) generoMap.generoEntList2DTOList((List<Genero>) peliculaEnt.getGeneros()));
        }

    }


    public void pelRefrescarDtoBas(PeliculaDtoBasico peliculaDtoBas, Pelicula peliculaEnt) {

        peliculaDtoBas.setPeliculaId(peliculaEnt.getId());
        peliculaDtoBas.setImagen(peliculaEnt.getImagen());
        peliculaDtoBas.setTitulo(peliculaEnt.getTitulo());

    
        LocalDate localDate = peliculaEnt.getLanzamiento();
        peliculaDtoBas.setLanzamiento(localDate2String(localDate));
    }
}
