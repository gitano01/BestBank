package com.bcn.service.DaoPermiso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Permiso;
import com.bcn.utils.DbConnect;
import com.bcn.utils.UtilsGeneric;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class PermisoDaoServiceImplement implements PermisoDaoService{
    
@Autowired
private DbConnect db;

@Autowired
private UtilsGeneric utils;

private Connection conn = null;
private PreparedStatement ps = null;
private ResultSet rs = null;

    public Permiso getPermiso(int id) throws Exception {
        JsonObject jObj = null;
        Permiso permiso = null;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * FROM permisos where permiso_id=?");
            ps.setInt(1, id);
            if((rs = ps.executeQuery()).next()){
                jObj =new JsonObject();
                do{
                    jObj = utils.getJsonObject(rs);
                }while(rs.next());
            }
        }catch(Exception e){
            throw new Exception (e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }

        if(jObj != null){
            permiso = new Gson().fromJson(jObj.getAsJsonObject().toString(), Permiso.class);
        }
    return permiso;
    }

    public List<Permiso> getPermisos() throws Exception {
        JsonArray jArray = null;
        JsonObject jObj = null;
        Permiso permiso = null;
        List<Permiso> lista = null;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * FROM permisos order by permiso_id asc");
            if((rs = ps.executeQuery()).next()){
                jArray =new JsonArray();
                do{                    
                    jObj = utils.getJsonObject(rs);
                    jArray.add(jObj);
                }while(rs.next());
            }
        }catch(Exception e){
            throw new Exception (e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }

        if(jArray.size()>0){
            lista = new ArrayList<>();
            for(JsonElement e : jArray){
                permiso = new Gson().fromJson(e.getAsJsonObject().toString(), Permiso.class);
                lista.add(permiso);
            }            
        }
    return lista;
    }


    public String postPermiso(Permiso permiso) throws Exception {
        String response = "";

        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("insert into permisos(permiso, descripcion) values (?,?)");
            ps.setString(1, permiso.getPermiso());
            ps.setString(2, permiso.getDescripcion());

            if (ps.executeUpdate() == 1) {
				System.out.println("Permiso agregado");
				response = "OK";
			} else {
				System.out.println("Error al agregar permiso");
				response = "No se pudo agregar el permiso";
			}

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        return response;
    }

    public String putPermiso(Permiso permiso, int id) throws Exception {
        String response="";
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("update permisos set permiso=?, descripcion=? where permiso_id=?");
            ps.setString(1, permiso.getPermiso());
            ps.setString(2, permiso.getDescripcion());
            ps.setInt(3, id);

            if(ps.executeUpdate() == 1){
                System.out.println("Permiso actualizado");
				response = "OK";
            }else{
                System.out.println("Error al actualizar rol");
				response = "No se pudo actualizar el permiso";
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        return response;
    }
   
}