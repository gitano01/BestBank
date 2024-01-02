package com.bcn.service.DaoEntidadFinanciera;

import java.sql.SQLException;
import java.util.List;

import com.bcn.model.EntidadFinanciera;

public interface EntidadFinancieraDaoService {
	public List<EntidadFinanciera> getEntidadesFinancieras() throws Exception, SQLException;

	public EntidadFinanciera getEntidadFinanciera(int id) throws Exception, SQLException;

	public EntidadFinanciera getEntidadFinancieraByAbm(String numero_abm) throws Exception, SQLException;
}
