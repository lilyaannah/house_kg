package boosterschool.realestatesearchservice.controllers.advice;

import boosterschool.realestatesearchservice.dto.response.ErrorResponse;
import boosterschool.realestatesearchservice.enums.ExceptionCode;
import boosterschool.realestatesearchservice.exceptions.ListNullExp;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import static boosterschool.realestatesearchservice.enums.ExceptionCode.*;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = ListNullExp.class)
    public ResponseEntity<ErrorResponse> cardNumberNotFoundException(ListNullExp exception){
        return ResponseEntity.status(LIST_IS_NULL.getStatus()).body(
                 ErrorResponse.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(new ErrorResponse(NULL, exception.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("Not valid input!");
        return ResponseEntity
                .status(NULL.getStatus())
                .body(new ErrorResponse(NULL, message));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .findFirst()
                .orElse(ex.getMessage());
        return ResponseEntity
                .status(NULL.getStatus())
                .body(new ErrorResponse(NULL, message));
    }

    @ExceptionHandler({jakarta.validation.UnexpectedTypeException.class})
    public ResponseEntity<ErrorResponse> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        String message = "Validation error: " + ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(NULL, message));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex) {
        ErrorResponse errorMessage = new ErrorResponse(NULL, "Invalid date format or value");

        return ResponseEntity.status(NULL.getStatus())
                .body(new ErrorResponse(NULL, errorMessage.getMessage()));

    }
}

