package Proyecto_Prog3.Proyecto_Prog3.Repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import Proyecto_Prog3.Proyecto_Prog3.models.Administrador;
import Proyecto_Prog3.Proyecto_Prog3.models.Usuario;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

   Optional<Usuario> findById(Long id);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

   boolean existsByNombreUsuario(String nombreUsuario);

    List<Usuario> findByNombreAndApellido(String nombre, String apellido);

    // Consulta para obtener solo los usuarios que son clientes
    @Query("SELECT u FROM Cliente u")
    List<Cliente> findAllClientes();

    // Consulta para obtener los usuarios que son clientes y tienen tal DNI
    @Query("SELECT u FROM Cliente u WHERE u.DNI = :dni")
    List<Cliente> findClientesByDNI(@Param("dni") String dni);

 // Consulta para obtener un cliente por su ID
 @Query("SELECT c FROM Cliente c WHERE c.id = :id")
 Optional<Cliente> findClienteById(@Param("id") Long id);


 // Consulta para obtener los usuarios que son clientes y tienen tal mail
    @Query("SELECT u FROM Cliente u WHERE u.mail = :mail")
    List<Cliente> findClientesByMail(@Param("mail") String mail);

}
