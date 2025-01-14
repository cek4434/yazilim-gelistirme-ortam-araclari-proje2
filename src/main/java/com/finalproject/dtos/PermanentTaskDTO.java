package com.finalproject.dtos;

import com.finalproject.entities.PermanentTask;
import com.finalproject.entities.Task;
import com.finalproject.enums.GorevTipi;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PermanentTaskDTO {
    @NotNull(message = "calisan id alanı zorunludur.")
    private Long calisanId;

    @NotBlank(message = "gorevAdı alanı zorunludur.")
    @Size(min = 3, max = 100, message = "gorev adı alanı 3 ile 100 harf arasında olmalıdır.")
    private String gorevAdi;

    @NotBlank(message = "gorevTanimi alanı zorunludur.")
    @Size(min = 3, max = 1000, message = "gorev tanımı alanı 3 ile 1000 harf arasında olmalıdır.")
    private String gorevTanimi;


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
        PermanentTask task = new PermanentTask();
        task.setGorevAdi(this.gorevAdi);
        task.setGorevTanimi(this.gorevTanimi);
        task.setGorevTipi(GorevTipi.KALICI_GOREV);

        return task;

    }
}
