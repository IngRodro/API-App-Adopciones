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

import com.sv.modelos.MascotasUpload;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("/Mascota")
public class MascotasController {

	@Autowired
	private InterfaceMascota interfaceMascota;


	@PostMapping("/upload")
	public void uploadFile(@RequestBody MascotasUpload mascotasUpload)throws IOException {
		Mascotas mascota = new Mascotas();

		if (mascotasUpload.getFoto() == null || mascotasUpload.getFoto().length == 0) {
		} else {

			mascota.setNombre(mascotasUpload.getNombre());
			mascota.setEdad(mascotasUpload.getEdad());
			mascota.setSexo(mascotasUpload.getSexo());
			Users user = new Users();
			user.setIduser(mascotasUpload.getIduser());
			System.out.println(user.getIduser().toString());
			mascota.setIduser(user);
			mascota.setRaza(mascotasUpload.getRaza());
			StringBuilder builder = new StringBuilder();
			//Obtener la ruta home del usuario
			builder.append(System.getProperty("user.home"));
			//Obtener el Separador ("\" en Windows, "/" en Linux y Mac)
			builder.append(File.separator);
			//Carpeta donde se guardara la imagen
			builder.append("spring_upload_example");
			builder.append(File.separator);
			//Nombre de la imagen
			builder.append(mascotasUpload.getNombre() + "_" + mascotasUpload.getEdad() + "_" + ".jpeg");

			mascota.setUrlfoto(builder.toString());
			//Asignacion del arreglo de bits de la imagen
			byte[] fileBytes = mascotasUpload.getFoto();
			//Asignando ruta
			Path path = Paths.get(builder.toString());
			//Creando la imagen en la ruta asignada
			Files.write(path, fileBytes);

			interfaceMascota.save(mascota);
		}

	}

	@GetMapping("/lista")
	public List<MascotasUpload> ListaMascotas() {
		List<Mascotas> listaMascotas = (List<Mascotas>) interfaceMascota.findAll();
		List<MascotasUpload> listamascotasUpload = new ArrayList<MascotasUpload>();
		for (int i = 0; i < listaMascotas.size(); i++) {
			MascotasUpload mascotasUpload = new MascotasUpload();
			Mascotas mascotas = new Mascotas();
			mascotas = listaMascotas.get(i);
			mascotasUpload.setIdmascota(mascotas.getIdmascota());
			mascotasUpload.setNombre(mascotas.getNombre());
			mascotasUpload.setEdad(mascotas.getEdad());
			mascotasUpload.setSexo(mascotas.getSexo());
			mascotasUpload.setRaza(mascotas.getRaza());
			Path path = Paths.get(mascotas.getUrlfoto());
			try {
				String base64String = Base64.encodeBase64URLSafeString(Files.readAllBytes(path));
				mascotasUpload.setFotoString(base64String);
			} catch (IOException e) {
				e.printStackTrace();
			}
			listamascotasUpload.add(mascotasUpload);
		}
		return listamascotasUpload;
	}

	


}
