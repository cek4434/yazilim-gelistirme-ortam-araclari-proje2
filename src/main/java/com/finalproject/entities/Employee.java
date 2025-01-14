package com.finalproject.entities;

import com.finalproject.enums.CalisanTipi;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @Inheritance(strategy = InheritanceType.JOINED) // diğer bir table stratejisi

public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "sicil no alanı zorunludur.")
    @Column(unique = true)
    @Size(min = 8, max = 8, message = "sicil no alanı 8 haneli olmalıdır.")
    private String sicilNo;

    @NotBlank(message = "Ad alanı zorunludur.")
    private String ad;

    @NotBlank(message = "Soyad alanı zorunludur.")
    private String soyad;

    @NotNull(message = "İşe giriş tarihi zorunludur.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate iseGirisTarihi;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate istenCikisTarihi;

    public CalisanTipi getCalisanTipi() {
        return calisanTipi;
    }

    public void setCalisanTipi(CalisanTipi calisanTipi) {
        this.calisanTipi = calisanTipi;
    }

    @Enumerated(EnumType.STRING)
    private CalisanTipi calisanTipi;

    /*

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;
    */


    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public LocalDate getIseGirisTarihi() {
        return iseGirisTarihi;
    }

    public void setIseGirisTarihi(LocalDate iseGirisTarihi) {
        this.iseGirisTarihi = iseGirisTarihi;
    }



    public LocalDate getIstenCikisTarihi() {
        return istenCikisTarihi;
    }

    public void setIstenCikisTarihi(LocalDate istenCikisTarihi) {
        this.istenCikisTarihi = istenCikisTarihi;
    }




}
