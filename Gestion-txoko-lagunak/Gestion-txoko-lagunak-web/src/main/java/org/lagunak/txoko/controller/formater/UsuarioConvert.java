package org.lagunak.txoko.controller.formater;

import org.lagunak.txoko.persisence.Usuario;
import org.lagunak.txoko.usuario.UsuarioServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class UsuarioConvert implements Converter<String,Usuario> {

	@Autowired
	UsuarioServiceRemote uS;
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaConvert.class);
	
	@Override
	public Usuario convert(String arg0) {
		LOGGER.info(arg0.toString());
		return uS.getById(Long.parseLong((String) arg0));
	}

}
