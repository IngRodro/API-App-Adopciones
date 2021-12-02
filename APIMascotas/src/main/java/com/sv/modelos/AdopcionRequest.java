package com.sv.modelos;


public class AdopcionRequest {
	private Integer idAdopcion;
	private Mascotas idMascota;
	private Users idUsuarioAdopta;
	private String estado;
	
	public Integer getIdAdopcion() {
		return idAdopcion;
	}
	public void setIdAdopcion(Integer idAdopcion) {
		this.idAdopcion = idAdopcion;
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
