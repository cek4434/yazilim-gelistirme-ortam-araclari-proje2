package com.finalproject.dtos;

import com.finalproject.entities.ContractEmployee;
import com.finalproject.entities.Employee;
import com.finalproject.enums.CalisanTipi;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ContractEmployeeDTO {

    @NotBlank(message = "sicil no alanı zorunludur.")
    @Size(min = 8, max = 8, message = "sicil no alanı 8 haneli olmalıdır.")
    private String sicilNo;

    @NotBlank(message = "Ad alanı zorunludur.")
    @Size(min = 3, max = 20, message = "ad alanı 3 ile 20 harf arasında olmalıdır.")
    private String ad;

    @NotBlank(message = "Soyad alanı zorunludur.")
    @Size(min = 3, max = 20, message = "soyad alanı 3 ile 20 harf arasında olmalıdır.")
    private String soyad;

    @NotNull(message = "İşe giriş tarihi zorunludur.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate iseGirisTarihi;

    @NotNull(message = "Sözleşme bitiş tarihi zorunludur.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sozlesmeBitisTarihi;

    @NotNull(message = "Aylık ücret alanı zorunludur.")
    @Positive(message = "Aylık ücret pozitif bir değer olmalıdır.")
    private Float aylikUcret;


    //getters and setters

    public String getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(String sicilNo) {
        this.sicilNo = sicilNo;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public LocalDate getIseGirisTarihi() {
        return iseGirisTarihi;
    }

    public void setIseGirisTarihi(LocalDate iseGirisTarihi) {
        this.iseGirisTarihi = iseGirisTarihi;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public LocalDate getSozlesmeBitisTarihi() {
        return sozlesmeBitisTarihi;
    }

    public void setSozlesmeBitisTarihi(LocalDate sozlesmeBitisTarihi) {
        this.sozlesmeBitisTarihi = sozlesmeBitisTarihi;
    }

    public Float getAylikUcret() {
        return aylikUcret;
    }

    public void setAylikUcret(Float aylikUcret) {
        this.aylikUcret = aylikUcret;
    }


    public Employee toEntity() {
        ContractEmployee employee = new ContractEmployee();
        employee.setSicilNo(this.sicilNo);
        employee.setAd(this.ad);
        employee.setSoyad(this.soyad);
        employee.setIseGirisTarihi(this.iseGirisTarihi);
        employee.setSozlesmeBitisTarihi(this.sozlesmeBitisTarihi);
        employee.setAylikUcret(this.aylikUcret);
        employee.setCalisanTipi(CalisanTipi.SOZLESMELI);

        return employee;

    }


}
