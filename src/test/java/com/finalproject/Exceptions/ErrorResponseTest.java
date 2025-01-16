package com.finalproject.Exceptions;

import com.finalproject.exceptions.ErrorResponse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testConstructorWithoutErrors() {
        // Başarısız bir isteğin durum kodu ve mesajı ile ErrorResponse nesnesi oluşturulmasını test eder
        int statusCode = 400;
        String message = "Geçersiz İstek";

        ErrorResponse errorResponse = new ErrorResponse(statusCode, message);

        assertEquals(statusCode, errorResponse.getStatusCode(), "Durum kodu doğru değil");
        assertEquals(message, errorResponse.getMessage(), "Mesaj doğru değil");
        assertEquals("false", errorResponse.getSuccess(), "Başarı durumu yanlış");
        assertNull(errorResponse.getErrors(), "Hatalar boş olmalı");
    }

    @Test
    void testConstructorWithErrors() {
        // Doğrulama hataları ile ErrorResponse nesnesi oluşturulmasını test eder
        int statusCode = 422;
        String message = "Doğrulama Başarısız";

        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "boş olmamalı");
        errors.put("field2", "0'dan büyük olmalı");

        ErrorResponse errorResponse = new ErrorResponse(statusCode, message, errors);

        assertEquals(statusCode, errorResponse.getStatusCode(), "Durum kodu doğru değil");
        assertEquals(message, errorResponse.getMessage(), "Mesaj doğru değil");
        assertEquals("false", errorResponse.getSuccess(), "Başarı durumu yanlış");
        assertNotNull(errorResponse.getErrors(), "Hatalar boş olmamalı");
        assertEquals(2, errorResponse.getErrors().size(), "Hata sayısı yanlış");
        assertEquals("boş olmamalı", errorResponse.getErrors().get("field1"), "field1 hatası doğru değil");
        assertEquals("0'dan büyük olmalı", errorResponse.getErrors().get("field2"), "field2 hatası doğru değil");
    }

    @Test
    void testSettersAndGetters() {
        // Setter ve getter metodlarının doğru çalıştığını test eder
        ErrorResponse errorResponse = new ErrorResponse(0, null);

        errorResponse.setStatusCode(500);
        errorResponse.setMessage("Sunucu Hatası");

        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "hata mesajı");
        errorResponse.setErrors(errors);

        assertEquals(500, errorResponse.getStatusCode(), "Durum kodu doğru değil");
        assertEquals("Sunucu Hatası", errorResponse.getMessage(), "Mesaj doğru değil");
        assertEquals("false", errorResponse.getSuccess(), "Başarı durumu yanlış");
        assertEquals(errors, errorResponse.getErrors(), "Hatalar doğru değil");
    }

    @Test
    void testToString() {
        // toString metodunun doğru çalıştığını test eder
        int statusCode = 404;
        String message = "Bulunamadı";

        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "bulunamadı");

        ErrorResponse errorResponse = new ErrorResponse(statusCode, message, errors);

        String expectedToString = "ErrorResponse(success=false, message=Bulunamadı, statusCode=404, errors={field1=bulunamadı})";
        assertEquals(expectedToString, errorResponse.toString(), "toString çıktısı doğru değil");
    }
}