package AlkemyDisney.MacaHannaArenas.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneroDTO {

    private String generoId;
    private String nombre;
    private String imagen;
    private List<PeliculaDTO> peliculas;

}
