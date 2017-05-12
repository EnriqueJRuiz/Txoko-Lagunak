package org.lagunak.txoko.api.restfulclient.articulos;

import java.util.List;

import org.lagunak.txoko.persisence.Articulo;
import org.springframework.web.client.RestTemplate;

public class ArticuloRestClientImp implements ArticuloRestClient{

	@Override
	public List<Articulo> getAll() {
		List<Articulo> articulos = null;
		RestTemplate template = new RestTemplate();
		articulos = template.getForObject(ArticuloRestClient.URL,List.class);
		return articulos;
	}

}
