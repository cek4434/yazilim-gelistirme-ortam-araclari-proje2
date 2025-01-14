package com.finalproject.entities;

import com.finalproject.enums.GorevTipi;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// @Inheritance(strategy = InheritanceType.JOINED) // diğer bir table stratejisi
public abstract class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gorevAdi;
    private String gorevTanimi;

    public GorevTipi getGorevTipi() {
        return gorevTipi;
    }

    public void setGorevTipi(GorevTipi gorevTipi) {
        this.gorevTipi = gorevTipi;
    }

    @Enumerated(EnumType.STRING)
    private GorevTipi gorevTipi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGorevAdi() {
        return gorevAdi;
    }

    public void setGorevAdi(String gorevAdi) {
        this.gorevAdi = gorevAdi;
    }

    public String getGorevTanimi() {
        return gorevTanimi;
    }

    public void setGorevTanimi(String gorevTanimi) {
        this.gorevTanimi = gorevTanimi;
    }



    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



}
