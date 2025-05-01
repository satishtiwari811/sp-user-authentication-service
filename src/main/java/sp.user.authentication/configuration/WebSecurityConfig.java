package sp.user.authentication.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sp.user.authentication.service.UserAuthenticationServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserAuthenticationServiceImpl userAuthenticationServiceImpl;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers("/api/v1/auth/registerUser","/h2-console/**").
               permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic(Customizer.withDefaults());

        http.headers().frameOptions().disable(); // Added this line because H2 console was not

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   //This been is used to authenticate the user
    // and also to encode the password
    // This bean is used in the authentication manager
    // This bean is used in the security filter chain
    // This bean is used in the user authentication service
   @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userAuthenticationServiceImpl)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

}