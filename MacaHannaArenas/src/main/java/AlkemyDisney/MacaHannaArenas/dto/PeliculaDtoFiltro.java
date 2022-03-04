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
public class PeliculaDtoFiltro {

    
    private String titulo;
  
    private List<String> generos;

   
    private String orden;

    public boolean isASC() {
        return orden.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return orden.compareToIgnoreCase("DESC") == 0;
    }

    public PeliculaDtoFiltro(String imagen, String titulo, List<String> generos, String orden) {
     
        this.titulo = titulo;

        this.generos = generos;
        this.orden = orden;
    }

    
}
