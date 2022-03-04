/*
cada vez que me llega una solicitud agarro un valor del header
clave-calor > el nombre de la KEY son por defecto aunque puedo tenr personalizados
authorization
 */
package AlkemyDisney.MacaHannaArenas.auth.filtros;

import AlkemyDisney.MacaHannaArenas.auth.servicio.JwtUtils;
import AlkemyDisney.MacaHannaArenas.auth.servicio.UserDetailsCustomService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager; //esto no lo tengo en uso y el si lo tiene

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String usuario = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            usuario = jwtUtil.extractUsername(jwt);
        }

        if (usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsCustomService.loadUserByUsername(usuario);
//esto es de spring
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authReq.setDetails(authReq);
                // Set auth in context
                SecurityContextHolder.getContext().setAuthentication(authReq);
            }
        }
        chain.doFilter(request, response);
    }

}
