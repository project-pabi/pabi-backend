package com.pabi.common.config

import com.pabi.common.jwt.JwtAccessDeniedHandler
import com.pabi.common.jwt.JwtAuthenticationEntryPoint
import com.pabi.common.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    val tokenProvider: TokenProvider,
    val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .csrf().disable()
            .formLogin().disable()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers(
                "/api/v1/user",
                "/api/v1/user/sign-in",

                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/webjars/**",
            ).permitAll()
            .anyRequest().authenticated()

            .and()
            .apply(JwtSecurityConfig(tokenProvider))
    }
}