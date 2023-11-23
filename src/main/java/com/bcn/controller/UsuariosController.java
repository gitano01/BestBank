package com.bcn.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcn.model.Usuario;
import com.bcn.service.DaoUsuario.UsuarioDaoServiceImplement;
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    
@Autowired
private UsuarioDaoServiceImplement userService;

@GetMapping("/getUsuarios")
public ResponseEntity<?> getUsuarios() throws Exception, SQLException{ 
    List<Usuario> lista ;
    try{
        lista = userService.getDatos();
        
    }catch(Exception e){
        throw new Exception(e.getMessage());
    }
   if(lista != null){
          return   new ResponseEntity<List<Usuario>>(lista,HttpStatus.OK); 
        }else{
          return  new ResponseEntity<String>("No existen usuarios registrados",HttpStatus.NOT_FOUND); 
        }
}

}
