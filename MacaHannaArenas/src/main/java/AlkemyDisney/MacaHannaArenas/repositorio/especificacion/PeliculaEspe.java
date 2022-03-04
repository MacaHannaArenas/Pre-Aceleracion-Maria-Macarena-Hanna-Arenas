package AlkemyDisney.MacaHannaArenas.repositorio.especificacion;

import AlkemyDisney.MacaHannaArenas.dto.PeliculaDtoFiltro;
import AlkemyDisney.MacaHannaArenas.entidad.Genero;
import AlkemyDisney.MacaHannaArenas.entidad.Pelicula;
import AlkemyDisney.MacaHannaArenas.entidad.Personaje;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class PeliculaEspe {

    public Specification<Pelicula> getFiltered(PeliculaDtoFiltro filtrosPelicula) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> filtros = new ArrayList<>();
           
          
            if (StringUtils.hasLength(filtrosPelicula.getTitulo())) {
                filtros.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtrosPelicula.getTitulo().toLowerCase() + "%"
                        )
                );
            }
/*
            if (filtrosPelicula.getImagen() != null) {
                filtros.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("imagen")),
                                "%" + filtrosPelicula.getImagen().toLowerCase() + "%"));
            }

            if (filtrosPelicula.getLanzamiento() != null) {
                filtros.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("lanzamiento")),
                                "%" + filtrosPelicula.getLanzamiento().toLowerCase() + "%"));
            }

            if (filtrosPelicula.getValoracion() != null) {
                filtros.add(
                        criteriaBuilder.equal(root.get("valoracion"), filtrosPelicula.getValoracion()));
            }

            if (!CollectionUtils.isEmpty(filtrosPelicula.getPersonajes())) {
                Join<Pelicula, Personaje> join = root.join("personajes", JoinType.INNER);
                Expression<String> personajeId = join.get("personajeId");
                filtros.add(personajeId.in(filtrosPelicula.getPersonajes()));
            }
*/
            if (!CollectionUtils.isEmpty(filtrosPelicula.getGeneros())) {
                Join<Pelicula, Genero> join = root.join("pelicula_generos", JoinType.INNER);
                Expression<String> generoId = join.get("generoId");
                filtros.add(generoId.in(filtrosPelicula.getGeneros()));
            }

            query.distinct(true);
            
            String orderByField = "titulo";

            query.orderBy(
                    
                    filtrosPelicula.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField)));
       
            return criteriaBuilder.and(filtros.toArray(new Predicate[0]));
        };
    }
}
