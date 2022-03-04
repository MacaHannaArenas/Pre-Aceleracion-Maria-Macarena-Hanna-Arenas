package AlkemyDisney.MacaHannaArenas.repositorio.especificacion;

import AlkemyDisney.MacaHannaArenas.dto.PeliculaDtoFiltro;
import AlkemyDisney.MacaHannaArenas.entidad.Genero;
import AlkemyDisney.MacaHannaArenas.entidad.Pelicula;
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

            if (!CollectionUtils.isEmpty(filtrosPelicula.getGeneros())) {
                Join<Pelicula, Genero> join = root.join("generos", JoinType.INNER);
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
