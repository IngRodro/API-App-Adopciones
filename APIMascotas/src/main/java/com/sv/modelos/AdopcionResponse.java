package com.sv.modelos;

public class AdopcionResponse {

	private Integer idadopcion;
	private MascotasResponse idMascota;
	private String estado;
	
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
