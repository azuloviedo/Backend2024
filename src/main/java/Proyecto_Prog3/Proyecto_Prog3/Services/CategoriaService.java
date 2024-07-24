package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.models.Categoria;
import java.util.List;
import java.util.Optional;
public interface CategoriaService {
    Optional<Categoria> findCategoriaById(Long idCategoria);
    List<Categoria> findCategoriasByNombre(String nombreCat);
    Categoria createCategoria(Categoria categoria);
    Categoria updateCategoria(Categoria categoria);
    void deleteCategoria(Categoria categoria);
    List<Categoria> getAllCategorias();
}
