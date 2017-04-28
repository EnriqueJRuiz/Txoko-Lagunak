package org.lagunak.txoko.service;

import java.util.List;

import javax.annotation.Resource;

import org.lagunak.txoko.categoria.CategoriaServiceRemote;
import org.lagunak.txoko.persisence.Categoria;
import org.lagunak.txoko.service.interfaces.CategoriaService;

public class CategoriaServiceImp implements CategoriaService{
	
	@Resource(name="categoriaServiceRemote")
	private CategoriaServiceRemote categoriaServiceRemote;
	
	@Override
	public void setCategoriaServiceRemote(CategoriaServiceRemote categoriaService) {
		this.categoriaServiceRemote = categoriaService;
		
	}

	@Override
	public List<Categoria> getAll() {
		return categoriaServiceRemote.getAll();
	}

	@Override
	public Categoria getById(long codigo) {
		return categoriaServiceRemote.getById(codigo);
	}

	@Override
	public Categoria update(Categoria categoria) {
		return categoriaServiceRemote.update(categoria);
	}

	@Override
	public Categoria create(Categoria categoria) {
		return categoriaServiceRemote.create(categoria);
	}

	@Override
	public void delete(long codigo) {
		categoriaServiceRemote.delete(codigo);
	}

}
