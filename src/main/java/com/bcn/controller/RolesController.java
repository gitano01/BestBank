package com.bcn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcn.model.Rol;
import com.bcn.service.DaoRol.RolDaoServiceImplement;

@RestController
@RequestMapping("/roles")
public class RolesController {
    
    @Autowired
    private RolDaoServiceImplement rolService;


    @GetMapping("/getRol/{id}")//obtiene el Rol
    public ResponseEntity<?> getRol(@PathVariable String id) throws Exception{
        try{
            Rol rol = rolService.getRol(Integer.parseInt(id));
            if(rol != null){
                return new ResponseEntity<Rol>(rol,HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("No existe el rol a buscar",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }        
    }
    
    @GetMapping("/getRoles")//obtiene el Rol
    public ResponseEntity<?> getRol() throws Exception{
        try{
            List<Rol> lista = rolService.getRoles();
            if(rol != null){
                return new ResponseEntity<List<Rol>>(lista,HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("No existe el rol a buscar",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }        
    }

    @PostMapping("/addRol")//obtiene el Rol
    public ResponseEntity<?> getRol(@RequestBody Rol rol) throws Exception{
        try{
            
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    @PutMapping("/updateRol/{id}")//obtiene el Rol
    public ResponseEntity<?> updateRol(@RequestBody Rol rol, int id) throws Exception{
        try{
            
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    @PutMapping("/updateRol/deactivate/{id}")//obtiene el Rol
    public ResponseEntity<?> deactivaeRol(@RequestBody Rol rol, int id) throws Exception{
        try{
            
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    @PutMapping("/updateRol/activate/{id}")//obtiene el Rol
    public ResponseEntity<?> activaeRol(@RequestBody Rol rol, int id) throws Exception{
        try{
            
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    

}