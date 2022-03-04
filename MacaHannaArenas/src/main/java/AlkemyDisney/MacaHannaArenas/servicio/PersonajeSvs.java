package AlkemyDisney.MacaHannaArenas.servicio;




import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDTO;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDtoBasico;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PersonajeSvs {

    public PersonajeDTO guardarPersonaje(PersonajeDTO personajeDto);

    public PersonajeDTO modificarPersonaje(String personajeId, PersonajeDTO personajeDto);

    public void borrarPersonaje(String personajeId);

    List<PersonajeDtoBasico> personajeBasico();

    List<PersonajeDTO> personajeCompleto();

    List<PersonajeDTO> personajeFiltro(String nombre, String imagen, Integer edad, Double peso, String biografia, List<PeliculaDTO>peliculas, String orden);

    PersonajeDTO personajePorId(String personajeId);

}
