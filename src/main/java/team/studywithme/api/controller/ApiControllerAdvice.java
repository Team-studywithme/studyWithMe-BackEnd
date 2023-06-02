package team.studywithme.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.studywithme.api.controller.dto.response.AuthenticationExceptionResponse;
import team.studywithme.api.controller.dto.response.IllegalArgumentExceptionResponse;
import team.studywithme.api.controller.dto.response.RuntimeExceptionResponse;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validException(MethodArgumentNotValidException ex){

        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(objectError -> errorMap.put(
                        ((FieldError) objectError).getField(),
                        objectError.getDefaultMessage()
                ));


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentExceptionResponse> IllegalArgumentException(IllegalArgumentException ex){
        IllegalArgumentExceptionResponse exceptionResponse = new IllegalArgumentExceptionResponse();

        exceptionResponse.setExceptionMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RuntimeExceptionResponse> RuntimeException(RuntimeException ex){
        RuntimeExceptionResponse exceptionResponse = new RuntimeExceptionResponse();

        exceptionResponse.setExceptionMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);
    }
  
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<AuthenticationExceptionResponse> AuthenticationException(AuthenticationException ex){
        AuthenticationExceptionResponse exceptionResponse = new AuthenticationExceptionResponse();

        exceptionResponse.setExceptionMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
}