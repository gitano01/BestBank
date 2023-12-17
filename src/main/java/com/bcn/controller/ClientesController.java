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

import com.bcn.model.Clientes;
import com.bcn.service.DaoCliente.ClienteDaoServiceImplement;

@RestController
@RequestMapping(value = "/clientes")
public class ClientesController {
	@Autowired
	ClienteDaoServiceImplement clientesService;

	@GetMapping("/getDatos")
	public ResponseEntity<?> getDatos() throws Exception {
		List<List<?>> datos = new ArrayList<List<?>>();
		try {
			datos = clientesService.getDatos();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (datos == null) {
			return new ResponseEntity<String>("No hay registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<List<?>>>(datos, HttpStatus.OK);
		}
	}

	@GetMapping("/getClientes")
	public ResponseEntity<?> getListaClientes() throws Exception {
		List<Clientes> cliente = null;
		try {
			cliente = new ArrayList<Clientes>();
			cliente = clientesService.getClientes();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (cliente == null) {
			return new ResponseEntity<String>("No se hallaron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Clientes>>(cliente, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getCliente/{clienteId}")
	public ResponseEntity<?> getCliente(@PathVariable String clienteId) throws Exception {
		int id = Integer.parseInt(clienteId);

		Clientes cliente = null;
		try {
			cliente = clientesService.getCliente(id);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (cliente == null) {
			return new ResponseEntity<String>("No se hallaron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Clientes>(cliente, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/addCliente")
	public ResponseEntity<?> addCliente(@RequestBody Clientes cliente) throws Exception {
		String response = "";
		try {
			response = clientesService.crearCliente(cliente);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping(value = "/updateCliente/{clienteId}")
	public ResponseEntity<?> updateCliente(@RequestBody Clientes cliente, @PathVariable String clienteId)
			throws Exception {
		int id = Integer.parseInt(clienteId);
		String response = "";
		try {
			response = clientesService.updateCliente(cliente, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping(value = "/dropCliente/{clienteId}")
	public ResponseEntity<?> dropCliente(@RequestBody Clientes cliente, @PathVariable String clienteId)
			throws Exception {
		int id = Integer.parseInt(clienteId);
		String response = "";
		try {
			response = clientesService.dropCliente(cliente, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/deleteCliente/{clienteId}")
	public ResponseEntity<?> deleteCliente(@PathVariable String clienteId) throws Exception {
		int id = Integer.parseInt(clienteId);
		String response = "";
		try {
			response = clientesService.deleteCliente(id);
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
