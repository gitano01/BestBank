package com.bcn.service.DaoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Usuario;
import com.bcn.utils.DbConnect;
import com.bcn.utils.UtilsGeneric;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class UsuarioDaoServiceImplement implements UsuarioDaoService {
    //Inyeccion de dependecia 
    @Autowired
    private DbConnect db;

    @Autowired
    private UtilsGeneric utils;
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Usuario> getDatos() throws Exception{
        List<Usuario> list = null;
        //obtener los usuarios de la base de datos        
        JsonArray jArray  = null;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("select * from usuarios order by usuario_id asc");
            if((rs =ps.executeQuery()).next()){
                jArray = new JsonArray();
                do{
                    JsonObject obj = utils.getJsonObject(rs);
                    jArray.add(obj);
                }while(rs.next());
            }           
            }catch(Exception e){
            throw  new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        //validar el contenido del Json Array
        if(jArray.size()>0){
            list = new ArrayList<>();
            for(JsonElement o : jArray){
                Usuario usr = new  Gson().fromJson(o.getAsJsonObject().toString(), Usuario.class);
                list.add(usr);
            }      
        }
        return list;
    }

    @Override
    public Usuario getUsuario(int id) throws Exception{
        JsonObject jObject = null;
        Usuario usr = null;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("select * from usuarios where usuario_id= ?;");
            ps.setInt(1, id);
            if((rs =ps.executeQuery()).next()){
                jObject = new JsonObject();
                do{
                    jObject = utils.getJsonObject(rs);
                    
                }while(rs.next());
            }   
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn,ps,rs);
        }
        //validar el contenido del JsonObject
        if(jObject != null){
            usr = new  Gson().fromJson(jObject.getAsJsonObject().toString(), Usuario.class);
        }
        // TODO Auto-generated method stub
        return usr;
    }

    
    public String postUsuario(Usuario usuario) throws Exception {
        String response = "";
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("Insert into usuarios(usuario,contrasenia,email,activo,"+
            "rol_id,permiso_id,empleado_id,fecha_alta,cliente_id)"
            +" values (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, utils.getMd5(usuario.getContrasenia()));
            ps.setString(3, usuario.getEmail());
            ps.setBoolean(4, false);
            ps.setInt(5, usuario.getRol_id());
            ps.setInt(6, usuario.getPermiso_id());
            ps.setInt(7, usuario.getEmpleado_id());
            ps.setTimestamp(8, utils.getFechaHoy());
            ps.setInt(9, usuario.getCliente_id());
            if (ps.executeUpdate() == 1) {
				System.out.println("Usuario agregado");
				response = "OK";
			} else {
				System.out.println("Error al agregar usuario");
				response = "No se pudo agregar al usuario";
			}

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        // TODO Auto-generated method stub
        return response;
    }

    
    public String deactiveUsuario(int id) throws Exception {
        // TODO Auto-generated method stub
        String response="";
        try{
        conn = db.getConnection();
        ps = conn.prepareStatement("update usuarios set activo=false where usuario_id= ? and fecha_modificacion = ?");        
        ps.setInt(1, id);
        ps.setTimestamp(2, utils.getFechaHoy());
        if (ps.executeUpdate() == 0) {
				System.out.println("Usuario dado de baja temporal");
				response = "OK";
			} else {
				System.out.println("Error al dar de baja a usuario");
				response = "No se pudo dar de baja a usuario";
			}
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        return response;
    }

    public String activeUsuario(int id) throws Exception {
        // TODO Auto-generated method stub
        String response="";
        try{
        conn = db.getConnection();
        ps = conn.prepareStatement("update usuarios set activo=true where usuario_id= ? and fecha_modificacion = ?");        
        ps.setInt(1, id);
        ps.setTimestamp(2, utils.getFechaHoy());        
        if (ps.executeUpdate() == 0) {
				System.out.println("Usuario activado");
				response = "OK";
			} else {
				System.out.println("Error al dar de baja a usuario");
				response = "No se pudo activar al usuario";
			}
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        return response;
    }

    public String putUsuario(Usuario usuario,int id) throws Exception{
    String  response="";
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("update usuarios set usuario=?,email=?,"+
            "rol_id=?,permiso_id=?,fecha_modificacion=? where usuario_id=?");            
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getEmail());           
            ps.setInt(3, usuario.getRol_id());
            ps.setInt(4, usuario.getPermiso_id());           
            ps.setTimestamp(5, utils.getFechaHoy()); 
            ps.setInt(6, id);           
            if (ps.executeUpdate() == 1) {
				System.out.println("Usuario actualizado");
				response = "OK";
			} else {
				System.out.println("Error al actualizar usuario");
				response = "No se pudo actualizar al usuario";
			}
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        return response;
    }

    @Override
    public Usuario loginUsuario(Usuario usuario) throws Exception {
        JsonObject jObject = null;
        Usuario usr = null;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("select * from usuarios where usuario= ? and contrasenia= ?");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, utils.getMd5(usuario.getContrasenia()));
            if((rs =ps.executeQuery()).next()){
                jObject = new JsonObject();
                do{
                    jObject = utils.getJsonObject(rs);
                    
                }while(rs.next());
            }   
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn,ps,rs);
        }
        //validar el contenido del JsonObject
        if(jObject != null){
            usr = new  Gson().fromJson(jObject.getAsJsonObject().toString(), Usuario.class);
            usr.setContrasenia(null);
        }
        // TODO Auto-generated method stub
        return usr;
    }    
    
    


}
