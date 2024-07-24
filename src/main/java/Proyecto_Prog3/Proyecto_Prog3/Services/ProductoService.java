package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.models.Pedido;
import Proyecto_Prog3.Proyecto_Prog3.models.Producto;
import Proyecto_Prog3.Proyecto_Prog3.models.TipoEnvio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductoService {
    Optional<Producto> findProductoById(Long idProducto);
    List<Producto> findProductosByNombre(String nombreProducto);
    List<Producto> findProductosByCategoriaId(Long idCategoria);
    List<Producto> findProductosByStock(int stock);
    Producto createProducto(Producto producto);
    Producto updateProducto(Producto producto);
    void deleteProducto(Producto producto);
    List<Producto> getAllProductos();

}
