package com.bcn.service.DaoRol;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Rol;
import com.bcn.utils.DbConnect;
import com.bcn.utils.UtilsGeneric;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
        }catch(Exception e){
            throw new Exception (e.getMessage());
        }finally{
            db.closeConnection(conn, ps, rs);
        }

        if(jObj != null){
            rol = new Gson().fromJson(jObj.getAsJsonObject().toString(), Rol.class);
        }
    return rol;
    }

    public Rol getRoles() throws Exception {
        JsonArray jArray = null;
        Rol rol = null;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * FROM roles order by asc");
            if((rs = ps.executeQuery()).next()){
                jArray =new JsonArray();
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
            rol = new Gson().fromJson(jObj.getAsJsonObject().toString(), Rol.class);
        }
    return rol;
    }


    public String postRol(Rol rol) throws Exception {
        return null;
    }

    public String putRol(Rol rol) throws Exception {
        return null;
    }

    public String deactiveRol(int i) throws Exception{
        return null;
    }
    
}