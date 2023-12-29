package com.bcn.service.DaoCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Cliente;
import com.bcn.utils.DbConnect;

@Service
public class ClienteDaoServiceImplement implements ClienteDaoService {

	@Autowired
	private DbConnect con;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<List<?>> getDatos() throws Exception {
		List<List<?>> datos = new ArrayList<List<?>>();
		List<Cliente> clientes = getClientes();
		try {
			datos.add(clientes);
		} catch (Exception e) {
			datos = null;
		}
		return datos;
	}

	@Override
	public List<Cliente> getClientes() throws Exception, SQLException {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente cliente = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from clientes");

			if ((rs = ps.executeQuery()).next()) {
				do {
					cliente = new Cliente();
					cliente.setClienteId(rs.getInt("cliente_id"));
					cliente.setNombre(rs.getString("nombre"));
					cliente.setApellidoPaterno(rs.getString("apellido_paterno"));
					cliente.setApellidoMaterno(rs.getString("apellido_materno"));
					cliente.setDireccion(rs.getString("direccion"));
					cliente.setTelefono(rs.getString("telefono"));
					cliente.setColonia(rs.getString("colonia"));
					cliente.setCodigoPostal(rs.getString("codigo_postal"));
					cliente.setCiudad(rs.getString("ciudad"));
					cliente.setEstado(rs.getString("estado"));
					cliente.setEmail(rs.getString("email"));
					cliente.setEstatus(rs.getString("estatus"));
					cliente.setActivo(rs.getBoolean("activo"));
					cliente.setFechaAlta(rs.getTimestamp("fecha_alta"));
					cliente.setFechaBaja(rs.getTimestamp("fecha_baja"));
					cliente.setSucursalId(rs.getInt("sucursal_id"));
					cliente.setEmpleadoId(rs.getInt("empleado_id"));
					listaClientes.add(cliente);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaClientes = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return listaClientes;
	}

	@Override
	public Cliente getCliente(int id) throws Exception, SQLException {
		Cliente cliente = null;

		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from clientes where cliente_id = ?");
			ps.setInt(1, id);

			if ((rs = ps.executeQuery()).next()) {
				cliente = new Cliente();

				cliente.setClienteId(rs.getInt("cliente_id"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellidoPaterno(rs.getString("apellido_paterno"));
				cliente.setApellidoMaterno(rs.getString("apellido_materno"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setColonia(rs.getString("colonia"));
				cliente.setCodigoPostal(rs.getString("codigo_postal"));
				cliente.setCiudad(rs.getString("ciudad"));
				cliente.setEstado(rs.getString("estado"));
				cliente.setEmail(rs.getString("email"));
				cliente.setEstatus(rs.getString("estatus"));
				cliente.setActivo(rs.getBoolean("activo"));
				cliente.setFechaAlta(rs.getTimestamp("fecha_alta"));
				cliente.setFechaBaja(rs.getTimestamp("fecha_baja"));
				cliente.setSucursalId(rs.getInt("sucursal_id"));
				cliente.setEmpleadoId(rs.getInt("empleado_id"));
			} else {
				System.out.println("No existen registros");
				cliente = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return cliente;
	}

	@Override
	public String crearCliente(Cliente cliente) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			String sql = "insert into clientes(nombre, apellido_paterno, apellido_materno, direccion, telefono, colonia, codigo_postal, ciudad, estado, email, estatus, activo, sucursal_id, empleado_id, fecha_alta)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			System.out.println("DATOS A INGRESAR: " + sql);
			ps = conn.prepareStatement(sql);
			Long datetime = System.currentTimeMillis();
			Timestamp tp = new Timestamp(datetime);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidoPaterno());
			ps.setString(3, cliente.getApellidoMaterno());
			ps.setString(4, cliente.getDireccion());
			ps.setString(5, cliente.getTelefono());
			ps.setString(6, cliente.getColonia());
			ps.setString(7, cliente.getCodigoPostal());
			ps.setString(8, cliente.getCiudad());
			ps.setString(9, cliente.getEstado());
			ps.setString(10, cliente.getEmail());
			ps.setString(11, cliente.getEstatus());
			ps.setBoolean(12, cliente.isActivo());
			ps.setInt(13, cliente.getSucursalId());
			ps.setInt(14, cliente.getEmpleadoId());
			ps.setTimestamp(15, tp);
			System.out.println("TRADUCCION SQL ES: " + ps.toString());

			if (ps.executeUpdate() == 1) {
				System.out.println("Cliente agregado");
				response = "OK";
			} else {
				System.out.println("Error al agregar cliente");
				response = "No se pudo agregar al cliente";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}

		return response;
	}

	@Override
	public String updateCliente(Cliente cliente, int id) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			String sql = "update clientes set nombre = ?, apellido_paterno = ?, apellido_materno = ?, direccion = ?, telefono = ?, colonia = ?, codigo_postal = ?, ciudad = ?, estado = ?, email = ?, estatus = ?, activo = ?, empleado_id = ?, sucursal_id = ?"
					+ " where cliente_id = " + id + ";";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidoPaterno());
			ps.setString(3, cliente.getApellidoMaterno());
			ps.setString(4, cliente.getDireccion());
			ps.setString(5, cliente.getTelefono());
			ps.setString(6, cliente.getColonia());
			ps.setString(7, cliente.getCodigoPostal());
			ps.setString(8, cliente.getCiudad());
			ps.setString(9, cliente.getEstado());
			ps.setString(10, cliente.getEmail());
			ps.setString(11, cliente.getEstatus());
			ps.setBoolean(12, cliente.isActivo());
			ps.setInt(13, cliente.getSucursalId());
			ps.setInt(14, cliente.getEmpleadoId());
			System.out.println("TRADUCCION SQL ES: " + ps.toString());

			if (ps.executeUpdate() == 1) {
				System.out.println("Cliente actualizado");
				response = "OK";
			} else {
				System.out.println("No se encontró información del cliente");
				response = "No se encontró información del cliente";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return response;
	}

	public String dropCliente(Cliente cliente, int id) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			String sql = "update clientes set estatus = ?, activo = ?, fecha_baja = ?" + " where cliente_id = " + id
					+ ";";
			ps = conn.prepareStatement(sql);
			Long datetime = System.currentTimeMillis();
			Timestamp tp = new Timestamp(datetime);
			ps.setString(1, cliente.getEstatus());
			ps.setBoolean(2, false);
			ps.setTimestamp(3, tp);
			System.out.println("TRADUCCION SQL ES: " + ps.toString());

			if (ps.executeUpdate() == 1) {
				System.out.println("Cliente dado de baja");
				response = "OK";
			} else {
				System.out.println("No se encontró información del cliente");
				response = "No se encontró información del cliente";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return response;
	}

	@Override
	public String deleteCliente(int id) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			String sql = "delete from clientes where cliente_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println("TRADUCCION SQL ES: " + ps.toString());

			if (ps.executeUpdate() == 1) {
				System.out.println("Cliente eliminado");
				response = "OK";
			} else {
				System.out.println("No se encontró información del cliente");
				response = "No se encontró información del cliente";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return response;
	}
}
