package org.lagunak.txoko.api.restfulservers.articulos;

import java.util.List;

import org.lagunak.txoko.persisence.Articulo;
import org.lagunak.txoko.service.interfaces.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/articulos")
public class ArticuloRestController {

	@Autowired
	ArticuloService aS;
	
	@RequestMapping(method = RequestMethod.GET,produces =   MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<List<Articulo>> getAll() {
 		List<Articulo> ampliaciones = aS.getAll();
 		ResponseEntity<List<Articulo>> response = null;
 
 		if (ampliaciones == null || ampliaciones.isEmpty()) {
 			response = new ResponseEntity<List<Articulo>>(HttpStatus.NO_CONTENT);
 		} else {
 			response = new ResponseEntity<List<Articulo>>(ampliaciones, HttpStatus.OK);
 		}
 
 		return response;
  	}
}
