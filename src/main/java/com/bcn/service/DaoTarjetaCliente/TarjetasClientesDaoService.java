package com.bcn.service.DaoTarjetaCliente;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.TarjetasClientes;

public interface TarjetasClientesDaoService {
	public List<List<?>> getDatos() throws Exception, SQLException;

	public List<TarjetasClientes> getTarjetas() throws Exception, SQLException;

	public TarjetasClientes getTarjeta(int id) throws Exception, SQLException;

	public String assignTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException;

	public String lockTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException;

	public String tempLockTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException;

	public String tempUnlockTarjeta(TarjetasClientes tarjetas) throws Exception, SQLException;

	public String migrateTarjeta(TarjetasClientes tarjetas) throws Exception, SQLException;

	public String cancelTarjeta(TarjetasClientes tarjetas) throws Exception, SQLException;

	public String deleteTarjeta(TarjetasClientes tarjetas) throws Exception, SQLException;
}
