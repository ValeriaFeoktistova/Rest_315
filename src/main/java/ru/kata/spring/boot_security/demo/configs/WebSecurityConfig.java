package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler,
                             @Lazy UserService userService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }/////////////////////////////////// эти настройки только для тестирования json
   // @Override
    protected void configure2(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll(); // Разрешаем все запросы без аутентификации
    }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET).permitAll() // Разрешить GET-запросы для всех
                    .antMatchers(HttpMethod.POST).permitAll() // Разрешить POST-запросы для всех
                    .antMatchers(HttpMethod.PUT).permitAll() // Разрешить PUT-запросы для всех
                    .antMatchers(HttpMethod.DELETE).permitAll() // Разрешить DELETE-запросы для всех
                    .anyRequest().authenticated() // Все остальные запросы требуют авторизации
                    .and()
                    .csrf().disable(); // Отключить защиту от CSRF-атак (для упрощения примера)
        }
    }
////////////////////////////// а эти настройки для секьюрити
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .successHandler(successUserHandler)
                .permitAll();
        http
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }*/
