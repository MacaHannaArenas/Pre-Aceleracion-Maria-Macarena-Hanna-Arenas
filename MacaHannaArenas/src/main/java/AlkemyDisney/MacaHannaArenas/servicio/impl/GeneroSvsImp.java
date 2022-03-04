package AlkemyDisney.MacaHannaArenas.servicio.impl;





import AlkemyDisney.MacaHannaArenas.dto.GeneroDTO;
import AlkemyDisney.MacaHannaArenas.dto.GeneroDtoBasico;
import AlkemyDisney.MacaHannaArenas.entidad.Genero;
import AlkemyDisney.MacaHannaArenas.excepcion.GeneroExc;
import AlkemyDisney.MacaHannaArenas.excepcion.msje.MsjeExc;
import AlkemyDisney.MacaHannaArenas.mapeo.GeneroMap;
import AlkemyDisney.MacaHannaArenas.repositorio.GeneroRepo;
import AlkemyDisney.MacaHannaArenas.servicio.GeneroSvs;
import AlkemyDisney.MacaHannaArenas.validaciones.dtoVal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;



@Lazy
@Service
public class GeneroSvsImp implements GeneroSvs {

    @Autowired
    private GeneroMap genMap;

    @Autowired
    private GeneroRepo genRepo;

    @Autowired
    private dtoVal valDTO;

    @Override
    public GeneroDTO guardarGenero(GeneroDTO generoDto) {

        if (valDTO.generoDtoVal(generoDto)) {

            Genero genEnt = genMap.genDTO2Ent(generoDto);
            Genero generoEntGuardado = genRepo.save(genEnt);
            GeneroDTO generoDtoGuardado = genMap.genEnt2DTO(generoEntGuardado);

            return generoDtoGuardado;

        } else {
            throw new GeneroExc(MsjeExc.DTO_INVALIDO);
        }
    }

    @Override
    public GeneroDTO modificarGenero(String generoId, GeneroDTO generoDto) {

        if (genRepo.existsById(generoId)) {
            if (valDTO.generoDtoVal(generoDto)) {

                Genero generoGuardado = genRepo.getById(generoId);
                generoGuardado.setNombre(generoDto.getNombre());
                Genero generoEntModif = genRepo.save(generoGuardado);
                GeneroDTO modifiedGenreDTO = genMap.genEnt2DTO(generoEntModif);

                return modifiedGenreDTO;

            } else {
                throw new GeneroExc(MsjeExc.DTO_INVALIDO);
            }
        } else {
            throw new GeneroExc(MsjeExc.GENERO_NO_ENCONTRADO);
        }
    }

    @Override
    public void borrarGeneroId(String generoId) {


        if (genRepo.existsById(generoId)) {
            genRepo.deleteById(generoId);
        } else {
            throw new GeneroExc(MsjeExc.GENERO_NO_ENCONTRADO);
        }
    }


    @Override
    public List<GeneroDTO> generoCompleto() {

        List<Genero> listaGenEnt = genRepo.findAll();
        List<GeneroDTO> listaGenDTO = genMap.generoEntList2DTOList(listaGenEnt);

        return listaGenDTO;
    }

    @Override
    public Genero generoPorId(String generoId) {

        Optional<Genero> genEnt = genRepo.findById(generoId);
        if (!genEnt.isPresent()) {
            throw new GeneroExc(MsjeExc.GENERO_NO_ENCONTRADO);
        }

        return genEnt.get();
    }

    @Override
    public List<GeneroDtoBasico> generoBasico() {

        List<Genero> listaGenEnt = genRepo.findAll();
        List<GeneroDtoBasico> generoDtoBasicList = genMap.genListEntBas2ListDtoBas(listaGenEnt);

        return generoDtoBasicList;
    }

}
