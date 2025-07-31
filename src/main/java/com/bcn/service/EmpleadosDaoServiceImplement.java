package com.bcn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Empleados;
import com.bcn.utils.DbConnect;

@Service
public class EmpleadosDaoServiceImplement implements EmpleadosDaoService {
	@Autowired
	private DbConnect con;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<Empleados> getEmpleados() throws Exception, SQLException {
		List<Empleados> listaEmpleados = new ArrayList<Empleados>();
		Empleados empleado = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from empleado");

			if ((rs = ps.executeQuery()).next()) {
				do {
					empleado = new Empleados();
					empleado.setEmpleadoId(rs.getInt("empleado_id"));
					empleado.setNombre(rs.getString("nombre"));
					empleado.setApellidoPaterno(rs.getString("apellido_paterno"));
					empleado.setApellidoMaterno(rs.getString("apellido_materno"));
					empleado.setTelefono(rs.getString("telefono"));
					empleado.setDireccion(rs.getString("direccion"));
					empleado.setColonia(rs.getString("colonia"));
					empleado.setCiudad(rs.getString("ciudad"));
					empleado.setEstado(rs.getString("estado"));
					empleado.setCodigoPostal(rs.getString("codigo_postal"));
					empleado.setDepartamento(rs.getString("departamento"));
					empleado.setEmail(rs.getString("email"));
					empleado.setActivo(rs.getBoolean("activo"));
					empleado.setFechaAlta(rs.getTimestamp("fecha_alta"));
					empleado.setFechaBaja(rs.getTimestamp("fecha_baja"));
					empleado.setSucursalId(rs.getInt("sucursal_id"));

					listaEmpleados.add(empleado);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaEmpleados = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return listaEmpleados;
	}

	@Override
	public List<List<?>> getDatos() throws Exception, SQLException {
		List<List<?>> datos = new ArrayList<List<?>>();
		List<Empleados> listaEmpleados = getEmpleados();
		try {
			datos.add(listaEmpleados);
		} catch (Exception e) {
			datos = null;
		}

		return datos;
	}

	@Override
	public Empleados getEmpleado(int id) throws Exception, SQLException {
		Empleados empleado = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from empleado where empleado_id = ?");
			ps.setInt(1, id);

			if ((rs = ps.executeQuery()).next()) {
				empleado = new Empleados();
				empleado.setEmpleadoId(rs.getInt("empleado_id"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellidoPaterno(rs.getString("apellido_paterno"));
				empleado.setApellidoMaterno(rs.getString("apellido_materno"));
				empleado.setTelefono(rs.getString("telefono"));
				empleado.setDireccion(rs.getString("direccion"));
				empleado.setColonia(rs.getString("colonia"));
				empleado.setCiudad(rs.getString("ciudad"));
				empleado.setEstado(rs.getString("estado"));
				empleado.setCodigoPostal(rs.getString("codigo_postal"));
				empleado.setDepartamento(rs.getString("departamento"));
				empleado.setEmail(rs.getString("email"));
				empleado.setActivo(rs.getBoolean("activo"));
				empleado.setFechaAlta(rs.getTimestamp("fecha_alta"));
				empleado.setFechaBaja(rs.getTimestamp("fecha_baja"));
				empleado.setSucursalId(rs.getInt("sucursal_id"));

			} else {
				System.out.println("No se hallaron registros");
				empleado = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return empleado;
	}

	@Override
	public String crearEmpleado(Empleados empleado) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		try {
			conn = con.getConnection();
			String sql = "insert into empleado(nombre, apellido_paterno, apellido_materno, telefono, direccion, colonia, ciudad, estado, codigo_postal, departamento, email, activo, fecha_alta, sucursal_id)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, empleado.getNombre());
			ps.setString(2, empleado.getApellidoPaterno());
			ps.setString(3, empleado.getApellidoMaterno());
			ps.setString(4, empleado.getTelefono());
			ps.setString(5, empleado.getDireccion());
			ps.setString(6, empleado.getColonia());
			ps.setString(7, empleado.getCiudad());
			ps.setString(8, empleado.getEstado());
			ps.setString(9, empleado.getCodigoPostal());
			ps.setString(10, empleado.getDepartamento());
			ps.setString(11, empleado.getEmail());
			ps.setBoolean(12, empleado.isActivo());
			ps.setTimestamp(13, tp);
			ps.setInt(14, empleado.getSucursalId());

			if (ps.executeUpdate() == 1) {
				System.out.println("Empleado agregado");
				response = "OK";
			} else {
				System.out.println("Error al agregar al empleado");
				response = "No se pudo agregar al empleado";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}

	@Override
	public String updateEmpleado(Empleados empleado, int id) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			String sql = "update empleado set nombre = ?, apellido_paterno = ?, apellido_materno = ?, telefono = ?, direccion = ?, colonia = ?, ciudad = ?, estado = ?, codigo_postal = ?, departamento = ?, email = ?, activo = ?, sucursal_id = ? "
					+ "where empleado_id = " + id + ";";

			ps = conn.prepareStatement(sql);
			ps.setString(1, empleado.getNombre());
			ps.setString(2, empleado.getApellidoPaterno());
			ps.setString(3, empleado.getApellidoMaterno());
			ps.setString(4, empleado.getTelefono());
			ps.setString(5, empleado.getDireccion());
			ps.setString(6, empleado.getColonia());
			ps.setString(7, empleado.getCiudad());
			ps.setString(8, empleado.getEstado());
			ps.setString(9, empleado.getCodigoPostal());
			ps.setString(10, empleado.getDepartamento());
			ps.setString(11, empleado.getEmail());
			ps.setBoolean(12, empleado.isActivo());
			ps.setInt(13, empleado.getSucursalId());

			if (ps.executeUpdate() == 1) {
				System.out.println("Empleado actualizado");
				response = "OK";
			} else {
				System.out.println("No se encontró información del empleado");
				response = "No se encontró información del empleado";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}

	@Override
	public String deleteEmpleado(Empleados empleado, int id) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			String sql = "delete from empleado where empleado_id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			if (ps.executeUpdate() == 1) {
				System.out.println("Empleado eliminado");
				response = "OK";
			} else {
				System.out.println("No se halló información del empleado");
				response = "Error al eliminar el empleado";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}

	@Override
	public String dropEmpleado(Empleados empleado, int id) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		try {
			conn = con.getConnection();
			String sql = "update empleado set activo = ?, fecha_baja = ? where empleado_id = ?;";

			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, false);
			ps.setTimestamp(2, tp);
			ps.setInt(3, id);

			if (ps.executeUpdate() == 1) {
				System.out.println("Empleado desactivado con éxito");
				response = "OK";
			} else {
				System.out.println("No se encontró información del empleado");
				response = "Error al desactivar al empleado. NO se halló información";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}
}
