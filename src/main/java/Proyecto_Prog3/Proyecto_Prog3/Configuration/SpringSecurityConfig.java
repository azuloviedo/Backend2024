package Proyecto_Prog3.Proyecto_Prog3.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable()) // Deshabilitar la protección CSRF (Cross-Site Request Forgery), ya que no estamos usando sesiones en el front
                .authorizeHttpRequests((authorize) -> authorize

                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll() // Permitir a todos los usuarios loguearse

                        .requestMatchers("/api/usuario/**").permitAll() //Permitir a todos crear un usuario

                        .requestMatchers("/api/pedido/**").permitAll() //Permitir a todos crear un pedido y hacer consultas
                        .requestMatchers("/api/detallePedido/**").permitAll()


                        .requestMatchers(HttpMethod.POST, "api/producto/**").hasAuthority("Administrador") //Permite solo a los administradores cargar productos
                        .requestMatchers(HttpMethod.PUT, "api/producto/**").hasAuthority("Administrador") //Permite solo a los administradores cargar productos
                        .requestMatchers(HttpMethod.DELETE, "api/producto/**").hasAuthority("Administrador")
                        .requestMatchers(HttpMethod.GET, "api/producto/**").permitAll() // Permitir a todos los usuarios hacer consultas sobre los productos

                        .requestMatchers(HttpMethod.POST, "api/tipoEnvio/**").hasAuthority("Administrador") //Permite solo a los administradores cargar un tipo de envio
                        .requestMatchers(HttpMethod.PUT, "api/tipoEnvio/**").hasAuthority("Administrador") //Permite solo a los administradores cargar un tipo de envio
                        .requestMatchers(HttpMethod.DELETE, "api/tipoEnvio/**").hasAuthority("Administrador")
                        .requestMatchers(HttpMethod.GET, "api/tipoEnvio/**").permitAll() // Permitir a todos los usuarios hacer consultas sobre los tipos de envio

                        .requestMatchers(HttpMethod.POST, "api/categoria/**").hasAuthority("Administrador")//Permite solo a los administradores cargar una categoria
                        .requestMatchers(HttpMethod.PUT, "api/categoria/**").hasAuthority("Administrador")//Permite solo a los administradores cargar una categoria
                        .requestMatchers(HttpMethod.DELETE, "api/categoria/**").hasAuthority("Administrador")
                        .requestMatchers(HttpMethod.GET, "api/categoria/**").permitAll() // Permitir a todos los usuarios hacer consultas sobre las categorias


                        .requestMatchers("api/carrito/**").permitAll()
                        .requestMatchers( "/api/carrito/{carritoId}/producto/{productoId}").permitAll() //Permite a todos los usuarios cargar productos al carrito

                        .anyRequest().authenticated() // Cualquier otra solicitud debe ser autenticada
                )
                .httpBasic(Customizer.withDefaults()) // Usar autenticación HTTP básica
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Establecer la creación de sesiones en STATELESS, la aplicación no crea sesiones de usuario
                );
        // Agregamos un filtro personalizado
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
