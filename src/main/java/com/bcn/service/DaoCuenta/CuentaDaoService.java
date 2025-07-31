package com.bcn.service.DaoCuenta;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Cuenta;

public interface CuentaDaoService {

	public List<Cuenta> getCuentas() throws Exception, SQLException;

	public List<List<?>> getDatos() throws Exception, SQLException;

	public Cuenta getCuenta(int id) throws Exception, SQLException;

	public String crearCuenta(Cuenta cuenta) throws Exception, SQLException;

	public String updateCuenta(Cuenta cuenta, int id) throws Exception, SQLException;

	public String dropCuenta(int id) throws Exception, SQLException;

	public String deleteCuenta(int id) throws Exception, SQLException;
}
