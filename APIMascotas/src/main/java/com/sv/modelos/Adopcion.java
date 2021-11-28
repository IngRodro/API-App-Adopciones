package com.sv.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adopcion")
public class Adopcion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Integer idadopcion;
	@ManyToOne
    @JoinColumn(name =	"idmascota")
	private Mascotas idMascota;
	@ManyToOne
    @JoinColumn(name =	"idusuarioadopta")
	private Users idUsuarioAdopta;
	private String estado;
	public Integer getIdAdopcion() {
		return idadopcion;
	}
	public void setIdAdopcion(Integer idadopcion) {
		this.idadopcion = idadopcion;
	}
	public Mascotas getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(Mascotas idMascota) {
		this.idMascota = idMascota;
	}
	public Users getIdUsuarioAdopta() {
		return idUsuarioAdopta;
	}
	public void setIdUsuarioAdopta(Users idUsuarioAdopta) {
		this.idUsuarioAdopta = idUsuarioAdopta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

}
