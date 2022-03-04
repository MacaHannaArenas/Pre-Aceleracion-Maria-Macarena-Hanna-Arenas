package AlkemyDisney.MacaHannaArenas.repositorio.especificacion;


import AlkemyDisney.MacaHannaArenas.dto.PersonajeDtoFiltro;
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
public class PersonajeEspe {

    
    public Specification<Personaje> porFiltros(PersonajeDtoFiltro filtrosDtoPersonaje) {

        // LAMBDA FUNCTION
        return (root, query, criteriaBuilder) -> {

            
            List<Predicate> filtros = new ArrayList<>();

                        
            if (StringUtils.hasLength(filtrosDtoPersonaje.getNombre())) {
                filtros.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtrosDtoPersonaje.getNombre().toLowerCase() + "%"));
            }

             if (StringUtils.hasLength(filtrosDtoPersonaje.getImagen())) {
                filtros.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("imagen")),
                                "%" + filtrosDtoPersonaje.getImagen().toLowerCase() + "%"));
            }

          
            if (filtrosDtoPersonaje.getEdad() != null) {
                filtros.add(
                        criteriaBuilder.equal(root.get("edad"), filtrosDtoPersonaje.getEdad())
                );
            }

        
            if (filtrosDtoPersonaje.getPeso() != null) {
                filtros.add(
                        criteriaBuilder.like(
                                root.get("peso").as(String.class),
                                "%" + filtrosDtoPersonaje.getPeso() + "%"
                        )
                );
            }

         
            if (!CollectionUtils.isEmpty(filtrosDtoPersonaje.getPeliculas())) {
                Join<Personaje, Pelicula> join = root.join("personajesPelicula", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                filtros.add(moviesId.in(filtrosDtoPersonaje.getPeliculas()));
            }

       
            query.distinct(true);

    
            String orderByField = "nombre";
            query.orderBy(
                    filtrosDtoPersonaje.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField))
            );

         
            return criteriaBuilder.and(filtros.toArray(new Predicate[0]));
        };
    }
}
