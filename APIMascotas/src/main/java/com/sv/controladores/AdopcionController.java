package com.sv.controladores;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.modelos.Adopcion;
import com.sv.modelos.AdopcionRequest;
import com.sv.modelos.AdopcionResponse;
import com.sv.modelos.Mascotas;
import com.sv.modelos.MascotasRequest;
import com.sv.modelos.MascotasResponse;
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
	
	@GetMapping("/misAdopciones")
	public List<AdopcionResponse> misAdopciones(@RequestBody Users user) {
		
		List<Adopcion> listaAdopciones = interfaceAdopcion.findByIdUsuarioAdopta(user);
		List<AdopcionResponse> listaAdopcionesResponse = new ArrayList<AdopcionResponse>();
		
		for(int i=0; i<listaAdopciones.size(); i++) {
			Adopcion adopcion = listaAdopciones.get(i);
			
			AdopcionResponse adopcionResponse = new AdopcionResponse();
			adopcionResponse.setEstado(adopcion.getEstado());
			adopcionResponse.setIdadopcion(adopcion.getIdAdopcion());
			
			MascotasResponse mascotaResponse = new MascotasResponse();
			mascotaResponse.setEdad(adopcion.getIdMascota().getEdad());
			mascotaResponse.setNombre(adopcion.getIdMascota().getNombre());
			mascotaResponse.setSexo(adopcion.getIdMascota().getSexo());
			Path path = Paths.get(adopcion.getIdMascota().getUrlfoto());
			try {
				String base64String = Base64.encodeBase64URLSafeString(Files.readAllBytes(path));
				mascotaResponse.setFotoString(base64String);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return listaAdopcionesResponse;
		
		
	}
	
}
