package by.home.jarward.web.configuration;

import by.home.jarward.web.authentication.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "by.home.jarward.web")
public class SecurityConfiguration {
    @Autowired
    AuthService authService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(authService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain loginFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/all").hasAnyRole("admin")
                        .requestMatchers("/topics/all").hasAnyRole("admin")
                        .requestMatchers("/users/*/delete").hasAnyRole("admin")
                        .requestMatchers("/WEB-INF/views/header.jsp", "/", "/login", "/login?error", "/logout", "/sing-up", "/style").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin()
                    .loginPage("/WEB-INF/views/login.jsp")
                    .usernameParameter("login")
                    .loginProcessingUrl("/loginS")
                    .defaultSuccessUrl("/loginS", true)
                    .failureUrl("/login?error")
                    .permitAll()
                    .and()
                .anonymous().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/logout")
                    .permitAll();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers("/static/**");
    }


}
