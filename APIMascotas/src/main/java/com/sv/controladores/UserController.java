package com.sv.controladores;

import com.sv.modelos.Users;
import com.sv.modelos.UsersSesion;
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
    public UsersSesion acceso(@RequestBody Users us){
    	
    	
    	UsersSesion user = new UsersSesion();
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
    public void SaveUser(@RequestBody Users us) {
    	interfaceUser.save(us);
    }
}
