package io.javabrains.springbootsecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSrc;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set your configuration on the auth object
    	// Using H2 InMemory DB for Authentication
        auth.inMemoryAuthentication()
                .withUser("blah")
                .password("blah")
                .roles("USER")
                .and()
                .withUser("foo")
                .password("foo")
                .roles("ADMIN");
        
        // Using JDBC for Authentication with H2 InMemory DB(DEFAULT SCHEMA)
        auth.jdbcAuthentication().dataSource(dataSrc)
        .withDefaultSchema().withUser(
        		User.withUsername("Surya").password("pass").roles("USER")
        ).withUser(
        		User.withUsername("admin").password("pass").roles("ADMIN")
        );
        

        // Using JDBC for Authentication with H2 InMemory DB(CUSTOM SCHEMA)
        auth.jdbcAuthentication().dataSource(dataSrc)
        .usersByUsernameQuery("select username, password, enabled from users"
        		+"where username=?")
        .authoritiesByUsernameQuery("select username, authority from authorities"
        		+"where username=?");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// For Authorization-->ORDERED FROM MOST RESTRICTED ROLE TO LEAST RESTRICTED ROLE
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
}
