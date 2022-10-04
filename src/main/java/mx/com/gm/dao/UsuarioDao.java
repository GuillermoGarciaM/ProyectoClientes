package mx.com.gm.dao;

import mx.com.gm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
//Se establece LONG ya que la llave primaria en Usuario es de este tipo
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    //Recupera un obj de tipo usuario por el username
    Usuario findByUsername(String username);
}
