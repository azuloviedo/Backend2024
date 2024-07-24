package Proyecto_Prog3.Proyecto_Prog3.Repositories;

import Proyecto_Prog3.Proyecto_Prog3.models.TipoEnvio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoEnvioRepository extends CrudRepository<TipoEnvio, Long> {
    Optional<TipoEnvio> findByIdEnvio(Long idEnvio);
    List<TipoEnvio> findByNombre(String nombre);

}
