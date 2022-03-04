package AlkemyDisney.MacaHannaArenas.repositorio;



import AlkemyDisney.MacaHannaArenas.entidad.Personaje;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepo extends JpaRepository<Personaje, String> {

    List<Personaje> findAll(Specification<Personaje> filtros);
    
}
