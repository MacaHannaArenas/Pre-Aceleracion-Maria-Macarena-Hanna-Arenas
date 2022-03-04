package AlkemyDisney.MacaHannaArenas.servicio.impl;

import AlkemyDisney.MacaHannaArenas.dto.PersonajeDTO;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDtoBasico;
import AlkemyDisney.MacaHannaArenas.dto.PersonajeDtoFiltro;
import AlkemyDisney.MacaHannaArenas.entidad.Personaje;
import AlkemyDisney.MacaHannaArenas.excepcion.PersonajeExc;
import AlkemyDisney.MacaHannaArenas.excepcion.msje.MsjeExc;
import AlkemyDisney.MacaHannaArenas.mapeo.PersonajeMap;
import AlkemyDisney.MacaHannaArenas.repositorio.PersonajeRepo;
import AlkemyDisney.MacaHannaArenas.repositorio.especificacion.PersonajeEspe;
import AlkemyDisney.MacaHannaArenas.servicio.PersonajeSvs;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class PersonajeSvsImp implements PersonajeSvs {

    @Lazy
    @Autowired
    private PersonajeMap persoMap;

    @Autowired
    private PersonajeRepo persRepo;

    @Lazy
    @Autowired
    private PersonajeEspe persEspe;

    @Override
    public PersonajeDTO guardarPersonaje(PersonajeDTO persDto) {

        try {
            Personaje persEnt = persoMap.perDTO2Ent(persDto);

            Personaje persGuardado = persRepo.save(persEnt);

            PersonajeDTO persGuardadoDTO = persoMap.perEnt2DTO(persGuardado, false);

            return persGuardadoDTO;

        } catch (PersonajeExc exception) {
            throw new PersonajeExc(MsjeExc.DTO_INVALIDO);
        }
    }

    @Override
    public void borrarPersonaje(String personajeId) {

        if (persRepo.existsById(personajeId)) {
            persRepo.deleteById(personajeId);
        } else {
            throw new EntityNotFoundException(MsjeExc.PERSONAJE_NO_ENCONTRADO);
        }
    }

    @Override
    public PersonajeDTO modificarPersonaje(String personajeId, PersonajeDTO personajeDTO) {

        try {

            Personaje personajeEnt = persRepo.getById(personajeId);
            personajeEnt.setNombre(personajeDTO.getNombre());
            personajeEnt.setImagen(personajeDTO.getImagen());
            Personaje entMod = persRepo.save(personajeEnt);
            PersonajeDTO dtoModif = persoMap.perEnt2DTO(entMod, false);

            return dtoModif;

        } catch (PersonajeExc exception) {
            throw new PersonajeExc(MsjeExc.GENERO_NO_ENCONTRADO);

        } catch (Exception exception) {
            throw new PersonajeExc(MsjeExc.DTO_INVALIDO);
        }
    }

    @Override
    public List<PersonajeDtoBasico> personajeBasico() {

        List<Personaje> persEntList = persRepo.findAll();

        List<PersonajeDtoBasico> listaPersDTOBasic = persoMap.perListEntBas2ListDtoBas(persEntList);

        return listaPersDTOBasic;
    }

    @Override
    public List<PersonajeDTO> personajeCompleto() {

        List<Personaje> listaPers = persRepo.findAll();
        List<PersonajeDTO> listaPersDTO = persoMap.perListEnt2ListDto(listaPers, true);

        return listaPersDTO;
    }

    @Override
    public List<PersonajeDTO> personajeFiltro(String nombre,Integer edad, List<String> peliculas, String orden) {
      
        PersonajeDtoFiltro characterFilter = new PersonajeDtoFiltro(nombre,  edad, peliculas, orden);

        List<Personaje> characterEntities = persRepo.findAll(persEspe.porFiltros(characterFilter));

        List<PersonajeDTO> listaPersDTO = persoMap.perListEnt2ListDto(characterEntities, true);

        return listaPersDTO;
    }


@Override
        public PersonajeDTO personajePorId(String personajeId
    ) {

        if (persRepo.existsById(personajeId)) {

            Personaje persEnt = persRepo.getById(personajeId);
            PersonajeDTO persDto = persoMap.perEnt2DTO(persEnt, true);

            return persDto;

        } else {
            throw new EntityNotFoundException(MsjeExc.PERSONAJE_NO_ENCONTRADO);
        }
    }
}
