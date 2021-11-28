package com.sv.modelos;


public class AdopcionRequest {
	private Integer idAdopcion;
	private Integer idMascota;
	private Integer idUsuarioAdopta;
	private String estado;
	public Integer getIdAdopcion() {
		return idAdopcion;
	}
	public void setIdAdopcion(Integer idAdopcion) {
		this.idAdopcion = idAdopcion;
	}
	public Integer getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(Integer idMascota) {
		this.idMascota = idMascota;
	}
	public Integer getIdUsuarioAdopta() {
		return idUsuarioAdopta;
	}
	public void setIdUsuarioAdopta(Integer idUsuarioAdopta) {
		this.idUsuarioAdopta = idUsuarioAdopta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
