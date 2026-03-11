package org.example.authapi.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(RegistrationException.class)
  public ResponseEntity<Object> handleRegistrationException(
      final RegistrationException ex, final WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.CONFLICT);
    body.put("errors", List.of(ex.getMessage()));
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.CONFLICT);
  }

  @Override
  protected ResponseEntity<@NonNull Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final @NonNull HttpHeaders headers,
      final @NonNull HttpStatusCode status,
      final @NonNull WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.BAD_REQUEST);
    List<String> errors =
        ex.getBindingResult().getAllErrors().stream().map(this::getErrorMessage).toList();
    body.put("errors", errors);
    return new ResponseEntity<>(body, headers, status);
  }

  private String getErrorMessage(final ObjectError error) {
    if (!(error instanceof FieldError)) {
      return error.getDefaultMessage();
    }
    String field = ((FieldError) error).getField();
    String message = error.getDefaultMessage();
    return field + ": " + message;
  }
}
