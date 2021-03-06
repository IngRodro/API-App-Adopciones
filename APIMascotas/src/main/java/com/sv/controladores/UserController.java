package com.sv.controladores;

import com.sv.modelos.Users;
import com.sv.modelos.UsersResponse;
import com.sv.repositorio.InterfaceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private InterfaceUser interfaceUser;

    @GetMapping
    public List<Users> usuarios(){
        return (List<Users>) interfaceUser.findAll();
    }
    
    @PostMapping("/inicio")
    public UsersResponse acceso(@RequestBody Users us){
    	
    	
    	UsersResponse user = new UsersResponse();
    	user.setUsername(us.getUsername());
    	user.setPassword(us.getPassword());
    	List<Users> usuarios = (List<Users>) interfaceUser.findAll();
    	boolean verificacion = false;
    	
    	for (int i=0;i<usuarios.size();i++) {
    		Users usuario = usuarios.get(i);
    	     if(us.getUsername().equals(usuario.getUsername()) && us.getPassword().equals(usuario.getPassword())) {
    	    	 verificacion = true;
    	    	 user.setId(usuario.getIduser());
    	     }
    	     
    	    }
    	
    	if(verificacion == true) {
    		user.setEstado("Acces");
    	}else {
    		user.setEstado("Error");
    	}
    	
		return user;
    	
    }
    
    @PostMapping("/save")
    public boolean SaveUser(@RequestBody Users us) {
    	boolean verificacion = false;
    	
    	List<Users> listauser = (List<Users>) interfaceUser.findAll();
    	for(int i =0; i<listauser.size(); i++) {
    		if(us.getUsername().equals(listauser.get(i).getUsername())) {
    			verificacion = true;
    			break;
    		}    		
    	}
    	
    	if(verificacion == false) {
    		interfaceUser.save(us);
    		return false;
    	}else {
    		return true;
    	}
    }
    
    @PostMapping("/update")
    public void UpdateUser(@RequestBody Users us) {
    	
    	Users user = new Users();
    	List<Users> lista =(List<Users>) interfaceUser.findAll();
    	for(int i=0; i<lista.size(); i++){
    		user = lista.get(i);
    	if(user.getIduser() == us.getIduser()) 
    	{
    		if(us.getTelefono().equals("")) {
    			user.setDepartamento(us.getDepartamento());
    			user.setMunicipio(us.getMunicipio());
    			interfaceUser.save(user);
    		}else {
    			if(us.getDepartamento().equals("Seleccione un Departamento") && us.getMunicipio().equals("Seleccione un Municipio")) {
    				user.setTelefono(us.getTelefono());
        			interfaceUser.save(user);
    			}else {
    				user.setDepartamento(us.getDepartamento());
        			user.setMunicipio(us.getMunicipio());
    				user.setTelefono(us.getTelefono());
        			interfaceUser.save(user);
    			}
    		}
    	}
    	
    	}
    	
    }
}
