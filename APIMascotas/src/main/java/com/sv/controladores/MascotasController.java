package com.sv.controladores;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sv.modelos.Adopcion;
import com.sv.modelos.Mascotas;
import com.sv.modelos.Users;
import com.sv.repositorio.InterfaceAdopcion;
import com.sv.repositorio.InterfaceMascota;
import com.sv.repositorio.InterfaceUser;

import org.apache.tomcat.util.codec.binary.Base64;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sv.modelos.MascotasRequest;
import com.sv.modelos.MascotasResponse;

@RestController
@RequestMapping("/Mascota")
public class MascotasController {

	@Autowired
	private InterfaceMascota interfaceMascota;
	@Autowired
	private InterfaceAdopcion interfaceAdopcion;
	@Autowired
	private InterfaceUser interfaceUser;
	
	@GetMapping
	public List<Mascotas> list(){
		return (List<Mascotas>) interfaceMascota.findAll();
	}

	@PostMapping("/upload")
	public void uploadFile(@RequestBody MascotasRequest mascotasRequest)throws IOException {
		Mascotas mascota = new Mascotas();

		if (mascotasRequest.getFoto() == null || mascotasRequest.getFoto().length == 0) {
		} else {

			mascota.setNombre(mascotasRequest.getNombre());
			mascota.setEdad(mascotasRequest.getEdad());
			mascota.setSexo(mascotasRequest.getSexo());
			mascota.setIduser(mascotasRequest.getIduser());
			mascota.setRaza(mascotasRequest.getRaza());
			StringBuilder builder = new StringBuilder();
			builder.append("src/main/resources/static/images/");
			//Nombre de la imagen
			builder.append(mascotasRequest.getNombre() + "_" + mascotasRequest.getEdad() + "_"+ mascotasRequest.getIduser().getIduser() + ".jpeg");

			mascota.setUrlfoto(builder.toString());
			//Asignacion del arreglo de bits de la imagen
			byte[] fileBytes = mascotasRequest.getFoto();
			//Asignando ruta
			Path path = Paths.get(builder.toString());
			//Creando la imagen en la ruta asignada
			Files.write(path, fileBytes);

			interfaceMascota.save(mascota);
		}

	}

	@PostMapping("/misMascotas")
	public List<MascotasResponse> ListaMisMascotas(@RequestBody Users user) {
		List<Mascotas> listaMascotas = (List<Mascotas>) interfaceMascota.findAll();
		List<MascotasResponse> listamascotasResponse = new ArrayList<MascotasResponse>();
		for (int i = 0; i < listaMascotas.size(); i++) {
			MascotasResponse mascotasResponse = new MascotasResponse();
			Mascotas mascotas = new Mascotas();
			mascotas = listaMascotas.get(i);
			String estado = "";
			if(mascotas.getEstado() !=null) {
				estado = mascotas.getEstado();
			}
			if(mascotas.getIduser().getIduser() == user.getIduser() && !estado.equals("Adoptado")) {
				List<Adopcion> listaAdopciones = interfaceAdopcion.findByIdMascota(mascotas);

				System.out.println(listaAdopciones.size());
				if(listaAdopciones.size() == 1) {
					for(int j=0; j<listaAdopciones.size(); j++) {
						Adopcion adopcion = listaAdopciones.get(j);
						mascotasResponse.setIdAdopcion(adopcion.getIdAdopcion());
					}
				}else {
					mascotasResponse.setIdAdopcion(null);
				}
				mascotasResponse.setIdmascota(mascotas.getIdmascota());
				mascotasResponse.setNombre(mascotas.getNombre());
				mascotasResponse.setEdad(mascotas.getEdad());
				mascotasResponse.setSexo(mascotas.getSexo());
				mascotasResponse.setRaza(mascotas.getRaza());
				mascotasResponse.setIduser(mascotas.getIduser());
				mascotasResponse.setEstado(mascotas.getEstado());
				Path path = Paths.get(mascotas.getUrlfoto());
				System.out.println(mascotas.getEstado());
				try {
					String base64String = Base64.encodeBase64URLSafeString(Files.readAllBytes(path));
					mascotasResponse.setFotoString(base64String);
				} catch (IOException e) {
					e.printStackTrace();
				}
				listamascotasResponse.add(mascotasResponse);
			}
		}
		return listamascotasResponse;
	}

	
	@PostMapping("/lista")
	public List<MascotasResponse> ListaMascotas(@RequestBody Users user) {
		List<Mascotas> listaMascotas = (List<Mascotas>) interfaceMascota.findAll();
		List<MascotasResponse> listamascotasRequest = new ArrayList<MascotasResponse>();
		
		List<Users> lista =(List<Users>) interfaceUser.findAll();
    	for(int i=0; i<lista.size(); i++){
    		if(lista.get(i).getIduser() == user.getIduser()) {
    			user.setMunicipio(lista.get(i).getMunicipio());
    		}
    		
    	}
    	
		for (int i = 0; i < listaMascotas.size(); i++) {
			MascotasResponse mascotasResponse = new MascotasResponse();
			Mascotas mascotas = new Mascotas();
			mascotas = listaMascotas.get(i);
			if(mascotas.getEstado() == null && mascotas.getIduser().getIduser() != user.getIduser() && mascotas.getIduser().getMunicipio().equals(user.getMunicipio())) {
				mascotasResponse.setIdmascota(mascotas.getIdmascota());
				mascotasResponse.setNombre(mascotas.getNombre());
				mascotasResponse.setEdad(mascotas.getEdad());
				mascotasResponse.setSexo(mascotas.getSexo());
				mascotasResponse.setRaza(mascotas.getRaza());
				mascotasResponse.setIduser(mascotas.getIduser());
				mascotasResponse.setEstado(mascotas.getEstado());
				Path path = Paths.get(mascotas.getUrlfoto());
				try {
					String base64String = Base64.encodeBase64URLSafeString(Files.readAllBytes(path));
					mascotasResponse.setFotoString(base64String);
				} catch (IOException e) {
					e.printStackTrace();
				}
				listamascotasRequest.add(mascotasResponse);
			}
		}
		return listamascotasRequest;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public boolean eliminar(@PathVariable("id") int idmascota){
		
		try {
			interfaceMascota.deleteById(idmascota);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	


}
