package com.finalproject.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public  class ContractEmployee extends Employee {

    @NotNull(message = "Sözleşme bitiş tarihi zorunludur.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sozlesmeBitisTarihi;

    @NotNull(message = "Aylık ücret alanı zorunludur.")
    @Positive(message = "Aylık ücret pozitif bir değer olmalıdır.")
    private Float aylikUcret;

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


}
