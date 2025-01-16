package com.finalproject.dtos;

import com.finalproject.dtos.ContractEmployeeDTO;
import com.finalproject.dtos.PermanentEmployeeDTO;
import com.finalproject.entities.ContractEmployee;
import com.finalproject.entities.PermanentEmployee;
import com.finalproject.enums.CalisanTipi;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PermanentEmployeeDTOTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    public void testToEntity() {
        // DTO nesnesini oluşturuyoruz ve gerekli alanları set ediyoruz
        PermanentEmployeeDTO permanentEmployeeDTO = new PermanentEmployeeDTO();
        permanentEmployeeDTO.setSicilNo("12345678");
        permanentEmployeeDTO.setAd("Ali");
        permanentEmployeeDTO.setSoyad("Yılmaz");
        permanentEmployeeDTO.setIseGirisTarihi(LocalDate.of(2020, 1, 1));
        permanentEmployeeDTO.setAylikUcret(5000f);

        // DTO'yu entity'ye dönüştürüyoruz
        PermanentEmployee permanentEmployee = (PermanentEmployee) permanentEmployeeDTO.toEntity();

        // Dönüştürülen entity'nin doğru alanlarını kontrol ediyoruz
        assertEquals("12345678", permanentEmployee.getSicilNo());
        assertEquals("Ali", permanentEmployee.getAd());
        assertEquals("Yılmaz", permanentEmployee.getSoyad());
        assertEquals(LocalDate.of(2020, 1, 1), permanentEmployee.getIseGirisTarihi());
        assertEquals(5000f, permanentEmployee.getAylikUcret());
        assertEquals(CalisanTipi.KADROLU, permanentEmployee.getCalisanTipi());
    }


    @Test
    public void testValidDTO() {
        // DTO nesnesini oluşturuyoruz ve gerekli alanları set ediyoruz
        PermanentEmployeeDTO permanentEmployeeDTO = new PermanentEmployeeDTO();
        permanentEmployeeDTO.setSicilNo("12345678");
        permanentEmployeeDTO.setAd("Ali");
        permanentEmployeeDTO.setSoyad("Yılmaz");
        permanentEmployeeDTO.setIseGirisTarihi(LocalDate.of(2020, 1, 1));
        permanentEmployeeDTO.setAylikUcret(5000f);

        // Validation test
        var violations = validator.validate(permanentEmployeeDTO);
        assertTrue(violations.isEmpty(), "Geçerli DTO'da ihlal olmamalıdır");
    }

    @Test
    public void testInvalidDTO() {
        // DTO nesnesini oluşturuyoruz ve gerekli alanları set ediyoruz
        PermanentEmployeeDTO permanentEmployeeDTO = new PermanentEmployeeDTO();
        permanentEmployeeDTO.setSicilNo("1234567");
        permanentEmployeeDTO.setAd("Ali");
        permanentEmployeeDTO.setSoyad("Yılmaz");
        permanentEmployeeDTO.setIseGirisTarihi(LocalDate.of(2020, 1, 1));
        permanentEmployeeDTO.setAylikUcret(5000f);

        // Validation test
        var violations = validator.validate(permanentEmployeeDTO);
        assertFalse(violations.isEmpty(), "Geçersiz DTO ihlallere sahip olmalıdır");
    }
}
