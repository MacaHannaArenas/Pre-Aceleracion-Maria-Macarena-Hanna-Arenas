package AlkemyDisney.MacaHannaArenas.mapeo;



import AlkemyDisney.MacaHannaArenas.dto.GeneroDTO;
import AlkemyDisney.MacaHannaArenas.dto.GeneroDtoBasico;
import AlkemyDisney.MacaHannaArenas.entidad.Genero;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

@Component
public class GeneroMap {


    public Genero genDTO2Ent(GeneroDTO generoDto) {

        Genero generoEnt = new Genero();
        generoEnt.setNombre(generoDto.getNombre());
        generoEnt.setImagen(generoDto.getImagen());

        return generoEnt;
    }


    public GeneroDTO genEnt2DTO(Genero generoEnt) {

        GeneroDTO generoDto = new GeneroDTO();
        generoDto.setGeneroId(generoEnt.getGeneroId());
        generoDto.setNombre(generoEnt.getNombre());
        generoDto.setImagen(generoEnt.getImagen());

        return generoDto;
    }


    public List<GeneroDTO> generoEntList2DTOList(List<Genero> generosEnt) {

        List<GeneroDTO> listaGenerosDto = new ArrayList<>();

        for (Genero generoEnt : generosEnt) {
            listaGenerosDto.add(this.genEnt2DTO(generoEnt));
        }
        return listaGenerosDto;
    }

    public GeneroDtoBasico genEntBas2DtoBas(Genero generoEnt) {

        GeneroDtoBasico generoDtoBasic = new GeneroDtoBasico();
        generoDtoBasic.setGeneroId(generoEnt.getGeneroId());
        generoDtoBasic.setNombre(generoEnt.getNombre());
        generoDtoBasic.setImagen(generoEnt.getImagen());
        return generoDtoBasic;
    }

    public List<GeneroDtoBasico> genListEntBas2ListDtoBas(List<Genero> generosEnt) {

        List<GeneroDtoBasico> listaGeneroDtoBasico = new ArrayList<>();

        for (Genero generoEnt : generosEnt) {
            listaGeneroDtoBasico.add(genEntBas2DtoBas(generoEnt));
        }
        return listaGeneroDtoBasico;
    }

    List<Genero> genListDTO2ListEnt(List<GeneroDTO> generosEnt) {

        List<Genero> listaGenerosEnt = new ArrayList<>();

        for (GeneroDTO generoDto : generosEnt) {
            listaGenerosEnt.add(genDTO2Ent(generoDto));
        }
        return listaGenerosEnt;
    }

}
