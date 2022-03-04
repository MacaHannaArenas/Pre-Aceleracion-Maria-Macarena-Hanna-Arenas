package AlkemyDisney.MacaHannaArenas.servicio;


import AlkemyDisney.MacaHannaArenas.dto.GeneroDTO;
import AlkemyDisney.MacaHannaArenas.dto.GeneroDtoBasico;
import AlkemyDisney.MacaHannaArenas.entidad.Genero;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface GeneroSvs {

    GeneroDTO guardarGenero(GeneroDTO generoDto);

    List<GeneroDTO> generoCompleto();

    List<GeneroDtoBasico> generoBasico();

    GeneroDTO modificarGenero(String generoId, GeneroDTO generoDto);

    void borrarGeneroId(String generoId);

    Genero generoPorId(String generoId);
}
