package com.bcn.service.DaoInventarioTarjeta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.InventarioTarjetas;
import com.bcn.utils.DbConnect;

@Service
public class InventarioTarjetasDaoServiceImplement implements InventarioTarjetasDaoService {
	@Autowired
	private DbConnect con;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<List<?>> getDatos() throws Exception, SQLException {
		List<List<?>> datos = new ArrayList<List<?>>();
		List<InventarioTarjetas> listaTarjetas = getTarjetas();

		try {
			datos.add(listaTarjetas);
		} catch (Exception e) {
			datos = null;
		}
		return datos;
	}

	@Override
	public List<InventarioTarjetas> getTarjetas() throws Exception, SQLException {
		List<InventarioTarjetas> listaTarjetas = new ArrayList<InventarioTarjetas>();
		InventarioTarjetas tarjeta = null;
		try {

			conn = con.getConnection();
			ps = conn.prepareStatement("select * from inventario_tarjetas");

			if ((rs = ps.executeQuery()).next()) {
				do {
					tarjeta = new InventarioTarjetas();

					tarjeta.setInventarioTarjetaId(rs.getInt("inventario_tarjeta_id"));
					tarjeta.setNumeroTarjeta(rs.getString("numero_tarjeta"));
					tarjeta.setMesExp(rs.getString("mes_expiracion"));
					tarjeta.setAñoExp(rs.getString("año_expiracion"));
					tarjeta.setCvv(rs.getString("cvv"));
					tarjeta.setNip(rs.getString("nip"));
					tarjeta.setTarjetaAsignada(rs.getBoolean("tarjeta_asignada"));
					tarjeta.setTarjetaDañada(rs.getBoolean("tarjeta_dañada"));
					tarjeta.setProveedor(rs.getString("proveedor"));
					tarjeta.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
					tarjeta.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
					tarjeta.setFechaRecepcion(rs.getTimestamp("fecha_recepcion"));
					tarjeta.setFechaBaja(rs.getTimestamp("fecha_baja"));
					tarjeta.setSucursalId(rs.getInt("sucursal_id"));

					listaTarjetas.add(tarjeta);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaTarjetas = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {

			con.closeConnection(conn, ps, rs);
		}
		return listaTarjetas;
	}

	@Override
	public InventarioTarjetas getTarjeta(int id) throws Exception, SQLException {
		InventarioTarjetas tarjeta = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from inventario_tarjetas where inventario_tarjeta_id = " + id + ";");

			if ((rs = ps.executeQuery()).next()) {
				tarjeta = new InventarioTarjetas();

				tarjeta.setInventarioTarjetaId(rs.getInt("inventario_tarjeta_id"));
				tarjeta.setNumeroTarjeta(rs.getString("numero_tarjeta"));
				tarjeta.setMesExp(rs.getString("mes_expiracion"));
				tarjeta.setAñoExp(rs.getString("año_expiracion"));
				tarjeta.setCvv(rs.getString("cvv"));
				tarjeta.setNip(rs.getString("nip"));
				tarjeta.setTarjetaAsignada(rs.getBoolean("tarjeta_asignada"));
				tarjeta.setTarjetaDañada(rs.getBoolean("tarjeta_dañada"));
				tarjeta.setProveedor(rs.getString("proveedor"));
				tarjeta.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
				tarjeta.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
				tarjeta.setFechaRecepcion(rs.getTimestamp("fecha_recepcion"));
				tarjeta.setFechaBaja(rs.getTimestamp("fecha_baja"));
				tarjeta.setSucursalId(rs.getInt("sucursal_id"));

			} else {
				System.out.println("No se hallaron registros");
				tarjeta = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return tarjeta;
	}

	@Override
	public List<InventarioTarjetas> getTarjetasByProvider(String providerName) throws Exception, SQLException {
		List<InventarioTarjetas> listaTarjetas = new ArrayList<InventarioTarjetas>();
		InventarioTarjetas tarjeta = null;
		try {

			conn = con.getConnection();
			ps = conn.prepareStatement(
					"select * from inventario_tarjetas where lower(proveedor) like lower('%" + providerName + "%')");
			if ((rs = ps.executeQuery()).next()) {
				do {
					tarjeta = new InventarioTarjetas();

					tarjeta.setInventarioTarjetaId(rs.getInt("inventario_tarjeta_id"));
					tarjeta.setNumeroTarjeta(rs.getString("numero_tarjeta"));
					tarjeta.setMesExp(rs.getString("mes_expiracion"));
					tarjeta.setAñoExp(rs.getString("año_expiracion"));
					tarjeta.setCvv(rs.getString("cvv"));
					tarjeta.setNip(rs.getString("nip"));
					tarjeta.setTarjetaAsignada(rs.getBoolean("tarjeta_asignada"));
					tarjeta.setTarjetaDañada(rs.getBoolean("tarjeta_dañada"));
					tarjeta.setProveedor(rs.getString("proveedor"));
					tarjeta.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
					tarjeta.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
					tarjeta.setFechaRecepcion(rs.getTimestamp("fecha_recepcion"));
					tarjeta.setFechaBaja(rs.getTimestamp("fecha_baja"));
					tarjeta.setSucursalId(rs.getInt("sucursal_id"));

					listaTarjetas.add(tarjeta);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaTarjetas = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {

			con.closeConnection(conn, ps, rs);
		}
		return listaTarjetas;
	}

	@Override
	public String addTarjeta(InventarioTarjetas tarjeta) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);

		try {
			conn = con.getConnection();
			String sql = "insert into inventario_tarjetas(numero_tarjeta, mes_expiracion, año_expiracion, cvv, nip, tarjeta_asignada, tarjeta_dañada, proveedor, fecha_recepcion, sucursal_id) "
					+ "values(?,?,?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tarjeta.getNumeroTarjeta());
			ps.setString(2, tarjeta.getMesExp());
			ps.setString(3, tarjeta.getAñoExp());
			ps.setString(4, tarjeta.getCvv());
			ps.setString(5, tarjeta.getNip());
			ps.setBoolean(6, false);
			ps.setBoolean(7, false);
			ps.setString(8, tarjeta.getProveedor());
			ps.setTimestamp(9, tp);
			ps.setInt(10, tarjeta.getSucursalId());

			if (ps.executeUpdate() == 1) {
				System.out.println("Tarjeta agregada al inventario");
				response = "OK";
			} else {
				System.out.println("Error al agregar la tarjeta");
				response = "No se pudo agregar la tarjeta";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return response;
	}

	@Override
	public String updateTarjeta(InventarioTarjetas tarjeta, int id) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);

		try {
			conn = con.getConnection();
			String sql = "update inventario_tarjetas set numero_tarjeta = ?, mes_expiracion = ?, año_expiracion = ?, cvv = ?, nip = ?, proveedor = ?, fecha_modificacion = ?, sucursal_id = ? "
					+ "where inventario_tarjeta_id = " + id + ";";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tarjeta.getNumeroTarjeta());
			ps.setString(2, tarjeta.getMesExp());
			ps.setString(3, tarjeta.getAñoExp());
			ps.setString(4, tarjeta.getCvv());
			ps.setString(5, tarjeta.getNip());
			ps.setString(6, tarjeta.getProveedor());
			ps.setTimestamp(7, tp);
			ps.setInt(8, tarjeta.getSucursalId());

			if (ps.executeUpdate() == 1) {
				System.out.println("Tarjeta actualizada");
				response = "OK";
			} else {
				System.out.println("Error al actualizar la tarjeta");
				response = "No se pudo actualizar la tarjeta";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return response;
	}

	@Override
	public String deleteTarjeta(int id) throws Exception, SQLException {
		String response = "";

		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("delete from inventario_tarjetas where inventario_tarjeta_id =" + id + ";");

			if (ps.executeUpdate() == 1) {
				System.out.println("Tarjeta eliminada del inventario");
				response = "OK";
			} else {
				System.out.println("No se encontró información de la tarjeta");
				response = "No se encontró información de la tarjeta";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}

	@Override
	public String removeTarjeta(int id) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		try {
			conn = con.getConnection();
			String sql = "update inventario_tarjetas set tarjeta_dañada = ?, fecha_modificacion = ?, fecha_baja = ? "
					+ "where inventario_tarjeta_id = " + id + ";";
			ps = conn.prepareStatement(sql);

			ps.setBoolean(1, true);
			ps.setTimestamp(2, tp);
			ps.setTimestamp(3, tp);
			if (ps.executeUpdate() == 1) {
				System.out.println("Tarjeta dada de baja del inventario");
				response = "OK";
			} else {
				System.out.println("No se encontró información de la tarjeta");
				response = "No se encontró información de la tarjeta";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}
}
