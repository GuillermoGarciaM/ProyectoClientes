package mx.com.gm.web;

//import mx.com.gm.servicio.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    //Inyectamos UserDetailsService ya que es la encargada de validar la informacion
    @Autowired
    private UserDetailsService userDetailsService;
    
    //Seleccionamos la encriptacion utilizada
    
    //@Bean
        public BCryptPasswordEncoder passwordEncoder(){        
        return new BCryptPasswordEncoder();
    }
    
    /*Spring security utilizara lo establecido en la clase UsuarioService
      gracias al nombre de userDetailsService
    */
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    /*
    Se establecen los roles y accesos o permisos de los mismos
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .antMatchers("/editar/**","/agregar/**","/eliminar")
                    .hasRole("ADMIN")
                .antMatchers("/")
                    .hasAnyRole("USER","ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/errores/403")
                ;
    }
}
