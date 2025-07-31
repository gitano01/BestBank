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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcn.model.Usuario;
import com.bcn.model.Responses.ApiErrorResponse;
import com.bcn.model.Responses.ApiResponse;
import com.bcn.model.Responses.ApiSuccessResponse;
import com.bcn.service.DaoUsuario.UsuarioDaoServiceImplement;
import com.bcn.utils.Constantes;
import com.bcn.utils.UtilsGeneric;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

  @Autowired
  private UsuarioDaoServiceImplement userService;

  @Autowired
  private UtilsGeneric utils;

  private ApiResponse apiResponse;

  String mensaje = "";
  
  // Obtener la coleccion de usuarios
  @GetMapping("/getUsuarios")
  public ResponseEntity<ApiResponse> getUsuarios() throws Exception {
    List<Usuario> lista;   
    try {
      lista = userService.getDatos();      
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    if (lista != null) {
        apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, lista);
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    } else {
      apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED, "No existen usuarios registrados");
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    
  }

  // Obtener el usuario por Id
  @GetMapping("/getUsuario/{id}")
  public ResponseEntity<ApiResponse> getUsuario(@PathVariable(required = true) String id) throws Exception {
    Usuario usr;
    if(!utils.isnumeric(id)){ 
        apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,"El parámetro id debe ser un dato numerico");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
      }
   try {      
      usr = userService.getUsuario(Integer.parseInt(id));
      if (usr != null) {
        apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, usr);
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    } else {
      apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED,"No existen usuarios registrados");
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    } catch (Exception e) {

      throw new Exception(e.getMessage());
    }
    
  }



    // Obtener el usuario por Id
  @GetMapping("/getUsuario/")
  public ResponseEntity<?> getUsuarioByName(@RequestParam(required = true) String usuario) throws Exception {
    
    return new ResponseEntity<String> ("Hola " + usuario, HttpStatus.OK);
    //Usuario usr;
  //   if(!utils.isnumeric(id)){ 
  //       apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,"El parámetro id debe ser un dato numerico");
  //       return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
  //     }
  //  try {      
  //     usr = userService.getUsuario(Integer.parseInt(id));
  //     if (usr != null) {
  //       apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, usr);
  //     return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
  //   } else {
  //     apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED,"No existen usuarios registrados");
  //     return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
  //   }
  //   } catch (Exception e) {

  //     throw new Exception(e.getMessage());
  //   }
    
  }

  // Dar de alta un usuario
  @PostMapping("/addUsuario")
  public ResponseEntity<ApiResponse> postUsuario(@RequestBody Usuario usr) throws Exception {

    try {
      mensaje = userService.postUsuario(usr);
      if (mensaje.equals("OK")){
        apiResponse = new ApiSuccessResponse(Constantes.Codigo.CREATED, Constantes.Mensaje.MSG_SUCCESS, "Usuario creado");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
      }else{
        apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,mensaje);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  //Actualizacion de usuario
   @PutMapping("/updateUsuario/{id}")
  public ResponseEntity<ApiResponse> putUsuario(@RequestBody Usuario usr, @PathVariable(required=true) String id) throws Exception {
    if(!utils.isnumeric(id)){ 
        apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,"El parámetro id debe ser un dato numerico");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
      }
    try {
      mensaje = userService.putUsuario(usr, Integer.parseInt(id));
      if (mensaje.equals("OK")){
       apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, "Usuario actualizado");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
      }else{
        apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,mensaje);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

   //Desactivar usuario
   @PutMapping("/deactiveUsuario/{id}")
  public ResponseEntity<ApiResponse> deactiveUsuario(@PathVariable(required=true) String id) throws Exception {
    if(!utils.isnumeric(id)){ 
        apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,"El parámetro id debe ser un dato numerico");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
      }
    try {
      mensaje = userService.deactiveUsuario(Integer.parseInt(id));
      if (mensaje.equals("OK")){
        apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, "Usuario dado de baja");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
       } else{
        apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,mensaje);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
       }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
  //
  //Activar usuario
   @PutMapping("/activeUsuario/{id}")
  public ResponseEntity<ApiResponse> activeUsuario(@PathVariable(required=true) String id) throws Exception {
   if(!utils.isnumeric(id)){ 
        apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,"El parámetro id debe ser un dato numerico");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
      }
    try {
      mensaje = userService.activeUsuario(Integer.parseInt(id));
      if (mensaje.equals("OK")){
        apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, "Activacion de usuario realizada");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
      }else{
        apiResponse = new ApiErrorResponse(Constantes.Codigo.BAD_REQUEST, Constantes.Mensaje.MSG_FAILED,mensaje);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  // Obtener el usuario por Id
  @PostMapping("/login")
  public ResponseEntity<ApiResponse> loginUsuario(@RequestBody Usuario usuario) throws Exception {
    Usuario usr;
    try {
      usr = userService.loginUsuario(usuario);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    if (usr != null) {
      apiResponse = new ApiSuccessResponse(Constantes.Codigo.OK, Constantes.Mensaje.MSG_SUCCESS, usr);
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    } else {
      apiResponse = new ApiErrorResponse(Constantes.Codigo.NOT_FOUND, Constantes.Mensaje.MSG_FAILED,"No existen usuarios registrados");
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
  }



}
