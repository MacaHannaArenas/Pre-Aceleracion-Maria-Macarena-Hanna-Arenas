package AlkemyDisney.MacaHannaArenas.controlador;

import AlkemyDisney.MacaHannaArenas.dto.GeneroDTO;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDTO;
import AlkemyDisney.MacaHannaArenas.servicio.PeliculaSvs;
import java.util.List;
import java.util.Set;


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
            //Dto completo
            //dto basico
      
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String imagen,
            @RequestParam(required = false) String lanzamiento,
            //dto basico
            @RequestParam(required = false) Float valoracion,
            @RequestParam(required = false) List<PersonajeDTO> personajes,
            
            
            @RequestParam(required = false) List<GeneroDTO> generos,
            
            
            @RequestParam(required = false, defaultValue = "ASC") String orden
    //Dto completo
    ) {
        List<PeliculaDTO> listaPelisDto = pelServ.peliculaFiltro(titulo, imagen, lanzamiento, valoracion, personajes, generos, orden);
        return ResponseEntity.status(HttpStatus.OK).body(listaPelisDto);
    }

    
    //POST
    @PostMapping
    public ResponseEntity<PeliculaDTO> guardarPelicula(@RequestBody PeliculaDTO peliculaDto) {
        PeliculaDTO peliculaGuardada = pelServ.guardarPelicula(peliculaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

//PUT
    @PutMapping("/{peliculaId}")
    public ResponseEntity<PeliculaDTO> modificarPelicula(@PathVariable String peliculaId, @RequestBody PeliculaDTO peliculaDto) {
        PeliculaDTO peliculaEditada = pelServ.modificarPelicula(peliculaId, peliculaDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaEditada);
    }

//DELETE
    @DeleteMapping("/{peliculaId}")
    public ResponseEntity<Void> borrarPelicula(@PathVariable String peliculaId) {
        pelServ.borrarPelicula(peliculaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//POST
    @PostMapping("/{peliculaId}/personaje/{personajeId}")
    public ResponseEntity<Void> agregarPersonaje(@PathVariable String personajeId, @PathVariable String peliculaId) {
        pelServ.agregarPersonaje(personajeId, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//DELETE
    @DeleteMapping("/{peliculaId}/personaje/{personajeId}")
    public ResponseEntity<Void> removerPersonaje(@PathVariable String personajeId, @PathVariable String peliculaId) {
        pelServ.removerPersonaje(personajeId, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//POST
    @PostMapping("/{peliculaId}/genero/{generoId}")
    public ResponseEntity<Void> agregarGenero(@PathVariable String generoId, @PathVariable String peliculaId) {
        pelServ.agregarGenero(generoId, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//DELETE
    @DeleteMapping("/{peliculaId}/genero/{generoId}")
    public ResponseEntity<Void> removerGenero(@PathVariable String generoId, @PathVariable String peliculaId) {
        pelServ.removerGenero(generoId, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
