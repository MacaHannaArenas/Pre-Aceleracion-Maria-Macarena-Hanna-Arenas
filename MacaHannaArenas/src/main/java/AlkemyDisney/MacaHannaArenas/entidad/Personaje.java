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
@Table(name = "personajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@SQLDelete(sql = "UPDATE personajes SET baja = true WHERE id=?")

@Where(clause = "baja = false")
//
public class Personaje {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String personajeId;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Double peso;

    private String biografia;

    private boolean baja = Boolean.FALSE;

    @ManyToMany(mappedBy = "personajes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pelicula> peliculas = new ArrayList<>();


}
