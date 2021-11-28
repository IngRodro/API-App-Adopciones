package com.sv.modelos;

public class MascotasResponse {

	private Integer idmascota;
	private String nombre;
    private String sexo;
    private Integer edad;
    private String raza;
    private String fotoString;
    private Integer iduser;


	public Integer getIdmascota() {
		return idmascota;
	}

	public void setIdmascota(Integer idmascota) {
		this.idmascota = idmascota;
	}

	public String getFotoString() {
		return fotoString;
	}

	public void setFotoString(String fotoString) {
		this.fotoString = fotoString;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}
	
}
