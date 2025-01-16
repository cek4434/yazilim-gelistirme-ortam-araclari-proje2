package com.finalproject.dtos;

import com.finalproject.entities.Task;
import com.finalproject.entities.TemporaryTask;
import com.finalproject.enums.GorevTipi;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TemporaryTaskDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testToEntity() {
        // DTO nesnesini oluşturuyoruz ve gerekli alanları set ediyoruz
        TemporaryTaskDTO temporaryTaskDTO = new TemporaryTaskDTO();
        temporaryTaskDTO.setCalisanId(1L);
        temporaryTaskDTO.setGorevAdi("Görev Adı");
        temporaryTaskDTO.setGorevTanimi("Görev Tanımı");
        temporaryTaskDTO.setGorevBaslangicTarihi(LocalDate.of(2023, 1, 1));
        temporaryTaskDTO.setGorevBitisTarihi(LocalDate.of(2023, 12, 31));

        // DTO'yu entity'ye dönüştürüyoruz
        Task task = temporaryTaskDTO.toEntity();

        // Dönüştürülen entity'nin doğru alanlarını kontrol ediyoruz
        assertEquals("Görev Adı", task.getGorevAdi(), "Görev adı doğru değil");
        assertEquals("Görev Tanımı", task.getGorevTanimi(), "Görev tanımı doğru değil");
        assertEquals(GorevTipi.GECICI_GOREV, task.getGorevTipi(), "Görev tipi doğru değil");
    }

    @Test
    public void testValidDTO() {
        // Geçerli bir DTO nesnesi oluşturuyoruz
        TemporaryTaskDTO temporaryTaskDTO = new TemporaryTaskDTO();
        temporaryTaskDTO.setCalisanId(1L);
        temporaryTaskDTO.setGorevAdi("Geçerli Görev");
        temporaryTaskDTO.setGorevTanimi("Bu geçerli bir görev tanımıdır.");
        temporaryTaskDTO.setGorevBaslangicTarihi(LocalDate.of(2023, 1, 1));
        temporaryTaskDTO.setGorevBitisTarihi(LocalDate.of(2023, 12, 31));

        // Doğrulama testi
        var violations = validator.validate(temporaryTaskDTO);
        assertTrue(violations.isEmpty(), "Geçerli DTO'nun doğrulama hatası olmamalıdır");
    }

    @Test
    public void testInvalidDTO() {
        // Geçersiz bir DTO nesnesi oluşturuyoruz
        TemporaryTaskDTO temporaryTaskDTO = new TemporaryTaskDTO();
        temporaryTaskDTO.setCalisanId(null);  // Null calisanId (Geçersiz)
        temporaryTaskDTO.setGorevAdi("");  // Boş gorevAdi (Geçersiz)
        temporaryTaskDTO.setGorevTanimi("");  // Boş gorevTanimi (Geçersiz)
        temporaryTaskDTO.setGorevBaslangicTarihi(null);  // Null gorevBaslangicTarihi (Geçersiz)
        temporaryTaskDTO.setGorevBitisTarihi(null);  // Null gorevBitisTarihi (Geçersiz)

        // Doğrulama testi
        var violations = validator.validate(temporaryTaskDTO);
        assertFalse(violations.isEmpty(), "Geçersiz DTO'nun doğrulama hatası olmalıdır");
    }
}
