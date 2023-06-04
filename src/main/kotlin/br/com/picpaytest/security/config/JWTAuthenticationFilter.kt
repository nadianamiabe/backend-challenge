package br.com.picpaytest.security.config

import br.com.picpaytest.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JWTAuthenticationFilter(
        @Autowired private val jwtUtil: JWTUtil,
        @Autowired private val userRepository: UserRepository
) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token: String
        val authorizationHeader = request.getHeader("Authorization")
        if(authorizationHeader != null) {
            token = authorizationHeader.replace("Bearer ", "")
            val subject = jwtUtil.getSubject(token)
            val user = userRepository.findByEmail(subject)
            val authentication = UsernamePasswordAuthenticationToken(user, null, null)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}
