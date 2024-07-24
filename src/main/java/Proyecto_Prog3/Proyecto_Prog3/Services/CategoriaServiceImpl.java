package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.CategoriaRepository;
import Proyecto_Prog3.Proyecto_Prog3.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Optional<Categoria> findCategoriaById(Long idCategoria) {
        return categoriaRepository.findByIdCategoria(idCategoria);
    }

    @Override
    public List<Categoria> findCategoriasByNombre(String nombreCat) {
        return categoriaRepository.findByNombreCat(nombreCat);
    }

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria updateCategoria(Categoria categoria) {
            return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategoria(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return (List<Categoria>) categoriaRepository.findAll();
    }
}
