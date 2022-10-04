package mx.com.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/*
Se designa la tabla usuario para almacenar los datos siguientes
*/
@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idUsuario;
    
    //Mapeamos el username y no debe estar vacio
    @NotEmpty
    private String username;
    
    //Mapeamos el password y no debe estar vacio
    @NotEmpty
    private String password;
    
    //Colocamos la columna que relaciona nuestras tablas
    @OneToMany
    @JoinColumn(name="id_usuario")
    //Generamos una lista para recuperar los roles asignados a un usuario
    private List<Rol> roles;
}
