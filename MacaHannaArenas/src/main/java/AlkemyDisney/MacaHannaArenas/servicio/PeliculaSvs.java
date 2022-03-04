package AlkemyDisney.MacaHannaArenas.servicio;




import AlkemyDisney.MacaHannaArenas.dto.GeneroDTO;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDtoBasico;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDTO;
import java.util.List;
import java.util.Set;


import org.springframework.stereotype.Service;

@Service
public interface PeliculaSvs {

    PeliculaDTO guardarPelicula(PeliculaDTO movie);

    PeliculaDTO  modificarPelicula(String id, PeliculaDTO movieDTO);

    void borrarPelicula(String id);

    List<PeliculaDtoBasico> peliculaBasica();

    List<PeliculaDTO> peliculaCompleta();

    //List<PeliculaDTO> peliculaFiltro(String titulo, String imagen, String lanzamiento, Float valoracion, List<PersonajeDTO> personajes, List<GeneroDTO> generos, String orden);

    List<PeliculaDTO> peliculaFiltro(String titulo, String imagen, String lanzamiento, Float valoracion, Set<String> personajes, Set<String> generos, String orden);
    
    PeliculaDTO peliculaPorId(String peliculaId);

    void agregarPersonaje(String personajeId, String peliculaId);

    void removerPersonaje(String personajeId, String peliculaId);

    void agregarGenero(String generoId, String peliculaId);

    void removerGenero(String generoId, String peliculaId);
}
