package AlkemyDisney.MacaHannaArenas.dto;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaDTO {

    private String peliculaId;
    private String imagen;
    private String titulo;
    private String lanzamiento;
    private Float valoracion;
/*
    private List<PersonajeDTO> personajes;
    private List<GeneroDTO> generos;
*/
     private Set<PersonajeDTO> personajes;
    private Set<GeneroDTO> generos;
}
