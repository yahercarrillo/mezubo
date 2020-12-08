package co.com.mezubo.carrillo.roulette.storage.exception;


import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Excepcion de control del componente de autorizacion
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
@ControllerAdvice
public class StorageException extends RestExceptionHandler {
    @ExceptionHandler(BadSqlGrammarException.class)
    public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.INSUFFICIENT_STORAGE.value(), "DataBase Dont Work");
    }
}
