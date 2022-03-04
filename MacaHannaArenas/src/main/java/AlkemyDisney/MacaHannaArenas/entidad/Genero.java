package AlkemyDisney.MacaHannaArenas.entidad;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "generos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@SQLDelete(sql = "UPDATE generos SET baja = true WHERE genero_id=?")

@Where(clause = "baja = false")

public class Genero {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uugeneroId", strategy = "uugeneroId2")
    @Column(unique = true)
    
    private String generoId;

    private String nombre;

    private String imagen;

    // SOFT DELETE
    private boolean baja = Boolean.FALSE;


    @ManyToMany(mappedBy = "generos", fetch = FetchType.LAZY, cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REFRESH})
    private List<Pelicula> peliculas = new ArrayList<>();


}
