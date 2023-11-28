package com.bcn.service;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Empleados;

public interface EmpleadosDaoService {
	public List<Empleados> getEmpleados() throws Exception, SQLException;

	public List<List<?>> getDatos() throws Exception, SQLException;

	public Empleados getEmpleado(int id) throws Exception, SQLException;

	public String crearEmpleado(Empleados empleado) throws Exception, SQLException;

	public String updateEmpleado(Empleados empleado, int id) throws Exception, SQLException;

	public String deleteEmpleado(Empleados empleado, int id) throws Exception, SQLException;

	public String dropEmpleado(Empleados empleado, int id) throws Exception, SQLException;

}
