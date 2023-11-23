package com.bcn.service.DaoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Usuario;
import com.bcn.utils.DbConnect;
import com.google.gson.Gson;

@Service
public class UsuarioDaoServiceImplement implements UsuarioDaoService {
    //Inyeccion de dependecia 
    @Autowired
    private DbConnect db;
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    public List<Usuario> getDatos() throws Exception, SQLException{
        //obtener los usuarios de la base de datos
        List<Usuario> list = null;
        try{
        conn = db.getConnection();
        ps = conn.prepareStatement("Select * from usuarios");
        System.out.println(ps.toString());
        rs =ps.executeQuery();
           list = new ArrayList<>();
            while(rs.next()){
                //System.out.println("****** el objetito" + new Gson().toJson(rs.toString()) +"* ******");
                
                Usuario usr = new Usuario();
                usr.setUsuario_id(rs.getInt("usuario_id"));
                usr.setUsuario(rs.getString("usuario"));
                usr.setContrasenia(rs.getString("contrasenia"));
                usr.setEmail(rs.getString("email"));
                usr.setActivo(rs.getBoolean("activo"));
                usr.setRol_id(rs.getInt("rol_id"));
                usr.setPermiso_id(rs.getInt("permiso_id"));
                usr.setEmpleado_id(rs.getInt("empleado_id"));
                usr.setFecha_alta(rs.getTimestamp("fecha_alta"));
                usr.setFecha_baja(rs.getTimestamp("fecha_baja"));
                usr.setFecha_modificacion(rs.getTimestamp("fecha_modificacion"));
                usr.setCliente_id(rs.getInt("cliente_id"));
                list.add(usr);
            }            
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.closeConnection(conn, ps, rs);
        }
        return list;
    }
}
