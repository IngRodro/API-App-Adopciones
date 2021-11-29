package com.sv.modelos;

public class AdopcionResponse {

	private Integer idadopcion;
	private MascotasResponse idMascota;
	private Users idUsuarioAdopta;
	private String estado;
	
	
	
	public Users getIdUsuarioAdopta() {
		return idUsuarioAdopta;
	}
	public void setIdUsuarioAdopta(Users idUsuarioAdopta) {
		this.idUsuarioAdopta = idUsuarioAdopta;
	}
	public Integer getIdadopcion() {
		return idadopcion;
	}
	public void setIdadopcion(Integer idadopcion) {
		this.idadopcion = idadopcion;
	}
	public MascotasResponse getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(MascotasResponse idMascota) {
		this.idMascota = idMascota;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
