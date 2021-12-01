package com.sv.modelos;

public class MascotasResponse {

	private Integer idmascota;
	private String nombre;
    private String sexo;
    private Integer edad;
    private String raza;
    private String fotoString;
    private Users iduser;
    private String estado;
    private Integer idAdopcion;

    
    
    
    
    public Integer getIdAdopcion() {
		return idAdopcion;
	}

	public void setIdAdopcion(Integer idAdopcion) {
		this.idAdopcion = idAdopcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


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

	public Users getIduser() {
		return iduser;
	}

	public void setIduser(Users iduser) {
		this.iduser = iduser;
	}
	
}
