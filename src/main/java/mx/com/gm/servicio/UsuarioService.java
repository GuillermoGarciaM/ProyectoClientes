package mx.com.gm.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//Debe tener el nombre userDetailsService de lo contario Spring no lo reconoce
@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{
    
    //Inyectamos la clase USuarioDAO
    @Autowired
    private UsuarioDao usuarioDao;
    
    //Filtra por el username
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        //Lanzamos la excepcion en caso de no encontrar usuario
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        /*definimos variable para recuperar roles, con la clase
          GrantedAuthority ya que es necesaria para el security con spring y JPA
        */
        var roles = new ArrayList<GrantedAuthority>();
        
        //Recorremos los roles definido en clase Usuario.java
        for(Rol rol: usuario.getRoles()){
            /*Recuperamos los roles pasando el nombre del rol a la clase
              SimpleGrantedAuthority
            */
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        /*Regresa USerDetails con la clase estabelcida en Spring User
          obteniendo los getes gracias al @DATA de usuario.java
        */
        return new User(usuario.getUsername(),usuario.getPassword(),roles);
    }
    
}
