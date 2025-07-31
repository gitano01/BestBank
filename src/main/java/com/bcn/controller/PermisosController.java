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
import com.bcn.model.Responses.ApiErrorResponse;
import com.bcn.model.Responses.ApiResponse;
import com.bcn.model.Responses.ApiSuccessResponse;
import com.bcn.service.DaoPermiso.PermisoDaoServiceImplement;
import com.bcn.utils.Constantes;
import com.bcn.utils.UtilsGeneric;


@RestController
@RequestMapping("/permisos")
public class PermisosController {

    @Autowired
    private PermisoDaoServiceImplement permisoService;
    
    @Autowired
    private UtilsGeneric utils;

    private ApiResponse apiResponse;
    String response = "";

    @GetMapping("/getPermiso/{id}")//obtiene el Rol
    public ResponseEntity<ApiResponse> getpermiso(@PathVariable String id) throws Exception{
        if (!utils.isnumeric(id)) {
            apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,
                    "El parámetro id debe ser un dato numerico");
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        try{
            Permiso permiso = permisoService.getPermiso(Integer.parseInt(id));
            if(permiso != null){
                apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, permiso);
                return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
            }else{
                apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED, "No existe el permiso  a buscar");
                return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            apiResponse = new ApiErrorResponse(Constantes.Codigo.INTERNAL_SERVER_ERROR, Constantes.Mensaje.MSG_FAILED,e.getMessage());
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @GetMapping("/getPermisos")//obtiene el Rol
    public ResponseEntity<ApiResponse> getPermisos() throws Exception{
        try{
            List<Permiso> lista = permisoService.getPermisos();

            if(lista != null){

                apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS,lista);
                return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
            }else{
                apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED,"No existen registros de permisos");
                return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            apiResponse = new ApiErrorResponse(Constantes.Codigo.INTERNAL_SERVER_ERROR, Constantes.Mensaje.MSG_FAILED,e.getMessage());
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @PostMapping("/addPermiso")//obtiene el Rol
    public ResponseEntity<ApiResponse> postPermiso(@RequestBody Permiso permiso) throws Exception{            
        try{
            response = permisoService.postPermiso(permiso);
            if(response.equals("OK") ){
                apiResponse = new ApiSuccessResponse(Constantes.Codigo.CREATED, Constantes.Mensaje.MSG_SUCCESS,"Permiso creado");
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
            }else{
                apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED,response);
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
            }    
        }catch(Exception e){
            apiResponse = new ApiErrorResponse(Constantes.Codigo.INTERNAL_SERVER_ERROR, Constantes.Mensaje.MSG_FAILED,e.getMessage());
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatePermiso/{id}")//obtiene el Rol
    public ResponseEntity<ApiResponse> updateRol(@RequestBody Permiso permiso, @PathVariable String id) throws Exception{   
        if (!utils.isnumeric(id)) {
            apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,
                    "El parámetro id debe ser un dato numerico");
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
        }     
        try{
            response = permisoService.putPermiso(permiso, Integer.parseInt(id));
            if(response.equals("OK") ){
                apiResponse = new ApiSuccessResponse(Constantes.Codigo.CREATED, Constantes.Mensaje.MSG_SUCCESS,"Permiso actualizado");
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
            }else{
                apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED,response);
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
            }            
        }catch(Exception e){
            apiResponse = new ApiErrorResponse(Constantes.Codigo.INTERNAL_SERVER_ERROR, Constantes.Mensaje.MSG_FAILED,e.getMessage());
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    
}