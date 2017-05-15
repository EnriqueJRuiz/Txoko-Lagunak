package org.lagunak.txoko.api.restfulservers.categorias;

import java.util.List;

import javax.validation.Valid;

import org.lagunak.txoko.persisence.Categoria;
import org.lagunak.txoko.service.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaRestController {

	@Autowired
	CategoriaService caS;
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value = "/{codigo}", 
			method = RequestMethod.GET,
			produces =   MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Categoria> getById(@PathVariable("codigo") int id) {
		Categoria categoria = caS.getById(id);
 		ResponseEntity<Categoria> response = null;
 
 		if (categoria == null) {// 404
 			response = new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
 		} else {// 200
 			response = new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
 		}
 
 		return response;
 	}
	@RequestMapping(value="/{codigo}", 
			method = RequestMethod.DELETE, 
			produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
 	public ResponseEntity<Categoria> deleteCategoria(@PathVariable("codigo") int id){
		Categoria categoria = caS.getById(id);
 		ResponseEntity<Categoria> response = null;
 		if (categoria == null) {
 			response = new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
 		} else {
 			caS.delete(id);
 			response = new ResponseEntity<Categoria>(categoria, HttpStatus.NO_CONTENT);
 		}
		return response;
 	}
	@RequestMapping(value = "/{codigo}", 
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> update(@PathVariable("codigo") int id,@RequestBody Categoria categoria){
		Categoria cate = caS.getById(id);
		ResponseEntity<Categoria> response = null;
		if(cate == null){
			response= new ResponseEntity<Categoria> (HttpStatus.NOT_FOUND);
		}else{
			cate = caS.update(categoria);
			response = new ResponseEntity<Categoria>(cate, HttpStatus.ACCEPTED);
		}
		return response;
	}
	
	@RequestMapping( method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Categoria categoria, UriComponentsBuilder ucBuilder){
		ResponseEntity<Void> response=null;
		try{
			Categoria aux = caS.create(categoria);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/ampliaciones/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
			response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}catch(Error e){
			response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return response;
	}
	
	
	
	
}
 