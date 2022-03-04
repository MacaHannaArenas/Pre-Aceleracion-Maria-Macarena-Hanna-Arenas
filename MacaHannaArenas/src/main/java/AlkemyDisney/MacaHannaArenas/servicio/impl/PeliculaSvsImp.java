package AlkemyDisney.MacaHannaArenas.servicio.impl;

import AlkemyDisney.MacaHannaArenas.dto.PeliculaDTO;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDtoBasico;
import AlkemyDisney.MacaHannaArenas.dto.PeliculaDtoFiltro;
import AlkemyDisney.MacaHannaArenas.entidad.Genero;
import AlkemyDisney.MacaHannaArenas.entidad.Pelicula;
import AlkemyDisney.MacaHannaArenas.entidad.Personaje;
import AlkemyDisney.MacaHannaArenas.excepcion.GeneroExc;
import AlkemyDisney.MacaHannaArenas.excepcion.PeliculaExc;
import AlkemyDisney.MacaHannaArenas.excepcion.PersonajeExc;
import AlkemyDisney.MacaHannaArenas.excepcion.msje.MsjeExc;
import AlkemyDisney.MacaHannaArenas.mapeo.PeliculaMap;
import AlkemyDisney.MacaHannaArenas.repositorio.GeneroRepo;
import AlkemyDisney.MacaHannaArenas.repositorio.PeliculaRepo;
import AlkemyDisney.MacaHannaArenas.repositorio.PersonajeRepo;
import AlkemyDisney.MacaHannaArenas.repositorio.especificacion.PeliculaEspe;
import AlkemyDisney.MacaHannaArenas.servicio.PeliculaSvs;
import AlkemyDisney.MacaHannaArenas.validaciones.dtoVal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class PeliculaSvsImp implements PeliculaSvs {

    @Autowired
    private PersonajeRepo personajeRep;

    @Autowired
    private PeliculaMap pelMap;

    @Autowired
    private PeliculaRepo peliculaRep;

    @Lazy
    @Autowired
    private PeliculaEspe pelEspe;

    @Autowired
    private GeneroRepo generoRep;

    @Autowired
    private dtoVal valDTO;

    @Override
    public PeliculaDTO guardarPelicula(PeliculaDTO peliculaDTO) {
        try {
            Pelicula pelEnt = pelMap.pelDTO2Ent(peliculaDTO, false);
            Pelicula peliculaGuardada = peliculaRep.save(pelEnt);
            PeliculaDTO peliculaDtoGuardado = pelMap.pelEnt2DTO(peliculaGuardada, false);

            return peliculaDtoGuardado;
        } catch (PeliculaExc exception) {
            throw new PeliculaExc(MsjeExc.DTO_INVALIDO);
        }

    }

    @Override
    public PeliculaDTO modificarPelicula(String peliculaId, PeliculaDTO peliculaDTO) {

        try {

            Pelicula peliculaGuardada = peliculaRep.getById(peliculaId);

            pelMap.pelRefrescarEnt(peliculaGuardada, peliculaDTO);

            Pelicula peliculaEntModif = peliculaRep.save(peliculaGuardada);

            PeliculaDTO peliculaDTOModif = pelMap.pelEnt2DTO(peliculaEntModif, false);

            return peliculaDTOModif;

        } catch (PeliculaExc exception) {
            throw new PeliculaExc(MsjeExc.PELICULA_NO_ENCONTRADO);
        } catch (Exception exception) {
            throw new PersonajeExc(MsjeExc.DTO_INVALIDO);
        }

    }

    @Override
    public void borrarPelicula(String peliculaId) {

        if (peliculaRep.existsById(peliculaId)) {
            peliculaRep.deleteById(peliculaId);
        } else {
            throw new PeliculaExc(MsjeExc.PELICULA_NO_ENCONTRADO);
        }
    }

    @Override
    public List<PeliculaDtoBasico> peliculaBasica() {

        List<Pelicula> listaPelEnt = peliculaRep.findAll();
        List<PeliculaDtoBasico> peliculaDTOBasicList = pelMap.pelListEntBasc2ListDtoBas(listaPelEnt);

        return peliculaDTOBasicList;
    }

    @Override
    public List<PeliculaDTO> peliculaCompleta() {

        List<Pelicula> listaPelEnt = peliculaRep.findAll();
        List<PeliculaDTO> listaPelsDTO = pelMap.pelListEnt2ListDTO(listaPelEnt, true);

        return listaPelsDTO;
    }

    @Override
    public List<PeliculaDTO> peliculaFiltro(String titulo, List<String> generos, String orden) {

        PeliculaDtoFiltro pelDtoFiltros = new PeliculaDtoFiltro(titulo, generos, orden);
        List<Pelicula> listaPelEnt = peliculaRep.findAll(pelEspe.getFiltered(pelDtoFiltros));
        List<PeliculaDTO> listaPelsDTO = pelMap.pelListEnt2ListDTO(listaPelEnt, true);

        return listaPelsDTO;

    }

    @Override
    public PeliculaDTO peliculaPorId(String peliculaId) {

        if (peliculaRep.existsById(peliculaId)) {
            Pelicula peliculaEnt = peliculaRep.getById(peliculaId);
            PeliculaDTO peliculaDTO = pelMap.pelEnt2DTO(peliculaEnt, true);
            return peliculaDTO;
        } else {
            throw new PeliculaExc(MsjeExc.PELICULA_NO_ENCONTRADO);
        }
    }

    @Override
    public void agregarPersonaje(String personajeId, String peliculaId) {

        try {
            Pelicula pelEnt = peliculaRep.getById(peliculaId);
            Personaje personajeEnt = personajeRep.getById(personajeId);

            List<Personaje> personajes = pelEnt.getPersonajes();

            personajes.add(personajeEnt);
            pelEnt.setPersonajes(personajes);
            peliculaRep.save(pelEnt);

        } catch (PeliculaExc exc) {
            throw new PeliculaExc(MsjeExc.PELICULA_NO_ENCONTRADO);

        } catch (PersonajeExc exc) {
            throw new PersonajeExc(MsjeExc.PERSONAJE_NO_ENCONTRADO);
        }
    }

    @Override
    public void removerPersonaje(String personajeId, String peliculaId) {

        try {

            Pelicula pelEnt = peliculaRep.getById(peliculaId);
            Personaje personajeEnt = personajeRep.getById(personajeId);

            List<Personaje> personajes = pelEnt.getPersonajes();

            personajes.remove(personajeEnt);
            pelEnt.setPersonajes(personajes);
            peliculaRep.save(pelEnt);

        } catch (PeliculaExc exc) {
            throw new PeliculaExc(MsjeExc.PELICULA_NO_ENCONTRADO);

        } catch (PersonajeExc exc) {
            throw new PersonajeExc(MsjeExc.PERSONAJE_NO_ENCONTRADO);
        }

    }

    @Override
    public void agregarGenero(String generoId, String peliculaId) {

        try {

            Pelicula pelEnt = peliculaRep.getById(peliculaId);
            Genero genre = generoRep.getById(generoId);

            List<Genero> generos = pelEnt.getGeneros();

            generos.add(genre);
            pelEnt.setGeneros(generos);
            peliculaRep.save(pelEnt);

        } catch (PeliculaExc exc) {
            throw new PeliculaExc(MsjeExc.PELICULA_NO_ENCONTRADO);

        } catch (GeneroExc exc) {
            throw new GeneroExc(MsjeExc.GENERO_NO_ENCONTRADO);
        }

    }

    @Override
    public void removerGenero(String generoId, String peliculaId) {

        try {

            Pelicula pelEnt = peliculaRep.getById(peliculaId);
            Genero genre = generoRep.getById(generoId);

            List<Genero> generos = pelEnt.getGeneros();

            generos.remove(genre);
            pelEnt.setGeneros(generos);
            peliculaRep.save(pelEnt);

        } catch (PeliculaExc exc) {
            throw new PeliculaExc(MsjeExc.PELICULA_NO_ENCONTRADO);

        } catch (GeneroExc exc) {
            throw new GeneroExc(MsjeExc.GENERO_NO_ENCONTRADO);
        }

    }
}
