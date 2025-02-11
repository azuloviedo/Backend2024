package Proyecto_Prog3.Proyecto_Prog3.Controllers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import Proyecto_Prog3.Proyecto_Prog3.Services.ProductoService;
import Proyecto_Prog3.Proyecto_Prog3.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value= "/api/producto")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping(value = "/id/{idProducto}")
    public Optional<Producto> findProductoById(@PathVariable Long idProducto) {
        return productoService.findProductoById(idProducto);
    }

    @GetMapping(value = "/nombre/{nombreProducto}")
    public List<Producto> findProductosByNombre(@PathVariable String nombreProducto) {
        return productoService.findProductosByNombre(nombreProducto);
    }

    @GetMapping(value = "/categoria/{idCategoria}")
    public List<Producto> findProductosByCategoriaId(@PathVariable Long idCategoria) {
        return productoService.findProductosByCategoriaId(idCategoria);
    }

    @GetMapping(value = "/stock/{stock}")
    public List<Producto> findProductosByStock(@PathVariable int stock) {
        return productoService.findProductosByStock(stock);
    }

    @PostMapping(value= "")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.createProducto(producto);
    }

    /*@PutMapping(value = "")
    public Producto updateProducto(@RequestBody Producto producto) {
        return productoService.updateProducto(producto);
    }*/

    @PutMapping(value = "/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setIdProducto(id); // Asegurarse que el ID coincida
        return productoService.updateProducto(producto);
    }

    /*@DeleteMapping(value = "")
    public void deleteProducto(@RequestBody Producto producto) {
        productoService.deleteProducto(producto);
    }*/

    @DeleteMapping(value = "/{id}")
    public void deleteProducto(@PathVariable Long id) {
        Producto producto = productoService.findProductoById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoService.deleteProducto(producto);
    }

    /*@GetMapping(value = "")
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }*/

    @GetMapping(value = "")
    public List<Producto> getAllProductos(HttpServletRequest request) {
        System.out.println("GET /api/producto - Headers recibidos:");
        Collections.list(request.getHeaderNames()).forEach(headerName ->
                System.out.println(headerName + ": " + request.getHeader(headerName))
        );
        return productoService.getAllProductos();
    }

}
