package com.finalproject.Exceptions;

import com.finalproject.exceptions.ErrorResponse;
import com.finalproject.exceptions.SpesificExceptionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations; // MockitoAnnotations'ı import ediyoruz
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpesificExceptionHandlerTest {

    // SpesificExceptionHandler sınıfı için mock nesnesi oluşturuyoruz
    @InjectMocks
    private SpesificExceptionHandler spesificExceptionHandler;

    // MethodArgumentNotValidException için mock nesnesi oluşturuyoruz
    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    // Testler başlamadan önce mock nesnelerini başlatıyoruz
    public SpesificExceptionHandlerTest() {
        MockitoAnnotations.openMocks(this); // Mock nesnelerini başlatıyoruz
    }

    @Test
    public void testHandleValidationExceptions() {
        // FieldError nesnesi için mock oluşturuyoruz
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getField()).thenReturn("name");
        when(fieldError.getDefaultMessage()).thenReturn("Name is required");

        // BindingResult nesnesi için mock oluşturuyoruz
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        // MethodArgumentNotValidException için BindingResult'i ayarlıyoruz
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

        // handleValidationExceptions metodunu çağırıyoruz
        ResponseEntity<ErrorResponse> response = spesificExceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        // Sonuçları doğruluyoruz
        assertEquals(400, response.getStatusCodeValue()); // Durum kodunun 400 olduğunu kontrol ediyoruz
        assertEquals("Name is required", response.getBody().getErrors().get("name")); // Hata mesajının doğru olduğunu kontrol ediyoruz
    }
}
