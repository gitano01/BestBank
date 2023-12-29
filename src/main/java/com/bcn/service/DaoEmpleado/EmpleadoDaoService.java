package com.bcn.service.DaoEmpleado;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.Empleado;

public interface EmpleadoDaoService {
	public List<Empleado> getEmpleados() throws Exception, SQLException;

	public List<List<?>> getDatos() throws Exception, SQLException;

	public Empleado getEmpleado(int id) throws Exception, SQLException;

	public String crearEmpleado(Empleado empleado) throws Exception, SQLException;

	public String updateEmpleado(Empleado empleado, int id) throws Exception, SQLException;

	public String deleteEmpleado(Empleado empleado, int id) throws Exception, SQLException;

	public String dropEmpleado(Empleado empleado, int id) throws Exception, SQLException;

}
