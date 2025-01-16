package com.finalproject.dtos;

import com.finalproject.dtos.ContractEmployeeDTO;
import com.finalproject.entities.ContractEmployee;
import com.finalproject.enums.CalisanTipi;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ContractEmployeeDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    public void testToEntity() {
        // DTO nesnesini oluşturuyoruz ve gerekli alanları set ediyoruz
        ContractEmployeeDTO contractEmployeeDTO = new ContractEmployeeDTO();
        contractEmployeeDTO.setSicilNo("12345678");
        contractEmployeeDTO.setAd("Ali");
        contractEmployeeDTO.setSoyad("Yılmaz");
        contractEmployeeDTO.setIseGirisTarihi(LocalDate.of(2020, 1, 1));
        contractEmployeeDTO.setSozlesmeBitisTarihi(LocalDate.of(2023, 12, 31));
        contractEmployeeDTO.setAylikUcret(5000f);

        // DTO'yu entity'ye dönüştürüyoruz
        ContractEmployee contractEmployee = (ContractEmployee) contractEmployeeDTO.toEntity();

        // Dönüştürülen entity'nin doğru alanlarını kontrol ediyoruz
        assertEquals("12345678", contractEmployee.getSicilNo());
        assertEquals("Ali", contractEmployee.getAd());
        assertEquals("Yılmaz", contractEmployee.getSoyad());
        assertEquals(LocalDate.of(2020, 1, 1), contractEmployee.getIseGirisTarihi());
        assertEquals(LocalDate.of(2023, 12, 31), contractEmployee.getSozlesmeBitisTarihi());
        assertEquals(5000f, contractEmployee.getAylikUcret());
        assertEquals(CalisanTipi.SOZLESMELI, contractEmployee.getCalisanTipi());
    }


    @Test
    public void testValidDTO() {
        ContractEmployeeDTO contractEmployeeDTO = new ContractEmployeeDTO();
        contractEmployeeDTO.setSicilNo("12345678");
        contractEmployeeDTO.setAd("Ali");
        contractEmployeeDTO.setSoyad("Yılmaz");
        contractEmployeeDTO.setIseGirisTarihi(LocalDate.of(2020, 1, 1));
        contractEmployeeDTO.setSozlesmeBitisTarihi(LocalDate.of(2023, 12, 31));
        contractEmployeeDTO.setAylikUcret(5000f);

        // Validation test
        var violations = validator.validate(contractEmployeeDTO);
        assertTrue(violations.isEmpty(), "Geçerli DTO'da ihlal olmamalıdır");
    }

    @Test
    public void testInvalidDTO() {
        ContractEmployeeDTO contractEmployeeDTO = new ContractEmployeeDTO();
        contractEmployeeDTO.setSicilNo("123");  // Invalid sicilNo (less than 8 digits)
        contractEmployeeDTO.setAd("");  // Empty ad (Invalid)
        contractEmployeeDTO.setSoyad("Y");  // Invalid soyad (too short)
        contractEmployeeDTO.setIseGirisTarihi(null);  // Null iseGirisTarihi (Invalid)
        contractEmployeeDTO.setSozlesmeBitisTarihi(null);  // Null sozlesmeBitisTarihi (Invalid)
        contractEmployeeDTO.setAylikUcret(-100f);  // Invalid aylikUcret (Negative value)

        // Validation test
        var violations = validator.validate(contractEmployeeDTO);
        assertFalse(violations.isEmpty(), "Geçersiz DTO ihlallere sahip olmalıdır");
    }
}
