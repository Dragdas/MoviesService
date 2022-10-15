package com.kkulpa.moviesservice.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


        //TODO zmienic na security filter chain
        //https://www.youtube.com/watch?v=s4X4SJv2RrU&ab_channel=DanVega
        @Override
        protected void  configure(HttpSecurity http) throws Exception{
            http
                    .authorizeRequests()
                    .antMatchers("/api/public/*")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();
        }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails testUser = User.builder()
                                    .username("tu")
                                    .password("123")
                                    .roles("USER")
                                    .build();

        return new InMemoryUserDetailsManager(testUser);
    }
}
