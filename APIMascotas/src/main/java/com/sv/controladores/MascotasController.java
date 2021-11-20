package com.sv.controladores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.modelos.MascotasUpload;

@RestController
@RequestMapping("/Mascota")
public class MascotasController {
	@PostMapping("/upload")
	public void uploadFile(@RequestBody MascotasUpload mascota)
			throws IOException {

		if (mascota.getFoto() == null || mascota.getFoto().length == 0) {
		}else {
			StringBuilder builder = new StringBuilder();
			//Obtener la ruta home del usuario
			builder.append(System.getProperty("user.home"));
			//Obtener el Separador ("\" en Windows, "/" en Linux y Mac)
			builder.append(File.separator);
			//Carpeta donde se guardara la imagen
			builder.append("spring_upload_example");
			builder.append(File.separator);
			//Nombre de la imagen
			builder.append(mascota.getNombre() + "_" + mascota.getEdad() + "_" + ".jpeg");

			//Asignacion del arreglo de bits de la imagen
			byte[] fileBytes = mascota.getFoto();
			//Asignando ruta
			Path path = Paths.get(builder.toString());
			//Creando la imagen en la ruta asignada
			Files.write(path, fileBytes);
		}
		
	}
	
	


}
