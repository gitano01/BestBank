package com.bcn.service.DaoInventarioTarjeta;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.InventarioTarjetas;

public interface InventarioTarjetasDaoService {
	public List<List<?>> getDatos() throws Exception, SQLException;

	public List<InventarioTarjetas> getTarjetas() throws Exception, SQLException;

	public InventarioTarjetas getTarjeta(int id) throws Exception, SQLException;

	public List<InventarioTarjetas> getTarjetasByProvider(String providerName) throws Exception, SQLException;

	public String addTarjeta(InventarioTarjetas tarjeta) throws Exception, SQLException;

	public String updateTarjeta(InventarioTarjetas tarjeta, int id) throws Exception, SQLException;

	public String removeTarjeta(int id) throws Exception, SQLException;

	public String deleteTarjeta(int id) throws Exception, SQLException;

}
