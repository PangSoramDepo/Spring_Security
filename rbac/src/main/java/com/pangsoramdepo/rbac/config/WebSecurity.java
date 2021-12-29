package com.pangsoramdepo.rbac.config;

import java.util.List;
import java.util.stream.Collectors;

import com.pangsoramdepo.rbac.filter.RequestFilter;
import com.pangsoramdepo.rbac.model.AccessControl;
import com.pangsoramdepo.rbac.repo.AccessControlRepository;
import com.pangsoramdepo.rbac.service.UserDetailServiceImp;
import com.pangsoramdepo.rbac.util.AccessControlEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImp userDetailService;

    @Autowired
    RequestFilter requestFilter;

    @Autowired
    AccessControlRepository accessControlRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // GET THE DETERMINATION FROM DATABASE FOR DETERMINE THE RIGHT FOR EXECUTE THE END POINT
        List<AccessControl> accessControls = accessControlRepository.findAll();
        for (AccessControl accessControl : accessControls) {
            if (accessControl.getAccessType().equals(AccessControlEnum.PERMIT.name()))
                http.authorizeRequests().antMatchers(accessControl.getApi()).permitAll();
            else
                http.authorizeRequests().antMatchers(accessControl.getApi()).hasAnyRole(accessControl.getRoles().stream().map(obj -> obj.getName()).collect(Collectors.toList()).toArray(new String[0]));
        }

        // DISABLE CSRF AND SET SESSION IS STATELESS TO MAKE SURE NO CACHE IN THE SESSION
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // SET REQUEST FILTER FOR VALIDATION JWT AND USER
        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
