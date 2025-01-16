package com.finalproject.Exceptions;

import com.finalproject.exceptions.APIError;
import com.finalproject.exceptions.ErrorResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorTest {

    @Test
    public void testAPIError() {
        // APIError sınıfının doğru bir şekilde oluşturulup oluşturulmadığını test eder
        APIError error = new APIError(404, "Bulunamadı");

        // Durum kodunun doğru atanıp atanmadığını kontrol eder
        assertEquals(404, error.getStatusCode(), "Durum kodu doğru değil");

        // Mesajın doğru atanıp atanmadığını kontrol eder
        assertEquals("Bulunamadı", error.getMessage(), "Mesaj doğru değil");
    }

    @Test
    public void testErrorResponse() {
        // ErrorResponse sınıfının doğru bir şekilde oluşturulup oluşturulmadığını test eder
        ErrorResponse errorResponse = new ErrorResponse(400, "Geçersiz İstek");

        // Durum kodunun doğru atanıp atanmadığını kontrol eder
        assertEquals(400, errorResponse.getStatusCode(), "Durum kodu doğru değil");

        // Mesajın doğru atanıp atanmadığını kontrol eder
        assertEquals("Geçersiz İstek", errorResponse.getMessage(), "Mesaj doğru değil");
    }
}