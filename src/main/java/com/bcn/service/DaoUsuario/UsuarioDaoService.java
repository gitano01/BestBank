package com.bcn.service.DaoUsuario;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Usuario;

public interface UsuarioDaoService {
    public List<Usuario> getDatos() throws Exception, SQLException;
}
