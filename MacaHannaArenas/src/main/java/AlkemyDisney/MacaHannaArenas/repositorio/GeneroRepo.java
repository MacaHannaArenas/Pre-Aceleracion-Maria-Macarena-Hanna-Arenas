package AlkemyDisney.MacaHannaArenas.repositorio;


import AlkemyDisney.MacaHannaArenas.entidad.Genero;
import AlkemyDisney.MacaHannaArenas.entidad.Pelicula;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepo extends JpaRepository<Genero, String> {

     List<Pelicula> findAll(Specification<Pelicula> filtros);
}
