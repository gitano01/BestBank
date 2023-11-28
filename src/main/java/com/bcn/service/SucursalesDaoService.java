package com.bcn.service;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Sucursales;

public interface SucursalesDaoService {
	public List<List<?>> getDatos() throws Exception, SQLException;

	public List<Sucursales> getSucursales() throws Exception, SQLException;

	public Sucursales getSucursal(int id) throws Exception, SQLException;

	public String crearSucursal(Sucursales sucursal) throws Exception, SQLException;

	public String updateSucursal(Sucursales sucursal, int id) throws Exception, SQLException;

	public String dropSucursal(int id) throws Exception, SQLException;

	public String deleteSucursal(int id) throws Exception, SQLException;

}
