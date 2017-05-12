package org.lagunak.txoko.api.restfulservers.categorias;

import java.util.List;

import org.lagunak.txoko.persisence.Categoria;
import org.lagunak.txoko.service.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaRestController {

	@Autowired
	CategoriaService caS;
	
	@RequestMapping(method = RequestMethod.GET,produces =   MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<List<Categoria>> getAll() {
 		List<Categoria> ampliaciones = caS.getAll();
 		ResponseEntity<List<Categoria>> response = null;
 
 		if (ampliaciones == null || ampliaciones.isEmpty()) {
 			response = new ResponseEntity<List<Categoria>>(HttpStatus.NO_CONTENT);
 		} else {
 			response = new ResponseEntity<List<Categoria>>(ampliaciones, HttpStatus.OK);
 		}
 
 		return response;
  	}
}
 