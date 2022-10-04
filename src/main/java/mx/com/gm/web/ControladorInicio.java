package mx.com.gm.web;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    /*Inyectamos el servicio*/
    @Autowired
    private PersonaService personaService;
    
    /*Mapeamos la pagina principal*/
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        
        var personas = personaService.listarPersonas();
        //Nos permite ver en el log la informacion y que se esta ejecuntado correctamente
        log.info("usuario que hizo login" + user);
        log.info("ejecuntando el controlador Spring MVC");
        model.addAttribute("personas", personas);
        /*Iniciamos el saldo total en 0 para posteriormente recorrerlo con un for
          Para obtener el saldo de las personas registradas
        */
        var saldoTotal = 0D;
        for(var p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index";
    }
    /*Se realiza accion de agregar*/
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        
        return "modificar";
    }
    /*Se guarda la informacion y se retorna un error en caso de existir*/
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    /*Con el emtodo encontrarPersona se localiza su informacion y se edita*/
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    /*Gracias al @Data se obtienen setters y getters, ene ste caso se elimina el registro*/
    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
    
    
}
