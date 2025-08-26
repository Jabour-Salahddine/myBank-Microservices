package com.mybank.loans.exception;

import com.mybank.loans.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice // This annotation tells Spring that this class will handle exceptions globally for all controllers

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { // this extends is just for handling validation issue properly, by default if we have a problem of validation it will be detected by our method :  handleGlobalException(Exception exception,  WebRequest webRequest)


    @ExceptionHandler(LoanAlreadyExistsException.class) // This annotation tells Spring to handle exceptions of type CustomerAlreadyExistsException
    public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExistsException(LoanAlreadyExistsException exception,
                                                                                 WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false), // false means we don't want the stack trace, just return the path
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false), // false means we don't want the stack trace, just return the path
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }


    // to handle any other exceptions that are not handled by the above methods, to handle runtime exceptions or any other exceptions that are not business exceptions

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false), // false means we don't want the stack trace, just return the path
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // exception handler for validation errors, this is just to handle the validation errors:
    /*
     *  Sans cette methode pour les endpoints qui ont l'annotation (@Valid et @resuqtBody) comme les autres problems de validation il seront gérés automatiquement par Spring, mais si on veut personnaliser la réponse de validation, on peut utiliser cette méthode :
     *  Pour les endpoints qui n'ont pas l'annotation (@Valid et @requestBody), cette méthode ne sera pas appelée, et les erreurs de validation seront gérées par la méthode handleGlobalException(Exception exception,  WebRequest webRequest)
     *
     * final reponse : cette methode gere les problemes de validation pour les endpoints qui ont l'annotation (@Valid et @requestBody), elle est appelée automatiquement par Spring lorsque la validation échoue, et elle retourne une réponse avec les erreurs de validation
     *  */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }



}
