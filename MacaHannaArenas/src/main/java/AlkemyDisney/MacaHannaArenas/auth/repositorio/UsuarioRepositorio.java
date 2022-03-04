package AlkemyDisney.MacaHannaArenas.auth.repositorio;


import AlkemyDisney.MacaHannaArenas.auth.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    Usuario findByUsername(String username);
//Username es un campo/columna de SpringJPA
}
