package AlkemyDisney.MacaHannaArenas.auth.dto;
//cuando mando mi login en el request, esto lo valida con la base de datos y me devulve el jwt encriptado

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String jwt;
}
