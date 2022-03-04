package AlkemyDisney.MacaHannaArenas.servicio.impl;

import AlkemyDisney.MacaHannaArenas.excepcion.msje.MsjeExc;
import AlkemyDisney.MacaHannaArenas.servicio.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; //LARGA ERROR
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

//El value va igual que en app.prop
    @Value("${alkemy.disney.email.sender}")
    private String emailSender;

    /* 
@Value("$email.enabled}")
    private boolean enabled;
     */
    @Override
    public void enviarMsjeBienvenida(String destinatario) throws IOException {
        /* activar o desactivar el mail, pero debo crear la properties en app.properties
   no hace falta tenerlo
        
        if (!enabled) {
            return;
        }
         */
        String apiKey = env.getProperty("TOKEN_DISNEY_ALKEMY"); //ACA VA EL TOKEN DE SEND GRID
        //copiar ese nombre> Menu>Run>SetConfig>Customize>Config>add>Id es el nombre TOKEN_DISNEY_ALKEMY 
        //y en la descripcion Env.TOKEN_DISNEY_ALKEMY=La clave que genera send grid

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(destinatario);
        //se puede hacer una clase de content factory para armar el mensaje personalizado
        Content content = new Content(
                "text/plain",
                "Registro Exitoso, Bienvenido/a PreAceleracion Alkemy");
        String subject = "Alkemy Pre-Aceleracion Challenge 3";

        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        //Request puede estar en otra clase
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            /*log 4j ...sacar los sout
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());*/
        } catch (IOException excep) {
            //System.out.println("Error mandando mail");
            throw new IOException(MsjeExc.ENVIO_MAILS_FALLIDO);
        }

    }
}
