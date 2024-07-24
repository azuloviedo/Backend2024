package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.models.TipoEnvio;
import java.util.List;
import java.util.Optional;

public interface TipoEnvioService {
    Optional<TipoEnvio> findTipoEnvioById(Long idEnvio);
    List<TipoEnvio> findTiposEnvioByNombre(String nombre);
    TipoEnvio createTipoEnvio(TipoEnvio tipoEnvio);
    TipoEnvio updateTipoEnvio(TipoEnvio tipoEnvio);
    void deleteTipoEnvio(TipoEnvio tipoEnvio);
    List<TipoEnvio> getAllTiposEnvio();

}
