package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Persona;

/*Se crea la interfas de servicio pata utilizar las acciones descritas abajo
*/
public interface PersonaService {
        
    public List<Persona> listarPersonas();
    
    public void guardar(Persona persona);
    
    public void eliminar(Persona persona);
    
    public Persona encontrarPersona(Persona persona);
    
    //public Persona editar(Persona persona);
}
