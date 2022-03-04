package AlkemyDisney.MacaHannaArenas.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonajeDTO {

    private String personajeId; //ver si no hay que sacarlo
    private String nombre;
    private String imagen;
    private Integer edad;
    private Double peso;
    private String biografia;

    private List<PeliculaDTO> peliculas;

}
