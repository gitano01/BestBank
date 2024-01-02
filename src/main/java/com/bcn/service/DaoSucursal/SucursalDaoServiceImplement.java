package com.bcn.service.DaoSucursal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Sucursal;
import com.bcn.utils.DbConnect;

@Service
public class SucursalDaoServiceImplement implements SucursalDaoService {
	@Autowired
	private DbConnect con;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<List<?>> getDatos() throws Exception, SQLException {
		List<List<?>> datos = new ArrayList<List<?>>();
		List<Sucursal> sucursales = getSucursales();
		try {
			datos.add(sucursales);
		} catch (Exception e) {
			datos = null;
		}

		return datos;
	}

	@Override
	public List<Sucursal> getSucursales() throws Exception, SQLException {
		List<Sucursal> listaSucursales = new ArrayList<Sucursal>();
		Sucursal sucursal = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from sucursales;");

			if ((rs = ps.executeQuery()).next()) {
				do {
					sucursal = new Sucursal();

					sucursal.setSucursalId(rs.getInt("sucursal_id"));
					sucursal.setNombreSucursal(rs.getString("nombre"));
					sucursal.setNumeroSucursal(rs.getString("numero_sucursal"));
					sucursal.setTelefono(rs.getString("telefono"));
					sucursal.setCiudad(rs.getString("ciudad"));
					sucursal.setDireccion(rs.getString("direccion"));
					sucursal.setEstado(rs.getString("estado"));
					sucursal.setFechaApertura(rs.getTimestamp("fecha_apertura"));
					sucursal.setFechaCierre(rs.getTimestamp("fecha_cierre"));
					sucursal.setPlazaId(rs.getInt("plaza_id"));
					listaSucursales.add(sucursal);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaSucursales = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return listaSucursales;
	}

	@Override
	public Sucursal getSucursal(int id) throws Exception, SQLException {
		Sucursal sucursal = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from sucursales where sucursal_id = ?;");
			ps.setInt(1, id);

			if ((rs = ps.executeQuery()).next()) {
				sucursal = new Sucursal();

				sucursal.setSucursalId(rs.getInt("sucursal_id"));
				sucursal.setNombreSucursal(rs.getString("nombre"));
				sucursal.setNumeroSucursal(rs.getString("numero_sucursal"));
				sucursal.setTelefono(rs.getString("telefono"));
				sucursal.setCiudad(rs.getString("ciudad"));
				sucursal.setDireccion(rs.getString("direccion"));
				sucursal.setEstado(rs.getString("estado"));
				sucursal.setFechaApertura(rs.getTimestamp("fecha_apertura"));
				sucursal.setFechaCierre(rs.getTimestamp("fecha_cierre"));
				sucursal.setPlazaId(rs.getInt("plaza_id"));

			} else {
				System.out.println("No existen registros");
				sucursal = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return sucursal;
	}

	@Override
	public String crearSucursal(Sucursal sucursal) throws Exception, SQLException {
		String response = "";

		try {
			conn = con.getConnection();
			Long datetime = System.currentTimeMillis();
			Timestamp tp = new Timestamp(datetime);
			String sql = "insert into sucursales (nombre, numero_sucursal, direccion, telefono, ciudad, estado, fecha_apertura)"
					+ "values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);

			ps.setString(1, sucursal.getNombreSucursal());
			ps.setString(2, sucursal.getNumeroSucursal());
			ps.setString(3, sucursal.getDireccion());
			ps.setString(4, sucursal.getTelefono());
			ps.setString(5, sucursal.getCiudad());
			ps.setString(6, sucursal.getEstado());
			ps.setInt(7, sucursal.getPlazaId());
			ps.setTimestamp(7, tp);

			if (ps.executeUpdate() == 1) {
				System.out.println("Sucursal agregada");
				response = "OK";
			} else {
				System.out.println("Error al agregar la sucursal");
				response = "No se pudo agregar la sucursal";
			}
		} catch (Exception e) {

		} finally {
			con.closeConnection(conn, ps);
		}

		return response;
	}

	@Override
	public String updateSucursal(Sucursal sucursal, int id) throws Exception, SQLException {
		String response = "";

		try {
			conn = con.getConnection();
			String sql = "update sucursales set nombre= ?, numero_sucursal = ?, telefono = ?, direccion = ?, ciudad = ?, estado = ? where sucursal_id = "
					+ id + ";";
			ps = conn.prepareStatement(sql);

			ps.setString(1, sucursal.getNombreSucursal());
			ps.setString(2, sucursal.getNumeroSucursal());
			ps.setString(3, sucursal.getTelefono());
			ps.setString(4, sucursal.getDireccion());
			ps.setString(5, sucursal.getCiudad());
			ps.setString(6, sucursal.getEstado());
			ps.setInt(7, sucursal.getPlazaId());
			if (ps.executeUpdate() == 1) {
				System.out.println("Sucursal actualizada");
				response = "OK";
			} else {
				System.out.println("No se encontró información de la sucursal");
				response = "No se encontró información de la sucursal";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}

	@Override
	public String dropSucursal(int id) throws Exception, SQLException {
		String response = "";
		try {
			long datetime = System.currentTimeMillis();
			Timestamp tp = new Timestamp(datetime);
			conn = con.getConnection();
			String sql = "update sucursales set fecha_cierre = ? where sucursal_id = " + id + ";";
			ps = conn.prepareStatement(sql);

			ps.setTimestamp(1, tp);

			if (ps.executeUpdate() == 1) {
				System.out.println("Sucursal dada de baja");
				response = "OK";
			} else {
				System.out.println("No se encontró información de la sucursal");
				response = "No se encontró información de la sucursal";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}

	@Override
	public String deleteSucursal(int id) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			String sql = "delete from sucursales where sucursal_id = " + id + ";";
			ps = conn.prepareStatement(sql);

			if (ps.executeUpdate() == 1) {
				System.out.println("Sucursal eliminada");
				response = "OK";
			} else {
				System.out.println("No se encontró información de la sucursal");
				response = "No se encontró información de la sucursal";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}

	public String getCodigoPlaza(DbConnect con, int cliente_id) throws Exception, SQLException {
		String codigo_plaza = "";
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement(
					"select cp.codigo_plaza from sucursales s " + "join clientes c on c.sucursal_id = s.sucursal_id "
							+ "join catalogo_plazas cp on cp.plaza_id = s.plaza_id " + "where c.cliente_id = ?;");
			ps.setInt(1, cliente_id);

			if ((rs = ps.executeQuery()).next()) {
				codigo_plaza = String.valueOf(rs.getInt("codigo_plaza"));
			} else {
				System.out.println("No se encontraron registros");
				codigo_plaza = "";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return codigo_plaza;
	}

}
