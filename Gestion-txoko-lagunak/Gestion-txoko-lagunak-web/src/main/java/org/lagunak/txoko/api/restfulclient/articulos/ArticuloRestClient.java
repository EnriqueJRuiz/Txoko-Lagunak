package org.lagunak.txoko.api.restfulclient.articulos;

import java.util.List;

import org.lagunak.txoko.persisence.Articulo;


public interface ArticuloRestClient {
	final static String URL = "http://localhost:8080/txokolagunak/api/articulos"; 
	
	public  List<Articulo> getAll();
}
