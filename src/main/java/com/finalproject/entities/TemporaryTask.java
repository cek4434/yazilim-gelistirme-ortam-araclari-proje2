package com.finalproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class TemporaryTask extends Task{
    public LocalDate getGorevBaslangicTarihi() {
        return gorevBaslangicTarihi;
    }

    public void setGorevBaslangicTarihi(LocalDate gorevBaslangicTarihi) {
        this.gorevBaslangicTarihi = gorevBaslangicTarihi;
    }

    public LocalDate getGorevBitisTarihi() {
        return gorevBitisTarihi;
    }

    public void setGorevBitisTarihi(LocalDate gorevBitisTarihi) {
        this.gorevBitisTarihi = gorevBitisTarihi;
    }

    @Column(nullable = false)
    private LocalDate gorevBaslangicTarihi;

    @Column(nullable = false)
    private LocalDate gorevBitisTarihi;



}
