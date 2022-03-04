package AlkemyDisney.MacaHannaArenas.servicio;




import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDtoBasico;
import java.util.List;


import org.springframework.stereotype.Service;

@Service
public interface PeliculaSvs {

    PeliculaDTO guardarPelicula(PeliculaDTO movie);

    PeliculaDTO  modificarPelicula(String id, PeliculaDTO movieDTO);

    void borrarPelicula(String id);

    List<PeliculaDtoBasico> peliculaBasica();

    List<PeliculaDTO> peliculaCompleta();

    List<PeliculaDTO> peliculaFiltro(String titulo,List<String> generos, String orden);

    PeliculaDTO peliculaPorId(String peliculaId);

    void agregarPersonaje(String personajeId, String peliculaId);

    void removerPersonaje(String personajeId, String peliculaId);

    void agregarGenero(String generoId, String peliculaId);

    void removerGenero(String generoId, String peliculaId);
}
