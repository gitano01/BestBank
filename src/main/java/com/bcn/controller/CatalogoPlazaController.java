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

import com.bcn.model.CatalogoPlaza;
import com.bcn.service.DaoCatalogoPlaza.CatalogoPlazaDaoService;

@RestController
@RequestMapping(value = "/catalogo")
public class CatalogoPlazaController {
	@Autowired
	CatalogoPlazaDaoService catalogoService;

	@GetMapping("/plazas/getPlazas")
	public ResponseEntity<?> getCatalogo() throws Exception {
		List<CatalogoPlaza> listaCatalogo = new ArrayList<CatalogoPlaza>();
		try {
			listaCatalogo = catalogoService.listCatalogo();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (listaCatalogo == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<CatalogoPlaza>>(listaCatalogo, HttpStatus.OK);
		}
	}

	@GetMapping("/plazas/getPlaza/{plazaId}")
	public ResponseEntity<?> getCatalogo(@PathVariable String plazaId) throws Exception {
		int id = Integer.parseInt(plazaId);
		CatalogoPlaza catalogo = null;
		try {
			catalogo = catalogoService.getCatalogo(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (catalogo == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<CatalogoPlaza>(catalogo, HttpStatus.OK);
		}
	}

	@GetMapping("/plazas/getPlazasByCodigoPlaza")
	public ResponseEntity<?> getCatalogoByCodigoPlaza(@RequestParam String codigo_plaza) throws Exception {
		List<CatalogoPlaza> listaCatalogo = new ArrayList<CatalogoPlaza>();
		try {
			listaCatalogo = catalogoService.listCatalogo(codigo_plaza);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		if (listaCatalogo == null) {
			return new ResponseEntity<String>("No se encontraron registros", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<CatalogoPlaza>>(listaCatalogo, HttpStatus.OK);
		}
	}
}