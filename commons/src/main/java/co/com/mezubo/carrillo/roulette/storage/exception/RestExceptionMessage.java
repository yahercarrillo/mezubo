package co.com.mezubo.carrillo.roulette.storage.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Estructura comun para objetos de respuestas
 * via http rest
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
@ApiModel(description = "Objeto generico para presentar las respuestas en caso de casuisticas aplicadas.")
public class RestExceptionMessage {
    @ApiModelProperty(notes = "Modelo HttpStatus", example = "OK,FORBIDDEN,BAD_REQUEST,NOT_FOUND, others")
    private HttpStatus status;
    @ApiModelProperty(notes = "Mensaje del resultado de la operacion", example = "Descripcion de lo sucedido")
    private String message;
    @ApiModelProperty(notes = "Listado de errores encontrados", example = "provider: must not be blank")
    private List<String> errors;
    @ApiModelProperty(notes = "Tiempo de ejecucion de la operación", example = "2020-11-09T15:04:27.978+00:00")
    private Date timestamp;

    public RestExceptionMessage(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = new Date();
    }

    public RestExceptionMessage(HttpStatus status, String message) {
        this(status, message, new ArrayList<String>());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
