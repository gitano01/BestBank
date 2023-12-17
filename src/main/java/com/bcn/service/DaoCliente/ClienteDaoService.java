package com.bcn.service.DaoCliente;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Clientes;

public interface ClienteDaoService {
	public List<Clientes> getClientes() throws Exception, SQLException;

	public List<List<?>> getDatos() throws Exception;

	public Clientes getCliente(int id) throws Exception, SQLException;

	public String crearCliente(Clientes cliente) throws Exception, SQLException;

	public String updateCliente(Clientes cliente, int id) throws Exception, SQLException;

	public String dropCliente(Clientes cliente, int id) throws Exception, SQLException;

	public String deleteCliente(int id) throws Exception, SQLException;

}
