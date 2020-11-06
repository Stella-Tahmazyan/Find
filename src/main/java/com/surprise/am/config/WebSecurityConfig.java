package com.surprise.am.config;

import com.surprise.am.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDetailServiceImpl userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
   http.csrf().disable()
       .formLogin()
       .loginPage("/login")

       .usernameParameter("username")
       .passwordParameter("password")
       .defaultSuccessUrl("/loginSuccess")
   . failureUrl("/page/register")
       .permitAll()
       .and().logout()
       .logoutSuccessUrl("/login")
       .and()
       .authorizeRequests()
       .antMatchers("/get/category").hasAuthority("FEMALE")
//       .antMatchers("/page").permitAll()
   ;
//       .c("/").permitAll()
//       .antMatchers("/login").permitAll()
//       .antMatchers("/get/category").permitAll()


  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
  }
}
