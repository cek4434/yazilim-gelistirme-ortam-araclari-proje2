package com.finalproject.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public  class PermanentEmployee extends Employee{
    @NotNull(message = "Aylık ücret alanı zorunludur.")
    @Positive(message = "Aylık ücret pozitif bir değer olmalıdır.")
    private Float aylikUcret;

    public Float getAylikUcret() {
        return aylikUcret;
    }

    public void setAylikUcret(Float aylikUcret) {
        this.aylikUcret = aylikUcret;
    }

}
