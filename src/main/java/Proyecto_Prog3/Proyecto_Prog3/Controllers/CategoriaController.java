package Proyecto_Prog3.Proyecto_Prog3.Controllers;
import Proyecto_Prog3.Proyecto_Prog3.Services.CategoriaService;
import org.springframework.web.bind.annotation.*;
import Proyecto_Prog3.Proyecto_Prog3.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/categoria")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/id/{idCategoria}")
    public Optional<Categoria> findCategoriaById(@PathVariable Long idCategoria) {
        return categoriaService.findCategoriaById(idCategoria);
    }

    @GetMapping(value = "/nombre/{nombreCat}")
    public List<Categoria> findCategoriasByNombre(@PathVariable String nombreCat) {
        return categoriaService.findCategoriasByNombre(nombreCat);
    }

    @PostMapping(value = "")
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaService.createCategoria(categoria);
    }

    @PutMapping(value = "")
    public Categoria updateCategoria(@RequestBody Categoria categoria) {
        return categoriaService.updateCategoria(categoria);
    }

    @DeleteMapping(value = "")
    public void deleteCategoria(@RequestBody Categoria categoria) {
        categoriaService.deleteCategoria(categoria);
    }

    @GetMapping(value = "")
    public List<Categoria> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

}
