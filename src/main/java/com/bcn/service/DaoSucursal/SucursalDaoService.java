package com.bcn.service.DaoSucursal;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Sucursal;

public interface SucursalDaoService {
	public List<List<?>> getDatos() throws Exception, SQLException;

	public List<Sucursal> getSucursales() throws Exception, SQLException;

	public Sucursal getSucursal(int id) throws Exception, SQLException;

	public String crearSucursal(Sucursal sucursal) throws Exception, SQLException;

	public String updateSucursal(Sucursal sucursal, int id) throws Exception, SQLException;

	public String dropSucursal(int id) throws Exception, SQLException;

	public String deleteSucursal(int id) throws Exception, SQLException;

}
