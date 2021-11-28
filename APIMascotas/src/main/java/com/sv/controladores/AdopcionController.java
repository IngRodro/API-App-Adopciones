package com.sv.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.modelos.Adopcion;
import com.sv.modelos.AdopcionRequest;
import com.sv.modelos.Mascotas;
import com.sv.modelos.Users;
import com.sv.repositorio.InterfaceAdopcion;

@RestController
@RequestMapping("/Adopcion")
public class AdopcionController {

	@Autowired
	private InterfaceAdopcion interfaceAdopcion;
	
	@PostMapping("/save")
	public void saveAdopcion(@RequestBody AdopcionRequest adopcionrequest) {
		
		Adopcion adopcion = new Adopcion();
		Users user = new Users();
		Mascotas mascota = new Mascotas();
		user.setIduser(adopcionrequest.getIdUsuarioAdopta());
		mascota.setIdmascota(adopcionrequest.getIdMascota());
		adopcion.setEstado(adopcionrequest.getEstado());
		adopcion.setIdMascota(mascota);
		adopcion.setIdUsuarioAdopta(user);
		
		interfaceAdopcion.save(adopcion);
		
	}
	
}
