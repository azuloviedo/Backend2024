package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.ProductoRepository;
import Proyecto_Prog3.Proyecto_Prog3.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Optional<Producto> findProductoById(Long idProducto) {
        return productoRepository.findByIdProducto(idProducto);
    }

    @Override
    public List<Producto> findProductosByNombre(String nombreProducto) {
        return productoRepository.findByNombreProducto(nombreProducto);
    }

    @Override
    public List<Producto> findProductosByCategoriaId(Long idCategoria) {
        return productoRepository.findByCategoria_IdCategoria(idCategoria);
    }

    @Override
    public List<Producto> findProductosByStock(int stock) {
        return productoRepository.findByStock(stock);
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
            return this.productoRepository.save(producto);

    }

    @Override
    public void deleteProducto(Producto producto) {
        productoRepository.delete(producto);
    }

    @Override
    public List<Producto> getAllProductos() {
        return (List<Producto>) productoRepository.findAll();
    }
}
