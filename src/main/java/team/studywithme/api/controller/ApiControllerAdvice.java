package team.studywithme.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}