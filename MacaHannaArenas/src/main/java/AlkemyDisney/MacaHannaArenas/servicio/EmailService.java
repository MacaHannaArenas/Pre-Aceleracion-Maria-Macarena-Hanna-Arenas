package AlkemyDisney.MacaHannaArenas.servicio;



import java.io.IOException;
import org.springframework.stereotype.Service;


public interface EmailService {

    void enviarMsjeBienvenida(String destinatario) throws IOException;

}
 