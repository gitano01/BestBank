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

import com.bcn.model.Permiso;
import com.bcn.service.DaoPermiso.PermisoDaoServiceImplement;

@RestController
@RequestMapping("/permisos")
public class PermisosController {

    @Autowired
    private PermisoDaoServiceImplement permisoService;
    String response ="";

    @GetMapping("/getPermiso/{id}")//obtiene el Rol
    public ResponseEntity<?> getpermiso(@PathVariable String id) throws Exception{
        try{
            Permiso permiso = permisoService.getPermiso(Integer.parseInt(id));
            if(permiso != null){
                return new ResponseEntity<Permiso>(permiso,HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("No existe el permiso a buscar",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }        
    }
    
    @GetMapping("/getPermisos")//obtiene el Rol
    public ResponseEntity<?> getPermisos() throws Exception{
        try{
            List<Permiso> lista = permisoService.getPermisos();

            if(lista != null){
                return new ResponseEntity<List<Permiso>>(lista,HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("No existen registros de permisos",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }        
    }

    @PostMapping("/addPermiso")//obtiene el Rol
    public ResponseEntity<?> postPermiso(@RequestBody Permiso permiso) throws Exception{            
        try{
            response = permisoService.postPermiso(permiso);
            if(response.equals("OK") ){
                return new ResponseEntity<String>("Permiso creado", HttpStatus.OK);
            }else{
                return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
            }    
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/updatePermiso/{id}")//obtiene el Rol
    public ResponseEntity<?> updateRol(@RequestBody Permiso permiso, @PathVariable String id) throws Exception{        
        try{
            response = permisoService.putPermiso(permiso, Integer.parseInt(id));
            if(response.equals("OK") ){
                return new ResponseEntity<String>("Permiso actualizado", HttpStatus.OK);
            }else{
                return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
            }            
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }        
    }

    
}