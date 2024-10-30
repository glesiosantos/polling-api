package services.webplus.polling.api.web.handler;

import  jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import services.webplus.polling.api.models.StandardError;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({BadCredentialsException.class, AccessDeniedException.class})
    public ResponseEntity<StandardError> handleSecurityException(Exception exception, HttpServletRequest request) {
        var error = StandardError.builder()
                .messageError(exception.getMessage())
                .status(exception.hashCode())
                .timestamp(System.currentTimeMillis())
                .path(request.getContextPath())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationException(Exception exception, HttpServletRequest request) {
        var error = StandardError.builder()
                .messageError(exception.getMessage())
                .status(exception.hashCode())
                .timestamp(System.currentTimeMillis())
                .path(request.getContextPath())
                .build();
        return ResponseEntity.badRequest().body(error);
    }
}
