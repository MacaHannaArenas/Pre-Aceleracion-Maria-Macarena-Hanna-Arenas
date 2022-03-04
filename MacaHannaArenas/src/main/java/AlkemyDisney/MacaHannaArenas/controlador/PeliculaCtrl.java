package AlkemyDisney.MacaHannaArenas.controlador;

import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.excepcion.msje.MsjeExc;
import AlkemyDisney.MacaHannaArenas.servicio.PeliculaSvs;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("peliculas")
public class PeliculaCtrl {

    @Autowired
    private PeliculaSvs pelServ;

    @GetMapping("/{peliculaId}")
    public ResponseEntity<PeliculaDTO> peliculaPorId(@PathVariable String peliculaId) {
        PeliculaDTO peliculaDto = pelServ.peliculaPorId(peliculaId);
        return ResponseEntity.status(HttpStatus.OK).body(peliculaDto);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> peliculaPorFiltros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) List<String> generos,
            @RequestParam(required = false, defaultValue = "ASC") String orden
    ) {
        List<PeliculaDTO> listaPelisDto = pelServ.peliculaFiltro(titulo, generos, orden);
        return ResponseEntity.status(HttpStatus.OK).body(listaPelisDto);
    }


    @PostMapping
    public ResponseEntity<PeliculaDTO> guardarPelicula(@RequestBody PeliculaDTO peliculaDto) {
        PeliculaDTO peliculaGuardada = pelServ.guardarPelicula(peliculaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }


    @PutMapping("/{peliculaId}")
    public ResponseEntity<PeliculaDTO> modificarPelicula(@PathVariable String peliculaId, @RequestBody PeliculaDTO peliculaDto) {
        PeliculaDTO peliculaEditada = pelServ.modificarPelicula(peliculaId, peliculaDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaEditada);
    }

    @DeleteMapping("/{peliculaId}")
    public ResponseEntity<Void> borrarPelicula(@PathVariable String peliculaId) {
        pelServ.borrarPelicula(peliculaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{peliculaId}/personaje/{personajeId}")
    public ResponseEntity<Void> agregarPersonaje(@PathVariable String personajeId, @PathVariable String peliculaId) {
        pelServ.agregarPersonaje(personajeId, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{peliculaId}/personaje/{personajeId}")
    public ResponseEntity<Void> removerPersonaje(@PathVariable String personajeId, @PathVariable String peliculaId) {
        pelServ.removerPersonaje(personajeId, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{peliculaId}/genero/{generoId}")
    public ResponseEntity<Void> agregarGenero(@PathVariable String generoId, @PathVariable String peliculaId) {
        pelServ.agregarGenero(generoId, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{peliculaId}/genero/{generoId}")
    public ResponseEntity<Void> removerGenero(@PathVariable String generoId, @PathVariable String peliculaId) {
        pelServ.removerGenero(generoId, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
