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
    private String raza;
    @Column
    private String urlfoto;
    @ManyToOne
    @JoinColumn(name =	"iduser")
    private Users iduser;
    @Column
    private String estado;

    
    
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
