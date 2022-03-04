package AlkemyDisney.MacaHannaArenas.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeDtoFiltro {

    //dto completo
    //dto basico

    private String nombre;
    private String imagen;
    //dto basico
    private Integer edad;
    private Double peso;
    private String biografia;
    private List<PeliculaDTO> peliculas;
    //dto completo
    private String orden;

    public boolean isASC() {
        return orden.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return orden.compareToIgnoreCase("DESC") == 0;
    }

}
