package AlkemyDisney.MacaHannaArenas.dto;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDtoFiltro {

    //dto completo
    //dto basico
    private String imagen;
    private String titulo;
    private String lanzamiento;
    //dto basico
    private Float valoracion;
    //private List<PersonajeDTO> personajes;
    //private String personaje;
    private Set<String> personajes;
    //private List<GeneroDTO> generos;
    private Set<String> generos;
    //dto completo
    private String orden;

    public boolean isASC() {
        return orden.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return orden.compareToIgnoreCase("DESC") == 0;
    }

}
