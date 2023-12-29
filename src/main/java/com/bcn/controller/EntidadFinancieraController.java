package com.bcn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcn.model.EntidadFinanciera;
import com.bcn.service.DaoEntidadFinanciera.EntidadFinancieraDaoService;

@RestController
@RequestMapping(value = "/entidades")
public class EntidadFinancieraController {
	@Autowired
	EntidadFinancieraDaoService entidadService;

	@GetMapping("/getEntidades")
	public ResponseEntity<?> getEntidadesFinancieras() throws Exception {
		List<EntidadFinanciera> listaEntidadesFinancieras = new ArrayList<EntidadFinanciera>();
		try {
			listaEntidadesFinancieras = entidadService.getEntidadesFinancieras();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (listaEntidadesFinancieras == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<EntidadFinanciera>>(listaEntidadesFinancieras, HttpStatus.OK);
		}
	}

	@GetMapping("/getEntidad/{entidadId}")
	public ResponseEntity<?> getEntidadFinanciera(@PathVariable String entidadId) throws Exception {
		int id = Integer.parseInt(entidadId);
		EntidadFinanciera entidad = null;
		try {
			entidad = entidadService.getEntidadFinanciera(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (entidad == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<EntidadFinanciera>(entidad, HttpStatus.OK);
		}
	}

	@GetMapping("/getEntidad")
	public ResponseEntity<?> getEntidadFinancieraByAbm(@RequestParam String numero_abm) throws Exception {
		EntidadFinanciera entidad = null;
		try {
			entidad = entidadService.getEntidadFinancieraByAbm(numero_abm);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (entidad == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<EntidadFinanciera>(entidad, HttpStatus.OK);
		}
	}
}
