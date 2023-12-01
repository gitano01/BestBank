package com.bcn.service.DaoPermiso;

import java.util.List;

import com.bcn.model.Permiso;

public interface PermisoDaoService {
    public Permiso getPermiso(int id) throws Exception;
    public List<Permiso> getPermisos() throws Exception;
    public String postPermiso(Permiso permiso) throws Exception;
    public String putPermiso(Permiso permiso, int id) throws Exception;
}