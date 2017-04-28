package org.lagunak.txoko.controller.formater;

import org.lagunak.txoko.comentario.ComentarioServiceRemote;
import org.lagunak.txoko.persisence.Comentario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ComentarioConvert implements Converter<String, Comentario> {

	@Autowired
	ComentarioServiceRemote coS;
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaConvert.class);
	
	@Override
	public Comentario convert(String arg0) {
		LOGGER.info(arg0.toString());
		return coS.getById(Long.parseLong((String) arg0));
	}

}
