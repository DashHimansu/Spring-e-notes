package com.ENotes.ENotesTaker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserInfoServiceImpl();
    }
    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/",
//        "/home", "/login","/signup")
//        .permitAll().anyRequest().authenticated()).formLogin((form) ->
//        form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/user/home"));

        http.csrf(csrf-> csrf.disable()).authorizeHttpRequests((requests) -> requests.requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/**")
                .permitAll().anyRequest().authenticated()).formLogin((form) ->
                form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/user/addnotes"));
//        .requestMatchers("/", "/home", "/login","/signup","/register-user")

        http.authenticationProvider(getDaoAuthenticationProvider());
        return http.build();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        /*
//         * http.authorizeHttpRequests((requests) -> requests.requestMatchers("/",
//         * "/home", "/unsecuregreetings")
//         * .permitAll().anyRequest().fullyAuthenticated()) .formLogin((form) ->
//         * form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll());
//         */
//
//        /*
//         * http .authorizeHttpRequests((requests)->requests.requestMatchers("/",
//         * "/home", "/unsecuregreetings", "/authenticate") .permitAll() .anyRequest()
//         * .fullyAuthenticated() ) .formLogin();
//         */
//
//        /*
//         * http.addFilterAfter( new LDAPUserNamePwdAuthnFilter(),
//         * UsernamePasswordAuthenticationFilter.class); return http.build();
//         */
//
//
//        /** http .httpBasic() .and()
//         .authorizeHttpRequests(
//         (requests)->requests.requestMatchers
//         ("/", "/home", "/unsecuregreetings", "/authenticate","/h2-console")
//         .permitAll()
//         .requestMatchers("/v1/api/authn").authenticated()
//         .requestMatchers("/v1/api/courses").hasAnyAuthority("ROLE_VTL_LECTURER","ROLE_VTL_TEACHER", "ROLE_VTL_TRAINER","ROLE_VTL_LEARNER")
//         .requestMatchers("/v1/api/addCourse").hasAnyAuthority("ROLE_VTL_LECTURER","ROLE_VTL_TEACHER", "ROLE_VTL_TRAINER")
//         .requestMatchers("/v1/api/deleteCourse").hasAuthority("ROLE_VTL_LECTURER")
//         .anyRequest().authenticated()
//         )
//         .csrf()
//         .disable()
//         .headers()
//         .frameOptions()
//         .disable(); **/
//
//        http .httpBasic() .and()
//                .authorizeHttpRequests(
//                        (requests)->requests.requestMatchers
//                                        ("/", "/home", "/unsecuregreetings", "/authenticate","/h2-console")
//                                .permitAll()
//                                .requestMatchers("/v1/api/authn").authenticated()
//                                .requestMatchers("/v1/api/courses").hasAnyAuthority("ROLE_VTL_LECTURER","ROLE_VTL_TEACHER", "ROLE_VTL_TRAINER","ROLE_VTL_LEARNER")
//                                .requestMatchers("/v1/api/addCourse").hasAnyAuthority("ROLE_VTL_LECTURER","ROLE_VTL_TEACHER", "ROLE_VTL_TRAINER")
//                                .requestMatchers("/v1/api/deleteCourse").hasAuthority("ROLE_VTL_LECTURER")
//                                .anyRequest().authenticated()
//                )
//                .csrf()
//                .disable()
//                .headers()
//                .frameOptions()
//                .disable();
//        /*
//         * http .authorizeHttpRequests() .requestMatchers(toH2Console()) .permitAll()
//         * .anyRequest() .authenticated() .and() .csrf()
//         * .ignoringRequestMatchers(toH2Console()) .disable()
//         * .headers().frameOptions().disable();
//         */
//
//
//
//
//
//
//
//
//        /*
//         * http .httpBasic() .and() .authorizeHttpRequests() .anyRequest()
//         * .authenticated() .and() .csrf() .disable();
//         */
//        return http.build();
//
//    }

}
