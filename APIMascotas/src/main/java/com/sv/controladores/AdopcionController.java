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
import com.sv.repositorio.InterfaceMascota;

@RestController
@RequestMapping("/Adopcion")
public class AdopcionController {

	@Autowired
	private InterfaceAdopcion interfaceAdopcion;
	@Autowired
	private InterfaceMascota interfaceMascota;
	
	@PostMapping("/save")
	public void saveAdopcion(@RequestBody AdopcionRequest adopcionrequest) {
		
		Adopcion adopcion = new Adopcion();
		adopcion.setEstado(adopcionrequest.getEstado());
		adopcion.setIdMascota(adopcionrequest.getIdMascota());
		adopcion.setIdUsuarioAdopta(adopcionrequest.getIdUsuarioAdopta());
		

		List<Mascotas> listaMascotas = interfaceMascota.findByIdmascota(adopcionrequest.getIdMascota().getIdmascota());
		
		if(listaMascotas.size() ==1) {
			Mascotas mascotas = new Mascotas();
			for(int i = 0; i<listaMascotas.size(); i++) {
				
				mascotas = listaMascotas.get(i);
			
			}
			mascotas.setEstado("Proceso de Adopcion");
			interfaceMascota.save(mascotas);
		}
		
		interfaceAdopcion.save(adopcion);
		
	}
	
	@PostMapping("/misAdopciones")
	public List<AdopcionResponse> misAdopciones(@RequestBody Users user) {
		
		List<Adopcion> listaAdopciones = interfaceAdopcion.findByIdUsuarioAdopta(user);
		List<AdopcionResponse> listaAdopcionesResponse = new ArrayList<AdopcionResponse>();
		
		for(int i=0; i<listaAdopciones.size(); i++) {
			Adopcion adopcion = listaAdopciones.get(i);
			
			AdopcionResponse adopcionResponse = new AdopcionResponse();
			adopcionResponse.setEstado(adopcion.getEstado());
			adopcionResponse.setIdadopcion(adopcion.getIdAdopcion());
			adopcionResponse.setIdUsuarioAdopta(adopcion.getIdUsuarioAdopta());
			
			MascotasResponse mascotaResponse = new MascotasResponse();
			mascotaResponse.setEdad(adopcion.getIdMascota().getEdad());
			mascotaResponse.setNombre(adopcion.getIdMascota().getNombre());
			mascotaResponse.setSexo(adopcion.getIdMascota().getSexo());
			mascotaResponse.setRaza(adopcion.getIdMascota().getRaza());
			mascotaResponse.setIdmascota(adopcion.getIdMascota().getIdmascota());
			mascotaResponse.setIduser(adopcion.getIdMascota().getIduser());
			Path path = Paths.get(adopcion.getIdMascota().getUrlfoto());
			try {
				String base64String = Base64.encodeBase64URLSafeString(Files.readAllBytes(path));
				mascotaResponse.setFotoString(base64String);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			adopcionResponse.setIdMascota(mascotaResponse);
			listaAdopcionesResponse.add(adopcionResponse);
		}
		
		return listaAdopcionesResponse;
		
		
	}
	
}
