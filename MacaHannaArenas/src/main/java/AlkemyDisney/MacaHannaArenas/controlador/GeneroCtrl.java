package AlkemyDisney.MacaHannaArenas.controlador;




import AlkemyDisney.MacaHannaArenas.dto.GeneroDTO;
import AlkemyDisney.MacaHannaArenas.dto.GeneroDtoBasico;
import AlkemyDisney.MacaHannaArenas.servicio.GeneroSvs;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("generos")
public class GeneroCtrl {

    @Autowired
    private GeneroSvs genServ;


    @GetMapping
    public ResponseEntity<List<GeneroDtoBasico>> generoBasico() {
        List<GeneroDtoBasico> listaGeneros = genServ.generoBasico();
        return ResponseEntity.ok().body(listaGeneros);
    }

   
    @PostMapping
    public ResponseEntity<GeneroDTO> guardarGenero(@RequestBody GeneroDTO generoDto) {
        GeneroDTO generoGuardado = genServ.guardarGenero(generoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

   
    @PutMapping("/{generoId}")
    public ResponseEntity<GeneroDTO> modificarGenero(@PathVariable String generoId, @RequestBody GeneroDTO generoDto) {
        GeneroDTO generoEditado = genServ.modificarGenero(generoId, generoDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(generoEditado);
    }

   
    @DeleteMapping("/{generoId}")
    public ResponseEntity<Void> borrarGeneroId(@PathVariable String generoId) {
        genServ.borrarGeneroId(generoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
