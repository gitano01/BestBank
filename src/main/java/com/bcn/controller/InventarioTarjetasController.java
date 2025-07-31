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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcn.model.InventarioTarjetas;
import com.bcn.service.DaoInventarioTarjeta.InventarioTarjetasDaoServiceImplement;

@RestController
@RequestMapping(value = "/tarjetas/inventory")
public class InventarioTarjetasController {
	@Autowired
	InventarioTarjetasDaoServiceImplement tarjetasService;

	@GetMapping("/getDatos")
	public ResponseEntity<?> getDatos() throws Exception {
		List<List<?>> datos = new ArrayList<List<?>>();
		try {
			datos = tarjetasService.getDatos();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (datos == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<List<?>>>(datos, HttpStatus.OK);
		}
	}

	@GetMapping("/getTarjetas")
	public ResponseEntity<?> getTarjetas() throws Exception {
		List<InventarioTarjetas> tarjetas = null;
		try {
			tarjetas = new ArrayList<InventarioTarjetas>();
			tarjetas = tarjetasService.getTarjetas();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (tarjetas == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<InventarioTarjetas>>(tarjetas, HttpStatus.OK);
		}
	}

	@GetMapping("/getTarjeta/{tarjetaId}")
	public ResponseEntity<?> getTarjeta(@PathVariable String tarjetaId) throws Exception {
		InventarioTarjetas tarjeta = null;
		int id = Integer.parseInt(tarjetaId);
		try {
			tarjeta = tarjetasService.getTarjeta(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (tarjeta == null) {
			return new ResponseEntity<String>("NO se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<InventarioTarjetas>(tarjeta, HttpStatus.OK);
		}
	}

	@GetMapping("/getTarjetasByProvider/")
	public ResponseEntity<?> getTarjetasByProvider(@RequestParam(required = true) String proveedor) throws Exception {
		List<InventarioTarjetas> listaTarjetas = null;

		try {
			listaTarjetas = new ArrayList<InventarioTarjetas>();
			listaTarjetas = tarjetasService.getTarjetasByProvider(proveedor);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (listaTarjetas == null) {
			return new ResponseEntity<String>("No se hallaron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<InventarioTarjetas>>(listaTarjetas, HttpStatus.OK);
		}
	}

	@PostMapping("/addTarjeta")
	public ResponseEntity<?> addTarjeta(@RequestBody InventarioTarjetas tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.addTarjeta(tarjeta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/updateTarjeta/{tarjetaId}")
	public ResponseEntity<?> updateTarjeta(@RequestBody InventarioTarjetas tarjeta, @PathVariable String tarjetaId)
			throws Exception {
		String response = "";
		int id = Integer.parseInt(tarjetaId);
		try {
			response = tarjetasService.updateTarjeta(tarjeta, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/removeTarjeta/{tarjetaId}")
	public ResponseEntity<?> removeTarjeta(@PathVariable String tarjetaId) throws Exception {
		String response = "";
		int id = Integer.parseInt(tarjetaId);
		try {
			response = tarjetasService.removeTarjeta(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteTarjeta/{tarjetaId}")
	public ResponseEntity<?> deleteTarjeta(@PathVariable String tarjetaId) throws Exception {
		String response = "";
		int id = Integer.parseInt(tarjetaId);
		try {
			response = tarjetasService.deleteTarjeta(id);
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
