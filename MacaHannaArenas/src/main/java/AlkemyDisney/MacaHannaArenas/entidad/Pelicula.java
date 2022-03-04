package AlkemyDisney.MacaHannaArenas.entidad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@SQLDelete(sql = "UPDATE peliculas SET baja = true WHERE id=?")

@Where(clause = "baja = false")
//
public class Pelicula {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    private String imagen;

    @Column(unique = true)
    private String titulo;

    @Column(name = "date_of_creation")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate lanzamiento;

    @Nullable
    
    private Float valoracion;

    
    private boolean baja = Boolean.FALSE;

   
    @ManyToMany(cascade = {
        CascadeType.DETACH,
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.REFRESH}, fetch = FetchType.LAZY)
   
    @JoinTable(
            name = "peliculas_personajes",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    //private List<Personaje> personajes = new ArrayList<>();
private Set<Personaje> personajes = new HashSet<>();
    
    @ManyToMany(cascade = {
        CascadeType.DETACH,
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.REFRESH}, fetch = FetchType.LAZY)

    
    @JoinTable(
            name = "peliculas_generos",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id"))
    //private List<Genero> generos = new ArrayList<>();
 private Set<Genero> generos = new HashSet<>();
    
@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Pelicula other = (Pelicula) obj;
		return other.id == this.id;
	}

    }


    

