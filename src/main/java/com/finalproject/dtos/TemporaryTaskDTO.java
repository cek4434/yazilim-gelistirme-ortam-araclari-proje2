package com.finalproject.dtos;

import com.finalproject.entities.Task;
import com.finalproject.entities.TemporaryTask;
import com.finalproject.enums.GorevTipi;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TemporaryTaskDTO {

    @NotNull(message = "calisan id alanı zorunludur.")
    private Long calisanId;

    @NotBlank(message = "gorevAdı alanı zorunludur.")
    @Size(min = 3, max = 100, message = "gorev adı alanı 3 ile 100 harf arasında olmalıdır.")
    private String gorevAdi;

    @NotBlank(message = "gorevTanimi alanı zorunludur.")
    @Size(min = 3, max = 1000, message = "gorev tanımı alanı 3 ile 1000 harf arasında olmalıdır.")
    private String gorevTanimi;

    @NotNull(message = "gorev başlangıç  tarihi zorunludur.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate gorevBaslangicTarihi;

    @NotNull(message = "gorev bitiş  tarihi zorunludur.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate gorevBitisTarihi;


    //getters and setters

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





    public String getGorevTanimi() {
        return gorevTanimi;
    }

    public void setGorevTanimi(String gorevTanimi) {
        this.gorevTanimi = gorevTanimi;
    }

    public String getGorevAdi() {
        return gorevAdi;
    }

    public void setGorevAdi(String gorevAdi) {
        this.gorevAdi = gorevAdi;
    }

    public Long getCalisanId() {
        return calisanId;
    }

    public void setCalisanId(Long calisanId) {
        this.calisanId = calisanId;
    }


    public Task toEntity() {
        TemporaryTask task = new TemporaryTask();
        task.setGorevAdi(this.gorevAdi);
        task.setGorevTanimi(this.gorevTanimi);
        task.setGorevTipi(GorevTipi.GECICI_GOREV);
        task.setGorevBaslangicTarihi(this.gorevBaslangicTarihi);
        task.setGorevBitisTarihi(this.gorevBitisTarihi);

        return task;

    }

}
