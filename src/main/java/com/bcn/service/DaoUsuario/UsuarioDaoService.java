package com.bcn.service.DaoUsuario;

import java.util.List;

import com.bcn.model.Usuario;

public interface UsuarioDaoService {
    public List<Usuario> getDatos() throws Exception;
    public Usuario getUsuario(int id) throws Exception;
    public String postUsuario(Usuario usuario) throws Exception;
    public String putUsuario(Usuario usuario,int id) throws Exception;
    public String deactiveUsuario(int id) throws Exception;
    public String activeUsuario(int id) throws Exception;
}
