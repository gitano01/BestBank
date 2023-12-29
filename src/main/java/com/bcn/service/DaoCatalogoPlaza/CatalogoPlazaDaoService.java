package com.bcn.service.DaoCatalogoPlaza;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.CatalogoPlaza;

public interface CatalogoPlazaDaoService {
	public List<CatalogoPlaza> listCatalogo() throws Exception, SQLException;

	public List<CatalogoPlaza> listCatalogo(String codigo_plaza) throws Exception, SQLException;

	public CatalogoPlaza getCatalogo(int id) throws Exception, SQLException;

	public CatalogoPlaza getCatalogoByCodigoPlaza(String codigo_plaza) throws Exception, SQLException;

}
