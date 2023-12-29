package com.bcn.service.DaoCliente;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Cliente;

public interface ClienteDaoService {
	public List<Cliente> getClientes() throws Exception, SQLException;

	public List<List<?>> getDatos() throws Exception;

	public Cliente getCliente(int id) throws Exception, SQLException;

	public String crearCliente(Cliente cliente) throws Exception, SQLException;

	public String updateCliente(Cliente cliente, int id) throws Exception, SQLException;

	public String dropCliente(Cliente cliente, int id) throws Exception, SQLException;

	public String deleteCliente(int id) throws Exception, SQLException;

}
