package com.bcn.service.DaoEntidadFinanciera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.EntidadFinanciera;
import com.bcn.utils.DbConnect;

@Service
public class EntidadFinancieraDaoServiceImplement implements EntidadFinancieraDaoService {

	@Autowired
	private DbConnect con;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<EntidadFinanciera> getEntidadesFinancieras() throws Exception, SQLException {
		List<EntidadFinanciera> listaEntidadesFinancieras = new ArrayList<EntidadFinanciera>();
		EntidadFinanciera entidadFinanciera = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from entidades_financieras;");

			if ((rs = ps.executeQuery()).next()) {
				do {
					entidadFinanciera = new EntidadFinanciera();
					entidadFinanciera.setEntidadId(rs.getInt("entidad_id"));
					entidadFinanciera.setNombreAbreviado(rs.getString("nombre_abreviado"));
					entidadFinanciera.setNumeroAbm(rs.getString("numero_abm"));
					entidadFinanciera.setNombreInstitucion(rs.getString("nombre_institucion"));
					listaEntidadesFinancieras.add(entidadFinanciera);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaEntidadesFinancieras = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return listaEntidadesFinancieras;
	}

	@Override
	public EntidadFinanciera getEntidadFinanciera(int id) throws Exception, SQLException {
		EntidadFinanciera entidadFinanciera = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from entidades_financieras where entidad_id = ?;");
			ps.setInt(1, id);

			if ((rs = ps.executeQuery()).next()) {
				entidadFinanciera = new EntidadFinanciera();
				entidadFinanciera.setEntidadId(rs.getInt("entidad_id"));
				entidadFinanciera.setNombreAbreviado(rs.getString("nombre_abreviado"));
				entidadFinanciera.setNumeroAbm(rs.getString("numero_abm"));
				entidadFinanciera.setNombreInstitucion(rs.getString("nombre_institucion"));
			} else {
				System.out.println("No se encontraron registros");
				entidadFinanciera = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return entidadFinanciera;
	}

	@Override
	public EntidadFinanciera getEntidadFinancieraByAbm(String numero_abm) throws Exception, SQLException {
		EntidadFinanciera entidadFinanciera = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from entidades_financieras where numero_abm = ?;");
			ps.setString(1, numero_abm);

			if ((rs = ps.executeQuery()).next()) {
				entidadFinanciera = new EntidadFinanciera();
				entidadFinanciera.setEntidadId(rs.getInt("entidad_id"));
				entidadFinanciera.setNombreAbreviado(rs.getString("nombre_abreviado"));
				entidadFinanciera.setNumeroAbm(rs.getString("numero_abm"));
				entidadFinanciera.setNombreInstitucion(rs.getString("nombre_institucion"));
			} else {
				System.out.println("No se encontraron registros");
				entidadFinanciera = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return entidadFinanciera;
	}

	public String getNumeroAbmByNombreInstitucion(DbConnect con, String nombre_institucion)
			throws Exception, SQLException {
		String numero_abm = "";
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement(
					"select ef.numero_abm  from entidades_financieras ef where lower(ef.nombre_abreviado) like lower(?);");
			ps.setString(1, nombre_institucion);

			if ((rs = ps.executeQuery()).next()) {
				numero_abm = rs.getString("numero_abm");
			} else {
				System.out.println("No se encontraron registros");
				numero_abm = "";
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return numero_abm;
	}

}
