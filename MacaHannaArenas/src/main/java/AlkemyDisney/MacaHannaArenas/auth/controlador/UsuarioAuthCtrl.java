package AlkemyDisney.MacaHannaArenas.auth.controlador;

import AlkemyDisney.MacaHannaArenas.auth.dto.AuthRequest;
import AlkemyDisney.MacaHannaArenas.auth.dto.AuthResponse;
import AlkemyDisney.MacaHannaArenas.auth.dto.UsuarioDto;
import AlkemyDisney.MacaHannaArenas.auth.servicio.JwtUtils;
import AlkemyDisney.MacaHannaArenas.auth.servicio.UserDetailsCustomService;
import AlkemyDisney.MacaHannaArenas.excepcion.msje.MsjeExc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UsuarioAuthCtrl {

    private final UserDetailsCustomService userDetailsService; 
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenUtil;

   //private PasswordEncoder passwordEncoder; /


    @Autowired
    public UsuarioAuthCtrl(
            UserDetailsCustomService userDetailsService,
            AuthenticationManager authenticationManager,
            JwtUtils jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody UsuarioDto userDto) throws Exception {
        this.userDetailsService.registro(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody AuthRequest authRequest) throws Exception {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception(MsjeExc.USERNAME_PASSWORD_INVALIDO);
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
