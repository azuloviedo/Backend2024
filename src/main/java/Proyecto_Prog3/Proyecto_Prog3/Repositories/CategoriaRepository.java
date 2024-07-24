package Proyecto_Prog3.Proyecto_Prog3.Repositories;

import Proyecto_Prog3.Proyecto_Prog3.models.Categoria;
import com.mysql.cj.log.Log;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    Optional<Categoria> findByIdCategoria (Long idCategoria);
    List<Categoria> findByNombreCat (String nombreCat);

}
