package org.lagunak.txoko.controller.formater;

import org.lagunak.txoko.articulo.ArticuloServiceRemote;
import org.lagunak.txoko.persisence.Articulo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ArticuloConvert implements Converter<String, Articulo>{
	
	@Autowired
	ArticuloServiceRemote aS;
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaConvert.class);
	
	@Override
	public Articulo convert(String arg0) {
		LOGGER.info(arg0.toString());
		return aS.getById(Long.parseLong((String) arg0));
	}

}
