package com.bcn.service.DaoTarjetaCliente;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Cuenta;
import com.bcn.model.InventarioTarjetas;
import com.bcn.model.TarjetasClientes;
import com.bcn.service.DaoCuenta.CuentaDaoServiceImplement;
import com.bcn.utils.DbConnect;

@Service
public class TarjetaClienteDaoServiceImplement implements TarjetaClienteDaoService {
	@Autowired // Dependency injection
	private DbConnect con;
	private CuentaDaoServiceImplement cuentasService;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<List<?>> getDatos() throws Exception, SQLException {
		List<List<?>> datos = new ArrayList<List<?>>();
		List<TarjetasClientes> listaTarjetas = getTarjetas();

		try {
			datos.add(listaTarjetas);
		} catch (Exception e) {
			datos = null;
		}
		return datos;
	}

	@Override
	public List<TarjetasClientes> getTarjetas() throws Exception, SQLException {
		List<TarjetasClientes> listaTarjetas = new ArrayList<TarjetasClientes>();
		TarjetasClientes tarjeta = null;
		try {

			conn = con.getConnection();
			ps = conn.prepareStatement("select * from tarjetas_clientes");

			if ((rs = ps.executeQuery()).next()) {
				do {
					tarjeta = new TarjetasClientes();

					tarjeta.setTarjetaClienteId(rs.getInt("tarjeta_cliente_id"));
					tarjeta.setNumeroTarjeta(rs.getString("numero_tarjeta"));
					tarjeta.setMesExp(rs.getString("mes_expiracion"));
					tarjeta.setAñoExp(rs.getString("año_expiracion"));
					tarjeta.setCvv(rs.getString("cvv"));
					tarjeta.setNip(rs.getString("nip"));
					tarjeta.setTipoTarjeta(rs.getString("tipo_tarjeta"));
					tarjeta.setEstatusTarjeta(rs.getString("estatus_tarjeta"));
					tarjeta.setBalance(rs.getDouble("balance"));
					tarjeta.setTarjetaActiva(rs.getBoolean("tarjeta_activa"));
					tarjeta.setTarjetaMigrada(rs.getBoolean("tarjeta_migrada"));
					tarjeta.setTarjetaCancelada(rs.getBoolean("tarjeta_cancelada"));
					tarjeta.setBloqueoTemporal(rs.getBoolean("bloqueo_temporal"));
					tarjeta.setBloqueoPermanente(rs.getBoolean("bloqueo_permanente"));
					tarjeta.setFechaAlta(rs.getTimestamp("fecha_alta"));
					tarjeta.setFechaActivacion(rs.getTimestamp("fecha_activacion"));
					tarjeta.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
					tarjeta.setFechaCancelacion(rs.getTimestamp("fecha_cancelacion"));
					tarjeta.setFechaMigrado(rs.getTimestamp("fecha_migrado"));
					tarjeta.setFechaBloqueo(rs.getTimestamp("fecha_bloqueo"));
					tarjeta.setCuentaId(rs.getObject("cuenta_id"));
					tarjeta.setClienteId(rs.getObject("cliente_id"));

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
	public TarjetasClientes getTarjeta(int id) throws Exception, SQLException {
		TarjetasClientes tarjeta = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from tarjetas_clientes where tarjeta_cliente_id = " + id + ";");

			if ((rs = ps.executeQuery()).next()) {
				tarjeta = new TarjetasClientes();

				tarjeta.setTarjetaClienteId(rs.getInt("tarjeta_cliente_id"));
				tarjeta.setNumeroTarjeta(rs.getString("numero_tarjeta"));
				tarjeta.setMesExp(rs.getString("mes_expiracion"));
				tarjeta.setAñoExp(rs.getString("año_expiracion"));
				tarjeta.setCvv(rs.getString("cvv"));
				tarjeta.setNip(rs.getString("nip"));
				tarjeta.setTipoTarjeta(rs.getString("tipo_tarjeta"));
				tarjeta.setEstatusTarjeta(rs.getString("estatus_tarjeta"));
				tarjeta.setBalance(rs.getDouble("balance"));
				tarjeta.setTarjetaActiva(rs.getBoolean("tarjeta_activa"));
				tarjeta.setTarjetaMigrada(rs.getBoolean("tarjeta_migrada"));
				tarjeta.setTarjetaCancelada(rs.getBoolean("tarjeta_cancelada"));
				tarjeta.setBloqueoTemporal(rs.getBoolean("bloqueo_temporal"));
				tarjeta.setBloqueoPermanente(rs.getBoolean("bloqueo_permanente"));
				tarjeta.setFechaAlta(rs.getTimestamp("fecha_alta"));
				tarjeta.setFechaActivacion(rs.getTimestamp("fecha_activacion"));
				tarjeta.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
				tarjeta.setFechaCancelacion(rs.getTimestamp("fecha_cancelacion"));
				tarjeta.setFechaMigrado(rs.getTimestamp("fecha_migrado"));
				tarjeta.setFechaBloqueo(rs.getTimestamp("fecha_bloqueo"));
				tarjeta.setCuentaId(rs.getObject("cuenta_id"));
				tarjeta.setClienteId(rs.getObject("cliente_id"));

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
	public String assignTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException {
		String response = "";
		Cuenta cuenta_cliente = null;
		cuentasService = new CuentaDaoServiceImplement();
		InventarioTarjetas tarjeta_inventario = null;
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		try {
			tarjeta_inventario = getTarjetaInventario(tarjeta);
			cuenta_cliente = cuentasService.getCuentaCliente(con, tarjeta);
			if (cuenta_cliente != null) {
				if (tarjeta_inventario != null) {
					conn = con.getConnection();
					String sql = "select * from addTarjeta(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, tarjeta_inventario.getNumeroTarjeta());
					ps.setString(2, tarjeta_inventario.getMesExp());
					ps.setString(3, tarjeta_inventario.getAñoExp());
					ps.setString(4, tarjeta_inventario.getCvv());
					if (tarjeta_inventario.getNip() == null)
						ps.setString(5, tarjeta.getNip());
					else
						ps.setString(5, tarjeta_inventario.getNip());
					ps.setString(6, tarjeta.getTipoTarjeta());
					if (cuenta_cliente.getBalance() != 0.0)
						ps.setDouble(7, cuenta_cliente.getBalance());
					else
						ps.setDouble(7, 0.0);
					ps.setTimestamp(8, tp);
					ps.setString(9, "inactiva");
					ps.setBoolean(10, false);
					ps.setTimestamp(11, null);
					ps.setObject(12, tarjeta.getCuentaId());
					ps.setObject(13, tarjeta.getClienteId());
					ps.setInt(14, tarjeta_inventario.getInventarioTarjetaId());
					System.out.println("SQL A CORRER: " + ps.toString());
					rs = ps.executeQuery();
					if (rs.next()) {
						Array array = rs.getArray(1);
						System.out.println(array.toString());
						response = array.toString();
						if (response.equals("OK")
								&& assignTarjetaInventario(tarjeta_inventario.getNumeroTarjeta()).equals("OK")) {
							System.out.println(response);
							if (tarjeta.isActivarTarjeta()) {
								response = activateTarjeta(tarjeta);
							} else {
								System.out.println("La tarjeta ha sido asignada al cliente");
								response = "La tarjeta ha sido asignada al cliente";
							}
						} else {
							System.out.println("Error al asignar la tarjeta");
							response = "No se pudo asignar la tarjeta";
						}
					} else {
						System.out.println("Error al asignar la tarjeta. Notificar a soporte");
						response = "Ocurrio un error al asignar la tarjeta. Notifique a soporte";
					}
				} else {
					System.out.println(
							"No se encontró tarjeta disponible en el inventario para asignar o ya ha sido asignada");
					response = "No hay tarjeta disponible para asignar o ya ha sido asignada";
				}
			} else {
				System.out.println("Error al asignar la tarjeta, La cuenta no corresponde al usuario");
				response = "Error al asignar la tarjeta, la cuenta no corresponde al usuario";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return response;
	}

	@Override
	public String lockTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		TarjetasClientes tarjeta_cliente = null;
		try {
			tarjeta_cliente = getTarjetaCliente(tarjeta.getNumeroTarjeta());
			if (tarjeta_cliente != null) {
				if (!tarjeta_cliente.isTarjetaMigrada()) {
					if (!tarjeta_cliente.isTarjetaCancelada()) {
						if (!tarjeta_cliente.isBloqueoPermanente()) {
							conn = con.getConnection();
							String sql = "update tarjetas_clientes set bloqueo_permanente = true, bloqueo_temporal = false, tarjeta_activa = false, tarjeta_cancelada = true, fecha_bloqueo = ?, fecha_cancelacion = ?, fecha_modificacion = ?, estatus_tarjeta = 'bloqueado permanentemente' where numero_tarjeta = '"
									+ tarjeta_cliente.getNumeroTarjeta() + "';";
							ps = conn.prepareStatement(sql);
							ps.setTimestamp(1, tp);
							ps.setTimestamp(2, tp);
							ps.setTimestamp(3, tp);

							if (ps.executeUpdate() == 1) {
								System.out.println("Tarjeta bloqueada permanentemente");
								response = "Tarjeta bloqueada permanentemente";
							} else {
								System.out.println("No se encontró información de la tarjeta");
								response = "No se encontró información de la tarjeta";
							}
						} else {
							System.out
									.println("ERROR: La tarjeta ya se encuentra en estatus bloqueada permanentemente");
							response = "ERROR: La tarjeta ya se encuentra en estatus bloqueada permanentemente";
						}
					} else {
						System.out.println(
								"ERROR AL BLOQUEAR PERMANENTEMENTE TARJETA: La tarjeta tiene estatus cancelada");
						response = "ERROR AL BLOQUEAR PERMANENTEMENTE TARJETA: La tarjeta tiene estatus cancelada";
					}
				} else {
					System.out.println("ERROR AL BLOQUEAR PERMANENTEMENTE TARJETA: La tarjeta ha sido migrada");
					response = "ERROR AL BLOQUEAR PERMANENTEMENTE TARJETA: La tarjeta ha sido migrada";
				}
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
	public String tempLockTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		TarjetasClientes tarjeta_cliente = null;
		try {
			tarjeta_cliente = getTarjetaCliente(tarjeta.getNumeroTarjeta());
			if (tarjeta_cliente != null) {
				if (!tarjeta_cliente.isTarjetaMigrada()) {
					if (!tarjeta_cliente.isTarjetaCancelada()) {
						if (!tarjeta_cliente.isBloqueoPermanente()) {
							if (!tarjeta_cliente.isBloqueoTemporal()) {
								conn = con.getConnection();
								String sql = "update tarjetas_clientes set bloqueo_temporal = true, fecha_bloqueo = ?, fecha_modificacion = ?, estatus_tarjeta = 'bloqueado temporalmente' where numero_tarjeta ='"
										+ tarjeta_cliente.getNumeroTarjeta() + "';";
								ps = conn.prepareStatement(sql);
								ps.setTimestamp(1, tp);
								ps.setTimestamp(2, tp);

								if (ps.executeUpdate() == 1) {
									System.out.println("Tarjeta bloqueada temporalmente");
									response = "Tarjeta bloqueada temporalmente";
								} else {
									System.out.println("No se encontró información de la tarjeta");
									response = "No se encontró información de la tarjeta";
								}
							} else {
								System.out.println("La tarjeta ya se encuentra bloqueada");
								response = "La tarjeta ya se encuentra bloqueada";
							}
						} else {
							System.out.println(
									"ERROR AL BLOQUEAR TEMPORALMENTE TARJETA: La tarjeta tiene estatus bloqueada permanentemente");
							response = "ERROR AL BLOQUEAR TEMPORALMENTE TARJETA: La tarjeta tiene estatus bloqueada permanentemente";
						}
					} else {
						System.out
								.println("ERROR AL BLOQUEAR TEMPORALMENTE TARJETA: La tarjeta tiene estatus cancelada");
						response = "ERROR AL BLOQUEAR TEMPORALMENTE TARJETA: La tarjeta tiene estatus cancelada";
					}
				} else {
					System.out.println("ERROR AL BLOQUEAR TEMPORALMENTE TARJETA: La tarjeta ha sido migrada");
					response = "ERROR AL BLOQUEAR TEMPORALMENTE TARJETA: La tarjeta ha sido migrada";
				}
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
	public String tempUnlockTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		TarjetasClientes tarjeta_cliente = null;
		try {
			tarjeta_cliente = getTarjetaCliente(tarjeta.getNumeroTarjeta());
			if (tarjeta_cliente != null) {
				if (!tarjeta_cliente.isTarjetaMigrada()) {
					if (!tarjeta_cliente.isTarjetaCancelada()) {
						if (!tarjeta_cliente.isBloqueoPermanente()) {
							if (tarjeta_cliente.isBloqueoTemporal()) {
								conn = con.getConnection();
								String sql = "update tarjetas_clientes set bloqueo_temporal = false, fecha_bloqueo = null, fecha_modificacion = ?, estatus_tarjeta = 'activa' where numero_tarjeta ='"
										+ tarjeta_cliente.getNumeroTarjeta() + "';";
								ps = conn.prepareStatement(sql);
								ps.setTimestamp(1, tp);

								if (ps.executeUpdate() == 1) {
									System.out.println("Tarjeta desbloqueada");
									response = "Tarjeta desbloqueada";
								} else {
									System.out.println("No se encontró información de la tarjeta");
									response = "No se encontró información de la tarjeta";
								}
							} else {
								System.out.println("La tarjeta ya se encuentra activa");
								response = "La tarjeta ya se encuentra activa";
							}
						} else {
							System.out.println(
									"ERROR AL DESBLOQUEAR TARJETA: La tarjeta tiene estatus bloqueada permanentemente");
							response = "ERROR AL DESBLOQUEAR TARJETA: La tarjeta tiene estatus bloqueada permanentemente";
						}
					} else {
						System.out.println("ERROR AL DESBLOQUEAR TARJETA: La tarjeta tiene estatus cancelada");
						response = "ERROR AL DESBLOQUEAR TARJETA: La tarjeta tiene estatus cancelada";
					}
				} else {
					System.out.println("ERROR AL DESBLOQUEAR TARJETA: La tarjeta ha sido migrada");
					response = "ERROR AL DESBLOQUEAR TARJETA: La tarjeta ha sido migrada";
				}
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
	public String migrateTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException {
		String response = "";
		TarjetasClientes tarjeta_cliente = null;
		Cuenta cuenta_cliente = null;
		cuentasService = new CuentaDaoServiceImplement();
		InventarioTarjetas tarjeta_inventario = null;
		int tarjeta_cliente_id = getTarjetaClienteIdByStatus(tarjeta.getNumeroTarjeta());
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		try {
			if (tarjeta_cliente_id != 0) {
				tarjeta_cliente = getTarjeta(tarjeta_cliente_id);
				tarjeta_inventario = getTarjetaInventario();
				cuenta_cliente = cuentasService.getCuentaCliente(con, tarjeta);
				if (tarjeta_cliente != null) {
					if (cuenta_cliente != null) {
						if (tarjeta_inventario != null) {
							conn = con.getConnection();
							String sql = "select * from addTarjeta(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							ps = conn.prepareStatement(sql);
							ps.setString(1, tarjeta_inventario.getNumeroTarjeta());
							ps.setString(2, tarjeta_inventario.getMesExp());
							ps.setString(3, tarjeta_inventario.getAñoExp());
							ps.setString(4, tarjeta_inventario.getCvv());
							if (tarjeta_inventario.getNip() == null)
								ps.setString(5, tarjeta.getNip());
							else
								ps.setString(5, tarjeta_inventario.getNip());
							ps.setString(6, tarjeta_cliente.getTipoTarjeta());
							ps.setDouble(7, cuenta_cliente.getBalance());
							ps.setTimestamp(8, tp);
							ps.setString(9, tarjeta_cliente.getEstatusTarjeta());
							ps.setBoolean(10, true);
							ps.setTimestamp(11, tp);
							ps.setObject(12, tarjeta_cliente.getCuentaId());
							ps.setObject(13, tarjeta_cliente.getClienteId());
							ps.setInt(14, tarjeta_inventario.getInventarioTarjetaId());
							System.out.println("SQL A CORRER: " + ps.toString());
							rs = ps.executeQuery();
							if (rs.next()) {
								Array array = rs.getArray(1);
								System.out.println(array.toString());
								response = array.toString();
								if (response.equals("OK")
										&& assignTarjetaInventario(tarjeta_inventario.getNumeroTarjeta())
												.equals("OK")) {
									System.out.println(response);
									response = unassignTarjeta(tarjeta.getNumeroTarjeta());
								} else {
									System.out.println("Error al reasignar tarjeta");
									response = "No se pudo reasignar tarjeta";
								}
							}
						} else {
							System.out.println(
									"No se encontró tarjeta disponible en el inventario para reasignar o ya ha sido asignada");
							response = "No hay tarjeta disponible para reasignar o ya ha sido asignada";
						}
					} else {
						System.out.println("Error al asignar la tarjeta, La cuenta no corresponde al usuario");
						response = "Error al asignar la tarjeta, la cuenta no corresponde al usuario";
					}
				} else {
					System.out.println("No se encontró información de la tarjeta");
					response = "No se encontró información de la tarjeta";
				}
			} else {
				System.out.println("ERROR AL MIGRAR TARJETA: estatus de tarjeta no válida para realizar este proceso");
				response = "ERROR AL MIGRAR TARJETA: estatus de tarjeta no válida para realizar este proceso";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return response;
	}

	@Override
	public String cancelTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		TarjetasClientes tarjeta_cliente = null;
		try {
			tarjeta_cliente = getTarjetaCliente(tarjeta.getNumeroTarjeta());
			if (tarjeta_cliente != null) {
				if (!tarjeta_cliente.isTarjetaCancelada()) {
					conn = con.getConnection();
					String sql = "update tarjetas_clientes set tarjeta_cancelada = true, tarjeta_activa = false, fecha_cancelacion = ?, fecha_modificacion = ?, estatus_tarjeta = 'cancelada' where numero_tarjeta ='"
							+ tarjeta_cliente.getNumeroTarjeta() + "';";
					ps = conn.prepareStatement(sql);
					ps.setTimestamp(1, tp);
					ps.setTimestamp(2, tp);

					if (ps.executeUpdate() == 1) {
						System.out.println("Tarjeta cancelada");
						response = "Tarjeta cancelada";
					} else {
						System.out.println("No se encontró información de la tarjeta");
						response = "No se encontró información de la tarjeta";
					}
				} else {
					System.out.println("La tarjeta ya ha sido cancelada");
					response = "La tarjeta ya ha sido cancelada";
				}
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
	public String deleteTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("delete from tarjetas_clientes where numero_tarjeta = '"
					+ tarjeta.getNumeroTarjeta() + "' and tarjeta_activa = false;");

			if (ps.executeUpdate() == 1) {
				System.out.println("Tarjeta eliminada");
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

	// Common Methods
	public InventarioTarjetas getTarjetaInventario(TarjetasClientes tarjeta) throws Exception, SQLException {
		InventarioTarjetas tarjeta_inventario = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement(
					"select * from inventario_tarjetas where numero_tarjeta = ? and tarjeta_dañada = false and tarjeta_asignada = false;");

			ps.setString(1, tarjeta.getNumeroTarjeta());

			if ((rs = (ps.executeQuery())).next()) {
				System.out.println("Tarjeta ha sido encontrada en el inventario");
				tarjeta_inventario = new InventarioTarjetas();
				tarjeta_inventario.setInventarioTarjetaId(rs.getInt("inventario_tarjeta_id"));
				tarjeta_inventario.setNumeroTarjeta(rs.getString("numero_tarjeta"));
				tarjeta_inventario.setMesExp(rs.getString("mes_expiracion"));
				tarjeta_inventario.setAñoExp(rs.getString("año_expiracion"));
				tarjeta_inventario.setCvv(rs.getString("cvv"));
				tarjeta_inventario.setNip(rs.getString("nip"));
				tarjeta_inventario.setTarjetaAsignada(rs.getBoolean("tarjeta_asignada"));
				tarjeta_inventario.setTarjetaDañada(rs.getBoolean("tarjeta_dañada"));
				tarjeta_inventario.setProveedor(rs.getString("proveedor"));
				tarjeta_inventario.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
				tarjeta_inventario.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
				tarjeta_inventario.setFechaRecepcion(rs.getTimestamp("fecha_recepcion"));
				tarjeta_inventario.setFechaBaja(rs.getTimestamp("fecha_baja"));
			} else {
				System.out.println("No se encontró la tarjeta en el inventario");
				tarjeta_inventario = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return tarjeta_inventario;
	}

	public InventarioTarjetas getTarjetaInventario() throws Exception, SQLException {
		InventarioTarjetas tarjeta_inventario = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement(
					"select * from inventario_tarjetas where tarjeta_dañada = false and tarjeta_asignada = false limit 1;");

			if ((rs = (ps.executeQuery())).next()) {
				System.out.println("Tarjeta ha sido encontrada en el inventario");
				tarjeta_inventario = new InventarioTarjetas();
				tarjeta_inventario.setInventarioTarjetaId(rs.getInt("inventario_tarjeta_id"));
				tarjeta_inventario.setNumeroTarjeta(rs.getString("numero_tarjeta"));
				tarjeta_inventario.setMesExp(rs.getString("mes_expiracion"));
				tarjeta_inventario.setAñoExp(rs.getString("año_expiracion"));
				tarjeta_inventario.setCvv(rs.getString("cvv"));
				tarjeta_inventario.setNip(rs.getString("nip"));
				tarjeta_inventario.setTarjetaAsignada(rs.getBoolean("tarjeta_asignada"));
				tarjeta_inventario.setTarjetaDañada(rs.getBoolean("tarjeta_dañada"));
				tarjeta_inventario.setProveedor(rs.getString("proveedor"));
				tarjeta_inventario.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
				tarjeta_inventario.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
				tarjeta_inventario.setFechaRecepcion(rs.getTimestamp("fecha_recepcion"));
				tarjeta_inventario.setFechaBaja(rs.getTimestamp("fecha_baja"));
			} else {
				System.out.println("No se encontró la tarjeta en el inventario");
				tarjeta_inventario = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return tarjeta_inventario;
	}

	public TarjetasClientes getTarjetaCliente(String numero_tarjeta) throws Exception, SQLException {
		TarjetasClientes tarjeta_cliente = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement(
					"select tarjeta_cliente_id, numero_tarjeta, nip, tarjeta_activa, balance, bloqueo_permanente, bloqueo_temporal, tarjeta_cancelada, tarjeta_migrada from tarjetas_clientes where numero_tarjeta = ?");

			ps.setString(1, numero_tarjeta);

			if ((rs = (ps.executeQuery())).next()) {
				System.out.println("Tarjeta del cliente ha sido encontrada");
				tarjeta_cliente = new TarjetasClientes();
				tarjeta_cliente.setTarjetaClienteId(rs.getInt("tarjeta_cliente_id"));
				tarjeta_cliente.setNumeroTarjeta(rs.getString("numero_tarjeta"));
				tarjeta_cliente.setNip(rs.getString("nip"));
				tarjeta_cliente.setBalance(rs.getDouble("balance"));
				tarjeta_cliente.setTarjetaActiva(rs.getBoolean("tarjeta_activa"));
				tarjeta_cliente.setBloqueoPermanente(rs.getBoolean("bloqueo_permanente"));
				tarjeta_cliente.setBloqueoTemporal(rs.getBoolean("bloqueo_temporal"));
				tarjeta_cliente.setTarjetaCancelada(rs.getBoolean("tarjeta_cancelada"));
				tarjeta_cliente.setTarjetaMigrada(rs.getBoolean("tarjeta_migrada"));
			} else {
				System.out.println("No se encontró la tarjeta del cliente");
				tarjeta_cliente = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return tarjeta_cliente;
	}

	public int getTarjetaClienteIdByStatus(String numero_tarjeta) throws Exception, SQLException {
		int tarjeta_cliente_id;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select tarjeta_cliente_id from tarjetas_clientes where numero_tarjeta = '"
					+ numero_tarjeta + "' and tarjeta_migrada = false and tarjeta_activa = true;");
			System.out.println("EJECUTANDO SQL PARA OBTENER ESTATUS VALIDO PARA MIGRAR TARJETA: " + ps.toString());
			if ((rs = (ps.executeQuery())).next()) {
				System.out.println("Tarjeta del cliente ha sido encontrada");
				tarjeta_cliente_id = rs.getInt("tarjeta_cliente_id");
			} else {
				System.out.println("Estatus de tarjeta inválida para migrarla");
				tarjeta_cliente_id = 0;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return tarjeta_cliente_id;
	}

	public String activateTarjeta(TarjetasClientes tarjeta) throws Exception, SQLException {
		String response = "";
		long datetime = System.currentTimeMillis();
		TarjetasClientes tarjeta_cliente = null;
		Timestamp tp = new Timestamp(datetime);
		try {
			tarjeta_cliente = getTarjetaCliente(tarjeta.getNumeroTarjeta());
			if (tarjeta_cliente != null) {
				conn = con.getConnection();
				// System.out.println(tarjeta_cliente.isTarjetaActiva());
				if (!tarjeta_cliente.isTarjetaMigrada()) {
					if (!tarjeta_cliente.isTarjetaActiva() && !tarjeta_cliente.isTarjetaCancelada()) {
						if (tarjeta_cliente.getNip() != null) {
							String sql = "update tarjetas_clientes set tarjeta_activa = true, fecha_activacion = ?, estatus_tarjeta = 'activa', fecha_modificacion = ? where numero_tarjeta ='"
									+ tarjeta.getNumeroTarjeta() + "';";
							ps = conn.prepareStatement(sql);
							ps.setTimestamp(1, tp);
							ps.setTimestamp(2, tp);
						} else {
							String sql = "update tarjetas_clientes set tarjeta_activa = true, nip = ?, fecha_activacion = ?, estatus_tarjeta = 'activa', fecha_modificacion = ? where numero_tarjeta ='"
									+ tarjeta.getNumeroTarjeta() + "';";
							ps = conn.prepareStatement(sql);
							ps.setString(1, tarjeta.getNip());
							ps.setTimestamp(2, tp);
							ps.setTimestamp(3, tp);
						}

						if (ps.executeUpdate() == 1) {
							System.out.println("La tarjeta ha sido activada y asignada al cliente");
							response = "La tarjeta ha sido activada y asignada al cliente";
						} else {
							System.out.println("No se encontró información de la tarjeta");
							response = "No se encontró información de la tarjeta";
						}
					} else {
						System.out.println(
								"ERROR AL ACTIVAR LA TARJETA: El estatus de la tarjeta es activa o ha sido cancelada");
						response = "ERROR AL ACTIVAR LA TARJETA: El estatus de la tarjeta es activa o ha sido cancelada";
					}
				} else {
					System.out.println("ERROR AL ACTIVAR LA TARJETA: El estatus de la tarjeta es migrada");
					response = "ERROR AL ACTIVAR LA TARJETA: El estatus de la tarjeta es migrada";
				}
			} else {
				System.out.println("No se encontró información de la tarjeta cliente");
				response = "No se encontró información de la tarjeta cliente";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return response;
	}

	public String assignTarjetaInventario(String numeroTarjeta) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement(
					"update inventario_tarjetas set tarjeta_asignada = true, fecha_asignacion = ?, fecha_modificacion = ? where numero_tarjeta = ?");
			ps.setTimestamp(1, tp);
			ps.setTimestamp(2, tp);
			ps.setString(3, numeroTarjeta);

			if (ps.executeUpdate() == 1) {
				System.out.println("Tarjeta del inventario ha sido asignada");
				response = "OK";
			} else {
				System.out.println("Error, la tarjeta del inventario no pudo ser asignada");
				response = "Error";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
			;
		}

		return response;
	}

	public String unassignTarjeta(String numero_tarjeta) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement(
					"update tarjetas_clientes set tarjeta_migrada = true, tarjeta_activa = false, estatus_tarjeta = 'migrada', fecha_modificacion = ?, fecha_migrado = ? where numero_tarjeta = '"
							+ numero_tarjeta + "';");
			ps.setTimestamp(1, tp);
			ps.setTimestamp(2, tp);

			if (ps.executeUpdate() == 1) {
				System.out.println("La tarjeta " + numero_tarjeta + "ha sido migrado con éxito");
				response = "Tarjeta migrada";
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
