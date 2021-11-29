package com.sv.controladores;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.sv.modelos.Mascotas;
import com.sv.modelos.Users;
import com.sv.repositorio.InterfaceMascota;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sv.modelos.MascotasRequest;
import com.sv.modelos.MascotasResponse;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("/Mascota")
public class MascotasController {

	@Autowired
	private InterfaceMascota interfaceMascota;


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
			//Obtener la ruta home del usuario
			builder.append(System.getProperty("user.home"));
			//Obtener el Separador ("\" en Windows, "/" en Linux y Mac)
			builder.append(File.separator);
			//Carpeta donde se guardara la imagen
			builder.append("spring_upload_example");
			builder.append(File.separator);
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

	@GetMapping("/lista")
	public List<MascotasResponse> ListaMascotas() {
		List<Mascotas> listaMascotas = (List<Mascotas>) interfaceMascota.findAll();
		List<MascotasResponse> listamascotasRequest = new ArrayList<MascotasResponse>();
		for (int i = 0; i < listaMascotas.size(); i++) {
			MascotasResponse mascotasResponse = new MascotasResponse();
			Mascotas mascotas = new Mascotas();
			mascotas = listaMascotas.get(i);
			if(mascotas.getEstado() == null) {
				mascotasResponse.setIdmascota(mascotas.getIdmascota());
				mascotasResponse.setNombre(mascotas.getNombre());
				mascotasResponse.setEdad(mascotas.getEdad());
				mascotasResponse.setSexo(mascotas.getSexo());
				mascotasResponse.setRaza(mascotas.getRaza());
				mascotasResponse.setIduser(mascotas.getIduser());
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

	


}
