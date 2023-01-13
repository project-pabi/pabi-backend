package com.pabi.common.jwt

import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtFilter(
    private val tokenProvider: TokenProvider
) : GenericFilterBean() {

    private val log = KotlinLogging.logger {}

    override fun doFilter(
        servletRequest: ServletRequest, servletResponse: ServletResponse,
        filterChain: FilterChain
    ) {
        val httpServletRequest = servletRequest as HttpServletRequest
        val requestURI = httpServletRequest.requestURI
        val jwt = resolveToken(httpServletRequest)
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { // 토큰의 유효성이 검증됐을 경우,
            val authentication: Authentication = tokenProvider.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
            log.debug(
                "Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.name,
                requestURI
            )
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI)
        }
        filterChain.doFilter(servletRequest, servletResponse)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)

        if (!StringUtils.hasText(bearerToken)) {
            return null
        }

        if (!bearerToken.startsWith("Bearer ")) {
            return null
        }

        return bearerToken.substring(7)
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }
}