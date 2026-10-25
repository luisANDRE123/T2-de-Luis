package com.example.laboratorio4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombres;
    private String carrera;
    private int ciclo;
    private double promedio;

    @Transient
    private String estadoAcademico;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public int getCiclo() { return ciclo; }
    public void setCiclo(int ciclo) { this.ciclo = ciclo; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { 
        this.promedio = promedio; 
    }

    public String getEstadoAcademico() {
        return (promedio >= 13) ? "REGULAR" : "EN RIESGO";
    }

    public void setEstadoAcademico(String estadoAcademico) { this.estadoAcademico = estadoAcademico; }
}