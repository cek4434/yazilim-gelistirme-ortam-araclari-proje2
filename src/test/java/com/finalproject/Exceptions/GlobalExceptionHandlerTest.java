package com.finalproject.Exceptions;

import com.finalproject.exceptions.APIError;
import com.finalproject.exceptions.ErrorResponse;
import com.finalproject.exceptions.GlobalExceptionHanlder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    @Mock
    private APIError apiError;

    @InjectMocks
    private GlobalExceptionHanlder globalExceptionHandler;

    // Testler başlamadan önce mock nesnelerini başlatıyoruz
    public GlobalExceptionHandlerTest() {
        MockitoAnnotations.openMocks(this); // Mock nesnelerini başlatmak için
    }

    @Test
    public void testHandleCustomError() {
        // APIError mock nesnesinin durum kodu ve mesajını döndürmesini sağlıyoruz
        when(apiError.getStatusCode()).thenReturn(404);
        when(apiError.getMessage()).thenReturn("Bulunamadı");

        // Mock APIError nesnesiyle handleCustomError metodunu çağırıyoruz
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleCustomError(apiError);

        // Durum kodunun ve mesajın beklenen değerlerle eşleşip eşleşmediğini kontrol ediyoruz
        assertEquals(404, response.getStatusCodeValue(), "Durum kodu doğru değil");
        assertEquals("Bulunamadı", response.getBody().getMessage(), "Mesaj doğru değil");
    }

    @Test
    public void testHandleGeneralException() {
        // Genel bir istisna oluşturuyoruz
        Exception exception = new Exception("Sunucu Hatası");

        // handleGeneralException metodunu çağırıyoruz
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleGeneralException(exception);

        // Durum kodunun ve mesajın beklenen değerlerle eşleşip eşleşmediğini kontrol ediyoruz
        assertEquals(500, response.getStatusCodeValue(), "Durum kodu doğru değil");
        assertEquals("Sunucu Hatası", response.getBody().getMessage(), "Mesaj doğru değil");
    }
}
