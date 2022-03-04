
package AlkemyDisney.MacaHannaArenas.dto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class APIErrorDTO {

    private HttpStatus status;
    private String msje;
    private List<String> errores;
}
