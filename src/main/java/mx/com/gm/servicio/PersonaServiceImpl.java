package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService{
    
    @Autowired
    private PersonaDao personaDao;
    
    //Esta anotacion nos permite leer la lista de personas y mostrarla
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();      
    
    }
    
    
    /*Con esta anotacion guardamos una persona*/
    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }
    
    /*Con esta accion eliminamos un registro*/
    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }
    
    /*Si la persona ya existe la devuelve*/
    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
