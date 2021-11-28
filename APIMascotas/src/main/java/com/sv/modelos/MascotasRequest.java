package com.sv.modelos;



public class MascotasRequest {

	private Integer idmascota;
	private String nombre;
    private String sexo;
    private Integer edad;
    private String raza;
    private byte[] foto;
    private String fotoString;
    private Users iduser;


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
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Users getIduser() {
		return iduser;
	}

	public void setIduser(Users iduser) {
		this.iduser = iduser;
	}
	

	
    
}
