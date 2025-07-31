package com.bcn.service.DaoRol;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Rol;
import com.bcn.utils.DbConnect;
import com.bcn.utils.UtilsGeneric;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class RolDaoServiceImplement implements RolDaoService {

@Autowired
private DbConnect db;

@Autowired
private UtilsGeneric utils;

private Connection conn = null;
private PreparedStatement ps = null;
private ResultSet rs = null;

    public Rol getRol(int id) throws Exception {
        JsonObject jObj = null;
        Rol rol = null;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * FROM roles where rol_id=?");
            ps.setInt(1, id);
            if((rs = ps.executeQuery()).next()){
                jObj =new JsonObject();
                do{
                    jObj = utils.getJsonObject(rs);
                }while(rs.next());
            }
        }catch(SQLException e){
            throw new SQLException (e.getMessage());
        
        }catch(Exception ex){
            throw new Exception (ex.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }

        if(jObj != null){
            rol = new Gson().fromJson(jObj.getAsJsonObject().toString(), Rol.class);
        }
    return rol;
    }

    public List<Rol> getRoles() throws Exception {
        JsonArray jArray = null;
        JsonObject jObj = null;
        Rol rol = null;
        List<Rol> lista = null;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * FROM roles order by rol_id asc");
            if((rs = ps.executeQuery()).next()){
                jArray =new JsonArray();
                do{                    
                    jObj = utils.getJsonObject(rs);
                    jArray.add(jObj);
                }while(rs.next());
            }
        }
        catch(Exception ex){
            throw new Exception (ex.getMessage());
        }
        finally{
            db.closeConnection(conn, ps, rs);
        }

        if(jArray.size()>0){
            lista = new ArrayList<>();
            for(JsonElement e : jArray){
                rol = new Gson().fromJson(e.getAsJsonObject().toString(), Rol.class);
                lista.add(rol);
            }            
        }
    return lista;
    }


    public String postRol(Rol rol) throws Exception {
        String response = "";

        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("insert into roles(rol, descripcion) values (?,?)");
            ps.setString(1, rol.getRol());
            ps.setString(2, rol.getDescripcion());

            if (ps.executeUpdate() == 1) {
				System.out.println("Rol agregado");
				response = "OK";
			} else {
				System.out.println("Error al agregar rol");
				response = "No se pudo agregar el rol";
			}

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        return response;
    }

    public String putRol(Rol rol, int id) throws Exception {
        String response="";
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("update roles set rol=?, descripcion=?, fecha_modificacion = ? where rol_id=?");
            ps.setString(1, rol.getRol());
            ps.setTimestamp(2, utils.getFechaHoy());
            ps.setString(3, rol.getDescripcion());
            ps.setInt(3, id);

            if(ps.executeUpdate() == 1){
                System.out.println("rol actualizado");
				response = "OK";
            }else{
                System.out.println("Error al actualizar rol");
				response = "No se pudo actualizar el rol";
            }
        }catch(SQLException e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        return response;
    }

   

    
    //se deja esta parte del codigo por si se tiene en mente agregar un campo de status a la tabla rol
    // public String deactiveRol(int i) throws Exception{
    // String response="";
    //     try{
    //         conn = db.getConnection();
    //         ps = conn.prepareStatement("update roles set rol=?, descripcion=? where rol_id=?");
    //         ps.setString(1, rol.getRol());
    //         ps.setString(2, rol.getDescripcion());
    //         ps.setInt(3, id);

    //         if(ps.execute()){
    //             System.out.println("rol actualizado");
	// 			response = "OK";
    //         }else{
    //             System.out.println("Error al actualizar rol");
	// 			response = "No se pudo actualizar el rol";
    //         }
    //     }catch(Exception e){
    //         throw new Exception(e.getMessage());
    //     }
    //     return response;
    // }
    
}