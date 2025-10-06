package com.prime.opt.dummy.project.exceptions;

import com.prime.opt.dummy.project.constants.LibrarySystemErrorCodes;
import com.prime.opt.dummy.project.request.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler{

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    BaseResponse<Object> response = BaseResponse.builder()
            .code(LibrarySystemErrorCodes.fail_code)
            .msg(LibrarySystemErrorCodes.fail_msg)
            .data(errors)
            .build();

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getConstraintViolations().forEach(violation -> {
      String fieldName = violation.getPropertyPath().toString();
      String errorMessage = violation.getMessage();
      errors.put(fieldName, errorMessage);
    });

    BaseResponse<Object> response = BaseResponse.builder()
            .code(LibrarySystemErrorCodes.fail_code)
            .msg(LibrarySystemErrorCodes.fail_msg)
            .data(errors)
            .build();

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleInvalidFormat(HttpMessageNotReadableException ex) {
    return new ResponseEntity<>("Invalid request format or enum value: " + ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
  }

}
