package com.bcn.service.DaoCatalogoPlaza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcn.model.CatalogoPlaza;
import com.bcn.utils.DbConnect;

@Service
public class CatalogoPlazaDaoServiceImplement implements CatalogoPlazaDaoService {
	@Autowired
	private DbConnect con;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<CatalogoPlaza> listCatalogo() throws Exception, SQLException {
		List<CatalogoPlaza> listaCatalogo = new ArrayList<CatalogoPlaza>();
		CatalogoPlaza catalogo = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from catalogo_plazas;");

			if ((rs = ps.executeQuery()).next()) {
				do {
					catalogo = new CatalogoPlaza();
					catalogo.setPlazaId(rs.getInt("plaza_id"));
					catalogo.setCodigoPlaza(rs.getString("codigo_plaza"));
					catalogo.setPoblacion(rs.getString("poblacion"));
					listaCatalogo.add(catalogo);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaCatalogo = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return listaCatalogo;
	}

	@Override
	public List<CatalogoPlaza> listCatalogo(String codigo_plaza) throws Exception, SQLException {
		List<CatalogoPlaza> listaCatalogo = new ArrayList<CatalogoPlaza>();
		CatalogoPlaza catalogo = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from catalogo_plazas where codigo_plaza = ?;");
			ps.setString(1, codigo_plaza);
			System.out.println("EJECUTANDO SQL: " + ps.toString());
			if ((rs = ps.executeQuery()).next()) {
				do {
					catalogo = new CatalogoPlaza();
					catalogo.setPlazaId(rs.getInt("plaza_id"));
					catalogo.setCodigoPlaza(rs.getString("codigo_plaza"));
					catalogo.setPoblacion(rs.getString("poblacion"));
					listaCatalogo.add(catalogo);
				} while (rs.next());
			} else {
				System.out.println("No existen registros");
				listaCatalogo = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}
		return listaCatalogo;
	}

	@Override
	public CatalogoPlaza getCatalogo(int id) throws Exception, SQLException {
		CatalogoPlaza catalogo = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from catalogo_plazas where plaza_id = ?;");
			ps.setInt(1, id);

			if ((rs = ps.executeQuery()).next()) {
				catalogo = new CatalogoPlaza();
				catalogo.setPlazaId(rs.getInt("plaza_id"));
				catalogo.setCodigoPlaza(rs.getString("codigo_plaza"));
				catalogo.setPoblacion(rs.getString("poblacion"));
			} else {
				System.out.println("No se encontraron registros");
				catalogo = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return catalogo;
	}

	@Override
	public CatalogoPlaza getCatalogoByCodigoPlaza(String codigo_plaza) throws Exception, SQLException {
		CatalogoPlaza catalogo = null;
		try {
			conn = con.getConnection();
			ps = conn.prepareStatement("select * from catalogo_plazas where codigo_plaza = ?;");
			ps.setString(1, codigo_plaza);

			if ((rs = ps.executeQuery()).next()) {
				catalogo = new CatalogoPlaza();
				catalogo.setPlazaId(rs.getInt("plaza_id"));
				catalogo.setCodigoPlaza(rs.getString("codigo_plaza"));
				catalogo.setPoblacion(rs.getString("poblacion"));
			} else {
				System.out.println("No se encontraron registros");
				catalogo = null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			con.closeConnection(conn, ps, rs);
		}

		return catalogo;
	}
}
