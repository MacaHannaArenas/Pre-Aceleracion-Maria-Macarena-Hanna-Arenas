package AlkemyDisney.MacaHannaArenas.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PersonajeDtoFiltro {

    private String nombre;

    private Integer edad;

    private List<String> peliculas;

    private String orden;

    public boolean isASC() {
        return orden.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return orden.compareToIgnoreCase("DESC") == 0;
    }

    public PersonajeDtoFiltro(String nombre, Integer edad, List<String> peliculas, String orden) {
        this.nombre = nombre;
        this.edad = edad;
        this.peliculas = peliculas;
        this.orden = orden;
    }

}
