package com.bcn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcn.model.Cuentas;
import com.bcn.service.CuentasDaoServiceImplement;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {
	@Autowired
	CuentasDaoServiceImplement cuentasService;

	@GetMapping("/getCuentas")
	public ResponseEntity<?> getCuentas() throws Exception {
		List<Cuentas> cuentas = null;
		try {
			cuentas = new ArrayList<Cuentas>();
			cuentas = cuentasService.getCuentas();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (cuentas == null) {
			return new ResponseEntity<String>("No se hallaron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Cuentas>>(cuentas, HttpStatus.OK);
		}
	}

	@GetMapping("/getDatos")
	public ResponseEntity<?> getDatos() throws Exception {
		List<List<?>> datos = new ArrayList<List<?>>();
		try {
			datos = cuentasService.getDatos();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (datos == null) {
			return new ResponseEntity<String>("No existen registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<List<?>>>(datos, HttpStatus.OK);
		}
	}

	@GetMapping("/getCuenta/{cuentaId}")
	public ResponseEntity<?> getCuenta(@PathVariable String cuentaId) throws Exception {
		int id = Integer.parseInt(cuentaId);
		Cuentas cuenta = null;
		try {
			cuenta = cuentasService.getCuenta(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (cuenta == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Cuentas>(cuenta, HttpStatus.OK);
		}
	}

	@PostMapping("/addCuenta")
	public ResponseEntity<?> addCuenta(@RequestBody Cuentas cuenta) throws Exception {
		String response = "";
		try {
			response = cuentasService.crearCuenta(cuenta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/updateCuenta/{cuentaId}")
	public ResponseEntity<?> updateCuenta(@RequestBody Cuentas cuenta, @PathVariable String cuentaId) throws Exception {
		String response = "";
		int id = Integer.parseInt(cuentaId);
		try {
			response = cuentasService.updateCuenta(cuenta, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/dropCuenta/{cuentaId}")
	public ResponseEntity<?> dropCuenta(@PathVariable String cuentaId) throws Exception {
		String response = "";
		int id = Integer.parseInt(cuentaId);
		try {
			response = cuentasService.dropCuenta(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@DeleteMapping("deleteCuenta/{cuentaId}")
	public ResponseEntity<?> deleteCuenta(@PathVariable String cuentaId) throws Exception {
		String response = "";
		int id = Integer.parseInt(cuentaId);
		try {
			response = cuentasService.deleteCuenta(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

}
