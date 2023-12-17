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
import com.bcn.model.Responses.ApiErrorResponse;
import com.bcn.model.Responses.ApiResponse;
import com.bcn.model.Responses.ApiSuccessResponse;
import com.bcn.service.DaoRol.RolDaoServiceImplement;
import com.bcn.utils.Constantes;
import com.bcn.utils.UtilsGeneric;


@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolDaoServiceImplement rolService;

    @Autowired
    private UtilsGeneric utils;

    private ApiResponse apiResponse;
    String response = "";

    @GetMapping("/getRol/{id}") // obtiene el Rol
    public ResponseEntity<ApiResponse> getRol(@PathVariable(required = true) String id) throws Exception {
        if (!utils.isnumeric(id)) {
            apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,
                    "El parámetro id debe ser un dato numerico");
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            Rol rol = rolService.getRol(Integer.parseInt(id));
            if (rol != null) {
                apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, rol);
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
            } else {
                apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED, "No existe el rol a buscar");
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            apiResponse = new ApiErrorResponse(Constantes.Codigo.INTERNAL_SERVER_ERROR, Constantes.Mensaje.MSG_FAILED,e.getMessage());
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRoles") // obtiene el Rol
    public ResponseEntity<ApiResponse> getRol() throws Exception {
        try {
            List<Rol> lista = rolService.getRoles();

            if (lista != null) {
                apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK,Constantes.Mensaje.MSG_SUCCESS,lista);
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
            } else {
                apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED, "No existen registros de roles");
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            apiResponse = new ApiErrorResponse(Constantes.Codigo.INTERNAL_SERVER_ERROR, Constantes.Mensaje.MSG_FAILED,e.getMessage());
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addRol") // obtiene el Rol
    public ResponseEntity<ApiResponse> postRol(@RequestBody(required = true) Rol rol) throws Exception {
        try {
            response = rolService.postRol(rol);
            if (response.equals("OK")) {
                apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK,Constantes.Mensaje.MSG_SUCCESS,"Rol creado");
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
            } else {
                apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED,response);
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            apiResponse = new ApiErrorResponse(Constantes.Codigo.INTERNAL_SERVER_ERROR, Constantes.Mensaje.MSG_FAILED,e.getMessage());
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            
        
    }

    @PutMapping("/updateRol/{id}") // obtiene el Rol
    public ResponseEntity<?> updateRol(@RequestBody(required=true) Rol rol, @PathVariable(required=true) String id) throws Exception {
        if (!utils.isnumeric(id)) {
            apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,
                    "El parámetro id debe ser un dato numerico");
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            response = rolService.putRol(rol, Integer.parseInt(id));
            if (response.equals("OK")) {
                apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, "Rol actualizado");
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
            } else {
                apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED, response);
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
           apiResponse = new ApiErrorResponse(Constantes.Codigo.INTERNAL_SERVER_ERROR, Constantes.Mensaje.MSG_FAILED,e.getMessage());
                return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // se deja esta parte del codigopro si se tiene en mente agregar un campo de
    // status a la tabla rol
    // @PutMapping("/updateRol/deactivate/{id}")//obtiene el Rol
    // public ResponseEntity<?> deactivaeRol(@RequestBody Rol rol, int id) throws
    // Exception{
    // try{

    // }catch(Exception e){
    // throw new Exception(e.getMessage());
    // }
    // return null;
    // }

    // @PutMapping("/updateRol/activate/{id}")//obtiene el Rol
    // public ResponseEntity<?> activaeRol(@RequestBody Rol rol, int id) throws
    // Exception{
    // try{

    // }catch(Exception e){
    // throw new Exception(e.getMessage());
    // }
    // return null;
    // }

}