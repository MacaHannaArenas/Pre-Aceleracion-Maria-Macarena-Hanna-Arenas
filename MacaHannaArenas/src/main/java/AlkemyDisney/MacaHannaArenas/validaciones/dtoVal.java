package AlkemyDisney.MacaHannaArenas.validaciones;


import AlkemyDisney.MacaHannaArenas.dto.GeneroDTO;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class dtoVal {
    
     public boolean peliculaDtoVal(PeliculaDTO peliculaDto) {
        
         boolean valid = false;
       
         if (peliculaDto.getImagen() != null &&
                peliculaDto.getTitulo() != null &&
                peliculaDto.getLanzamiento()!= null &&
                peliculaDto.getValoracion()>= 1 &&
                peliculaDto.getValoracion()<= 5 &&
                peliculaDto.getPersonajes()!= null &&
                peliculaDto.getGeneros()!= null) {
            if (!peliculaDto.getPersonajes().isEmpty()) {
                valid = personajeIterador(peliculaDto.getPersonajes());
            }
            if (!peliculaDto.getPersonajes().isEmpty()) {
                valid = generoIterador(peliculaDto.getGeneros());
            }
        }
        return valid;
    }

    private boolean personajeIterador(List<PersonajeDTO> personajesDto) {
        for (PersonajeDTO personajeDto : personajesDto) {
            if (!personajeDtoVal(personajeDto)) {
                return false;
            }
        }
        return true;
    }

    public boolean personajeDtoVal(PersonajeDTO personajeDto) {
        return 
                personajeDto.getNombre() != null &&
                personajeDto.getImagen() != null &&
                personajeDto.getEdad() > 0 &&
                personajeDto.getPeso() > 0 &&
                personajeDto.getBiografia()!= null 
                
                ;
    }

    private boolean generoIterador(List<GeneroDTO> generos) {
        for (GeneroDTO generoDto : generos) {
            if (!generoDtoVal(generoDto)) {
                return false;
            }
        }
        return true;
    }

    public boolean generoDtoVal(GeneroDTO generoDto) {
        return generoDto.getImagen() != null &&
                generoDto.getNombre() != null;
    }

    public boolean peliculaDtoToUpdateIsValid(PeliculaDTO peliculaDto) {
        return peliculaDto.getImagen() != null &&
                peliculaDto.getTitulo() != null &&
                peliculaDto.getLanzamiento()!= null &&
                peliculaDto.getValoracion()>= 1 &&
                peliculaDto.getValoracion()<= 5;
    }
}
