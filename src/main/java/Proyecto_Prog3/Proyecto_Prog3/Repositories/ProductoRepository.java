package Proyecto_Prog3.Proyecto_Prog3.Repositories;

import Proyecto_Prog3.Proyecto_Prog3.models.Categoria;
import Proyecto_Prog3.Proyecto_Prog3.models.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    Optional<Producto> findByIdProducto(Long idProducto);
    List<Producto> findByNombreProducto(String nombreProducto);
    List<Producto> findByCategoria_IdCategoria(Long idCategoria);
    List<Producto> findByStock(int stock);

}
