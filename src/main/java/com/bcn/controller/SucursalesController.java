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

import com.bcn.model.Sucursales;
import com.bcn.service.DaoSucursal.SucursalDaoServiceImplement;

@RestController
@RequestMapping(value = "/sucursales")
public class SucursalesController {
	@Autowired
	SucursalDaoServiceImplement sucursalesService;

	@GetMapping(value = "/getDatos")
	public ResponseEntity<?> getDatos() throws Exception {
		List<List<?>> datos = new ArrayList<List<?>>();
		try {
			datos = sucursalesService.getDatos();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (datos == null) {
			return new ResponseEntity<String>("No hay registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<List<?>>>(datos, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getSucursales")
	public ResponseEntity<?> getSucursales() throws Exception {
		List<Sucursales> sucursales = new ArrayList<Sucursales>();
		try {
			sucursales = sucursalesService.getSucursales();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (sucursales == null) {
			return new ResponseEntity<String>("No hay registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<?>>(sucursales, HttpStatus.OK);
		}
	}

	@GetMapping(value = "getSucursal/{sucursalId}")
	public ResponseEntity<?> getSucursal(@PathVariable String sucursalId) throws Exception {
		int id = Integer.parseInt(sucursalId);
		Sucursales sucursal = null;
		try {
			sucursal = sucursalesService.getSucursal(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (sucursal == null) {
			return new ResponseEntity<String>("No hay registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Sucursales>(sucursal, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/addSucursal")
	public ResponseEntity<?> addSucursal(@RequestBody Sucursales sucursal) throws Exception {
		String response = "";
		try {
			response = sucursalesService.crearSucursal(sucursal);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>("No se pudo agregar la sucursal", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
	}

	@PutMapping(value = "/updateSucursal/{sucursalId}")
	public ResponseEntity<?> updateSucursal(@RequestBody Sucursales sucursal, @PathVariable String sucursalId)
			throws Exception {
		String response = "";
		int id = Integer.parseInt(sucursalId);
		try {
			response = sucursalesService.updateSucursal(sucursal, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>("No se encontró información de la sucursal", HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
	}

	@PutMapping(value = "/dropSucursal/{sucursalId}")
	public ResponseEntity<?> dropSucursal(@PathVariable String sucursalId) throws Exception {
		int id = Integer.parseInt(sucursalId);
		String response = "";
		try {
			response = sucursalesService.dropSucursal(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>("No se encontró información de la sucursal", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/deleteSucursal/{sucursalId}")
	public ResponseEntity<?> deleteSucursal(@PathVariable String sucursalId) throws Exception {
		String response = "";
		int id = Integer.parseInt(sucursalId);
		try {
			response = sucursalesService.deleteSucursal(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>("No se encontró información de la sucursal", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
	}
}
