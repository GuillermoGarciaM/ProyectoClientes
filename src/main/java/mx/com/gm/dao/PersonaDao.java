package mx.com.gm.dao;

import mx.com.gm.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/*Al estar heredando de CrudRepository nos permite
crear un crud sin necesidad de obtener getters y setters*/
/*public interface PersonaDao extends CrudRepository<Persona, Long>{
    
}
se realizo el cambio a JPA repository 
*/

public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
