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

import com.bcn.model.Usuario;
import com.bcn.service.DaoUsuario.UsuarioDaoServiceImplement;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

  @Autowired
  private UsuarioDaoServiceImplement userService;

  // Obtener la coleccion de usuarios
  @GetMapping("/getUsuarios")
  public ResponseEntity<?> getUsuarios() throws Exception {
    List<Usuario> lista;
    try {
      lista = userService.getDatos();

    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    if (lista != null) {
      return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("No existen usuarios registrados", HttpStatus.NOT_FOUND);
    }
  }

  // Obtener el usuario por Id
  @GetMapping("/getUsuario/{id}")
  public ResponseEntity<?> getUsuario(@PathVariable String id) throws Exception {
    Usuario usr;
    try {
      usr = userService.getUsuario(Integer.parseInt(id));
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    if (usr != null) {
      return new ResponseEntity<Usuario>(usr, HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("No existen usuarios registrados", HttpStatus.NOT_FOUND);
    }
  }

  // Dar de alta un usuario
  @PostMapping("/addUsuario")
  public ResponseEntity<?> postUsuario(@RequestBody Usuario usr) throws Exception {
    String mensaje = "";
    try {
      mensaje = userService.postUsuario(usr);
      if (mensaje.equals("OK"))
        return new ResponseEntity<String>("Usuario creado", HttpStatus.OK);
      else
        return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  //Actualizacion de usuario
   @PutMapping("/updateUsuario/{id}")
  public ResponseEntity<?> putUsuario(@RequestBody Usuario usr, @PathVariable String id) throws Exception {
    String mensaje = "";
    try {
      mensaje = userService.putUsuario(usr, Integer.parseInt(id));
      if (mensaje.equals("OK"))
        return new ResponseEntity<String>("Usuario actualizado", HttpStatus.OK);
      else
        return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

   //Desactivar usuario
   @PutMapping("/deactiveUsuario/{id}")
  public ResponseEntity<?> deactiveUsuario(@PathVariable String id) throws Exception {
    String mensaje = "";
    try {
      mensaje = userService.deactiveUsuario(Integer.parseInt(id));
      if (mensaje.equals("OK"))
        return new ResponseEntity<String>("Baja de usuario realizada", HttpStatus.OK);
      else
        return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
  //
  //Activar usuario
   @PutMapping("/activeUsuario/{id}")
  public ResponseEntity<?> activeUsuario(@PathVariable String id) throws Exception {
    String mensaje = "";
    try {
      mensaje = userService.activeUsuario(Integer.parseInt(id));
      if (mensaje.equals("OK"))
        return new ResponseEntity<String>("Activacion de usuario realizada", HttpStatus.OK);
      else
        return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  // Obtener el usuario por Id
  @PostMapping("/login")
  public ResponseEntity<?> loginUsuario(@RequestBody Usuario usuario) throws Exception {
    Usuario usr;
    try {
      usr = userService.loginUsuario(usuario);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    if (usr != null) {
      return new ResponseEntity<Usuario>(usr, HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("No existen usuarios registrados", HttpStatus.NOT_FOUND);
    }
  }

}
