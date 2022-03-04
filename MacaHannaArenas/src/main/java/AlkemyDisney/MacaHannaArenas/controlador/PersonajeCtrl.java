package AlkemyDisney.MacaHannaArenas.controlador;


import AlkemyDisney.MacaHannaArenas.dto.PersonajeDTO;
import AlkemyDisney.MacaHannaArenas.servicio.PersonajeSvs;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
@RequestMapping("personajes")
public class PersonajeCtrl {

    @Lazy
    @Autowired
    private PersonajeSvs perServ;

  
       @GetMapping("/{personajeId}")
    public ResponseEntity<PersonajeDTO> personajePorId(@PathVariable String personajeId) {
        PersonajeDTO personajeDto = perServ.personajePorId(personajeId);
        return ResponseEntity.ok(personajeDto);
    }
/*
     @GetMapping
    public ResponseEntity<List<PersonajeDtoBasico>> getAllGenres() {
        List<PersonajeDtoBasico> genreList = perServ.personajeBasico();
        return ResponseEntity.ok().body(genreList);
    }
    */
   
    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> personajesPorFiltro(

            @RequestParam(required = false) String nombre,
      
            @RequestParam(required = false) Integer edad,
   

            @RequestParam(required = false) List<String> peliculas,
            @RequestParam(required = false, defaultValue = "ASC") String orden) {
        List<PersonajeDTO> ListaPersonajesDto = perServ.personajeFiltro(nombre, edad, peliculas, orden);
        return ResponseEntity.ok(ListaPersonajesDto);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> guardarPersonaje(@RequestBody PersonajeDTO personajeDto) {
        PersonajeDTO personajeGuardado = perServ.guardarPersonaje(personajeDto);
              return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);

    }

   
    @PutMapping("/{personajeId}")
    public ResponseEntity<PersonajeDTO> modificarPersonaje(@PathVariable String personajeId, @RequestBody PersonajeDTO personajeDto) {
        PersonajeDTO editarPersonaje = perServ.modificarPersonaje(personajeId, personajeDto);
        return ResponseEntity.ok(editarPersonaje);
    }


    @DeleteMapping("/{personajeId}")
    public ResponseEntity<PersonajeDTO> borrarPersonaje(@PathVariable String personajeId) {
        perServ.borrarPersonaje(personajeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

  
 
}
