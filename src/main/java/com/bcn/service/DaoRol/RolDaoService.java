package com.bcn.service.DaoRol;

import com.bcn.model.Rol;

public interface RolDaoService {
    
    public Rol getRol(int id) throws Exception;
    public String postRol(Rol rol) throws Exception;
    public String putRol(Rol rol) throws Exception;
    public String deactiveRol(int i)throws Exception;

}