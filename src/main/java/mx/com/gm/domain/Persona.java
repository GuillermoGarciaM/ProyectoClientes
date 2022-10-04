package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable{
    //Se serializa para la transmision del objeto
    private static final long serialVersionUID = 1L;
    /*Se generan los atributos de la persona que seran guardados
    Se selecciona el Id ya que este nos permitira hacer las acciones correspondientes
    igualmente se selecciona que no esten vacios
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    @Email
    private String email;
    
    
    private String telefono;
    
    //Este campop no debe estar vacio pero al ser numeros se usa esta anotacion
    //En lugar de NotEmpty
    @NotNull
    private Double saldo;
}
