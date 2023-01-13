package com.pabi.common.jwt

import com.pabi.common.enum.Role
import com.pabi.common.response.Token
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import mu.KotlinLogging
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.stream.Collectors

@Component
class TokenProvider(
    @param:Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.token-validity-in-seconds}") tokenValidityInMilliseconds: Long,
    @Value("\${jwt.access-token-validity-in-seconds}") accessTokenValidityInSeconds: Long,
    @Value("\${jwt.refresh-token-validity-in-seconds}") refreshTokenValidityInSeconds: Long
) : InitializingBean {
    private val tokenValidityInMilliseconds: Long
    private val accessTokenValidityInMilliseconds: Long
    private val refreshTokenValidityInMilliseconds: Long
    private var key: Key? = null

    init {
        accessTokenValidityInMilliseconds = accessTokenValidityInSeconds * 1000
        refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds * 1000
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds
    }

    private val log = KotlinLogging.logger {}

    fun createTokens(email: String, roles: List<Role>): Token {
        val claims: MutableMap<String, Any?> = HashMap()
        claims["roles"] = roles

        return doGenerateToken(claims, email)
    }

    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(secret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

    fun getAuthentication(token: String?): Authentication {
        val claims = Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
        val authorities: Collection<GrantedAuthority> = Arrays.stream(
            claims[AUTHORITIES_KEY].toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray())
            .map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
            .collect(Collectors.toList())
        val principal = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            log.info("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            log.info("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            log.info("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            log.info("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            log.info("JWT 토큰이 잘못되었습니다.")
        }
        return false
    }

    private fun doGenerateToken(claims: Map<String, Any?>, email: String): Token {
        val createdDate = Date()
        val accessTokenExpirationDate =
            Date(createdDate.time + accessTokenValidityInMilliseconds * 1000)
        val refreshTokenExpirationDate =
            Date(createdDate.time + refreshTokenValidityInMilliseconds * 1000)

        val accessToken = Jwts.builder()
            .setClaims(claims)
            .setSubject(email)
            .setIssuedAt(createdDate)
            .setExpiration(accessTokenExpirationDate)
            .signWith(key)
            .compact()
        val refreshToken = Jwts.builder()
            .setClaims(claims)
            .setSubject(email)
            .setIssuedAt(createdDate)
            .setExpiration(refreshTokenExpirationDate)
            .signWith(key)
            .compact()

        return Token(
            accessToken,
            refreshToken,
            "Bearer",
            accessTokenValidityInMilliseconds,
            createdDate
        )
    }

    companion object {
        private const val AUTHORITIES_KEY = "cp"
    }
}
