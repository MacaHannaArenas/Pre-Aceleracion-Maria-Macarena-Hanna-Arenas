package AlkemyDisney.MacaHannaArenas.auth.servicio;

import AlkemyDisney.MacaHannaArenas.auth.dto.UsuarioDto;
import AlkemyDisney.MacaHannaArenas.auth.entidad.Usuario;
import AlkemyDisney.MacaHannaArenas.auth.repositorio.UsuarioRepositorio;
import AlkemyDisney.MacaHannaArenas.excepcion.ExcepcionRegistro;
import AlkemyDisney.MacaHannaArenas.excepcion.msje.MsjeExc;
import AlkemyDisney.MacaHannaArenas.servicio.EmailService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import org.springframework.context.annotation.Lazy;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio userRepository;

    @Autowired
    private EmailService emailService;

    @Lazy //agregue
    @Autowired
    private PasswordEncoder passwordEncoder; //lo usamos para setear el password

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//le dejo este nombre para que coincida con la implementacion
        Usuario usuarioEnt = userRepository.findByUsername(username);
        if (usuarioEnt == null) {
            throw new ExcepcionRegistro(MsjeExc.USERNAME_PASSWORD_INVALIDO);
        }
        return new User(usuarioEnt.getUsername(), usuarioEnt.getPassword(), Collections.emptyList());
    }

    public Boolean usuarioExistente(String username) {

        return userRepository.findByUsername(username) != null;
    }

    /*
    public boolean registro(UsuarioDto usuarDTO) throws IOException {
        if (usuarioExistente(usuarDTO.getUsername())) {
            throw new ExcepcionRegistro(MsjeExc.USUARIO_REPETIDO);
        }
        
        Usuario usuarioEnt = new Usuario();
        usuarioEnt.setUsername(usuarDTO.getUsername());
        usuarioEnt.setPassword(usuarDTO.getPassword());
        usuarioEnt = this.userRepository.save(usuarioEnt);

        if (usuarioEnt != null) {
            emailService.enviarMsjeBienvenida(usuarioEnt.getUsername());
        }
        return usuarioEnt != null;
    }
}*/

    public boolean registro(UsuarioDto usuarDTO) throws IOException {

        if (usuarioExistente(usuarDTO.getUsername())) {
            throw new ExcepcionRegistro(MsjeExc.USUARIO_REPETIDO);
        }
        
        Usuario usuarioEnt = new Usuario();
        usuarioEnt.setUsername(usuarDTO.getUsername());

        String encriptacionPassword = passwordEncoder.encode(usuarDTO.getPassword());
        usuarioEnt.setPassword(encriptacionPassword);

        usuarioEnt = userRepository.save(usuarioEnt);
        
        if (usuarioEnt != null) {
            emailService.enviarMsjeBienvenida(usuarioEnt.getUsername());
        }
        return usuarioEnt != null;
        
/*
        if (userRepository.findByUsername(usuarioEnt.getUsername()) != null) {

            emailService.enviarMsjeBienvenida(usuarioEnt.getUsername());

        } else {
            throw new ExcepcionRegistro(MsjeExc.USUARIO_REPETIDO);*/
        }
    }


