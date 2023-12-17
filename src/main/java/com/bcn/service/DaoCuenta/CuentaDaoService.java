package com.bcn.service.DaoCuenta;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Cuentas;

public interface CuentaDaoService {

	public List<Cuentas> getCuentas() throws Exception, SQLException;

	public List<List<?>> getDatos() throws Exception, SQLException;

	public Cuentas getCuenta(int id) throws Exception, SQLException;

	public String crearCuenta(Cuentas cuenta) throws Exception, SQLException;

	public String updateCuenta(Cuentas cuenta, int id) throws Exception, SQLException;

	public String dropCuenta(int id) throws Exception, SQLException;

	public String deleteCuenta(int id) throws Exception, SQLException;
}
