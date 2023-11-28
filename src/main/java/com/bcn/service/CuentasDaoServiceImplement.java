package com.bcn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.Cuentas;
import com.bcn.utils.DbConnect;

@Service
public class CuentasDaoServiceImplement implements CuentasDaoService {
	@Autowired
	private DbConnect con;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<Cuentas> getCuentas() throws Exception, SQLException {
		List<Cuentas> listaCuentas = new ArrayList<>();
		Cuentas cuenta = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from cuenta;");

			if ((rs = ps.executeQuery()).next()) {
				do {

					cuenta = new Cuentas();
					cuenta.setCuentaId(rs.getInt("cuenta_id"));
					cuenta.setFechaApertura(rs.getTimestamp("fecha_apertura"));
					cuenta.setFechaCierre(rs.getTimestamp("fecha_cierre"));
					cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
					cuenta.setClabe(rs.getString("clabe"));
					cuenta.setTipoCuenta(rs.getString("tipo_cuenta"));
					cuenta.setSaldoInicial(rs.getDouble("saldo_inicial"));
					cuenta.setSaldoMaximo(rs.getDouble("saldo_maximo"));
					cuenta.setBalance(rs.getDouble("balance"));
					cuenta.setActivo(rs.getBoolean("activo"));
					cuenta.setEstatusCuenta(rs.getString("estatus_cuenta"));
					cuenta.setClienteId(rs.getInt("cliente_id"));

					listaCuentas.add(cuenta);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaCuentas = null;
			}
			cuenta = new Cuentas();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return listaCuentas;
	}

	@Override
	public List<List<?>> getDatos() throws Exception, SQLException {
		List<List<?>> datos = new ArrayList<List<?>>();
		List<Cuentas> listaCuentas = getCuentas();

		try {
			datos.add(listaCuentas);
		} catch (Exception e) {
			datos = null;
		}
		return datos;
	}

	@Override
	public Cuentas getCuenta(int id) throws Exception, SQLException {
		Cuentas cuenta = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from cuenta where cuenta_id = " + id + ";");

			if ((rs = ps.executeQuery()).next()) {
				cuenta = new Cuentas();
				cuenta.setCuentaId(rs.getInt("cuenta_id"));
				cuenta.setFechaApertura(rs.getTimestamp("fecha_apertura"));
				cuenta.setFechaCierre(rs.getTimestamp("fecha_cierre"));
				cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
				cuenta.setClabe(rs.getString("clabe"));
				cuenta.setTipoCuenta(rs.getString("tipo_cuenta"));
				cuenta.setSaldoInicial(rs.getDouble("saldo_inicial"));
				cuenta.setSaldoMaximo(rs.getDouble("saldo_maximo"));
				cuenta.setBalance(rs.getDouble("balance"));
				cuenta.setActivo(rs.getBoolean("activo"));
				cuenta.setEstatusCuenta(rs.getString("estatus_cuenta"));
				cuenta.setClienteId(rs.getInt("cliente_id"));

			} else {
				System.out.println("No se encontró información de la cuenta");
				cuenta = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return cuenta;
	}

	@Override
	public String crearCuenta(Cuentas cuenta) throws Exception, SQLException {
		String response = "";
		Long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
//		int min = 1;
//		int max = 10;
//		double numCta1 =  100000 + Math.random() * 900000;
//		double numCta2 = 10000 + Math.random() * 90000;
//		Random random = new Random();
//		int numCtaA = (int)(numCta1);
//		int numCtaB = (int)(numCta2);
//		int verifyDigit = random.nextInt(max + min) + min;
		try {

//			String numCta = String.valueOf(numCtaA) + String.valueOf(numCtaB);
//			String clabe = "014180" + numCta + verifyDigit;
//			System.out.println(numCta);
//			System.out.println(clabe);
			conn = con.getConnection();
			String sql = "insert into cuenta(fecha_apertura, numero_cuenta, clabe, saldo_anterior, saldo_inicial, saldo_maximo, balance, tipo_cuenta, estatus_cuenta, activo, cliente_id)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?);";

			ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, tp);
			ps.setString(2, cuenta.getNumeroCuenta());
			ps.setString(3, cuenta.getClabe());
			ps.setDouble(4, 0.0);
			ps.setDouble(5, cuenta.getSaldoInicial());
			ps.setDouble(6, cuenta.getSaldoMaximo());
			ps.setDouble(7, cuenta.getBalance());
			ps.setString(8, cuenta.getTipoCuenta());
			ps.setString(9, cuenta.getEstatusCuenta());
			ps.setBoolean(10, cuenta.isActivo());
			ps.setInt(11, cuenta.getClienteId());

			if (ps.executeUpdate() == 1) {
				System.out.println("Cuenta creada");
				response = "OK";
			} else {
				System.out.println("No se pudo crear la cuenta");
				response = "No se pudo crear la cuenta";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return response;
	}

	@Override
	public String updateCuenta(Cuentas cuenta, int id) throws Exception, SQLException {
		String response = "";

		try {
			conn = con.getConnection();
			String sql = "update cuenta set activo = ?, estatus_cuenta = ?, saldo_maximo = ? where cuenta_id = " + id
					+ ";";
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, cuenta.isActivo());
			ps.setString(2, cuenta.getEstatusCuenta());
			ps.setDouble(3, cuenta.getSaldoMaximo());

			if (ps.executeUpdate() == 1) {
				System.out.println("Cuenta actualizada");
				response = "OK";
			} else {
				System.out.println("No se encontró información de la cuenta");
				response = "No se encontró información de la cuenta";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}

	@Override
	public String dropCuenta(int id) throws Exception, SQLException {
		String response = "";
		long datetime = System.currentTimeMillis();
		Timestamp tp = new Timestamp(datetime);
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement(
					"update cuenta set activo = false, estatus_cuenta = 'cerrado', fecha_cierre = ? where cuenta_id = "
							+ id + ";");

			ps.setTimestamp(1, tp);

			if (ps.executeUpdate() == 1) {
				System.out.println("cuenta dada de baja");
				response = "OK";
			} else {
				System.out.println("No se encontró información de la cuenta");
				response = "No se encontró información de la cuenta";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}

		return response;
	}

	@Override
	public String deleteCuenta(int id) throws Exception, SQLException {
		String response = "";
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("delete from cuenta where cuenta_id = " + id + ";");

			if (ps.executeUpdate() == 1) {
				System.out.println("Cuenta eliminada");
				response = "OK";
			} else {
				System.out.println("No se encontró información de la cuenta");
				response = "No se encontró información de la cuenta";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps);
		}
		return response;
	}
}
