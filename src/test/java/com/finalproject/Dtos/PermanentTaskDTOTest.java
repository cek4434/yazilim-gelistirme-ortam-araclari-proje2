package com.finalproject.dtos;

import com.finalproject.entities.PermanentTask;
import com.finalproject.entities.Task;
import com.finalproject.enums.GorevTipi;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PermanentTaskDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testToEntity() {
        // DTO nesnesini oluşturuyoruz ve gerekli alanları set ediyoruz
        PermanentTaskDTO permanentTaskDTO = new PermanentTaskDTO();
        permanentTaskDTO.setCalisanId(1L);
        permanentTaskDTO.setGorevAdi("Permanent Task");
        permanentTaskDTO.setGorevTanimi("Bu kalıcı bir görev tanımıdır.");

        // DTO'yu entity'ye dönüştürüyoruz
        Task task = permanentTaskDTO.toEntity();

        // Dönüştürülen entity'nin doğru alanlarını kontrol ediyoruz
        assertEquals("Permanent Task", task.getGorevAdi());
        assertEquals("Bu kalıcı bir görev tanımıdır.", task.getGorevTanimi());
        assertEquals(GorevTipi.KALICI_GOREV, task.getGorevTipi());
    }

    @Test
    public void testValidDTO() {
        PermanentTaskDTO permanentTaskDTO = new PermanentTaskDTO();
        permanentTaskDTO.setCalisanId(1L);
        permanentTaskDTO.setGorevAdi("Geçerli Permanent Task");
        permanentTaskDTO.setGorevTanimi("Bu geçerli bir kalıcı görev tanımıdır.");

        // Validation test
        var violations = validator.validate(permanentTaskDTO);
        assertTrue(violations.isEmpty(), "Geçerli DTO'da ihlal olmamalıdır");
    }

    @Test
    public void testInvalidDTO() {
        PermanentTaskDTO permanentTaskDTO = new PermanentTaskDTO();
        permanentTaskDTO.setCalisanId(null);
        permanentTaskDTO.setGorevAdi("");
        permanentTaskDTO.setGorevTanimi("");

        // Validation test
        var violations = validator.validate(permanentTaskDTO);
        assertFalse(violations.isEmpty(), "Geçersiz DTO ihlallere sahip olmalıdır");
    }
}
