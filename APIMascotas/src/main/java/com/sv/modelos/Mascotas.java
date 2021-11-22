package com.sv.modelos;

import javax.persistence.*;

@Entity
@Table(name = "mascota")
public class Mascotas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idmascota;
    @Column
    private String nombre;
    @Column
    private Integer edad;
    @Column
    private String sexo;
    @Column
    private String urlfoto;

    public Integer getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(Integer idmascota) {
        this.idmascota = idmascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }
}
