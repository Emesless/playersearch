package ru.emesless.searchplayers.config;

import ru.emesless.searchplayers.security.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    //favicon
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/home", "/profile/**").permitAll()
            .antMatchers("/registration").permitAll()
            .antMatchers("/api/registration/contacts").permitAll()
            .antMatchers("/api/game/**").permitAll()
            .antMatchers("/api/servers/**").permitAll()
            .antMatchers("/game/**").permitAll()
            .antMatchers("/chat/**").permitAll()
            .antMatchers("/app/**").permitAll()
            .antMatchers("/gs-guide-websocket/**").permitAll()
            .antMatchers("/blog", "/servers/**", "/favorite/**", "/feedback").permitAll()
            .antMatchers("/api/registration").permitAll()
            .antMatchers("/favicon.ico", "/gamewall.jpg", "/userprof.jpg").permitAll()
            .antMatchers("/image/**", "/static/**", "/static/*").permitAll()
            .antMatchers("/", "/login**", "/js/**", "/error**", "**/favicon.ico").permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/auth/login").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
