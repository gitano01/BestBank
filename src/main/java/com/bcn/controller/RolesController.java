package com.bcn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcn.model.Rol;

@RestController
@RequestMapping("/roles")
public class RolesController {
    

    @GetMapping("/getRol")//obtiene el Rol
    public ResponseEntity<?> getRol(@PathVariable String id) throws Exception{
        try{
            
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
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