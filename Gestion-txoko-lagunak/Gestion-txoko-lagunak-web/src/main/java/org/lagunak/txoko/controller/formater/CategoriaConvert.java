package org.lagunak.txoko.controller.formater;

import org.lagunak.txoko.categoria.CategoriaServiceRemote;
import org.lagunak.txoko.persisence.Categoria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


public class CategoriaConvert implements Converter<String, Categoria> {
	
	@Autowired
	CategoriaServiceRemote caS;
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaConvert.class);
	
	@Override
	public Categoria convert(String arg0) {
		LOGGER.info(arg0.toString());
		return caS.getById(Long.parseLong((String) arg0));
	}

}
