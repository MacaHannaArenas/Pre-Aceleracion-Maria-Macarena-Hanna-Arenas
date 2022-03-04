package AlkemyDisney.MacaHannaArenas.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioDto {
    
    @Email(message = "El usuario debe ser un E-mail")
    @NotNull //no lo tiene
    private String username;
    
    @Size(min = 8)
    @NotNull //no lo tiene
    private String password;
    
}

