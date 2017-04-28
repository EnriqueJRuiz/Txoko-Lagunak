package org.lagunak.txoko.service;

import java.util.List;

import javax.annotation.Resource;

import org.lagunak.txoko.articulo.ArticuloServiceRemote;
import org.lagunak.txoko.persisence.Articulo;
import org.lagunak.txoko.service.interfaces.ArticuloService;

public class ArticuloServiceImp implements ArticuloService {

	@Resource(name="articuloServiceRemote")
	private ArticuloServiceRemote articuloServiceRemote;
	
	@Override
	public void setArticuloServiceRemote(ArticuloServiceRemote articuloService) {
		this.articuloServiceRemote = articuloService;
		
	}

	@Override
	public List<Articulo> getAll() {
		return articuloServiceRemote.getAll();
	}

	@Override
	public Articulo getById(long codigo) {
		return articuloServiceRemote.getById(codigo);
	}

	@Override
	public Articulo update(Articulo articulo) {
		return articuloServiceRemote.update(articulo);
	}

	@Override
	public Articulo create(Articulo articulo) {
		return articuloServiceRemote.create(articulo);
	}

	@Override
	public void delete(long codigo) {
		articuloServiceRemote.delete(codigo);
		
	}

}
