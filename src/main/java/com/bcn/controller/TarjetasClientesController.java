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

import com.bcn.model.TarjetasClientes;
import com.bcn.service.DaoTarjetaCliente.TarjetasClientesDaoServiceImplement;

@RestController
@RequestMapping(value = "/tarjetas/customer")
public class TarjetasClientesController {
	@Autowired
	TarjetasClientesDaoServiceImplement tarjetasService;

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
		List<TarjetasClientes> tarjetas = null;
		try {
			tarjetas = new ArrayList<TarjetasClientes>();
			tarjetas = tarjetasService.getTarjetas();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (tarjetas == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<TarjetasClientes>>(tarjetas, HttpStatus.OK);
		}
	}

	@GetMapping("/getTarjeta/{tarjetaId}")
	public ResponseEntity<?> getTarjeta(@PathVariable String tarjetaId) throws Exception {
		TarjetasClientes tarjeta = null;
		int id = Integer.parseInt(tarjetaId);

		try {
			tarjeta = tarjetasService.getTarjeta(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (tarjeta == null) {
			return new ResponseEntity<String>("No se hallaron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<TarjetasClientes>(tarjeta, HttpStatus.OK);
		}
	}

	@PostMapping("/assignTarjeta")
	public ResponseEntity<?> assignTarjeta(@RequestBody TarjetasClientes tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.assignTarjeta(tarjeta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (response.equals("La tarjeta ha sido activada y asignada al cliente"))
			return new ResponseEntity<String>(response, HttpStatus.OK);
		if (response.equals("La tarjeta ha sido asignada al cliente")) {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/activateTarjeta")
	public ResponseEntity<?> activateTarjeta(@RequestBody TarjetasClientes tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.activateTarjeta(tarjeta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		if (!response.equals("La tarjeta ha sido activada y asignada al cliente")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/lockTarjeta")
	public ResponseEntity<?> lockTarjeta(@RequestBody TarjetasClientes tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.lockTarjeta(tarjeta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("Tarjeta bloqueada permanentemente")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/tempLockTarjeta")
	public ResponseEntity<?> tempLockTarjeta(@RequestBody TarjetasClientes tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.tempLockTarjeta(tarjeta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("Tarjeta bloqueada temporalmente")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/tempUnlockTarjeta")
	public ResponseEntity<?> tempUnlockTarjeta(@RequestBody TarjetasClientes tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.tempUnlockTarjeta(tarjeta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("Tarjeta desbloqueada")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/cancelTarjeta")
	public ResponseEntity<?> cancelTarjeta(@RequestBody TarjetasClientes tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.cancelTarjeta(tarjeta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("Tarjeta cancelada")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/migrateTarjeta")
	public ResponseEntity<?> migrateTarjeta(@RequestBody TarjetasClientes tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.migrateTarjeta(tarjeta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("Tarjeta migrada")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>("La tarjeta " + tarjeta.getNumeroTarjeta() + " ha sido migrada con éxito",
					HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteTarjeta")
	public ResponseEntity<?> deleteTarjeta(@RequestBody TarjetasClientes tarjeta) throws Exception {
		String response = "";
		try {
			response = tarjetasService.deleteTarjeta(tarjeta);
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
