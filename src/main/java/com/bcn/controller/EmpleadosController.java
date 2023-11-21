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

import com.bcn.model.Empleados;
import com.bcn.service.EmpleadosDaoServiceImplement;

@RestController
@RequestMapping(value = "/empleados")
public class EmpleadosController {
	@Autowired
	EmpleadosDaoServiceImplement empleadosService;

	@GetMapping("/getDatos")
	public ResponseEntity<?> getDatos() throws Exception {
		List<List<?>> datos = new ArrayList<List<?>>();
		try {
			datos = empleadosService.getDatos();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (datos == null) {
			return new ResponseEntity<String>("No hay registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<List<?>>>(datos, HttpStatus.OK);
		}
	}

	@GetMapping("/getEmpleados")
	public ResponseEntity<?> getListaEmpleados() throws Exception {
		List<Empleados> empleados = null;
		try {
			empleados = new ArrayList<Empleados>();
			empleados = empleadosService.getEmpleados();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (empleados == null) {
			return new ResponseEntity<String>("No se hallaron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Empleados>>(empleados, HttpStatus.OK);
		}
	}

	@GetMapping("/getEmpleado/{empleadoId}")
	public ResponseEntity<?> getEmpleado(@PathVariable String empleadoId) throws Exception {
		int id = Integer.parseInt(empleadoId);
		Empleados empleado = null;
		try {
			empleado = empleadosService.getEmpleado(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (empleado == null) {
			return new ResponseEntity<String>("No se hallaron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Empleados>(empleado, HttpStatus.OK);
		}
	}

	@PostMapping("/addEmpleado")
	public ResponseEntity<?> addEmpleado(@RequestBody Empleados empleado) throws Exception {
		String response = "";
		try {
			response = empleadosService.crearEmpleado(empleado);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/updateEmpleado/{empleadoId}")
	public ResponseEntity<?> updateEmpleado(@RequestBody Empleados empleado, @PathVariable String empleadoId)
			throws Exception {
		int id = Integer.parseInt(empleadoId);
		String response = "";
		try {
			response = empleadosService.updateEmpleado(empleado, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/dropEmpleado/{empleadoId}")
	public ResponseEntity<?> dropEmpleado(@RequestBody Empleados empleado, @PathVariable String empleadoId)
			throws Exception {
		int id = Integer.parseInt(empleadoId);
		String response = "";
		try {
			response = empleadosService.dropEmpleado(empleado, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (!response.equals("OK")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteEmpleado/{empleadoId}")
	public ResponseEntity<?> deleteEmpleado(@RequestBody Empleados empleado, @PathVariable String empleadoId)
			throws Exception {
		int id = Integer.parseInt(empleadoId);
		String response = "";
		try {
			response = empleadosService.deleteEmpleado(empleado, id);
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
