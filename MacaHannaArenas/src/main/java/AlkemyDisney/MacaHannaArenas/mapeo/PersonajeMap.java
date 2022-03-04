package AlkemyDisney.MacaHannaArenas.mapeo;


import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDTO;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDtoBasico;
import AlkemyDisney.MacaHannaArenas.entidad.Pelicula;
import AlkemyDisney.MacaHannaArenas.entidad.Personaje;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonajeMap {

    @Autowired
    private PeliculaMap peliculaMap;


    public Personaje perDTO2Ent(PersonajeDTO persDto) {

        Personaje personajeEnt = new Personaje();
        perRefrescarEnt(personajeEnt, persDto);

        return personajeEnt;
    }

    public PersonajeDTO perEnt2DTO(Personaje personajeEnt, boolean cargaPel) {

        PersonajeDTO persDto = new PersonajeDTO();
        perRefrescarDto(persDto, personajeEnt);


        if (cargaPel) {
            List<PeliculaDTO> listaPeliculasDto = new ArrayList<>();
            for (Pelicula peliculaEnt : personajeEnt.getPeliculas()) {
                listaPeliculasDto.add(peliculaMap.pelEnt2DTO(peliculaEnt, false));
            }
            persDto.setPeliculas(listaPeliculasDto);
        }
        return persDto;
    }

    public List<PersonajeDTO> perListEnt2ListDto(List<Personaje> personajesEnt, boolean cargaPel) {

        List<PersonajeDTO> perListaDTO = new ArrayList<>();

        for (Personaje personajeEnt : personajesEnt) {
            perListaDTO.add(perEnt2DTO(personajeEnt, cargaPel));
        }
        return perListaDTO;
    }

    public List<PersonajeDtoBasico> perListEntBas2ListDtoBas(List<Personaje> personajesEnt) {

        List<PersonajeDtoBasico> personajeDtoBasList = new ArrayList<>();

        for (Personaje personajeEnt : personajesEnt) {
            PersonajeDtoBasico personajeDtoBas = new PersonajeDtoBasico();
            perRefrescarDtoBas(personajeDtoBas, personajeEnt);

            personajeDtoBasList.add(personajeDtoBas);
        }
        return personajeDtoBasList;
    }


    public PersonajeDtoBasico perEntBas2DtoBas(Personaje personajeEnt) {

        PersonajeDtoBasico personajeDtoBas = new PersonajeDtoBasico();
        perRefrescarDtoBas(personajeDtoBas, personajeEnt);

        return personajeDtoBas;
    }


    public void perRefrescarEnt(Personaje personajeEnt, PersonajeDTO persDto) {

        personajeEnt.setImagen(persDto.getImagen());
        personajeEnt.setNombre(persDto.getNombre());
        personajeEnt.setEdad(persDto.getEdad());
        personajeEnt.setPeso(persDto.getPeso());
        personajeEnt.setBiografia(persDto.getBiografia());
    }


    public void perRefrescarDto(PersonajeDTO persDto, Personaje personajeEnt) {

        persDto.setPersonajeId(personajeEnt.getPersonajeId());
        persDto.setNombre(personajeEnt.getNombre());
        persDto.setImagen(personajeEnt.getImagen());
        persDto.setEdad(personajeEnt.getEdad());
        persDto.setPeso(personajeEnt.getPeso());
        persDto.setBiografia(personajeEnt.getBiografia());
    }


    public void perRefrescarDtoBas(PersonajeDtoBasico personajeDtoBas, Personaje personajeEnt) {

        personajeDtoBas.setPersonajeId(personajeEnt.getPersonajeId());
        personajeDtoBas.setNombre(personajeEnt.getNombre());
        personajeDtoBas.setImagen(personajeEnt.getImagen());
    }

    List<Personaje> perListDto2ListEnt(List<PersonajeDTO> personajes) {
        
         List<Personaje> perListaEnt = new ArrayList<>();

        for (PersonajeDTO persDto : personajes) {
            perListaEnt.add(perDTO2Ent(persDto));
        }
        return perListaEnt;
    }
}
